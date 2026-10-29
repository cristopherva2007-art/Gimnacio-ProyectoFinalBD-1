package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginView extends JFrame {

    private JTextField txtCorreo;
    private JPasswordField txtContrasena;
    private JButton btnIngresar;
    private JLabel lblError;

    public LoginView() {
        setTitle("Pintuya Gym — Iniciar Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);
        initComponents();
    }

    private void initComponents() {

        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBackground(new Color(18, 18, 18));

        JPanel panelCard = new JPanel();
        panelCard.setBackground(new Color(28, 28, 28));
        panelCard.setLayout(new BoxLayout(panelCard, BoxLayout.Y_AXIS));
        panelCard.setBorder(new EmptyBorder(40, 50, 40, 50));
        panelCard.setPreferredSize(new Dimension(420, 480));

        // ── LOGO Y TÍTULO ──
        JLabel lblLogo = new JLabel("🏋️", SwingConstants.CENTER);
        lblLogo.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 52));
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblTitulo = new JLabel("Pintuya Gym");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(255, 165, 0));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubtitulo = new JLabel("Sistema de Gestión de Gimnasio");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSubtitulo.setForeground(new Color(160, 160, 160));
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ── CAMPOS ──
        JLabel lblCorreo = new JLabel("Correo electrónico");
        lblCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblCorreo.setForeground(new Color(160, 160, 160));
        lblCorreo.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtCorreo = new JTextField();
        txtCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtCorreo.setBackground(new Color(40, 40, 40));
        txtCorreo.setForeground(Color.WHITE);
        txtCorreo.setCaretColor(Color.WHITE);
        txtCorreo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 70, 70), 1),
            new EmptyBorder(8, 12, 8, 12)));
        txtCorreo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        txtCorreo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblContrasena = new JLabel("Contraseña");
        lblContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblContrasena.setForeground(new Color(160, 160, 160));
        lblContrasena.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtContrasena = new JPasswordField();
        txtContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtContrasena.setBackground(new Color(40, 40, 40));
        txtContrasena.setForeground(Color.WHITE);
        txtContrasena.setCaretColor(Color.WHITE);
        txtContrasena.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 70, 70), 1),
            new EmptyBorder(8, 12, 8, 12)));
        txtContrasena.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        txtContrasena.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblError = new JLabel(" ");
        lblError.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblError.setForeground(new Color(220, 80, 80));
        lblError.setAlignmentX(Component.LEFT_ALIGNMENT);

        btnIngresar = new JButton("Ingresar");
        btnIngresar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnIngresar.setBackground(new Color(255, 165, 0));
        btnIngresar.setForeground(new Color(18, 18, 18));
        btnIngresar.setFocusPainted(false);
        btnIngresar.setBorderPainted(false);
        btnIngresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnIngresar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        btnIngresar.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnIngresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btnIngresar.setBackground(new Color(230, 145, 0));
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                btnIngresar.setBackground(new Color(255, 165, 0));
            }
        });

        panelCard.add(lblLogo);
        panelCard.add(Box.createVerticalStrut(8));
        panelCard.add(lblTitulo);
        panelCard.add(Box.createVerticalStrut(4));
        panelCard.add(lblSubtitulo);
        panelCard.add(Box.createVerticalStrut(30));
        panelCard.add(lblCorreo);
        panelCard.add(Box.createVerticalStrut(6));
        panelCard.add(txtCorreo);
        panelCard.add(Box.createVerticalStrut(16));
        panelCard.add(lblContrasena);
        panelCard.add(Box.createVerticalStrut(6));
        panelCard.add(txtContrasena);
        panelCard.add(Box.createVerticalStrut(8));
        panelCard.add(lblError);
        panelCard.add(Box.createVerticalStrut(16));
        panelCard.add(btnIngresar);

        panelPrincipal.add(panelCard);
        setContentPane(panelPrincipal);
    }

    public String getCorreo()        { return txtCorreo.getText().trim(); }
    public String getContrasena()    { return new String(txtContrasena.getPassword()); }
    public JButton getBtnIngresar()  { return btnIngresar; }
    public void mostrarError(String mensaje) { lblError.setText(mensaje); }
    public void limpiarError()       { lblError.setText(" "); }
}