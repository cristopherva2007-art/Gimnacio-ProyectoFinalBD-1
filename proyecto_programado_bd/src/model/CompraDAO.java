package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CompraDAO {

    public boolean insertar(Compra compra) {
        String sql = "INSERT INTO Compra (id_cliente, id_producto, cantidad, precio_unitario, total) VALUES (?, ?, ?, ?, ?)";
        try (Connection cx = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cx.prepareStatement(sql)) {
            ps.setInt(1, compra.getIdCliente());
            ps.setInt(2, compra.getIdProducto());
            ps.setInt(3, compra.getCantidad());
            ps.setDouble(4, compra.getPrecioUnitario());
            ps.setDouble(5, compra.getTotal());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Compra> listar() {
        List<Compra> lista = new ArrayList<>();
        String sql = "SELECT * FROM Compra";
        try (Connection cx = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cx.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Compra compra = new Compra();
                compra.setIdCompra(rs.getInt("id_compra"));
                compra.setIdCliente(rs.getInt("id_cliente"));
                compra.setIdProducto(rs.getInt("id_producto"));
                compra.setCantidad(rs.getInt("cantidad"));
                compra.setPrecioUnitario(rs.getDouble("precio_unitario"));
                compra.setTotal(rs.getDouble("total"));
                compra.setFechaCompra(rs.getTimestamp("fecha_compra").toLocalDateTime());
                lista.add(compra);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Compra> listarPorCliente(int idCliente) {
        List<Compra> lista = new ArrayList<>();
        String sql = "SELECT * FROM Compra WHERE id_cliente = ?";
        try (Connection cx = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cx.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Compra compra = new Compra();
                    compra.setIdCompra(rs.getInt("id_compra"));
                    compra.setIdCliente(rs.getInt("id_cliente"));
                    compra.setIdProducto(rs.getInt("id_producto"));
                    compra.setCantidad(rs.getInt("cantidad"));
                    compra.setPrecioUnitario(rs.getDouble("precio_unitario"));
                    compra.setTotal(rs.getDouble("total"));
                    compra.setFechaCompra(rs.getTimestamp("fecha_compra").toLocalDateTime());
                    lista.add(compra);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Compra buscarPorId(int id) {
        String sql = "SELECT * FROM Compra WHERE id_compra = ?";
        try (Connection cx = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cx.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Compra compra = new Compra();
                    compra.setIdCompra(rs.getInt("id_compra"));
                    compra.setIdCliente(rs.getInt("id_cliente"));
                    compra.setIdProducto(rs.getInt("id_producto"));
                    compra.setCantidad(rs.getInt("cantidad"));
                    compra.setPrecioUnitario(rs.getDouble("precio_unitario"));
                    compra.setTotal(rs.getDouble("total"));
                    compra.setFechaCompra(rs.getTimestamp("fecha_compra").toLocalDateTime());
                    return compra;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM Compra WHERE id_compra = ?";
        try (Connection cx = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cx.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean eliminarPorCliente(int idCliente) {
        String sql = "DELETE FROM Compra WHERE id_cliente = ?";
        try (Connection cx = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cx.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean actualizarCompra(Compra compra) {

        String sql =
            "UPDATE Compra " +
            "SET cantidad = ?, " +
            "precio_unitario = ?, " +
            "total = ? " +
            "WHERE id_compra = ?";

        try ( Connection con = ConexionBD.getInstancia().getConexion();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, compra.getCantidad());
            ps.setDouble(2, compra.getPrecioUnitario());
            ps.setDouble(3,compra.getCantidad() * compra.getPrecioUnitario()
            );

            ps.setInt(4, compra.getIdCompra());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }
}