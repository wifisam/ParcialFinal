package edu.sv.ujmd.parcialfinal.dao;

import edu.sv.ujmd.parcialfinal.model.Alimentos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.sv.ujmd.parcialfinal.model.Factura;
import edu.sv.ujmd.parcialfinal.Conexion;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 *
 * @author Ramesh Fadatare
 *
 */
public class FacturaDAO {

    private final Connection connection;
    private static final String INSERT_FACTURA_SQL = "INSERT INTO factura" + "  (id_alimento, id_bebida, subtotal) VALUES "
            + " (?, ?, ?);";
    private static final String SELECT_FACTURA_BY_ID = "select id_factura,id_alimento,id_bebida, subtotal from bebidas where id_factura =?";
    private static final String SELECT_ALL_FACTURA = "select * from factura";
    private static final String DELETE_FACTURA_SQL = "delete from factura where id_factura = ?;";
    private static final String UPDATE_FACTURA_SQL = "update bebidas set bebida = ?,precio= ? where id_bebida = ?;";

    public FacturaDAO() {
        connection = Conexion.Conectar();
    }

    public void insertFactura(Factura facturas) throws SQLException {
        System.out.println(INSERT_FACTURA_SQL);
        // try-with-resource statement will auto close the connection.
        try (
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FACTURA_SQL)) {
            preparedStatement.setInt(1, facturas.getIdAlimento());
            preparedStatement.setInt(2, facturas.getIdBebida());
            preparedStatement.setDouble(3, facturas.getSubtotal());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Factura selectFactura(int id) {
        Factura facturas = null;
        // Step 1: Establishing a Connection
        try (
                // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FACTURA_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id_alimento = rs.getInt("id_alimento");
                int id_bebida = rs.getInt("id_bebida");
                Double subtotal = rs.getDouble("subtotal");
                facturas = new Factura(id_alimento, id_bebida, subtotal);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return facturas;
    }

    public List<Factura> selectAllFacturas() {
        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Factura> facturas = new ArrayList<>();
        // Step 1: Establishing a Connection
        try ( // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FACTURA);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id_factura");
                int id_alimento = rs.getInt("id_alimento");
                int id_bebida = rs.getInt("id_bebida");
                Double subtotal = rs.getDouble("subtotal");
                facturas.add(new Factura(id, id_alimento, id_bebida, subtotal));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return facturas;
    }

    public boolean deleteFacturas(int id) throws SQLException {
        boolean rowDeleted;
        try (
                PreparedStatement statement = connection.prepareStatement(DELETE_FACTURA_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateFacturas(Factura facturas) throws SQLException {
        boolean rowUpdated;
        try (
                PreparedStatement statement = connection.prepareStatement(UPDATE_FACTURA_SQL);) {
            statement.setInt(1, facturas.getIdAlimento());
            statement.setInt(2, facturas.getIdBebida());
            statement.setDouble(3, facturas.getSubtotal());
            statement.setInt(4, facturas.getId());

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
