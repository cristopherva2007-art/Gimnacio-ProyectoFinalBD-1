package controller;

import model.Cliente;
import model.ClienteDAO;
import model.Compra;
import model.CompraDAO;
import model.Membresia;
import model.MembresiaDAO;
import model.Producto;
import model.ProductoDAO;
import model.Usuario;
import view.ClienteView;
import view.ProductosClienteView;
import view.ComprasClienteView;
import view.MembresiaClienteView;
import view.LoginView;

import javax.swing.JOptionPane;
import java.time.LocalDateTime;
import java.util.List;

public class ClienteController {

    private ClienteView vista;
    private Usuario usuario;
    private ClienteDAO clienteDAO;
    private ProductoDAO productoDAO;
    private CompraDAO compraDAO;
    private MembresiaDAO membresiaDAO;
    private Cliente cliente;

    public ClienteController(ClienteView vista, Usuario usuario) {
        this.vista  = vista;
        this.usuario = usuario;
        this.clienteDAO= new ClienteDAO();
        this.productoDAO= new ProductoDAO();
        this.compraDAO = new CompraDAO();
        this.membresiaDAO= new MembresiaDAO();
        this.cliente = clienteDAO.buscarPorUsuario(usuario.getIdUsuario());
        initListeners();
    }

    private void initListeners() {
        vista.getBtnProductos().addActionListener(e -> abrirProductos());
        vista.getBtnCompras().addActionListener(e -> abrirCompras());
        vista.getBtnMembresia().addActionListener(e -> abrirMembresia());
        vista.getBtnCerrarSesion().addActionListener(e -> cerrarSesion());
    }

    private void abrirProductos() {
        ProductosClienteView productosView = new ProductosClienteView();
        cargarProductos(productosView);
        productosView.getBtnComprar().addActionListener(e -> comprar(productosView));
        productosView.setVisible(true);
    }

    private void cargarProductos(ProductosClienteView productosView) {
        productosView.getModeloTabla().setRowCount(0); // limpia la tabla
        List<Producto> lista = productoDAO.listar(); // pide los productos
        for (Producto producto : lista) {
            productosView.getModeloTabla().addRow(new Object[]{ // mete cada producto en la tabla basicamente
                producto.getIdProducto(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getCategoria()
            });
        }
    }

    private void comprar(ProductosClienteView productosView) {
        if (cliente == null) {
            JOptionPane.showMessageDialog(vista, "No se encontró el perfil de cliente.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int fila = productosView.getTabla().getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(productosView, "Seleccione un producto de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String cantidadStr = productosView.getCantidad();
        if (cantidadStr.isEmpty()) {
            JOptionPane.showMessageDialog(productosView, "Ingrese una cantidad.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int cantidad = Integer.parseInt(cantidadStr); // obtiene datos del produto
            int idProducto = (int)    productosView.getModeloTabla().getValueAt(fila, 0);
            double precio = (double) productosView.getModeloTabla().getValueAt(fila, 3);
            int stock  = (int)    productosView.getModeloTabla().getValueAt(fila, 4);

            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(productosView, "La cantidad debe ser mayor a 0.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (cantidad > stock) {
                JOptionPane.showMessageDialog(productosView, "Stock insuficiente. Disponible: " + stock, "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double total = cantidad * precio;

            Compra compra = new Compra(); // crea la compra
            compra.setIdCliente(cliente.getIdCliente());
            compra.setIdProducto(idProducto);
            compra.setCantidad(cantidad);
            compra.setPrecioUnitario(precio);
            compra.setTotal(total);
            compra.setFechaCompra(LocalDateTime.now());

            if (compraDAO.insertar(compra)) { // aqui guarda la compra
                Producto producto = productoDAO.buscarPorId(idProducto);
                producto.setStock(stock - cantidad); // actualiza stock
                productoDAO.actualizar(producto);

                JOptionPane.showMessageDialog(productosView, "Compra realizada. Total: ₡" + String.format("%.2f", total), "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarProductos(productosView);
            } else {
                JOptionPane.showMessageDialog(productosView, "Error al procesar la compra.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(productosView, "La cantidad debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirCompras() {
        if (cliente == null) {
            JOptionPane.showMessageDialog(vista, "No se encontró el perfil de cliente.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ComprasClienteView comprasView = new ComprasClienteView();
        comprasView.getModeloTabla().setRowCount(0);

        List<Compra> lista = compraDAO.listarPorCliente(cliente.getIdCliente());
        double totalGastado = 0;

        for (Compra compra : lista) {
            comprasView.getModeloTabla().addRow(new Object[]{
                compra.getIdCompra(),
                compra.getIdProducto(),
                compra.getCantidad(),
                compra.getPrecioUnitario(),
                compra.getTotal(),
                compra.getFechaCompra()
            });
            totalGastado += compra.getTotal(); // se suma por cada compra que haga
        }

        comprasView.setTotalGastado(totalGastado);
        comprasView.setVisible(true);
    }

    private void abrirMembresia() {
        if (cliente == null) {
            JOptionPane.showMessageDialog(vista, "No se encontró el perfil de cliente.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        MembresiaClienteView membresiaView = new MembresiaClienteView();

        if (cliente.getIdMembresia() == 0) {
            membresiaView.sinMembresia();
        } else {
            Membresia membresia = membresiaDAO.buscarPorId(cliente.getIdMembresia());
            if (membresia != null) {
                membresiaView.setNombreMembresia(membresia.getNombre());
                membresiaView.setDuracion(membresia.getDuracionDias());
                membresiaView.setPrecio(membresia.getPrecio());
                membresiaView.setFechaInicio(String.valueOf(cliente.getFechaInicioMembresia()));
                membresiaView.setFechaFin(String.valueOf(cliente.getFechaFinMembresia()));

                boolean activa = cliente.getFechaFinMembresia() != null &&
                                 cliente.getFechaFinMembresia().isAfter(java.time.LocalDate.now()); 
                // compara el fin de la membresia con la fecha acctual
                membresiaView.setEstado(activa);
               
            } else {
                membresiaView.sinMembresia(); 
            }
        }

        membresiaView.setVisible(true);
    }

    private void cerrarSesion() {
        vista.dispose();
        LoginView loginView = new LoginView();
        new LoginController(loginView);
        loginView.setVisible(true);
    }
}