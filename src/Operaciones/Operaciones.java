/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Operaciones;

import conexion.conexion;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Operaciones {

    conexion con = new conexion();

    public void listarCatalogo(JTable tablita, int idcatalogo) {
        try {
            Statement sentencia = null;
            ResultSet resultado = null;

            sentencia = con.conectar().createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM cuenta WHERE catalogo_idcatalogo ='" + idcatalogo + "' ORDER BY idcuenta asc");

            DefaultTableModel modelo = new DefaultTableModel();

            modelo.addColumn("Id Cuenta");
            modelo.addColumn("Nombre");
            modelo.addColumn("Valor");

            int valor = 0;

            while (resultado.next()) {
                modelo.addRow(new Object[]{});
                modelo.setValueAt(resultado.getString("idcuenta"), valor, 0);
                modelo.setValueAt(resultado.getString("nombre"), valor, 1);
                modelo.setValueAt(resultado.getString("valor"), valor, 2);
                valor = valor + 1;
            }
            tablita.setModel(modelo);
        } catch (SQLException ex) {
            System.out.println("Error de Listado de Catalogo : " + ex.getMessage());
        }
        con.desconectar();
    }

    public void listarUsuarios(JTable tablita, String nombre) {
        try {
            Statement sentencia = null;
            ResultSet resultado = null;

            sentencia = con.conectar().createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM conta2.usuarios WHERE email != '" + nombre + "'");

            DefaultTableModel modelo = new DefaultTableModel();

            modelo.addColumn("Id Usuario");
            modelo.addColumn("Nombre");
            modelo.addColumn("Tipo Usuario");

            int valor = 0;

            while (resultado.next()) {
                modelo.addRow(new Object[]{});
                modelo.setValueAt(resultado.getString("idusuarios"), valor, 0);
                modelo.setValueAt(resultado.getString("email"), valor, 1);

                if (resultado.getString("tipo_usuario").trim().equals("0")) {
                    modelo.setValueAt("Administrador", valor, 2);
                } else {
                    modelo.setValueAt("Usuario Normal", valor, 2);
                }
                valor = valor + 1;
            }
            tablita.setModel(modelo);
        } catch (SQLException ex) {
            System.out.println("Error de Listado de Catalogo : " + ex.getMessage());
        }
        con.desconectar();
    }
}
