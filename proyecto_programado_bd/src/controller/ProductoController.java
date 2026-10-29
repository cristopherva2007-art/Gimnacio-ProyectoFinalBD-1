package controller;

import model.Producto;
import model.ProductoDAO;
import view.GestionProductosView;

import javax.swing.JOptionPane;
import java.util.List;

public class ProductoController {

    private GestionProductosView vista;
    private ProductoDAO productoDAO;
    private int idSeleccionado = -1;

    public ProductoController(GestionProductosView vista) {
        this.vista       = vista;
        this.productoDAO = new ProductoDAO();
        initListeners();
        cargarTabla();
    }

    private void initListeners() {
        vista.getBtnAgregar().addActionListener(e -> agregar());
        vista.getBtnActualizar().addActionListener(e -> actualizar());
        vista.getBtnEliminar().addActionListener(e -> eliminar());
        vista.getBtnLimpiar().addActionListener(e -> limpiar());

        vista.getTabla().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) seleccionarFila();
        });
    }

    private void cargarTabla() {
        vista.getModeloTabla().setRowCount(0);
        List<Producto> lista = productoDAO.listar();
        for (Producto producto : lista) {
            vista.getModeloTabla().addRow(new Object[]{
                producto.getIdProducto(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getCategoria(),
                producto.getRutaImagen()
            });
        }
    }

    private void agregar() {
        String nombre  = vista.getNombre();
        String descripcion = vista.getDescripcion();
        String precioStr= vista.getPrecio();
        String stockStr = vista.getStock();
        String categoria = vista.getCategoria();
        String rutaImagen = vista.getRutaImagen();

        if (nombre.isEmpty() || precioStr.isEmpty() || stockStr.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Complete los campos obligatorios.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double precio = Double.parseDouble(precioStr);
            int stock = Integer.parseInt(stockStr);
            
            if (precio <= 0) {
                JOptionPane.showMessageDialog(
                    vista,
                    "El precio debe ser mayor que cero.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            if (stock < 0) {
                JOptionPane.showMessageDialog(
                    vista,
                    "El stock no puede ser negativo.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            Producto producto = new Producto();
            producto.setNombre(nombre);
            producto.setDescripcion(descripcion);
            producto.setPrecio(precio);
            producto.setStock(stock);
            producto.setCategoria(categoria);
            producto.setRutaImagen(rutaImagen);

            if (productoDAO.insertar(producto)) {
                JOptionPane.showMessageDialog(vista, "Producto agregado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al agregar producto.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Precio y stock deben ser números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizar() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un producto de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nombre= vista.getNombre();
        String descripcion= vista.getDescripcion();
        String precioStr= vista.getPrecio();
        String stockStr= vista.getStock();
        String categoria = vista.getCategoria();
        String rutaImagen= vista.getRutaImagen();
        
        

        if (nombre.isEmpty() || precioStr.isEmpty() || stockStr.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Complete los campos obligatorios.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double precio = Double.parseDouble(precioStr);
            int stock   = Integer.parseInt(stockStr);

            Producto producto = new Producto();
            producto.setIdProducto(idSeleccionado);
            producto.setNombre(nombre);
            producto.setDescripcion(descripcion);
            producto.setPrecio(precio);
            producto.setStock(stock);
            producto.setCategoria(categoria);
            producto.setRutaImagen(rutaImagen);

            if (productoDAO.actualizar(producto)) {
                JOptionPane.showMessageDialog(vista, "Producto actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al actualizar producto.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Precio y stock deben ser números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminar() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un producto de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmar = JOptionPane.showConfirmDialog(vista, "¿Está seguro de eliminar este producto?", "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirmar == JOptionPane.YES_OPTION) {
            if (productoDAO.eliminar(idSeleccionado)) {
                JOptionPane.showMessageDialog(vista, "Producto eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al eliminar producto.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void seleccionarFila() {
        int fila = vista.getTabla().getSelectedRow();
        if (fila == -1) return;

        idSeleccionado = (int)    vista.getModeloTabla().getValueAt(fila, 0);
        String nombre  = (String) vista.getModeloTabla().getValueAt(fila, 1);
        String descripcion = (String) vista.getModeloTabla().getValueAt(fila, 2);
        String precio  = String.valueOf(vista.getModeloTabla().getValueAt(fila, 3));
        String stock  = String.valueOf(vista.getModeloTabla().getValueAt(fila, 4));
        String categoria = (String) vista.getModeloTabla().getValueAt(fila, 5);
        String rutaImagen = (String) vista.getModeloTabla().getValueAt(fila, 6);

        vista.llenarFormulario(nombre, descripcion, precio, stock, categoria, rutaImagen);
    }

    private void limpiar() {
        vista.limpiarFormulario();
        idSeleccionado = -1;
    }
}