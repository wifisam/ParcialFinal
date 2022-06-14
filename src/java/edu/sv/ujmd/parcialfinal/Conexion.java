package edu.sv.ujmd.parcialfinal;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Labderecho03
 */
public class Conexion {

    static String user = "root";
    static String pass = "placa166528";
    static String url = "jdbc:mysql://localhost/restaurante?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    static Connection cnn;
    static String resultado = null;

    public static String getResultado() {
        return resultado;
    }

    public static void setResultado(String resultado) {
        Conexion.resultado = resultado;
    }   


    public static Connection Conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cnn = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException cnfex) {         
            System.out.println(cnfex.getMessage());
            resultado = cnfex.getMessage();
        } catch (SQLException sqlex) {           
            System.out.println(sqlex.getMessage());
            resultado = sqlex.getMessage();
        } catch (Exception ex) {            
            System.out.println(ex.getMessage());
            resultado = ex.getMessage();
        }
        return cnn;
    }

    public void cerrarConexion() {
        try {
            cnn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            resultado = ex.getMessage();
        }
    }

}

