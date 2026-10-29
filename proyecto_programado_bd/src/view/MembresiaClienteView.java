package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MembresiaClienteView extends JFrame {

    private JLabel lblNombreMembresia;
    private JLabel lblDuracion;
    private JLabel lblPrecio;
    private JLabel lblFechaInicio;
    private JLabel lblFechaFin;
    private JLabel lblEstado;

    public MembresiaClienteView() {
        setTitle("Pintuya Gym — Mi Membresía");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);
        initComponents();
    }

    private void initComponents() {

        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBackground(new Color(18, 18, 18));

        JPanel panelContenido = new JPanel(new BorderLayout(0, 24));
        panelContenido.setBackground(new Color(18, 18, 18));
        panelContenido.setPreferredSize(new Dimension(600, 500));

        // ── TÍTULO ──
        JLabel lblTitulo = new JLabel("Mi Membresía");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(255, 165, 0));
        lblTitulo.setBorder(new EmptyBorder(0, 0, 10, 0));

        // ── TARJETA ──
        JPanel tarjeta = new JPanel();
        tarjeta.setBackground(new Color(28, 28, 28));
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 165, 0, 80)),
            new EmptyBorder(30, 30, 30, 30)));

        JLabel lblIcono = new JLabel("🪪");
        lblIcono.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 52));
        lblIcono.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblNombreMembresia = new JLabel("—");
        lblNombreMembresia.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblNombreMembresia.setForeground(Color.WHITE);
        lblNombreMembresia.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblEstado = new JLabel("—");
        lblEstado.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblEstado.setForeground(new Color(80, 200, 120));
        lblEstado.setAlignmentX(Component.LEFT_ALIGNMENT);

        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(50, 50, 50));
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));

        lblDuracion    = crearLabelInfo("Duración: —");
        lblPrecio      = crearLabelInfo("Precio: —");
        lblFechaInicio = crearLabelInfo("Inicio: —");
        lblFechaFin    = crearLabelInfo("Vence: —");

        tarjeta.add(lblIcono);
        tarjeta.add(Box.createVerticalStrut(12));
        tarjeta.add(lblNombreMembresia);
        tarjeta.add(Box.createVerticalStrut(6));
        tarjeta.add(lblEstado);
        tarjeta.add(Box.createVerticalStrut(20));
        tarjeta.add(sep);
        tarjeta.add(Box.createVerticalStrut(20));
        tarjeta.add(lblDuracion);
        tarjeta.add(Box.createVerticalStrut(12));
        tarjeta.add(lblPrecio);
        tarjeta.add(Box.createVerticalStrut(12));
        tarjeta.add(lblFechaInicio);
        tarjeta.add(Box.createVerticalStrut(12));
        tarjeta.add(lblFechaFin);

        panelContenido.add(lblTitulo, BorderLayout.NORTH);
        panelContenido.add(tarjeta,   BorderLayout.CENTER);

        panelPrincipal.add(panelContenido);
        setContentPane(panelPrincipal);
    }

    private JLabel crearLabelInfo(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        lbl.setForeground(new Color(180, 180, 180));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
    }

    // ── SETTERS ──
    public void setNombreMembresia(String nombre) {
        lblNombreMembresia.setText(nombre);
    }

    public void setDuracion(int dias) {
        lblDuracion.setText("Duración: " + dias + " días");
    }

    public void setPrecio(double precio) {
        lblPrecio.setText("Precio: ₡" + String.format("%.2f", precio));
    }

    public void setFechaInicio(String fecha) {
        lblFechaInicio.setText("Inicio: " + fecha);
    }

    public void setFechaFin(String fecha) {
        lblFechaFin.setText("Vence: " + fecha);
    }

    public void setEstado(boolean activa) {
        if (activa) {
            lblEstado.setText("● Activa");
            lblEstado.setForeground(new Color(80, 200, 120));
        } else {
            lblEstado.setText("● Vencida");
            lblEstado.setForeground(new Color(220, 80, 80));
        }
    }

    public void sinMembresia() {
        lblNombreMembresia.setText("Sin membresía");
        lblEstado.setText("● No activa");
        lblEstado.setForeground(new Color(220, 80, 80));
        lblDuracion.setText("Duración: —");
        lblPrecio.setText("Precio: —");
        lblFechaInicio.setText("Inicio: —");
        lblFechaFin.setText("Vence: —");
    }
}