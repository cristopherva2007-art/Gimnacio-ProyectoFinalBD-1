package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GestionMembresiasView extends JFrame {

    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JTextField txtNombre;
    private JTextField txtDuracionDias;
    private JTextField txtPrecio;
    private JButton btnAgregar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnLimpiar;

    public GestionMembresiasView() {
        setTitle("Pintuya Gym — Gestión de Membresías");
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
        JLabel lblTitulo = new JLabel("Gestión de Membresías");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(255, 165, 0));
        lblTitulo.setBorder(new EmptyBorder(0, 0, 14, 0));

        // ── TABLA ──
        String[] columnas = {"ID", "Nombre", "Duración (días)", "Precio"};
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

        txtDuracionDias = new JTextField();
        txtDuracionDias.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtDuracionDias.setBackground(new Color(40, 40, 40));
        txtDuracionDias.setForeground(Color.WHITE);
        txtDuracionDias.setCaretColor(Color.WHITE);
        txtDuracionDias.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 70, 70)),
            new EmptyBorder(10, 12, 10, 12)));

        txtPrecio = new JTextField();
        txtPrecio.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtPrecio.setBackground(new Color(40, 40, 40));
        txtPrecio.setForeground(Color.WHITE);
        txtPrecio.setCaretColor(Color.WHITE);
        txtPrecio.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 70, 70)),
            new EmptyBorder(10, 12, 10, 12)));

        // ── LABELS ──
        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblNombre.setForeground(new Color(160, 160, 160));

        JLabel lblDuracionDias = new JLabel("Duración (días)");
        lblDuracionDias.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblDuracionDias.setForeground(new Color(160, 160, 160));

        JLabel lblPrecio = new JLabel("Precio");
        lblPrecio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblPrecio.setForeground(new Color(160, 160, 160));

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
        panelForm.add(lblDuracionDias, g3);

        GridBagConstraints g4 = new GridBagConstraints();
        g4.insets = new Insets(8, 8, 8, 8);
        g4.fill = GridBagConstraints.HORIZONTAL;
        g4.gridx = 3; g4.gridy = 0; g4.weightx = 0.7;
        panelForm.add(txtDuracionDias, g4);

        GridBagConstraints g5 = new GridBagConstraints();
        g5.insets = new Insets(8, 8, 8, 8);
        g5.fill = GridBagConstraints.HORIZONTAL;
        g5.gridx = 0; g5.gridy = 1; g5.weightx = 0.3;
        panelForm.add(lblPrecio, g5);

        GridBagConstraints g6 = new GridBagConstraints();
        g6.insets = new Insets(8, 8, 8, 8);
        g6.fill = GridBagConstraints.HORIZONTAL;
        g6.gridx = 1; g6.gridy = 1; g6.weightx = 0.7;
        panelForm.add(txtPrecio, g6);

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
    public String getDuracionDias()           { return txtDuracionDias.getText().trim(); }
    public String getPrecio()                 { return txtPrecio.getText().trim(); }
    public JButton getBtnAgregar()            { return btnAgregar; }
    public JButton getBtnActualizar()         { return btnActualizar; }
    public JButton getBtnEliminar()           { return btnEliminar; }
    public JButton getBtnLimpiar()            { return btnLimpiar; }

    public void limpiarFormulario() {
        txtNombre.setText("");
        txtDuracionDias.setText("");
        txtPrecio.setText("");
    }

    public void llenarFormulario(String nombre, String duracionDias, String precio) {
        txtNombre.setText(nombre);
        txtDuracionDias.setText(duracionDias);
        txtPrecio.setText(precio);
    }
}