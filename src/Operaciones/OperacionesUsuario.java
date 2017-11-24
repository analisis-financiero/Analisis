/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Operaciones;

import conexion.conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class OperacionesUsuario {
    
     conexion con = new conexion();

    public void crearUsuario(String mail, String pass, int tipo) {
        try {
            int updateRows = 0;
            PreparedStatement sentencia = con.conectar().prepareStatement("INSERT INTO usuarios (email, password,tipo_usuario) VALUES (?,?,?)");

            sentencia.setString(1, mail);
            sentencia.setString(2, pass);
            sentencia.setInt(3, tipo);
           

            updateRows = sentencia.executeUpdate();

            con.desconectar();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void eliminarUsuario(int idusuario){
        try {
            int updateRows = 0;

            PreparedStatement sentencia = con.conectar().prepareStatement("DELETE FROM usuarios WHERE idusuarios = " + idusuario);
            updateRows = sentencia.executeUpdate();

            if (updateRows == 1) {
                JOptionPane.showMessageDialog(null, "Usuario Eliminado Exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error Critico: ¡¡¡Verificar Datos!!!");
            }
            con.desconectar();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
