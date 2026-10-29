package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GestionUsuariosView extends JFrame {

    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JTextField txtNombre;
    private JTextField txtApellidos;
    private JTextField txtCorreo;
    private JPasswordField txtContrasena;
    private JComboBox<String> cmbRol;
    private JButton btnAgregar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnLimpiar;

    public GestionUsuariosView() {
        setTitle("Pintuya Gym — Gestión de Usuarios");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);
        initComponents();
    }

    private void initComponents() {

        JPanel panelPrincipal = new JPanel(new BorderLayout(16, 16));
        panelPrincipal.setBackground(new Color(18, 18, 18));
        panelPrincipal.setBorder(new EmptyBorder(30, 30, 30, 30));

        // ── TÍTULO ──
        JLabel lblTitulo = new JLabel("Gestión de Usuarios");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(255, 165, 0));
        lblTitulo.setBorder(new EmptyBorder(0, 0, 14, 0));

        // ── TABLA ──
        String[] columnas = {"ID", "Nombre", "Apellidos", "Correo", "Rol"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };

        tabla = new JTable(modeloTabla);
        tabla.setBackground(new Color(28, 28, 28));
        tabla.setForeground(Color.WHITE);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        tabla.setRowHeight(38);
        tabla.setGridColor(new Color(45, 45, 45));
        tabla.setSelectionBackground(new Color(255, 165, 0, 80));
        tabla.setSelectionForeground(Color.WHITE);
        tabla.getTableHeader().setBackground(new Color(40, 40, 40));
        tabla.getTableHeader().setForeground(new Color(255, 165, 0));
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.getViewport().setBackground(new Color(28, 28, 28));
        scroll.setBorder(BorderFactory.createLineBorder(new Color(45, 45, 45)));

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

        cmbRol = new JComboBox<>(new String[]{"cliente", "recepcionista", "admin"});
        cmbRol.setBackground(new Color(40, 40, 40));
        cmbRol.setForeground(Color.WHITE);
        cmbRol.setFont(new Font("Segoe UI", Font.PLAIN, 15));

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

        JLabel lblRol = new JLabel("Rol");
        lblRol.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblRol.setForeground(new Color(160, 160, 160));

        // ── FORMULARIO ──
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBackground(new Color(28, 28, 28));
        panelForm.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(45, 45, 45)),
            new EmptyBorder(20, 20, 20, 20)));

        GridBagConstraints g1 = new GridBagConstraints();
        g1.insets = new Insets(8, 8, 8, 8);
        g1.fill = GridBagConstraints.HORIZONTAL;
        g1.gridx = 0; g1.gridy = 0; g1.weightx = 0.3;
        panelForm.add(lblNombre, g1);

        GridBagConstraints g2 = new GridBagConstraints();
        g2.insets = new Insets(8, 8, 8, 8);
        g2.fill = GridBagConstraints.HORIZONTAL;
        g2.gridx = 1; g2.gridy = 0; g2.weightx = 0.7;
        panelForm.add(txtNombre, g2);

        GridBagConstraints g3 = new GridBagConstraints();
        g3.insets = new Insets(8, 8, 8, 8);
        g3.fill = GridBagConstraints.HORIZONTAL;
        g3.gridx = 2; g3.gridy = 0; g3.weightx = 0.3;
        panelForm.add(lblApellidos, g3);

        GridBagConstraints g4 = new GridBagConstraints();
        g4.insets = new Insets(8, 8, 8, 8);
        g4.fill = GridBagConstraints.HORIZONTAL;
        g4.gridx = 3; g4.gridy = 0; g4.weightx = 0.7;
        panelForm.add(txtApellidos, g4);

        GridBagConstraints g5 = new GridBagConstraints();
        g5.insets = new Insets(8, 8, 8, 8);
        g5.fill = GridBagConstraints.HORIZONTAL;
        g5.gridx = 0; g5.gridy = 1; g5.weightx = 0.3;
        panelForm.add(lblCorreo, g5);

        GridBagConstraints g6 = new GridBagConstraints();
        g6.insets = new Insets(8, 8, 8, 8);
        g6.fill = GridBagConstraints.HORIZONTAL;
        g6.gridx = 1; g6.gridy = 1; g6.weightx = 0.7;
        panelForm.add(txtCorreo, g6);

        GridBagConstraints g7 = new GridBagConstraints();
        g7.insets = new Insets(8, 8, 8, 8);
        g7.fill = GridBagConstraints.HORIZONTAL;
        g7.gridx = 2; g7.gridy = 1; g7.weightx = 0.3;
        panelForm.add(lblContrasena, g7);

        GridBagConstraints g8 = new GridBagConstraints();
        g8.insets = new Insets(8, 8, 8, 8);
        g8.fill = GridBagConstraints.HORIZONTAL;
        g8.gridx = 3; g8.gridy = 1; g8.weightx = 0.7;
        panelForm.add(txtContrasena, g8);

        GridBagConstraints g9 = new GridBagConstraints();
        g9.insets = new Insets(8, 8, 8, 8);
        g9.fill = GridBagConstraints.HORIZONTAL;
        g9.gridx = 0; g9.gridy = 2; g9.weightx = 0.3;
        panelForm.add(lblRol, g9);

        GridBagConstraints g10 = new GridBagConstraints();
        g10.insets = new Insets(8, 8, 8, 8);
        g10.fill = GridBagConstraints.HORIZONTAL;
        g10.gridx = 1; g10.gridy = 2; g10.weightx = 0.7;
        panelForm.add(cmbRol, g10);

        // ── BOTONES ──
        btnAgregar = new JButton("Agregar");
        btnAgregar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnAgregar.setBackground(new Color(255, 165, 0));
        btnAgregar.setForeground(new Color(18, 18, 18));
        btnAgregar.setFocusPainted(false);
        btnAgregar.setBorderPainted(false);
        btnAgregar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAgregar.setPreferredSize(new Dimension(130, 44));

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnActualizar.setBackground(new Color(70, 130, 180));
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.setFocusPainted(false);
        btnActualizar.setBorderPainted(false);
        btnActualizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnActualizar.setPreferredSize(new Dimension(130, 44));

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnEliminar.setBackground(new Color(220, 80, 80));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setBorderPainted(false);
        btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEliminar.setPreferredSize(new Dimension(130, 44));

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
        panelBotones.add(btnEliminar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnAgregar);

        JPanel panelSur = new JPanel(new BorderLayout(0, 14));
        panelSur.setBackground(new Color(18, 18, 18));
        panelSur.add(panelForm,    BorderLayout.CENTER);
        panelSur.add(panelBotones, BorderLayout.SOUTH);

        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        panelPrincipal.add(scroll,    BorderLayout.CENTER);
        panelPrincipal.add(panelSur,  BorderLayout.SOUTH);

        setContentPane(panelPrincipal);
    }

    // ── GETTERS ──
    public JTable getTabla()                  { return tabla; }
    public DefaultTableModel getModeloTabla() { return modeloTabla; }
    public String getNombre()                 { return txtNombre.getText().trim(); }
    public String getApellidos()              { return txtApellidos.getText().trim(); }
    public String getCorreo()                 { return txtCorreo.getText().trim(); }
    public String getContrasena()             { return new String(txtContrasena.getPassword()); }
    public String getRol()                    { return (String) cmbRol.getSelectedItem(); }
    public JButton getBtnAgregar()            { return btnAgregar; }
    public JButton getBtnActualizar()         { return btnActualizar; }
    public JButton getBtnEliminar()           { return btnEliminar; }
    public JButton getBtnLimpiar()            { return btnLimpiar; }

    // Getters de componentes para validaciones visuales
    public JTextField getTxtNombre()         { return txtNombre; }
    public JTextField getTxtApellidos()      { return txtApellidos; }
    public JTextField getTxtCorreo()         { return txtCorreo; }
    public JPasswordField getTxtContrasena() { return txtContrasena; }

    public void limpiarFormulario() {
        txtNombre.setText("");
        txtApellidos.setText("");
        txtCorreo.setText("");
        txtContrasena.setText("");
        cmbRol.setSelectedIndex(0);
    }

    public void llenarFormulario(String nombre, String apellidos, String correo, String rol) {
        txtNombre.setText(nombre);
        txtApellidos.setText(apellidos);
        txtCorreo.setText(correo);
        cmbRol.setSelectedItem(rol);
    }
}