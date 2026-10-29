package controller;

import model.Cliente;
import model.ClienteDAO;
import model.CompraDAO;
import model.Usuario;
import model.UsuarioDAO;
import view.GestionUsuariosView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class UsuarioController {

    private GestionUsuariosView vista;
    private UsuarioDAO usuarioDAO;
    private int idSeleccionado = -1;
    private ClienteDAO clienteDAO;
    private CompraDAO compraDAO;

    private static final Color COLOR_ERROR  = new Color(220, 80, 80);
    private static final Color COLOR_NORMAL = new Color(70, 70, 70);

    public UsuarioController(GestionUsuariosView vista) {
        this.vista      = vista;
        this.usuarioDAO = new UsuarioDAO();
        this.clienteDAO = new ClienteDAO();
        this.compraDAO  = new CompraDAO();
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
        List<Usuario> lista = usuarioDAO.listar();
        for (Usuario u : lista) {
            vista.getModeloTabla().addRow(new Object[]{
                u.getIdUsuario(), u.getNombre(), u.getApellidos(), u.getCorreo(), u.getRol()
            });
        }
    }

    private void agregar() {
        String nombre     = vista.getNombre();
        String apellidos  = vista.getApellidos();
        String correo     = vista.getCorreo();
        String contrasena = vista.getContrasena();

        boolean valido = true;
        resetBordes();

        if (nombre.isEmpty()) {
            marcarError(vista.getTxtNombre()); valido = false;
        }
        if (apellidos.isEmpty()) {
            marcarError(vista.getTxtApellidos()); valido = false;
        }
        if (!correo.matches("^[\\w._%+\\-]+@[\\w.\\-]+\\.[a-zA-Z]{2,}$")) {
            marcarError(vista.getTxtCorreo()); valido = false;
        }
        if (contrasena.length() < 4) {
            marcarError(vista.getTxtContrasena()); valido = false;
        }

        if (!valido) {
            JOptionPane.showMessageDialog(vista,
                "Revisá los campos marcados en rojo.", "Datos inválidos",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setCorreo(correo);
        usuario.setContrasena(contrasena);
        usuario.setRol(vista.getRol());

        if (usuarioDAO.insertar(usuario)) {
            JOptionPane.showMessageDialog(vista, "Usuario agregado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiar();
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(vista, "Error al agregar usuario. El correo puede estar duplicado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizar() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un usuario de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nombre = vista.getNombre();
        String apellidos = vista.getApellidos();
        String correo  = vista.getCorreo();

        boolean valido = true;
        resetBordes();

        if (nombre.isEmpty()) {
            marcarError(vista.getTxtNombre()); valido = false;
        }
        if (apellidos.isEmpty()) {
            marcarError(vista.getTxtApellidos()); valido = false;
        }
        if (!correo.matches("^[\\w._%+\\-]+@[\\w.\\-]+\\.[a-zA-Z]{2,}$")) {
            marcarError(vista.getTxtCorreo()); valido = false;
        }

        if (!valido) {
            JOptionPane.showMessageDialog(vista,
                "Revisá los campos marcados en rojo.", "Datos inválidos",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idSeleccionado);
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setCorreo(correo);
        usuario.setContrasena(vista.getContrasena());
        usuario.setRol(vista.getRol());

        if (usuarioDAO.actualizar(usuario)) {
            JOptionPane.showMessageDialog(vista, "Usuario actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiar();
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(vista, "Error al actualizar usuario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminar() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un usuario de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmar = JOptionPane.showConfirmDialog(vista,
            "¿Está seguro? Esto también eliminará el cliente y sus compras asociadas.",
            "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirmar == JOptionPane.YES_OPTION) {
            Cliente cliente = clienteDAO.buscarPorUsuario(idSeleccionado);
            if (cliente != null) {
                compraDAO.eliminarPorCliente(cliente.getIdCliente());
                clienteDAO.eliminar(cliente.getIdCliente());
            }
            if (usuarioDAO.eliminar(idSeleccionado)) {
                JOptionPane.showMessageDialog(vista, "Usuario eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al eliminar usuario.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void seleccionarFila() {
        int fila = vista.getTabla().getSelectedRow();
        if (fila == -1) 
        	return;
        idSeleccionado   = (int)    vista.getModeloTabla().getValueAt(fila, 0);
        String nombre    = (String) vista.getModeloTabla().getValueAt(fila, 1);
        String apellidos = (String) vista.getModeloTabla().getValueAt(fila, 2);
        String correo    = (String) vista.getModeloTabla().getValueAt(fila, 3);
        String rol       = (String) vista.getModeloTabla().getValueAt(fila, 4);
        vista.llenarFormulario(nombre, apellidos, correo, rol);
    }

    private void marcarError(JComponent campo) {
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_ERROR, 2),
            new EmptyBorder(10, 12, 10, 12)));
    }

    private void resetBordes() {
        JComponent[] campos = {
            vista.getTxtNombre(), vista.getTxtApellidos(),
            vista.getTxtCorreo(), vista.getTxtContrasena()
        };
        for (JComponent c : campos) {
            c.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_NORMAL),
                new EmptyBorder(10, 12, 10, 12)));
        }
    }

    private void limpiar() {
        vista.limpiarFormulario();
        resetBordes();
        idSeleccionado = -1;
    }
}
