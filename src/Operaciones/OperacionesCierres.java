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


public class OperacionesCierres {

    conexion con = new conexion();
    private double saldoCapital = 0;
    private double saldoPasivo = 0;
    private double saldoActivos = 0;
    private double totalIngresos = 0;
    private double totalGastos = 0;
    private double totalRetiros = 0;

    public void IngresosCierres(JTable table, int idCatalogo) {

        try {

            Statement sentencia = con.conectar().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM cuenta WHERE (catalogo_idcatalogo ='" + idCatalogo + "') AND (idpadre = 4) ORDER BY idcuenta asc");
            DefaultTableModel model = new DefaultTableModel();

            model.addColumn("Nombre");
            model.addColumn("Valor");

            int valor = 0;

            while (resultado.next()) {
                model.addRow(new Object[]{});
                model.setValueAt(resultado.getString("nombre"), valor, 0);
                model.setValueAt("$" + resultado.getString("valor"), valor, 1);
                valor++;

                setTotalIngresos(getTotalIngresos() + Double.parseDouble(resultado.getString("valor")));
            }
            table.setModel(model);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        con.desconectar();
    }

    public void GastosCierres(JTable table, int idCatalogo) {
        try {

            Statement sentencia = con.conectar().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM cuenta WHERE (catalogo_idcatalogo ='" + idCatalogo + "') AND (idpadre = 5) ORDER BY idcuenta asc");
            DefaultTableModel model = new DefaultTableModel();

            model.addColumn("Nombre");
            model.addColumn("Valor");

            int valor = 0;

            while (resultado.next()) {
                model.addRow(new Object[]{});
                model.setValueAt(resultado.getString("nombre"), valor, 0);
                model.setValueAt("$" + resultado.getString("valor"), valor, 1);
                valor++;

                setTotalGastos(getTotalGastos() + Double.parseDouble(resultado.getString("valor")));
            }
            table.setModel(model);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        con.desconectar();
    }

    //Variacion de Capital
    public void CapitalCierre(JTable table, int idCatalogo) {
        try {
            Statement sentencia = con.conectar().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM cuenta WHERE (catalogo_idcatalogo ='" + idCatalogo + "') AND (idpadre = 3) ORDER BY idcuenta asc");
            DefaultTableModel model = new DefaultTableModel();

            model.addColumn("Nombre");
            model.addColumn("Valor");

            int valor = 0;

            while (resultado.next()) {
                model.addRow(new Object[]{});
                model.setValueAt(resultado.getString("nombre"), valor, 0);
                model.setValueAt("$" + resultado.getString("valor"), valor, 1);
                valor++;
                setSaldoCapital(getSaldoCapital() + Double.parseDouble(resultado.getString("valor")));
            }

            table.setModel(model);
        } catch (Exception e) {
        }
        con.desconectar();
    }

    public void RetirosCierres(JTable table, int idCatalogo) {
        try {
            Statement sentencia = con.conectar().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM cuenta WHERE (catalogo_idcatalogo ='" + idCatalogo + "') AND (idpadre = 6) ORDER BY idcuenta asc");
            DefaultTableModel model = new DefaultTableModel();

            model.addColumn("Nombre");
            model.addColumn("Valor");

            int valor = 0;

            while (resultado.next()) {
                model.addRow(new Object[]{});
                model.setValueAt(resultado.getString("nombre"), valor, 0);
                model.setValueAt("$" + resultado.getString("valor"), valor, 1);
                valor++;
                setTotalRetiros(getTotalRetiros() + Double.parseDouble(resultado.getString("valor")));
            }
            table.setModel(model);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        con.desconectar();
    }

    public void actualizarDatos(int idcatalogo) {
        int contador = 0;
        int carga = 0;
        Double total_gastos = 0.0;
        Double total_ingresos = null;
        Double utilidad;
        Double retiros;
        Double capital;
        try {
            Statement sentencia = con.conectar().createStatement();

            ResultSet resultado = sentencia.executeQuery("SELECT MAX(idcuenta) FROM cuenta");

            while (resultado.next()) {
                contador = resultado.getInt("MAX(idcuenta)");
                //GASTOS

                resultado = sentencia.executeQuery("SELECT SUM(valor) FROM cuenta WHERE (catalogo_idcatalogo = " + idcatalogo + ") AND (idcuenta =5)");
                resultado.last();
                total_gastos = resultado.getDouble("SUM(valor)");
                PreparedStatement sentencia2 = con.conectar().prepareStatement("UPDATE cuenta SET valor=" + 0 + " WHERE (catalogo_idcatalogo=" + idcatalogo + ") AND (idcuenta like '5%')");
                carga = sentencia2.executeUpdate();
                // System.out.print("Actualizacion");



                resultado = sentencia.executeQuery("SELECT SUM(valor) FROM cuenta WHERE (catalogo_idcatalogo = " + idcatalogo + ") AND (idcuenta =4)");
                resultado.last();
                total_ingresos = resultado.getDouble("SUM(valor)");
                PreparedStatement sentencia3 = con.conectar().prepareStatement("UPDATE cuenta SET valor=" + 0 + " WHERE (catalogo_idcatalogo=" + idcatalogo + ") AND (idcuenta like '4%')");
                carga = sentencia3.executeUpdate();
                // System.out.print("Actualizacion");

                resultado = sentencia.executeQuery("SELECT SUM(valor) FROM cuenta WHERE (catalogo_idcatalogo = " + idcatalogo + ") AND (idcuenta =6)");
                resultado.last();
                retiros = resultado.getDouble("SUM(valor)");
                PreparedStatement sentencia4 = con.conectar().prepareStatement("UPDATE cuenta SET valor=" + 0 + " WHERE (catalogo_idcatalogo=" + idcatalogo + ") AND (idcuenta like '6%')");
                carga = sentencia4.executeUpdate();


                resultado = sentencia.executeQuery("SELECT valor FROM cuenta WHERE (catalogo_idcatalogo = " + idcatalogo + ") AND (idcuenta =3)");
                resultado.last();
                capital = resultado.getDouble("valor");

                utilidad = total_ingresos - total_gastos;
                capital = capital + utilidad;
                capital = capital - retiros;
                PreparedStatement sentencia5 = con.conectar().prepareStatement("UPDATE cuenta SET valor=" + capital + " WHERE (catalogo_idcatalogo=" + idcatalogo + ") AND (idcuenta like '3%')");
                carga = sentencia5.executeUpdate();



            }
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
        con.desconectar();
    }

    public void listarCierres(JTable tablita, int idcatalogo) {
        try {
            Statement sentencia = null;
            ResultSet resultado = null;

            sentencia = con.conectar().createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM cierresAntiguos WHERE idcatalogo ='" + idcatalogo + "'");

            DefaultTableModel modelo = new DefaultTableModel();

            modelo.addColumn("Fecha");
            modelo.addColumn("Utilidad");
            modelo.addColumn("Ingresos");
            modelo.addColumn("Gastos");
            modelo.addColumn("Retiros");
            modelo.addColumn("Capital");

            int valor = 0;

            while (resultado.next()) {
                modelo.addRow(new Object[]{});
                modelo.setValueAt(resultado.getString("fecha"), valor, 0);
                modelo.setValueAt(resultado.getString("utilidad"), valor, 1);
                modelo.setValueAt(resultado.getString("totalIngresos"), valor, 2);
                modelo.setValueAt(resultado.getString("totalGastos"), valor, 3);
                modelo.setValueAt(resultado.getString("retiros"), valor, 4);
                modelo.setValueAt(resultado.getString("capital"), valor, 5);

                valor = valor + 1;
            }
            tablita.setModel(modelo);
        } catch (SQLException ex) {
            System.out.println("Error de Listado de Cierres Contables : " + ex.getMessage());
        }
        con.desconectar();
    }

    public void guardarCierre(String fecha, String utilidad, String totalIngresos, String totalGastas, String retiros, String capital, int idcatalogo) {

        try {
            int updateRows = 0;
            PreparedStatement sentencia = con.conectar().prepareStatement("INSERT INTO cierresAntiguos (fecha, utilidad, totalIngresos, totalGastos, retiros, capital, idcatalogo) VALUES (?,?,?,?,?,?,?)");
            
            sentencia.setString(1, fecha);
            sentencia.setString(2, utilidad);
            sentencia.setString(3, totalIngresos);
            sentencia.setString(4, totalGastas);
            sentencia.setString(5, retiros);
            sentencia.setString(6, capital);
            sentencia.setInt(7, idcatalogo);

            updateRows = sentencia.executeUpdate();
            if (updateRows == 1) {
                JOptionPane.showMessageDialog(null, "Cierre agregado exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error Critico: ¡¡¡Verificar Datos!!!");
            }
            con.desconectar();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @return the saldoCapital
     */
    public double getSaldoCapital() {
        return saldoCapital;
    }

    /**
     * @param saldoCapital the saldoCapital to set
     */
    public void setSaldoCapital(double saldoCapital) {
        this.saldoCapital = saldoCapital;
    }

    /**
     * @return the saldoPasivo
     */
    public double getSaldoPasivo() {
        return saldoPasivo;
    }

    /**
     * @param saldoPasivo the saldoPasivo to set
     */
    public void setSaldoPasivo(double saldoPasivo) {
        this.saldoPasivo = saldoPasivo;
    }

    /**
     * @return the saldoActivos
     */
    public double getSaldoActivos() {
        return saldoActivos;
    }

    /**
     * @param saldoActivos the saldoActivos to set
     */
    public void setSaldoActivos(double saldoActivos) {
        this.saldoActivos = saldoActivos;
    }

    /**
     * @return the totalIngresos
     */
    public double getTotalIngresos() {
        return totalIngresos;
    }

    /**
     * @param totalIngresos the totalIngresos to set
     */
    public void setTotalIngresos(double totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    /**
     * @return the totalGastos
     */
    public double getTotalGastos() {
        return totalGastos;
    }

    /**
     * @param totalGastos the totalGastos to set
     */
    public void setTotalGastos(double totalGastos) {
        this.totalGastos = totalGastos;
    }

    /**
     * @return the totalRetiros
     */
    public double getTotalRetiros() {
        return totalRetiros;
    }

    /**
     * @param totalRetiros the totalRetiros to set
     */
    public void setTotalRetiros(double totalRetiros) {
        this.totalRetiros = totalRetiros;
    }
}
