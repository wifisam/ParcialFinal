package edu.sv.ujmd.parcialfinal.dao;

import edu.sv.ujmd.parcialfinal.model.Alimentos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.sv.ujmd.parcialfinal.model.Bebida;
import edu.sv.ujmd.parcialfinal.Conexion;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 *
 * @author Ramesh Fadatare
 *
 */
public class BebidasDAO {

    private final Connection connection;
    private static final String INSERT_BEBIDAS_SQL = "INSERT INTO bebidas" + "  (bebida, precio) VALUES "
            + " (?, ?, ?);";
    private static final String SELECT_BEBIDAS_BY_ID = "select id_bebida,bebida,precio from bebidas where id_bebida =?";
    private static final String SELECT_ALL_BEBIDAS = "select * from bebidas";
    private static final String DELETE_BEBIDAS_SQL = "delete from bebidas where id_bebida = ?;";
    private static final String UPDATE_BEBIDAS_SQL = "update bebidas set bebida = ?,precio= ? where id_bebida = ?;";

    public BebidasDAO() {
        connection = Conexion.Conectar();
    }

    public void insertBebida(Bebida bebida) throws SQLException {
        System.out.println(INSERT_BEBIDAS_SQL);
        // try-with-resource statement will auto close the connection.
        try (
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BEBIDAS_SQL)) {
            preparedStatement.setString(1, bebida.getBebida());
            preparedStatement.setDouble(2, bebida.getPrecio());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Bebida selectBebida(int id) {
        Bebida bebidas = null;
        // Step 1: Establishing a Connection
        try (
                // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BEBIDAS_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String bebida = rs.getString("bebida");
                Double precio = rs.getDouble("precio");
                bebidas = new Bebida(id, bebida, precio);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return bebidas;
    }

    public List<Bebida> selectAllBebidas() {
        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Bebida> bebidas = new ArrayList<>();
        // Step 1: Establishing a Connection
        try ( // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BEBIDAS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id_alimento");
                String bebida = rs.getString("bebida");
                Double precio = rs.getDouble("precio");
                bebidas.add(new Bebida(id, bebida, precio));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return bebidas;
    }

    public boolean deleteBebidas(int id) throws SQLException {
        boolean rowDeleted;
        try (
                PreparedStatement statement = connection.prepareStatement(DELETE_BEBIDAS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateBebida(Bebida bebidas) throws SQLException {
        boolean rowUpdated;
        try (
                PreparedStatement statement = connection.prepareStatement(UPDATE_BEBIDAS_SQL);) {
            statement.setString(1, bebidas.getBebida());
            statement.setDouble(2, bebidas.getPrecio());
            statement.setInt(4, bebidas.getId());

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
