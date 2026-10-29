package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RegistrarClienteView extends JFrame {

    private JTextField txtNombre;
    private JTextField txtApellidos;
    private JTextField txtCorreo;
    private JPasswordField txtContrasena;
    private JTextField txtTelefono;
    private JComboBox<String> cmbMembresia;
    private JTextField txtFechaInicio;
    private JTextField txtFechaFin;
    private JButton btnRegistrar;
    private JButton btnLimpiar;

    public RegistrarClienteView() {
        setTitle("Pintuya Gym — Registrar Cliente");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);
        initComponents();
    }

    private void initComponents() {

        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBackground(new Color(18, 18, 18));

        JPanel panelContenido = new JPanel(new BorderLayout(0, 20));
        panelContenido.setBackground(new Color(18, 18, 18));
        panelContenido.setPreferredSize(new Dimension(700, 600));

        // ── TÍTULO ──
        JLabel lblTitulo = new JLabel("Registrar Nuevo Cliente");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(255, 165, 0));
        lblTitulo.setBorder(new EmptyBorder(0, 0, 10, 0));

        // ── CAMPOS ──
        txtNombre = new JTextField();
        txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtNombre.setBackground(new Color(40, 40, 40));
        txtNombre.setForeground(Color.WHITE);
        txtNombre.setCaretColor(Color.WHITE);
        txtNombre.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 70, 70)),
            new EmptyBorder(10, 12, 10, 12)));

        txtApellidos = new JTextField();
        txtApellidos.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtApellidos.setBackground(new Color(40, 40, 40));
        txtApellidos.setForeground(Color.WHITE);
        txtApellidos.setCaretColor(Color.WHITE);
        txtApellidos.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 70, 70)),
            new EmptyBorder(10, 12, 10, 12)));

        txtCorreo = new JTextField();
        txtCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtCorreo.setBackground(new Color(40, 40, 40));
        txtCorreo.setForeground(Color.WHITE);
        txtCorreo.setCaretColor(Color.WHITE);
        txtCorreo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 70, 70)),
            new EmptyBorder(10, 12, 10, 12)));

        txtContrasena = new JPasswordField();
        txtContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtContrasena.setBackground(new Color(40, 40, 40));
        txtContrasena.setForeground(Color.WHITE);
        txtContrasena.setCaretColor(Color.WHITE);
        txtContrasena.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 70, 70)),
            new EmptyBorder(10, 12, 10, 12)));

        txtTelefono = new JTextField();
        txtTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtTelefono.setBackground(new Color(40, 40, 40));
        txtTelefono.setForeground(Color.WHITE);
        txtTelefono.setCaretColor(Color.WHITE);
        txtTelefono.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 70, 70)),
            new EmptyBorder(10, 12, 10, 12)));

        cmbMembresia = new JComboBox<>();
        cmbMembresia.setBackground(new Color(40, 40, 40));
        cmbMembresia.setForeground(Color.WHITE);
        cmbMembresia.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        txtFechaInicio = new JTextField();
        txtFechaInicio.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtFechaInicio.setBackground(new Color(40, 40, 40));
        txtFechaInicio.setForeground(Color.WHITE);
        txtFechaInicio.setCaretColor(Color.WHITE);
        txtFechaInicio.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 70, 70)),
            new EmptyBorder(10, 12, 10, 12)));

        txtFechaFin = new JTextField();
        txtFechaFin.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtFechaFin.setBackground(new Color(40, 40, 40));
        txtFechaFin.setForeground(Color.WHITE);
        txtFechaFin.setCaretColor(Color.WHITE);
        txtFechaFin.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 70, 70)),
            new EmptyBorder(10, 12, 10, 12)));

        // ── LABELS ──
        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblNombre.setForeground(new Color(160, 160, 160));

        JLabel lblApellidos = new JLabel("Apellidos");
        lblApellidos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblApellidos.setForeground(new Color(160, 160, 160));

        JLabel lblCorreo = new JLabel("Correo");
        lblCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblCorreo.setForeground(new Color(160, 160, 160));

        JLabel lblContrasena = new JLabel("Contraseña");
        lblContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblContrasena.setForeground(new Color(160, 160, 160));

        JLabel lblTelefono = new JLabel("Teléfono");
        lblTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblTelefono.setForeground(new Color(160, 160, 160));

        JLabel lblMembresia = new JLabel("Membresía");
        lblMembresia.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblMembresia.setForeground(new Color(160, 160, 160));

        JLabel lblFechaInicio = new JLabel("Inicio (YYYY-MM-DD)");
        lblFechaInicio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblFechaInicio.setForeground(new Color(160, 160, 160));

        JLabel lblFechaFin = new JLabel("Fin (YYYY-MM-DD)");
        lblFechaFin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblFechaFin.setForeground(new Color(160, 160, 160));

        // ── FORMULARIO ──
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBackground(new Color(28, 28, 28));
        panelForm.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(45, 45, 45)),
            new EmptyBorder(24, 24, 24, 24)));

        GridBagConstraints g1 = new GridBagConstraints();
        g1.insets = new Insets(8, 8, 8, 8);
        g1.fill = GridBagConstraints.HORIZONTAL;
        g1.gridx = 0; g1.gridy = 0; g1.weightx = 0.4;
        panelForm.add(lblNombre, g1);

        GridBagConstraints g2 = new GridBagConstraints();
        g2.insets = new Insets(8, 8, 8, 8);
        g2.fill = GridBagConstraints.HORIZONTAL;
        g2.gridx = 1; g2.gridy = 0; g2.weightx = 0.6;
        panelForm.add(txtNombre, g2);

        GridBagConstraints g3 = new GridBagConstraints();
        g3.insets = new Insets(8, 8, 8, 8);
        g3.fill = GridBagConstraints.HORIZONTAL;
        g3.gridx = 0; g3.gridy = 1; g3.weightx = 0.4;
        panelForm.add(lblApellidos, g3);

        GridBagConstraints g4 = new GridBagConstraints();
        g4.insets = new Insets(8, 8, 8, 8);
        g4.fill = GridBagConstraints.HORIZONTAL;
        g4.gridx = 1; g4.gridy = 1; g4.weightx = 0.6;
        panelForm.add(txtApellidos, g4);

        GridBagConstraints g5 = new GridBagConstraints();
        g5.insets = new Insets(8, 8, 8, 8);
        g5.fill = GridBagConstraints.HORIZONTAL;
        g5.gridx = 0; g5.gridy = 2; g5.weightx = 0.4;
        panelForm.add(lblCorreo, g5);

        GridBagConstraints g6 = new GridBagConstraints();
        g6.insets = new Insets(8, 8, 8, 8);
        g6.fill = GridBagConstraints.HORIZONTAL;
        g6.gridx = 1; g6.gridy = 2; g6.weightx = 0.6;
        panelForm.add(txtCorreo, g6);

        GridBagConstraints g7 = new GridBagConstraints();
        g7.insets = new Insets(8, 8, 8, 8);
        g7.fill = GridBagConstraints.HORIZONTAL;
        g7.gridx = 0; g7.gridy = 3; g7.weightx = 0.4;
        panelForm.add(lblContrasena, g7);

        GridBagConstraints g8 = new GridBagConstraints();
        g8.insets = new Insets(8, 8, 8, 8);
        g8.fill = GridBagConstraints.HORIZONTAL;
        g8.gridx = 1; g8.gridy = 3; g8.weightx = 0.6;
        panelForm.add(txtContrasena, g8);

        GridBagConstraints g9 = new GridBagConstraints();
        g9.insets = new Insets(8, 8, 8, 8);
        g9.fill = GridBagConstraints.HORIZONTAL;
        g9.gridx = 0; g9.gridy = 4; g9.weightx = 0.4;
        panelForm.add(lblTelefono, g9);

        GridBagConstraints g10 = new GridBagConstraints();
        g10.insets = new Insets(8, 8, 8, 8);
        g10.fill = GridBagConstraints.HORIZONTAL;
        g10.gridx = 1; g10.gridy = 4; g10.weightx = 0.6;
        panelForm.add(txtTelefono, g10);

        GridBagConstraints g11 = new GridBagConstraints();
        g11.insets = new Insets(8, 8, 8, 8);
        g11.fill = GridBagConstraints.HORIZONTAL;
        g11.gridx = 0; g11.gridy = 5; g11.weightx = 0.4;
        panelForm.add(lblMembresia, g11);

        GridBagConstraints g12 = new GridBagConstraints();
        g12.insets = new Insets(8, 8, 8, 8);
        g12.fill = GridBagConstraints.HORIZONTAL;
        g12.gridx = 1; g12.gridy = 5; g12.weightx = 0.6;
        panelForm.add(cmbMembresia, g12);

        GridBagConstraints g13 = new GridBagConstraints();
        g13.insets = new Insets(8, 8, 8, 8);
        g13.fill = GridBagConstraints.HORIZONTAL;
        g13.gridx = 0; g13.gridy = 6; g13.weightx = 0.4;
        panelForm.add(lblFechaInicio, g13);

        GridBagConstraints g14 = new GridBagConstraints();
        g14.insets = new Insets(8, 8, 8, 8);
        g14.fill = GridBagConstraints.HORIZONTAL;
        g14.gridx = 1; g14.gridy = 6; g14.weightx = 0.6;
        panelForm.add(txtFechaInicio, g14);

        GridBagConstraints g15 = new GridBagConstraints();
        g15.insets = new Insets(8, 8, 8, 8);
        g15.fill = GridBagConstraints.HORIZONTAL;
        g15.gridx = 0; g15.gridy = 7; g15.weightx = 0.4;
        panelForm.add(lblFechaFin, g15);

        GridBagConstraints g16 = new GridBagConstraints();
        g16.insets = new Insets(8, 8, 8, 8);
        g16.fill = GridBagConstraints.HORIZONTAL;
        g16.gridx = 1; g16.gridy = 7; g16.weightx = 0.6;
        panelForm.add(txtFechaFin, g16);

        // ── BOTONES ──
        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnRegistrar.setBackground(new Color(255, 165, 0));
        btnRegistrar.setForeground(new Color(18, 18, 18));
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setBorderPainted(false);
        btnRegistrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegistrar.setPreferredSize(new Dimension(130, 44));

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnLimpiar.setBackground(new Color(60, 60, 60));
        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.setBorderPainted(false);
        btnLimpiar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLimpiar.setPreferredSize(new Dimension(130, 44));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        panelBotones.setBackground(new Color(18, 18, 18));
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnRegistrar);

        panelContenido.add(lblTitulo,    BorderLayout.NORTH);
        panelContenido.add(panelForm,    BorderLayout.CENTER);
        panelContenido.add(panelBotones, BorderLayout.SOUTH);

        panelPrincipal.add(panelContenido);
        setContentPane(panelPrincipal);
    }

    // ── GETTERS ──
    public String getNombre()      { return txtNombre.getText().trim(); }
    public String getApellidos()   { return txtApellidos.getText().trim(); }
    public String getCorreo()      { return txtCorreo.getText().trim(); }
    public String getContrasena()  { return new String(txtContrasena.getPassword()); }
    public String getTelefono()    { return txtTelefono.getText().trim(); }
    public JComboBox<String> getCmbMembresia() { return cmbMembresia; }
    public String getFechaInicio() { return txtFechaInicio.getText().trim(); }
    public String getFechaFin()    { return txtFechaFin.getText().trim(); }
    public JButton getBtnRegistrar() { return btnRegistrar; }
    public JButton getBtnLimpiar()   { return btnLimpiar; }

    // Getters de componentes (para validaciones visuales desde el controller)
    public JTextField getTxtNombre()         { return txtNombre; }
    public JTextField getTxtApellidos()      { return txtApellidos; }
    public JTextField getTxtCorreo()         { return txtCorreo; }
    public JPasswordField getTxtContrasena() { return txtContrasena; }
    public JTextField getTxtTelefono()       { return txtTelefono; }

    // Setters de fecha (para cálculo automático)
    public void setFechaInicio(String fecha) { txtFechaInicio.setText(fecha); }
    public void setFechaFin(String fecha)    { txtFechaFin.setText(fecha); }

    public void limpiarFormulario() {
        txtNombre.setText("");
        txtApellidos.setText("");
        txtCorreo.setText("");
        txtContrasena.setText("");
        txtTelefono.setText("");
        cmbMembresia.setSelectedIndex(0);
        txtFechaInicio.setText("");
        txtFechaFin.setText("");
    }
}