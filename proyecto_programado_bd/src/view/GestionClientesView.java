package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GestionClientesView extends JFrame {

    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JTextField txtIdUsuario;
    private JTextField txtTelefono;
    private JComboBox<String> cmbMembresia;
    private JTextField txtFechaInicio;
    private JTextField txtFechaFin;
    private JButton btnAgregar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnLimpiar;

    public GestionClientesView() {
        setTitle("Pintuya Gym — Gestión de Clientes");
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
        JLabel lblTitulo = new JLabel("Gestión de Clientes");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(255, 165, 0));
        lblTitulo.setBorder(new EmptyBorder(0, 0, 14, 0));

        // ── TABLA ──
        String[] columnas = {"ID Cliente", "ID Usuario", "Membresía", "Teléfono", "Inicio Membresía", "Fin Membresía"};
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
        txtIdUsuario = new JTextField();
        txtIdUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtIdUsuario.setBackground(new Color(40, 40, 40));
        txtIdUsuario.setForeground(Color.WHITE);
        txtIdUsuario.setCaretColor(Color.WHITE);
        txtIdUsuario.setBorder(BorderFactory.createCompoundBorder(
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
        JLabel lblIdUsuario = new JLabel("ID Usuario");
        lblIdUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblIdUsuario.setForeground(new Color(160, 160, 160));

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
            new EmptyBorder(20, 20, 20, 20)));

        GridBagConstraints g1 = new GridBagConstraints();
        g1.insets = new Insets(8, 8, 8, 8);
        g1.fill = GridBagConstraints.HORIZONTAL;
        g1.gridx = 0; g1.gridy = 0; g1.weightx = 0.3;
        panelForm.add(lblIdUsuario, g1);

        GridBagConstraints g2 = new GridBagConstraints();
        g2.insets = new Insets(8, 8, 8, 8);
        g2.fill = GridBagConstraints.HORIZONTAL;
        g2.gridx = 1; g2.gridy = 0; g2.weightx = 0.7;
        panelForm.add(txtIdUsuario, g2);

        GridBagConstraints g3 = new GridBagConstraints();
        g3.insets = new Insets(8, 8, 8, 8);
        g3.fill = GridBagConstraints.HORIZONTAL;
        g3.gridx = 2; g3.gridy = 0; g3.weightx = 0.3;
        panelForm.add(lblTelefono, g3);

        GridBagConstraints g4 = new GridBagConstraints();
        g4.insets = new Insets(8, 8, 8, 8);
        g4.fill = GridBagConstraints.HORIZONTAL;
        g4.gridx = 3; g4.gridy = 0; g4.weightx = 0.7;
        panelForm.add(txtTelefono, g4);

        GridBagConstraints g5 = new GridBagConstraints();
        g5.insets = new Insets(8, 8, 8, 8);
        g5.fill = GridBagConstraints.HORIZONTAL;
        g5.gridx = 0; g5.gridy = 1; g5.weightx = 0.3;
        panelForm.add(lblMembresia, g5);

        GridBagConstraints g6 = new GridBagConstraints();
        g6.insets = new Insets(8, 8, 8, 8);
        g6.fill = GridBagConstraints.HORIZONTAL;
        g6.gridx = 1; g6.gridy = 1; g6.weightx = 0.7;
        panelForm.add(cmbMembresia, g6);

        GridBagConstraints g7 = new GridBagConstraints();
        g7.insets = new Insets(8, 8, 8, 8);
        g7.fill = GridBagConstraints.HORIZONTAL;
        g7.gridx = 2; g7.gridy = 1; g7.weightx = 0.3;
        panelForm.add(lblFechaInicio, g7);

        GridBagConstraints g8 = new GridBagConstraints();
        g8.insets = new Insets(8, 8, 8, 8);
        g8.fill = GridBagConstraints.HORIZONTAL;
        g8.gridx = 3; g8.gridy = 1; g8.weightx = 0.7;
        panelForm.add(txtFechaInicio, g8);

        GridBagConstraints g9 = new GridBagConstraints();
        g9.insets = new Insets(8, 8, 8, 8);
        g9.fill = GridBagConstraints.HORIZONTAL;
        g9.gridx = 0; g9.gridy = 2; g9.weightx = 0.3;
        panelForm.add(lblFechaFin, g9);

        GridBagConstraints g10 = new GridBagConstraints();
        g10.insets = new Insets(8, 8, 8, 8);
        g10.fill = GridBagConstraints.HORIZONTAL;
        g10.gridx = 1; g10.gridy = 2; g10.weightx = 0.7;
        panelForm.add(txtFechaFin, g10);

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
    public JTable getTabla()                   { return tabla; }
    public DefaultTableModel getModeloTabla()  { return modeloTabla; }
    public String getIdUsuario()               { return txtIdUsuario.getText().trim(); }
    public String getTelefono()                { return txtTelefono.getText().trim(); }
    public JComboBox<String> getCmbMembresia() { return cmbMembresia; }
    public String getFechaInicio()             { return txtFechaInicio.getText().trim(); }
    public String getFechaFin()                { return txtFechaFin.getText().trim(); }
    public JButton getBtnAgregar()             { return btnAgregar; }
    public JButton getBtnActualizar()          { return btnActualizar; }
    public JButton getBtnEliminar()            { return btnEliminar; }
    public JButton getBtnLimpiar()             { return btnLimpiar; }

    public void limpiarFormulario() {
        txtIdUsuario.setText("");
        txtTelefono.setText("");
        cmbMembresia.setSelectedIndex(0);
        txtFechaInicio.setText("");
        txtFechaFin.setText("");
    }

    public void llenarFormulario(String idUsuario, String telefono, String fechaInicio, String fechaFin) {
        txtIdUsuario.setText(idUsuario);
        txtTelefono.setText(telefono);
        txtFechaInicio.setText(fechaInicio);
        txtFechaFin.setText(fechaFin);
    }
}