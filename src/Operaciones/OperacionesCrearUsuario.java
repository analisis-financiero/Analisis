/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Operaciones;

import conexion.conexion;
import java.sql.PreparedStatement;

/**
 *
 * @author Tablo
 */
public class OperacionesCrearUsuario {
    
     conexion con = new conexion();

    public void crearUsuario(String mail, String pass) {
        try {
            int updateRows = 0;
            PreparedStatement sentencia = con.conectar().prepareStatement("INSERT INTO usuarios (email, password) VALUES (?,?)");

            sentencia.setString(1, mail);
            sentencia.setString(2, pass);

            updateRows = sentencia.executeUpdate();

            con.desconectar();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
