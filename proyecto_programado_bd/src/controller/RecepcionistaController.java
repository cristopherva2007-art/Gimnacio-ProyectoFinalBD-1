package controller;

import model.Membresia;
import model.MembresiaDAO;
import model.Usuario;
import model.UsuarioDAO;
import model.Cliente;
import model.ClienteDAO;
import view.RecepcionistaView;
import view.RegistrarClienteView;
import view.GestionMembresiasView;
import view.LoginView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class RecepcionistaController {

    private RecepcionistaView vista;
    private UsuarioDAO usuarioDAO;
    private ClienteDAO clienteDAO;
    private MembresiaDAO membresiaDAO;

    private static final Color COLOR_ERROR  = new Color(220, 80, 80);
    private static final Color COLOR_NORMAL = new Color(70, 70, 70);

    public RecepcionistaController(RecepcionistaView vista) {
        this.vista        = vista;
        this.usuarioDAO   = new UsuarioDAO();
        this.clienteDAO   = new ClienteDAO();
        this.membresiaDAO = new MembresiaDAO();
        initListeners();
    }

    private void initListeners() {
        vista.getBtnRegistrarCliente().addActionListener(e -> abrirRegistrarCliente());
        vista.getBtnGestionMembresias().addActionListener(e -> abrirGestionMembresias());
        vista.getBtnCerrarSesion().addActionListener(e -> cerrarSesion());
    }

    private void abrirRegistrarCliente() {
        RegistrarClienteView registrarView = new RegistrarClienteView();
        cargarMembresias(registrarView);

        // Al cambiar membresía → calcular fecha fin automáticamente
        registrarView.getCmbMembresia().addActionListener(e ->
            calcularFechaFin(registrarView));

        registrarView.getBtnRegistrar().addActionListener(e -> registrarCliente(registrarView));
        registrarView.getBtnLimpiar().addActionListener(e -> {
            registrarView.limpiarFormulario();
            resetBordes(registrarView);
            calcularFechaFin(registrarView);
        });

        registrarView.setVisible(true);
    }

    private void cargarMembresias(RegistrarClienteView registrarView) {
        registrarView.getCmbMembresia().removeAllItems();
        List<Membresia> lista = membresiaDAO.listar();
        for (Membresia m : lista) {
            registrarView.getCmbMembresia().addItem(
                m.getIdMembresia() + " - " + m.getNombre() + " (" + m.getDuracionDias() + " días)");
        }
        calcularFechaFin(registrarView);
    }

    // Pone fecha_inicio = hoy y fecha_fin = hoy + duración de la membresía seleccionada
    private void calcularFechaFin(RegistrarClienteView registrarView) {
        try {
            String seleccion = (String) registrarView.getCmbMembresia().getSelectedItem();
            if (seleccion == null) return;
            int idMembresia = Integer.parseInt(seleccion.split(" - ")[0]);

            for (Membresia m : membresiaDAO.listar()) {
                if (m.getIdMembresia() == idMembresia) {
                    LocalDate inicio = LocalDate.now();
                    LocalDate fin    = inicio.plusDays(m.getDuracionDias());
                    registrarView.setFechaInicio(inicio.toString());
                    registrarView.setFechaFin(fin.toString());
                    break;
                }
            }
        } catch (Exception ex) {
            // silencioso
        }
    }

    private void registrarCliente(RegistrarClienteView registrarView) {
        String nombre     = registrarView.getNombre();
        String apellidos  = registrarView.getApellidos();
        String correo     = registrarView.getCorreo();
        String contrasena = registrarView.getContrasena();
        String telefono   = registrarView.getTelefono();
        String fechaInicio = registrarView.getFechaInicio();
        String fechaFin    = registrarView.getFechaFin();

        boolean valido = true;
        resetBordes(registrarView);

        if (nombre.isEmpty()) {
            marcarError(registrarView.getTxtNombre()); valido = false;
        }
        if (apellidos.isEmpty()) {
            marcarError(registrarView.getTxtApellidos()); valido = false;
        }
        if (!correo.matches("^[\\w._%+\\-]+@[\\w.\\-]+\\.[a-zA-Z]{2,}$")) {
            marcarError(registrarView.getTxtCorreo()); valido = false;
        }
        if (contrasena.length() < 4) {
            marcarError(registrarView.getTxtContrasena()); valido = false;
        }
        if (!telefono.isEmpty() && !telefono.matches("\\d{7,15}")) {
            marcarError(registrarView.getTxtTelefono()); valido = false;
        }

        if (!valido) {
            JOptionPane.showMessageDialog(registrarView,
                "Revisá los campos marcados en rojo.", "Datos inválidos",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int idMembresia  = obtenerIdMembresia(registrarView);
            LocalDate inicio = LocalDate.parse(fechaInicio);
            LocalDate fin    = LocalDate.parse(fechaFin);

            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setApellidos(apellidos);
            usuario.setCorreo(correo);
            usuario.setContrasena(contrasena);
            usuario.setRol("cliente");

            if (!usuarioDAO.insertar(usuario)) {
                JOptionPane.showMessageDialog(registrarView,
                    "Error al crear el usuario. El correo puede estar duplicado.",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Usuario usuarioCreado = usuarioDAO.buscarPorId(obtenerUltimoIdUsuario());
            if (usuarioCreado == null) {
                JOptionPane.showMessageDialog(registrarView,
                    "Error al obtener el usuario creado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Cliente cliente = new Cliente();
            cliente.setIdUsuario(usuarioCreado.getIdUsuario());
            cliente.setIdMembresia(idMembresia);
            cliente.setTelefono(telefono);
            cliente.setFechaInicioMembresia(inicio);
            cliente.setFechaFinMembresia(fin);

            if (clienteDAO.insertar(cliente)) {
                JOptionPane.showMessageDialog(registrarView,
                    "Cliente registrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                registrarView.limpiarFormulario();
                resetBordes(registrarView);
                calcularFechaFin(registrarView);
            } else {
                JOptionPane.showMessageDialog(registrarView,
                    "Error al registrar el cliente.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(registrarView,
                "Formato de fecha inválido. Use YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void marcarError(JComponent campo) {
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_ERROR, 2),
            new EmptyBorder(10, 12, 10, 12)));
    }

    private void resetBordes(RegistrarClienteView v) {
        JComponent[] campos = {
            v.getTxtNombre(), v.getTxtApellidos(), v.getTxtCorreo(),
            v.getTxtContrasena(), v.getTxtTelefono()
        };
        for (JComponent c : campos) {
            c.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_NORMAL),
                new EmptyBorder(10, 12, 10, 12)));
        }
    }

    private int obtenerIdMembresia(RegistrarClienteView registrarView) {
        String seleccion = (String) registrarView.getCmbMembresia().getSelectedItem();
        if (seleccion == null) return 0;
        return Integer.parseInt(seleccion.split(" - ")[0]);
    }

    private int obtenerUltimoIdUsuario() {
        List<Usuario> lista = usuarioDAO.listar();
        if (lista.isEmpty()) return -1;
        return lista.get(lista.size() - 1).getIdUsuario();
    }

    private void abrirGestionMembresias() {
        GestionMembresiasView membresiasView = new GestionMembresiasView();
        new MembresiaController(membresiasView);
        membresiasView.setVisible(true);
    }

    private void cerrarSesion() {
        vista.dispose();
        LoginView loginView = new LoginView();
        new LoginController(loginView);
        loginView.setVisible(true);
    }
}
