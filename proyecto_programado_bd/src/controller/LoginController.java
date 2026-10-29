package controller;

import model.Usuario;
import model.UsuarioDAO;
import view.LoginView;
import view.AdminView;
import view.ClienteView;
import view.RecepcionistaView;

public class LoginController {

    private LoginView vista;
    private UsuarioDAO usuarioDAO;

    public LoginController(LoginView vista) {
        this.vista = vista;
        this.usuarioDAO = new UsuarioDAO();
        initListeners();
    }

    private void initListeners() {
        vista.getBtnIngresar().addActionListener(e -> login());
    }

    private void login() {
        String correo = vista.getCorreo(); //obtiene correo y contra
        String contrasena = vista.getContrasena();

        if (correo.isEmpty() || contrasena.isEmpty()) {
            vista.mostrarError("Por favor complete todos los campos.");
            return;
        }

        Usuario usuario = usuarioDAO.login(correo, contrasena);

        if (usuario == null) {
            vista.mostrarError("Correo o contraseña incorrectos.");
            return;
        }

        vista.limpiarError();
        vista.setVisible(false);

        switch (usuario.getRol()) { // dependiendo del rol muestra la ventana correspondente
            case "admin":
                AdminView adminView = new AdminView(usuario.getNombre());
                new AdminController(adminView);
                adminView.setVisible(true);
                break;

            case "recepcionista":
                RecepcionistaView recepView = new RecepcionistaView(usuario.getNombre());
                new RecepcionistaController(recepView);
                recepView.setVisible(true);
                break;

            case "cliente":
                ClienteView clienteView = new ClienteView(usuario.getNombre());
                new ClienteController(clienteView, usuario);
               // Aquí sí se pasa el objeto usuario Por qué
 //Porque el cliente necesita saber quién inició sesión para
                // ver sus compras
                //ver su membresía
                //comprar productos
                clienteView.setVisible(true);
                break;

            default:
                vista.mostrarError("Rol no reconocido.");
                vista.setVisible(true);
                break;
        }
    }
}