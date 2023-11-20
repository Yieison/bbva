package co.bbva.test.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionPostgreSQL {

    private static ConexionPostgreSQL instance;
    private Connection connection;
    private PreparedStatement preparedStatement;

    private static final String URL = "jdbc:postgresql://localhost:5432/";
    private static final String DB_NAME = "bbva";
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root";

    private ConexionPostgreSQL() {
        try {
            Class.forName(DRIVER);
            this.connection = DriverManager.getConnection(URL + DB_NAME, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static ConexionPostgreSQL getInstance() {
        if (instance == null) {
            instance = new ConexionPostgreSQL();
        }
        return instance;
    }

    public void cerrarConexion() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet query(String sql) throws SQLException {
        this.preparedStatement = connection.prepareStatement(sql);
        return this.preparedStatement.executeQuery();
    }

    public int execute(String sql) throws SQLException {
        this.preparedStatement = connection.prepareStatement(sql);
        return this.preparedStatement.executeUpdate();
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return this.connection.prepareStatement(sql);
    }

}

	
