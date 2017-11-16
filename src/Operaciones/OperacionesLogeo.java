/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Operaciones;

import conexion.conexion;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Tablo
 */
public class OperacionesLogeo {

    conexion con = new conexion();

    public int logeo(String idUser, String pass, String empresa) {
        int id = 0;
        String nombre;
        try {

            Statement sentencia = null;
            ResultSet resultado = null;
            sentencia = con.conectar().createStatement();
            resultado = sentencia.executeQuery("SELECT idcatalogo, nombre, idusuarios FROM catalogo, usuarios WHERE(catalogo.nombre='" + empresa + "' AND usuarios.email='" + idUser + "' AND usuarios.password = '" + pass + "')");
            resultado.last();
            id = resultado.getInt("idcatalogo");
            nombre = resultado.getString("nombre");

        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Verifique los Datos", "Error de Logueo", JOptionPane.ERROR_MESSAGE); 
        }

        return id;
    }

    public String listaEmpresas(JComboBox combo) {

        String nombre = "";
        try {

            Statement sentencia = null;
            ResultSet resultado = null;
            sentencia = con.conectar().createStatement();
            resultado = sentencia.executeQuery("SELECT nombre FROM catalogo ");

            while (resultado.next()) {
                //System.out.println(resultado.getString("nombre"));
                combo.addItem(resultado.getObject("nombre"));
            }

        } catch (Exception e) {
        }

        return nombre;
    }

    public String obtenerTipoUsuario(String nombre) {

        String tipo = "";
        try {

            Statement sentencia = null;
            ResultSet resultado = null;
            sentencia = con.conectar().createStatement();
            resultado = sentencia.executeQuery("SELECT tipo_usuario FROM conta2.usuarios WHERE email = '" + nombre + "'");
            resultado.last();

            tipo = "" + resultado.getInt("tipo_usuario");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }

        return tipo;
    }
}
