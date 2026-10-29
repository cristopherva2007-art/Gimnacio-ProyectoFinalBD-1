package model;

import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class ConsultasDAO {

    public DefaultTableModel consulta1() {
        String sql = """
            SELECT
                CONCAT(u.nombre, ' ', u.apellidos) AS Cliente,
                m.nombre                           AS Membresia,
                COUNT(c.id_compra)                 AS Total_Compras,
                SUM(c.total)                       AS Total_Gastado,
                CASE
                    WHEN cl.fecha_fin_membresia >= CURDATE() THEN 'Activa'
                    ELSE 'Vencida'
                END AS Estado_Membresia
            FROM Compra c
            JOIN Cliente   cl ON c.id_cliente   = cl.id_cliente
            JOIN Usuario   u  ON cl.id_usuario  = u.id_usuario
            JOIN Membresia m  ON cl.id_membresia = m.id_membresia
            GROUP BY cl.id_cliente, u.nombre, u.apellidos, m.nombre, cl.fecha_fin_membresia
            ORDER BY Total_Gastado DESC
            LIMIT 5
            """;
        return ejecutar(sql);
    }

    public DefaultTableModel consulta2() {
        String sql = """
            SELECT
                p.categoria                    AS Categoria,
                COUNT(c.id_compra)             AS Veces_Vendido,
                SUM(c.cantidad)                AS Unidades_Vendidas,
                SUM(c.total)                   AS Ingreso_Total,
                ROUND(AVG(c.total), 2)         AS Promedio_Por_Venta,
                MAX(c.total)                   AS Venta_Maxima
            FROM Compra c
            JOIN Producto p ON c.id_producto = p.id_producto
            GROUP BY p.categoria
            ORDER BY Ingreso_Total DESC
            """;
        return ejecutar(sql);
    }

    public DefaultTableModel consulta3() {
        String sql = """
            SELECT
                p.nombre                AS Producto,
                p.categoria             AS Categoria,
                SUM(c.cantidad)         AS Unidades_Vendidas,
                p.stock                 AS Stock_Actual,
                SUM(c.total)            AS Ingresos_Generados,
                CASE
                    WHEN p.stock < 5  THEN 'CRITICO'
                    WHEN p.stock < 10 THEN 'BAJO'
                    ELSE 'NORMAL'
                END AS Alerta_Stock
            FROM Compra c
            JOIN Producto p ON c.id_producto = p.id_producto
            GROUP BY p.id_producto, p.nombre, p.categoria, p.stock
            ORDER BY Unidades_Vendidas DESC
            """;
        return ejecutar(sql);
    }

    public DefaultTableModel consulta4() {
        String sql = """
            SELECT
                m.nombre                                              AS Membresia,
                m.duracion_dias                                       AS Duracion_Dias,
                m.precio                                              AS Precio,
                COUNT(cl.id_cliente)                                  AS Total_Clientes,
                SUM(CASE WHEN cl.fecha_fin_membresia >= CURDATE() THEN 1 ELSE 0 END) AS Activas,
                SUM(CASE WHEN cl.fecha_fin_membresia <  CURDATE() THEN 1 ELSE 0 END) AS Vencidas,
                COUNT(cl.id_cliente) * m.precio                      AS Ingreso_Estimado,
                ROUND(AVG(DATEDIFF(cl.fecha_fin_membresia, cl.fecha_inicio_membresia)), 0) AS Promedio_Dias
            FROM Membresia m
            LEFT JOIN Cliente cl ON m.id_membresia = cl.id_membresia
            GROUP BY m.id_membresia, m.nombre, m.duracion_dias, m.precio
            ORDER BY Total_Clientes DESC
            """;
        return ejecutar(sql);
    }

    public DefaultTableModel consulta5() {
        String sql = """
            SELECT
                CONCAT(u.nombre, ' ', u.apellidos) AS Cliente,
                u.correo                            AS Correo,
                m.nombre                            AS Membresia,
                CASE
                    WHEN cl.fecha_fin_membresia >= CURDATE() THEN 'Activa'
                    ELSE 'Vencida'
                END                                 AS Estado,
                COUNT(c.id_compra)                  AS Total_Compras,
                COALESCE(SUM(c.total), 0)           AS Total_Gastado,
                (
                    SELECT p2.nombre
                    FROM Compra c2
                    JOIN Producto p2 ON c2.id_producto = p2.id_producto
                    WHERE c2.id_cliente = cl.id_cliente
                    ORDER BY c2.fecha_compra DESC
                    LIMIT 1
                )                                   AS Ultimo_Producto
            FROM Cliente cl
            JOIN Usuario   u  ON cl.id_usuario   = u.id_usuario
            JOIN Membresia m  ON cl.id_membresia = m.id_membresia
            LEFT JOIN Compra c ON cl.id_cliente  = c.id_cliente
            GROUP BY cl.id_cliente, u.nombre, u.apellidos, u.correo, m.nombre, cl.fecha_fin_membresia
            ORDER BY Total_Gastado DESC
            """;
        return ejecutar(sql);
    }

    private DefaultTableModel ejecutar(String sql) {
        DefaultTableModel modelo = new DefaultTableModel();
        try (Connection cx = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cx.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            ResultSetMetaData meta = rs.getMetaData();
            int cols = meta.getColumnCount();

            String[] columnas = new String[cols];
            for (int i = 1; i <= cols; i++) {
                columnas[i - 1] = meta.getColumnLabel(i).replace("_", " ");
            }
            modelo.setColumnIdentifiers(columnas);

            while (rs.next()) {
                Object[] fila = new Object[cols];
                for (int i = 1; i <= cols; i++) {
                    fila[i - 1] = rs.getObject(i);
                }
                modelo.addRow(fila);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelo;
    }
}
