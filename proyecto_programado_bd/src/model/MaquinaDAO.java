package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MaquinaDAO {

    public boolean insertar(Maquina maquina) {
        String sql = "INSERT INTO Maquina (nombre, descripcion, estado, ruta_imagen) VALUES (?, ?, ?, ?)";
        try (Connection cx = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cx.prepareStatement(sql)) {
            ps.setString(1, maquina.getNombre());
            ps.setString(2, maquina.getDescripcion());
            ps.setString(3, maquina.getEstado());
            ps.setString(4, maquina.getRutaImagen());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Maquina> listar() {
        List<Maquina> lista = new ArrayList<>();
        String sql = "SELECT * FROM Maquina";
        try (Connection cx = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cx.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Maquina maquina = new Maquina();
                maquina.setIdMaquina(rs.getInt("id_maquina"));
                maquina.setNombre(rs.getString("nombre"));
                maquina.setDescripcion(rs.getString("descripcion"));
                maquina.setEstado(rs.getString("estado"));
                maquina.setRutaImagen(rs.getString("ruta_imagen"));
                lista.add(maquina);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Maquina buscarPorId(int id) {
        String sql = "SELECT * FROM Maquina WHERE id_maquina = ?";
        try (Connection cx = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cx.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Maquina maquina = new Maquina();
                    maquina.setIdMaquina(rs.getInt("id_maquina"));
                    maquina.setNombre(rs.getString("nombre"));
                    maquina.setDescripcion(rs.getString("descripcion"));
                    maquina.setEstado(rs.getString("estado"));
                    maquina.setRutaImagen(rs.getString("ruta_imagen"));
                    return maquina;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean actualizar(Maquina maquina) {
        String sql = "UPDATE Maquina SET nombre=?, descripcion=?, estado=?, ruta_imagen=? WHERE id_maquina=?";
        try (Connection cx = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cx.prepareStatement(sql)) {
            ps.setString(1, maquina.getNombre());
            ps.setString(2, maquina.getDescripcion());
            ps.setString(3, maquina.getEstado());
            ps.setString(4, maquina.getRutaImagen());
            ps.setInt(5, maquina.getIdMaquina());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM Maquina WHERE id_maquina = ?";
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