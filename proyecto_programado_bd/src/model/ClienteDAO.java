package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public boolean insertar(Cliente cliente) {
        String sql = "INSERT INTO Cliente (id_usuario, id_membresia, telefono, fecha_inicio_membresia, fecha_fin_membresia) VALUES (?, ?, ?, ?, ?)";
        try (Connection cx = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cx.prepareStatement(sql)) {
            ps.setInt(1, cliente.getIdUsuario());
            ps.setInt(2, cliente.getIdMembresia());
            ps.setString(3, cliente.getTelefono());
            ps.setDate(4, java.sql.Date.valueOf(cliente.getFechaInicioMembresia()));
            ps.setDate(5, java.sql.Date.valueOf(cliente.getFechaFinMembresia()));
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";
        try (Connection cx = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cx.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setIdUsuario(rs.getInt("id_usuario"));
                cliente.setIdMembresia(rs.getInt("id_membresia"));
                cliente.setTelefono(rs.getString("telefono"));
                if (rs.getDate("fecha_inicio_membresia") != null)
                    cliente.setFechaInicioMembresia(rs.getDate("fecha_inicio_membresia").toLocalDate());
                if (rs.getDate("fecha_fin_membresia") != null)
                    cliente.setFechaFinMembresia(rs.getDate("fecha_fin_membresia").toLocalDate());
                lista.add(cliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Cliente buscarPorId(int id) {
        String sql = "SELECT * FROM Cliente WHERE id_cliente = ?";
        try (Connection cx = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cx.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(rs.getInt("id_cliente"));
                    cliente.setIdUsuario(rs.getInt("id_usuario"));
                    cliente.setIdMembresia(rs.getInt("id_membresia"));
                    cliente.setTelefono(rs.getString("telefono"));
                    if (rs.getDate("fecha_inicio_membresia") != null)
                        cliente.setFechaInicioMembresia(rs.getDate("fecha_inicio_membresia").toLocalDate());
                    if (rs.getDate("fecha_fin_membresia") != null)
                        cliente.setFechaFinMembresia(rs.getDate("fecha_fin_membresia").toLocalDate());
                    return cliente;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Cliente buscarPorUsuario(int idUsuario) {
        String sql = "SELECT * FROM Cliente WHERE id_usuario = ?";
        try (Connection cx = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cx.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(rs.getInt("id_cliente"));
                    cliente.setIdUsuario(rs.getInt("id_usuario"));
                    cliente.setIdMembresia(rs.getInt("id_membresia"));
                    cliente.setTelefono(rs.getString("telefono"));
                    if (rs.getDate("fecha_inicio_membresia") != null)
                        cliente.setFechaInicioMembresia(rs.getDate("fecha_inicio_membresia").toLocalDate());
                    if (rs.getDate("fecha_fin_membresia") != null)
                        cliente.setFechaFinMembresia(rs.getDate("fecha_fin_membresia").toLocalDate());
                    return cliente;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean actualizar(Cliente cliente) {
        String sql = "UPDATE Cliente SET id_membresia=?, telefono=?, fecha_inicio_membresia=?, fecha_fin_membresia=? WHERE id_cliente=?";
        try (Connection cx = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cx.prepareStatement(sql)) {
            ps.setInt(1, cliente.getIdMembresia());
            ps.setString(2, cliente.getTelefono());
            ps.setDate(3, java.sql.Date.valueOf(cliente.getFechaInicioMembresia()));
            ps.setDate(4, java.sql.Date.valueOf(cliente.getFechaFinMembresia()));
            ps.setInt(5, cliente.getIdCliente());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM Cliente WHERE id_cliente = ?";
        try (Connection cx = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cx.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}