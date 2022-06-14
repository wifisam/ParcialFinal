package edu.sv.ujmd.parcialfinal.dao;

import edu.sv.ujmd.parcialfinal.model.Alimentos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.sv.ujmd.parcialfinal.model.Alimentos;
import edu.sv.ujmd.parcialfinal.Conexion;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 *
 * @author Ramesh Fadatare
 *
 */
public class AlimentosDAO {

    private final Connection connection;
    private static final String INSERT_ALMIENTOS_SQL = "INSERT INTO alimentos" + "  (alimento, precio) VALUES "
            + " (?, ?, ?);";
    private static final String SELECT_ALIMENTO_BY_ID = "select id_alimento,alimento,precio from alimentos where id_alimento =?";
    private static final String SELECT_ALL_ALIMENTO = "select * from alimentos";
    private static final String DELETE_ALIMENTO_SQL = "delete from alimentos where id_alimento = ?;";
    private static final String UPDATE_ALIMENTO_SQL = "update alimentos set alimento = ?,precio= ? where id_alimento = ?;";

    public AlimentosDAO() {
        connection = Conexion.Conectar();
    }

    public void insertAlimento(Alimentos alimento) throws SQLException {
        System.out.println(INSERT_ALMIENTOS_SQL);
        // try-with-resource statement will auto close the connection.
        try (
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ALMIENTOS_SQL)) {
            preparedStatement.setString(1, alimento.getAlimento());
            preparedStatement.setDouble(2, alimento.getPrecio());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Alimentos selectAlimento(int id) {
        Alimentos alimentos = null;
        // Step 1: Establishing a Connection
        try (
                // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALIMENTO_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String alimento = rs.getString("alimento");
                Double precio = rs.getDouble("precio");
                alimentos = new Alimentos(id, alimento, precio);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return alimentos;
    }

    public List<Alimentos> selectAllAlimentos() {
        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Alimentos> alimentos = new ArrayList<>();
        // Step 1: Establishing a Connection
        try ( // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ALIMENTO);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id_alimento");
                String alimento = rs.getString("alimento");
                Double precio = rs.getDouble("precio");
                alimentos.add(new Alimentos(id, alimento, precio));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return alimentos;
    }

    public boolean deleteAlimentos(int id) throws SQLException {
        boolean rowDeleted;
        try (
                PreparedStatement statement = connection.prepareStatement(DELETE_ALIMENTO_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateAlimento(Alimentos alimento) throws SQLException {
        boolean rowUpdated;
        try (
                PreparedStatement statement = connection.prepareStatement(UPDATE_ALIMENTO_SQL);) {
            statement.setString(1, alimento.getAlimento());
            statement.setDouble(2, alimento.getPrecio());
            statement.setInt(4, alimento.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
