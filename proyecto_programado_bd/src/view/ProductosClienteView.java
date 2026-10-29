package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ProductosClienteView extends JFrame {

    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JTextField txtCantidad;
    private JButton btnComprar;

    public ProductosClienteView() {
        setTitle("Pintuya Gym — Productos");
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
        JLabel lblTitulo = new JLabel("Productos Disponibles");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(255, 165, 0));
        lblTitulo.setBorder(new EmptyBorder(0, 0, 14, 0));

        // ── TABLA ──
        String[] columnas = {"ID", "Nombre", "Descripción", "Precio", "Stock", "Categoría"};
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

        // ── PANEL COMPRA ──
        JPanel panelCompra = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 12));
        panelCompra.setBackground(new Color(28, 28, 28));
        panelCompra.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(45, 45, 45)),
            new EmptyBorder(8, 16, 8, 16)));

        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblCantidad.setForeground(new Color(160, 160, 160));

        txtCantidad = new JTextField("1");
        txtCantidad.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtCantidad.setBackground(new Color(40, 40, 40));
        txtCantidad.setForeground(Color.WHITE);
        txtCantidad.setCaretColor(Color.WHITE);
        txtCantidad.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 70, 70)),
            new EmptyBorder(8, 12, 8, 12)));
        txtCantidad.setPreferredSize(new Dimension(80, 44));

        btnComprar = new JButton("Comprar");
        btnComprar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnComprar.setBackground(new Color(255, 165, 0));
        btnComprar.setForeground(new Color(18, 18, 18));
        btnComprar.setFocusPainted(false);
        btnComprar.setBorderPainted(false);
        btnComprar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnComprar.setPreferredSize(new Dimension(130, 44));

        panelCompra.add(lblCantidad);
        panelCompra.add(txtCantidad);
        panelCompra.add(btnComprar);

        // ── ENSAMBLADO ──
        panelPrincipal.add(lblTitulo,   BorderLayout.NORTH);
        panelPrincipal.add(scroll,      BorderLayout.CENTER);
        panelPrincipal.add(panelCompra, BorderLayout.SOUTH);

        setContentPane(panelPrincipal);
    }

    // ── GETTERS ──
    public JTable getTabla()                  { return tabla; }
    public DefaultTableModel getModeloTabla() { return modeloTabla; }
    public String getCantidad()               { return txtCantidad.getText().trim(); }
    public JButton getBtnComprar()            { return btnComprar; }
}