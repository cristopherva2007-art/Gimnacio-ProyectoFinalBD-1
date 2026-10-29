package controller;

import view.AdminView;
import view.GestionUsuariosView;
import view.GestionProductosView;
import view.GestionMaquinasView;
import view.GestionMembresiasView;
import view.GestionClientesView;
import view.ConsultasView;
import view.LoginView;

public class AdminController {

    private AdminView vista;

    public AdminController(AdminView vista) {
        this.vista = vista;
        initListeners();
    }

    private void initListeners() {
        vista.getBtnUsuarios().addActionListener(e -> abrirUsuarios());
        vista.getBtnProductos().addActionListener(e -> abrirProductos());
        vista.getBtnMaquinas().addActionListener(e -> abrirMaquinas());
        vista.getBtnMembresias().addActionListener(e -> abrirMembresias());
        vista.getBtnClientes().addActionListener(e -> abrirClientes());
        vista.getBtnConsultas().addActionListener(e -> abrirConsultas());
        vista.getBtnCerrarSesion().addActionListener(e -> cerrarSesion());
    }

    private void abrirUsuarios() {
        GestionUsuariosView usuariosView = new GestionUsuariosView();
        new UsuarioController(usuariosView);
        usuariosView.setVisible(true);
    }

    private void abrirProductos() {
        GestionProductosView productosView = new GestionProductosView();
        new ProductoController(productosView);
        productosView.setVisible(true);
    }

    private void abrirMaquinas() {
        GestionMaquinasView maquinasView = new GestionMaquinasView();
        new MaquinaController(maquinasView);
        maquinasView.setVisible(true);
    }

    private void abrirMembresias() {
        GestionMembresiasView membresiasView = new GestionMembresiasView();
        new MembresiaController(membresiasView);
        membresiasView.setVisible(true);
    }

    private void abrirClientes() {
        GestionClientesView clientesView = new GestionClientesView();
        new ClienteDAOController(clientesView);
        clientesView.setVisible(true);
    }

    private void abrirConsultas() {
        ConsultasView consultasView = new ConsultasView();
        consultasView.setVisible(true);
    }

    private void cerrarSesion() {
        vista.dispose();
        LoginView loginView = new LoginView();
        new LoginController(loginView);
        loginView.setVisible(true);
    }
}