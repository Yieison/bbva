package co.bbva.test.dao;

import co.empresa.bbva.modelo.Bill;
import co.empresa.bbva.modelo.Usuario;
import co.empresa.bbva.util.ConexionPostgreSQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDaoPostgreSQL implements UsuarioDao {
    private ConexionPostgreSQL conexion;

    public UsuarioDaoPostgreSQL() {
        this.conexion = ConexionPostgreSQL.getConexion();
    }

    @Override
    public void insert(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO users (username, pass, email) VALUES (?, ?, ?)";
        try (PreparedStatement statement = conexion.getCon().prepareStatement(sql)) {
            statement.setString(1, usuario.getUsername());
            statement.setString(2, usuario.getPassword());
            statement.setString(3, usuario.getEmail());
            statement.executeUpdate();
        }
    }

    @Override
    public Usuario select(int id) {
        Usuario usuario = null;
        String sql = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement statement = conexion.getCon().prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                usuario = new Usuario(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("pass"),
                        resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public List<Usuario> selectAll() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Statement statement = conexion.getCon().createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Usuario usuario = new Usuario(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("pass"),
                        resultSet.getString("email"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement statement = conexion.getCon().prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Usuario usuario) throws SQLException {
        String sql = "UPDATE users SET username = ?, pass = ?, email = ? WHERE id = ?";
        try (PreparedStatement statement = conexion.getCon().prepareStatement(sql)) {
            statement.setString(1, usuario.getUsername());
            statement.setString(2, usuario.getPassword());
            statement.setString(3, usuario.getEmail());
            statement.setInt(4, usuario.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public Usuario checkLogin(String username, String password) {
        // Implementación para comprobar el login
        String sql = "SELECT * FROM users WHERE username = ? AND pass = ?;";
        try (PreparedStatement statement = conexion.getCon().prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Usuario(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("pass"),
                        resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Bill> selectBillsByUser(int userId) {
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT * FROM bill WHERE user_id = ?";
        try (PreparedStatement statement = conexion.getCon().prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Bill bill = new Bill(
                        resultSet.getInt("id"),
                        resultSet.getDate("date_bill"),
                        resultSet.getInt("user_id"),
                        resultSet.getBigDecimal("value"),
                        resultSet.getInt("type"),
                        resultSet.getString("observation")
                );
                bills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }

}
