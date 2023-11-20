package co.bbva.test.dao;

import java.sql.SQLException;
import java.util.List;
import co.bbva.test.modelo.Usuario;

public interface UsuarioDao {
    void insert(Usuario usuario) throws SQLException;
    Usuario select(int id);
    List<Usuario> selectAll();
    void delete(int id) throws SQLException;
    void update(Usuario usuario) throws SQLException;
    Usuario checkLogin(String username, String password);
}
