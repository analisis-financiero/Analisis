/*
 * Conexion Base De Datos
 */
package conexion;

import java.sql.*;
import javax.swing.*;

public class conexion {

    private String password = "admin";
    private String usuario = "root";
    private String direccionServidor = "localhost";
    private String nombreBaseDatos = "conta2";
    private Connection conexion = null;

    public Connection conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception msj) {
            JOptionPane.showMessageDialog(null, msj.getMessage());
        }
        try {
//           password = JOptionPane.showInputDialog("Pass?");
            conexion = DriverManager.getConnection("jdbc:mysql://" + direccionServidor
                    + "/" + nombreBaseDatos, usuario, password);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Conexion 1: " + ex.getMessage());
        }
        return conexion;
    }

    public void desconectar() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Conexion 2: " + ex.getMessage());
        }
    }
}