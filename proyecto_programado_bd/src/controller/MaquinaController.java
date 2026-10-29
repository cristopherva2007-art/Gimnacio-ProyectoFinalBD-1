package controller;

import model.Maquina;
import model.MaquinaDAO;
import view.GestionMaquinasView;

import javax.swing.JOptionPane;
import java.util.List;

public class MaquinaController {

    private GestionMaquinasView vista;
    private MaquinaDAO maquinaDAO;
    private int idSeleccionado = -1;

    public MaquinaController(GestionMaquinasView vista) {
        this.vista      = vista;
        this.maquinaDAO = new MaquinaDAO();
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
        List<Maquina> lista = maquinaDAO.listar();
        for (Maquina maquina : lista) {
            vista.getModeloTabla().addRow(new Object[]{
                maquina.getIdMaquina(),
                maquina.getNombre(),
                maquina.getDescripcion(),
                maquina.getEstado(),
                maquina.getRutaImagen()
            });
        }
    }

    private void agregar() {
        String nombre = vista.getNombre();
        String descripcion = vista.getDescripcion();
        String estado= vista.getEstado();
        String rutaImagen= vista.getRutaImagen();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "El nombre es obligatorio.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (nombre.length() < 3) {
            JOptionPane.showMessageDialog(
                vista,
                "El nombre debe tener al menos 3 caracteres.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        Maquina maquina = new Maquina();
        maquina.setNombre(nombre);
        maquina.setDescripcion(descripcion);
        maquina.setEstado(estado);
        maquina.setRutaImagen(rutaImagen);

        if (maquinaDAO.insertar(maquina)) {
            JOptionPane.showMessageDialog(vista, "Máquina agregada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiar();
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(vista, "Error al agregar máquina.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizar() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione una máquina de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nombre = vista.getNombre();
        String descripcion= vista.getDescripcion();
        String estado = vista.getEstado();
        String rutaImagen = vista.getRutaImagen();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "El nombre es obligatorio.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Maquina maquina = new Maquina();
        maquina.setIdMaquina(idSeleccionado);
        maquina.setNombre(nombre);
        maquina.setDescripcion(descripcion);
        maquina.setEstado(estado);
        maquina.setRutaImagen(rutaImagen);

        if (maquinaDAO.actualizar(maquina)) {
            JOptionPane.showMessageDialog(vista, "Máquina actualizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiar();
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(vista, "Error al actualizar máquina.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminar() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione una máquina de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmar = JOptionPane.showConfirmDialog(vista, "¿Está seguro de eliminar esta máquina?", "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirmar == JOptionPane.YES_OPTION) {
            if (maquinaDAO.eliminar(idSeleccionado)) {
                JOptionPane.showMessageDialog(vista, "Máquina eliminada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al eliminar máquina.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void seleccionarFila() {
        int fila = vista.getTabla().getSelectedRow();
        if (fila == -1) return;

        idSeleccionado  = (int)    vista.getModeloTabla().getValueAt(fila, 0);
        String nombre  = (String) vista.getModeloTabla().getValueAt(fila, 1);
        String descripcion = (String) vista.getModeloTabla().getValueAt(fila, 2);
        String estado  = (String) vista.getModeloTabla().getValueAt(fila, 3);
        String rutaImagen = (String) vista.getModeloTabla().getValueAt(fila, 4);

        vista.llenarFormulario(nombre, descripcion, estado, rutaImagen);
    }

    private void limpiar() {
        vista.limpiarFormulario();
        idSeleccionado = -1;
    }
}