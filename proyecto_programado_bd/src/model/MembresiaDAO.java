package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MembresiaDAO {

    public boolean insertar(Membresia membresia) {
        String sql = "INSERT INTO Membresia (nombre, duracion_dias, precio) VALUES (?, ?, ?)";
        try (Connection cx = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cx.prepareStatement(sql)) {
            ps.setString(1, membresia.getNombre());
            ps.setInt(2, membresia.getDuracionDias());
            ps.setDouble(3, membresia.getPrecio());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Membresia> listar() {
        List<Membresia> lista = new ArrayList<>();
        String sql = "SELECT * FROM Membresia";
        try (Connection cx = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cx.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Membresia membresia = new Membresia();
                membresia.setIdMembresia(rs.getInt("id_membresia"));
                membresia.setNombre(rs.getString("nombre"));
                membresia.setDuracionDias(rs.getInt("duracion_dias"));
                membresia.setPrecio(rs.getDouble("precio"));
                lista.add(membresia);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Membresia buscarPorId(int id) {
        String sql = "SELECT * FROM Membresia WHERE id_membresia = ?";
        try (Connection cx = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cx.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Membresia membresia = new Membresia();
                    membresia.setIdMembresia(rs.getInt("id_membresia"));
                    membresia.setNombre(rs.getString("nombre"));
                    membresia.setDuracionDias(rs.getInt("duracion_dias"));
                    membresia.setPrecio(rs.getDouble("precio"));
                    return membresia;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean actualizar(Membresia membresia) {
        String sql = "UPDATE Membresia SET nombre=?, duracion_dias=?, precio=? WHERE id_membresia=?";
        try (Connection cx = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cx.prepareStatement(sql)) {
            ps.setString(1, membresia.getNombre());
            ps.setInt(2, membresia.getDuracionDias());
            ps.setDouble(3, membresia.getPrecio());
            ps.setInt(4, membresia.getIdMembresia());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM Membresia WHERE id_membresia = ?";
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