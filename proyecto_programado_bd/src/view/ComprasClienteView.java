package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ComprasClienteView extends JFrame {

    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JLabel lblTotalValor;

    public ComprasClienteView() {
        setTitle("Pintuya Gym — Mis Compras");
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
        JLabel lblTitulo = new JLabel("Mis Compras");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(255, 165, 0));
        lblTitulo.setBorder(new EmptyBorder(0, 0, 14, 0));

        // ── TABLA ──
        String[] columnas = {"ID Compra", "ID Producto", "Cantidad", "Precio Unitario", "Total", "Fecha"};
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

        // ── PANEL TOTAL ──
        JPanel panelTotal = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 12));
        panelTotal.setBackground(new Color(28, 28, 28));
        panelTotal.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(45, 45, 45)),
            new EmptyBorder(8, 16, 8, 16)));

        JLabel lblTotalTexto = new JLabel("Total gastado:");
        lblTotalTexto.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblTotalTexto.setForeground(new Color(160, 160, 160));

        lblTotalValor = new JLabel("₡0.00");
        lblTotalValor.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTotalValor.setForeground(new Color(255, 165, 0));

        panelTotal.add(lblTotalTexto);
        panelTotal.add(lblTotalValor);

        // ── ENSAMBLADO ──
        panelPrincipal.add(lblTitulo,  BorderLayout.NORTH);
        panelPrincipal.add(scroll,     BorderLayout.CENTER);
        panelPrincipal.add(panelTotal, BorderLayout.SOUTH);

        setContentPane(panelPrincipal);
    }

    // ── GETTERS ──
    public JTable getTabla()                  { return tabla; }
    public DefaultTableModel getModeloTabla() { return modeloTabla; }

    public void setTotalGastado(double total) {
        lblTotalValor.setText(String.format("₡%.2f", total));
    }
}