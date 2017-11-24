/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Operaciones;

import conexion.conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class OperacionesAsiento {

    conexion con = new conexion();

    public void asientosuma(int id, int idcatalogo, double importe) {
        try {
            int updateRows = 0;
            PreparedStatement sentencia = con.conectar().prepareStatement("UPDATE cuenta SET valor =CASE WHEN (saldo_deudor=0) THEN valor+'" + importe + "'WHEN (saldo_deudor=1) THEN valor-'" + importe + "' END WHERE (idcuenta ='" + id + "') AND (catalogo_idcatalogo ='" + idcatalogo + "')");
            updateRows = sentencia.executeUpdate();
            if (updateRows == 1) {
                JOptionPane.showMessageDialog(null, "Asiento Exitoso");
            } else {
                JOptionPane.showMessageDialog(null, "Error Critico: ¡¡¡Verificar Datos!!!");
            }
            con.desconectar();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void asientoresta(int id, int idcatalogo, double importe) {
        try {
            int updateRows = 0;
            PreparedStatement sentencia = con.conectar().prepareStatement("UPDATE cuenta SET valor =CASE WHEN (saldo_deudor=0) THEN valor-'" + importe + "'WHEN (saldo_deudor=1) THEN valor+'" + importe + "' END WHERE (idcuenta ='" + id + "') AND (catalogo_idcatalogo ='" + idcatalogo + "')");
            updateRows = sentencia.executeUpdate();
            if (updateRows == 1) {
                // JOptionPane.showMessageDialog(null, "Asiento Exitoso");
            } else {
                JOptionPane.showMessageDialog(null, "Error Critico: ¡¡¡Verificar Datos!!!");
            }
            con.desconectar();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void listarCatalogo(JTable tablita, int idcatalogo) {

        try {
            Statement sentencia = con.conectar().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM cuenta WHERE (catalogo_idcatalogo ='" + idcatalogo + "') AND (idcuenta > 30) AND(idcuenta not like '7%') ORDER BY idcuenta asc");

            DefaultTableModel modelo = new DefaultTableModel();

            modelo.addColumn("Id Cuenta");
            modelo.addColumn("Nombre");

            int valor = 0;
            while (resultado.next()) {
                modelo.addRow(new Object[]{});
                modelo.setValueAt(resultado.getString("idcuenta"), valor, 0);
                modelo.setValueAt(resultado.getString("nombre"), valor, 1);
                valor++;
            }
            tablita.setModel(modelo);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        con.desconectar();
    }

    public void actualizarDatos(int idcatalogo) {
        int contador = 0;
        int carga = 0;
        Double total = 0.0;
        try {
            Statement sentencia = con.conectar().createStatement();

            ResultSet resultado = sentencia.executeQuery("SELECT MAX(idpadre) FROM cuenta");

            while (resultado.next()) {
                contador = resultado.getInt("MAX(idpadre)");

                for (int i = contador; i > 0; i--) {
                    resultado = sentencia.executeQuery("SELECT SUM(valor) FROM cuenta WHERE (catalogo_idcatalogo = " + idcatalogo + ") AND (idpadre = " + i + ")");
                    resultado.last();

                    total = resultado.getDouble("SUM(valor)");
                    PreparedStatement sentencia2 = con.conectar().prepareStatement("UPDATE cuenta SET valor=" + total + " WHERE (catalogo_idcatalogo=" + idcatalogo + ") AND (idcuenta=" + i + ")");
                    carga = sentencia2.executeUpdate();
                    // System.out.print("Actualizacion");
                }
            }
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
        con.desconectar();
    }

    public void guardarAsiento(int asiento,int idcatalogo,String fecha, String cuenta, String tipo, Double importe, String descripcion) {

        try {
            int updateRows = 0;
            PreparedStatement sentencia = con.conectar().prepareStatement("INSERT INTO mayor (Asiento,idcatalogo, Fecha, Cuenta, D_H, Importe, Descripcion) VALUES (?,?,?,?,?,?,?)");

            sentencia.setInt(1, asiento);
            sentencia.setInt(2, idcatalogo);
            sentencia.setString(3, fecha);
            sentencia.setString(4, cuenta);
            sentencia.setString(5, tipo);
            sentencia.setDouble(6, importe);
            sentencia.setString(7, descripcion);


            updateRows = sentencia.executeUpdate();
//            if (updateRows == 1) {
//                JOptionPane.showMessageDialog(null, "Cierre agregado exitosamente");
//            } else {
//                JOptionPane.showMessageDialog(null, "Error Critico: ¡¡¡Verificar Datos!!!");
//            }
            con.desconectar();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void listarCuentas(JTable tablita, int id, int idcatalogo) {

        try {
            Statement sentencia = null;
            ResultSet resultado = null;

            sentencia = con.conectar().createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM mayor WHERE (Cuenta = '" + id + "')  AND (idcatalogo ='" + idcatalogo + "') ORDER BY Fecha asc");
            DefaultTableModel modelo = new DefaultTableModel();

            modelo.addColumn("Fecha");
            modelo.addColumn("Cuenta");
            modelo.addColumn("Tipo");
            modelo.addColumn("Valor");
            modelo.addColumn("Descripcion");

            int valor = 0;
            while (resultado.next()) {
                modelo.addRow(new Object[]{});
                modelo.setValueAt(resultado.getString("Fecha"), valor, 0);
                modelo.setValueAt(resultado.getString("Cuenta"), valor, 1);
                modelo.setValueAt(resultado.getString("D_H"), valor, 2);
                modelo.setValueAt(resultado.getString("Importe"), valor, 3);
                modelo.setValueAt(resultado.getString("Descripcion"), valor, 4);
                valor = valor + 1;
            }
            tablita.setModel(modelo);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        con.desconectar();
    }

}
