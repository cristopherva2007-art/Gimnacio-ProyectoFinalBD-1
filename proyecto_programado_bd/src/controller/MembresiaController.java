package controller;

import model.Membresia;
import model.MembresiaDAO;
import view.GestionMembresiasView;

import javax.swing.JOptionPane;
import java.util.List;

public class MembresiaController {

    private GestionMembresiasView vista;
    private MembresiaDAO membresiaDAO;
    private int idSeleccionado = -1;

    public MembresiaController(GestionMembresiasView vista) {
        this.vista= vista;
        this.membresiaDAO = new MembresiaDAO();
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
        List<Membresia> lista = membresiaDAO.listar();
        for (Membresia membresia : lista) {
            vista.getModeloTabla().addRow(new Object[]{
                membresia.getIdMembresia(),
                membresia.getNombre(),
                membresia.getDuracionDias(),
                membresia.getPrecio()
            });
        }
    }

    private void agregar() {
        String nombre = vista.getNombre();
        String duracionStr= vista.getDuracionDias();
        String precioStr= vista.getPrecio();

        if (nombre.isEmpty() || duracionStr.isEmpty() || precioStr.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Complete todos los campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int duracion  = Integer.parseInt(duracionStr);
            double precio = Double.parseDouble(precioStr);
            if (duracion <= 0) {
                JOptionPane.showMessageDialog(
                    vista,
                    "La duración debe ser mayor que cero.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            if (precio <= 0) {
                JOptionPane.showMessageDialog(
                    vista,
                    "El precio debe ser mayor que cero.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            Membresia membresia = new Membresia();
            membresia.setNombre(nombre);
            membresia.setDuracionDias(duracion);
            membresia.setPrecio(precio);

            if (membresiaDAO.insertar(membresia)) {
                JOptionPane.showMessageDialog(vista, "Membresía agregada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al agregar membresía.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Duración y precio deben ser números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizar() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione una membresía de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nombre = vista.getNombre();
        String duracionStr= vista.getDuracionDias();
        String precioStr = vista.getPrecio();

        if (nombre.isEmpty() || duracionStr.isEmpty() || precioStr.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Complete todos los campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int duracion  = Integer.parseInt(duracionStr);
            double precio = Double.parseDouble(precioStr);

            Membresia membresia = new Membresia();
            membresia.setIdMembresia(idSeleccionado);
            membresia.setNombre(nombre);
            membresia.setDuracionDias(duracion);
            membresia.setPrecio(precio);

            if (membresiaDAO.actualizar(membresia)) {
                JOptionPane.showMessageDialog(vista, "Membresía actualizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al actualizar membresía.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Duración y precio deben ser números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminar() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione una membresía de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmar = JOptionPane.showConfirmDialog(vista, "¿Está seguro de eliminar esta membresía?", "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirmar == JOptionPane.YES_OPTION) {
            if (membresiaDAO.eliminar(idSeleccionado)) {
                JOptionPane.showMessageDialog(vista, "Membresía eliminada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al eliminar membresía.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void seleccionarFila() {
        int fila = vista.getTabla().getSelectedRow();
        if (fila == -1) 
        	return;

        idSeleccionado  = (int)   vista.getModeloTabla().getValueAt(fila, 0);
        String nombre  = (String) vista.getModeloTabla().getValueAt(fila, 1);
        String duracion= String.valueOf(vista.getModeloTabla().getValueAt(fila, 2));
        String precio = String.valueOf(vista.getModeloTabla().getValueAt(fila, 3));

        vista.llenarFormulario(nombre, duracion, precio);
    }

    private void limpiar() {
        vista.limpiarFormulario();
        idSeleccionado = -1;
    }
}