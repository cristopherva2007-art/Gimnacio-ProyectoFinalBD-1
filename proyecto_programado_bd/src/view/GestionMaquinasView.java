package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;

public class GestionMaquinasView extends JFrame {

    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JComboBox<String> cmbEstado;
    private JTextField txtRutaImagen;
    private JButton btnSeleccionarImagen;
    private JLabel lblPreviewImagen;
    private JButton btnAgregar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnLimpiar;

    public GestionMaquinasView() {
        setTitle("Pintuya Gym — Gestión de Máquinas");
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
        JLabel lblTitulo = new JLabel("Gestión de Máquinas");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(255, 165, 0));
        lblTitulo.setBorder(new EmptyBorder(0, 0, 14, 0));

        // ── TABLA ──
        String[] columnas = {"ID", "Nombre", "Descripción", "Estado", "Imagen"};
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

        txtDescripcion = new JTextField();
        txtDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtDescripcion.setBackground(new Color(40, 40, 40));
        txtDescripcion.setForeground(Color.WHITE);
        txtDescripcion.setCaretColor(Color.WHITE);
        txtDescripcion.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 70, 70)),
            new EmptyBorder(10, 12, 10, 12)));

        cmbEstado = new JComboBox<>(new String[]{"disponible", "mantenimiento", "fuera_de_servicio"});
        cmbEstado.setBackground(new Color(40, 40, 40));
        cmbEstado.setForeground(Color.WHITE);
        cmbEstado.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        txtRutaImagen = new JTextField();
        txtRutaImagen.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtRutaImagen.setBackground(new Color(40, 40, 40));
        txtRutaImagen.setForeground(Color.WHITE);
        txtRutaImagen.setCaretColor(Color.WHITE);
        txtRutaImagen.setEditable(false);
        txtRutaImagen.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 70, 70)),
            new EmptyBorder(10, 12, 10, 12)));

        btnSeleccionarImagen = new JButton("📁");
        btnSeleccionarImagen.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
        btnSeleccionarImagen.setBackground(new Color(60, 60, 60));
        btnSeleccionarImagen.setForeground(Color.WHITE);
        btnSeleccionarImagen.setFocusPainted(false);
        btnSeleccionarImagen.setBorderPainted(false);
        btnSeleccionarImagen.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSeleccionarImagen.setPreferredSize(new Dimension(50, 44));
        btnSeleccionarImagen.addActionListener(e -> seleccionarImagen());

        // ── PREVIEW IMAGEN ──
        lblPreviewImagen = new JLabel("Sin imagen", SwingConstants.CENTER);
        lblPreviewImagen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblPreviewImagen.setForeground(new Color(120, 120, 120));
        lblPreviewImagen.setBackground(new Color(28, 28, 28));
        lblPreviewImagen.setOpaque(true);
        lblPreviewImagen.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70)));
        lblPreviewImagen.setPreferredSize(new Dimension(220, 220));

        // ── LABELS ──
        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblNombre.setForeground(new Color(160, 160, 160));

        JLabel lblDescripcion = new JLabel("Descripción");
        lblDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblDescripcion.setForeground(new Color(160, 160, 160));

        JLabel lblEstado = new JLabel("Estado");
        lblEstado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblEstado.setForeground(new Color(160, 160, 160));

        JLabel lblRutaImagen = new JLabel("Imagen");
        lblRutaImagen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblRutaImagen.setForeground(new Color(160, 160, 160));

        // ── PANEL RUTA + BOTÓN ──
        JPanel panelRuta = new JPanel(new BorderLayout(4, 0));
        panelRuta.setBackground(new Color(28, 28, 28));
        panelRuta.add(txtRutaImagen,        BorderLayout.CENTER);
        panelRuta.add(btnSeleccionarImagen, BorderLayout.EAST);

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
        panelForm.add(lblDescripcion, g3);

        GridBagConstraints g4 = new GridBagConstraints();
        g4.insets = new Insets(8, 8, 8, 8);
        g4.fill = GridBagConstraints.HORIZONTAL;
        g4.gridx = 3; g4.gridy = 0; g4.weightx = 0.7;
        panelForm.add(txtDescripcion, g4);

        GridBagConstraints g5 = new GridBagConstraints();
        g5.insets = new Insets(8, 8, 8, 8);
        g5.fill = GridBagConstraints.HORIZONTAL;
        g5.gridx = 0; g5.gridy = 1; g5.weightx = 0.3;
        panelForm.add(lblEstado, g5);

        GridBagConstraints g6 = new GridBagConstraints();
        g6.insets = new Insets(8, 8, 8, 8);
        g6.fill = GridBagConstraints.HORIZONTAL;
        g6.gridx = 1; g6.gridy = 1; g6.weightx = 0.7;
        panelForm.add(cmbEstado, g6);

        GridBagConstraints g7 = new GridBagConstraints();
        g7.insets = new Insets(8, 8, 8, 8);
        g7.fill = GridBagConstraints.HORIZONTAL;
        g7.gridx = 2; g7.gridy = 1; g7.weightx = 0.3;
        panelForm.add(lblRutaImagen, g7);

        GridBagConstraints g8 = new GridBagConstraints();
        g8.insets = new Insets(8, 8, 8, 8);
        g8.fill = GridBagConstraints.HORIZONTAL;
        g8.gridx = 3; g8.gridy = 1; g8.weightx = 0.7;
        panelForm.add(panelRuta, g8);

        // ── PREVIEW AL LADO ──
        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.setBackground(new Color(18, 18, 18));
        panelDerecho.setBorder(new EmptyBorder(0, 14, 0, 0));
        panelDerecho.add(lblPreviewImagen, BorderLayout.CENTER);

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

        JPanel panelSurContenido = new JPanel(new BorderLayout(0, 0));
        panelSurContenido.setBackground(new Color(18, 18, 18));
        panelSurContenido.add(panelForm,    BorderLayout.CENTER);
        panelSurContenido.add(panelDerecho, BorderLayout.EAST);

        JPanel panelSur = new JPanel(new BorderLayout(0, 14));
        panelSur.setBackground(new Color(18, 18, 18));
        panelSur.add(panelSurContenido, BorderLayout.CENTER);
        panelSur.add(panelBotones,      BorderLayout.SOUTH);

        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        panelPrincipal.add(scroll,    BorderLayout.CENTER);
        panelPrincipal.add(panelSur,  BorderLayout.SOUTH);

        setContentPane(panelPrincipal);
    }

    private void seleccionarImagen() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar imagen");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
            "Imágenes (jpg, png, gif)", "jpg", "jpeg", "png", "gif"));

        int resultado = fileChooser.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            String ruta = fileChooser.getSelectedFile().getAbsolutePath();
            txtRutaImagen.setText(ruta);
            mostrarPreview(ruta);
        }
    }

    public void mostrarPreview(String ruta) {
        if (ruta == null || ruta.isEmpty()) {
            lblPreviewImagen.setIcon(null);
            lblPreviewImagen.setText("Sin imagen");
            return;
        }
        File archivo = new File(ruta);
        if (!archivo.exists()) {
            lblPreviewImagen.setIcon(null);
            lblPreviewImagen.setText("Sin imagen");
            return;
        }
        ImageIcon icon = new ImageIcon(ruta);
        Image imagen   = icon.getImage().getScaledInstance(220, 220, Image.SCALE_SMOOTH);
        lblPreviewImagen.setIcon(new ImageIcon(imagen));
        lblPreviewImagen.setText("");
    }

    // ── GETTERS ──
    public JTable getTabla()                  { return tabla; }
    public DefaultTableModel getModeloTabla() { return modeloTabla; }
    public String getNombre()                 { return txtNombre.getText().trim(); }
    public String getDescripcion()            { return txtDescripcion.getText().trim(); }
    public String getEstado()                 { return (String) cmbEstado.getSelectedItem(); }
    public String getRutaImagen()             { return txtRutaImagen.getText().trim(); }
    public JButton getBtnAgregar()            { return btnAgregar; }
    public JButton getBtnActualizar()         { return btnActualizar; }
    public JButton getBtnEliminar()           { return btnEliminar; }
    public JButton getBtnLimpiar()            { return btnLimpiar; }

    public void limpiarFormulario() {
        txtNombre.setText("");
        txtDescripcion.setText("");
        cmbEstado.setSelectedIndex(0);
        txtRutaImagen.setText("");
        lblPreviewImagen.setIcon(null);
        lblPreviewImagen.setText("Sin imagen");
    }

    public void llenarFormulario(String nombre, String descripcion, String estado, String rutaImagen) {
        txtNombre.setText(nombre);
        txtDescripcion.setText(descripcion);
        cmbEstado.setSelectedItem(estado);
        txtRutaImagen.setText(rutaImagen);
        mostrarPreview(rutaImagen);
    }
}