package view;

import model.ConsultasDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.*;

public class ConsultasView extends JFrame {

    private static final Color BG_DARK    = new Color(18, 18, 18);
    private static final Color BG_CARD    = new Color(28, 28, 28);
    private static final Color NARANJA    = new Color(255, 165, 0);
    private static final Color GRIS_TEXT  = new Color(200, 200, 200);
    private static final Color GRIS_MUTE  = new Color(120, 120, 120);

    private final ConsultasDAO dao = new ConsultasDAO();
    private JTable tabla;
    private JLabel lblTitulo;
    private JLabel lblDesc;

    private static final String[] TITULOS = {
        "Top 5 clientes que más han gastado",
        "Ventas totales por categoría de producto",
        "Productos más vendidos y alerta de stock",
        "Resumen de membresías",
        "Actividad general por cliente"
    };

    private static final String[] DESCS = {
        "JOIN · GROUP BY · ORDER BY · LIMIT",
        "JOIN · GROUP BY · funciones de agregación",
        "JOIN · GROUP BY · HAVING · CASE",
        "LEFT JOIN · SUM condicional · DATEDIFF",
        "Múltiples JOINs · subconsulta · GROUP BY"
    };

    public ConsultasView() {
        setTitle("Consultas SQL — Panel Administrador");
        setSize(1100, 680);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(BG_DARK);

        // ── PANEL SUPERIOR (botones) ──
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(BG_CARD);
        panelBotones.setBorder(new EmptyBorder(18, 24, 18, 24));
        panelBotones.setLayout(new GridLayout(1, 5, 12, 0));

        for (int i = 1; i <= 5; i++) {
            final int num = i;
            JButton btn = crearBotonConsulta("Consulta " + i, TITULOS[i - 1]);
            btn.addActionListener(e -> ejecutarConsulta(num));
            panelBotones.add(btn);
        }

        // ── PANEL CENTRAL (título + tabla) ──
        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setBackground(BG_DARK);
        panelCentro.setBorder(new EmptyBorder(24, 24, 24, 24));

        // Header de resultado
        JPanel panelHeader = new JPanel(new BorderLayout());
        panelHeader.setBackground(BG_DARK);
        panelHeader.setBorder(new EmptyBorder(0, 0, 16, 0));

        lblTitulo = new JLabel("Seleccioná una consulta");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(Color.WHITE);

        lblDesc = new JLabel(" ");
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblDesc.setForeground(GRIS_MUTE);

        panelHeader.add(lblTitulo, BorderLayout.NORTH);
        panelHeader.add(lblDesc,   BorderLayout.SOUTH);

        // Tabla
        tabla = new JTable();
        tabla.setBackground(BG_CARD);
        tabla.setForeground(GRIS_TEXT);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabla.setRowHeight(34);
        tabla.setShowGrid(false);
        tabla.setIntercellSpacing(new Dimension(0, 0));
        tabla.setSelectionBackground(new Color(50, 50, 50));
        tabla.setSelectionForeground(NARANJA);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Header de la tabla
        JTableHeader header = tabla.getTableHeader();
        header.setBackground(new Color(38, 38, 38));
        header.setForeground(NARANJA);
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setReorderingAllowed(false);
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(50, 50, 50)));

        // Filas alternadas
        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object val,
                    boolean sel, boolean foc, int row, int col) {
                super.getTableCellRendererComponent(t, val, sel, foc, row, col);
                if (!sel) {
                    setBackground(row % 2 == 0 ? BG_CARD : new Color(33, 33, 33));
                    setForeground(GRIS_TEXT);
                }
                setBorder(new EmptyBorder(0, 12, 0, 12));
                return this;
            }
        });

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBackground(BG_DARK);
        scroll.getViewport().setBackground(BG_CARD);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(45, 45, 45)));

        panelCentro.add(panelHeader, BorderLayout.NORTH);
        panelCentro.add(scroll,      BorderLayout.CENTER);

        root.add(panelBotones, BorderLayout.NORTH);
        root.add(panelCentro,  BorderLayout.CENTER);

        setContentPane(root);
    }

    private JButton crearBotonConsulta(String titulo, String desc) {
        JButton btn = new JButton("<html><center><b>" + titulo + "</b><br>"
                + "<span style='font-size:10px; color:#888'>" + recortarDesc(desc) + "</span></center></html>");
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setForeground(new Color(200, 200, 200));
        btn.setBackground(new Color(38, 38, 38));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(55, 55, 55)),
            new EmptyBorder(10, 8, 10, 8)
        ));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(new Color(55, 55, 55));
                btn.setForeground(NARANJA);
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(new Color(38, 38, 38));
                btn.setForeground(new Color(200, 200, 200));
            }
        });
        return btn;
    }

    private String recortarDesc(String desc) {
        return desc.length() > 30 ? desc.substring(0, 28) + "…" : desc;
    }

    private void ejecutarConsulta(int numero) {
        lblTitulo.setText(TITULOS[numero - 1]);
        lblDesc.setText(DESCS[numero - 1]);

        DefaultTableModel modelo = switch (numero) {
            case 1 -> dao.consulta1();
            case 2 -> dao.consulta2();
            case 3 -> dao.consulta3();
            case 4 -> dao.consulta4();
            case 5 -> dao.consulta5();
            default -> new DefaultTableModel();
        };

        tabla.setModel(modelo);
    }
}
