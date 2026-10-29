package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ClienteView extends JFrame {

    private JButton btnProductos;
    private JButton btnCompras;
    private JButton btnMembresia;
    private JButton btnCerrarSesion;
    private JLabel lblBienvenida;

    public ClienteView(String nombreCliente) {
        setTitle("Pintuya Gym — Panel Cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);
        initComponents(nombreCliente);
    }

    private void initComponents(String nombreCliente) {

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(18, 18, 18));

        // ── SIDEBAR ──
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(28, 28, 28));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(280, 0));
        sidebar.setBorder(new EmptyBorder(40, 24, 40, 24));

        JLabel lblLogo = new JLabel("🏋️  Pintuya Gym");
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblLogo.setForeground(new Color(255, 165, 0));
        lblLogo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblRol = new JLabel("CLIENTE");
        lblRol.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblRol.setForeground(new Color(120, 120, 120));
        lblRol.setAlignmentX(Component.LEFT_ALIGNMENT);

        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(50, 50, 50));
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));

        btnProductos = crearBotonMenu("🛒  Productos");
        btnCompras   = crearBotonMenu("📋  Mis Compras");
        btnMembresia = crearBotonMenu("🪪  Mi Membresía");

        btnCerrarSesion = new JButton("🚪  Cerrar Sesión");
        btnCerrarSesion.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btnCerrarSesion.setForeground(new Color(220, 80, 80));
        btnCerrarSesion.setBackground(new Color(28, 28, 28));
        btnCerrarSesion.setBorderPainted(false);
        btnCerrarSesion.setFocusPainted(false);
        btnCerrarSesion.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCerrarSesion.setHorizontalAlignment(SwingConstants.LEFT);
        btnCerrarSesion.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        btnCerrarSesion.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnCerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btnCerrarSesion.setForeground(Color.RED);
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                btnCerrarSesion.setForeground(new Color(220, 80, 80));
            }
        });

        sidebar.add(lblLogo);
        sidebar.add(Box.createVerticalStrut(6));
        sidebar.add(lblRol);
        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(sep);
        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(btnProductos);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnCompras);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnMembresia);
        sidebar.add(Box.createVerticalGlue());
        sidebar.add(btnCerrarSesion);

        // ── PANEL CONTENIDO ──
        JPanel panelContenido = new JPanel(new BorderLayout());
        panelContenido.setBackground(new Color(18, 18, 18));
        panelContenido.setBorder(new EmptyBorder(40, 40, 40, 40));

        JPanel panelHeader = new JPanel(new BorderLayout());
        panelHeader.setBackground(new Color(18, 18, 18));

        lblBienvenida = new JLabel("Bienvenido, " + nombreCliente + " 👋");
        lblBienvenida.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lblBienvenida.setForeground(Color.WHITE);

        JLabel lblFecha = new JLabel(java.time.LocalDate.now().toString());
        lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblFecha.setForeground(new Color(120, 120, 120));

        panelHeader.add(lblBienvenida, BorderLayout.NORTH);
        panelHeader.add(lblFecha,      BorderLayout.SOUTH);

        JPanel panelTarjetas = new JPanel(new GridLayout(1, 3, 20, 20));
        panelTarjetas.setBackground(new Color(18, 18, 18));
        panelTarjetas.setBorder(new EmptyBorder(30, 0, 0, 0));

        panelTarjetas.add(crearTarjeta("🛒", "Productos",    "Ver productos disponibles"));
        panelTarjetas.add(crearTarjeta("📋", "Mis Compras",  "Historial de compras"));
        panelTarjetas.add(crearTarjeta("🪪", "Mi Membresía", "Ver membresía activa"));

        panelContenido.add(panelHeader,   BorderLayout.NORTH);
        panelContenido.add(panelTarjetas, BorderLayout.CENTER);

        panelPrincipal.add(sidebar,        BorderLayout.WEST);
        panelPrincipal.add(panelContenido, BorderLayout.CENTER);

        setContentPane(panelPrincipal);
    }

    private JButton crearBotonMenu(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btn.setForeground(new Color(200, 200, 200));
        btn.setBackground(new Color(28, 28, 28));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(new Color(45, 45, 45));
                btn.setForeground(new Color(255, 165, 0));
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(new Color(28, 28, 28));
                btn.setForeground(new Color(200, 200, 200));
            }
        });
        return btn;
    }

    private JPanel crearTarjeta(String icono, String titulo, String descripcion) {
        JPanel tarjeta = new JPanel();
        tarjeta.setBackground(new Color(28, 28, 28));
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBorder(new EmptyBorder(28, 28, 28, 28));

        JLabel lblIcono = new JLabel(icono);
        lblIcono.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 36));
        lblIcono.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblDesc = new JLabel(descripcion);
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblDesc.setForeground(new Color(120, 120, 120));
        lblDesc.setAlignmentX(Component.LEFT_ALIGNMENT);

        tarjeta.add(lblIcono);
        tarjeta.add(Box.createVerticalStrut(10));
        tarjeta.add(lblTitulo);
        tarjeta.add(Box.createVerticalStrut(6));
        tarjeta.add(lblDesc);

        return tarjeta;
    }

    // ── GETTERS ──
    public JButton getBtnProductos()    { return btnProductos; }
    public JButton getBtnCompras()      { return btnCompras; }
    public JButton getBtnMembresia()    { return btnMembresia; }
    public JButton getBtnCerrarSesion() { return btnCerrarSesion; }
}