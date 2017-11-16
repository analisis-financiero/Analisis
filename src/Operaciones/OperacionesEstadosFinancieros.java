/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Operaciones;

import conexion.conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tablo
 */
public class OperacionesEstadosFinancieros {

    private double saldoCapital = 0;
    private double saldoPasivo = 0;
    private double saldoActivos = 0;
    private double totalIngresos = 0;
    private double totalGastos = 0;
    private double totalRetiros = 0;
    conexion con = new conexion();

    //Metodos para La Utilidad Neta
    public void IngresosEstadoResultado(JTable table, int idCatalogo) {

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

    public void GastosEstadoResultado(JTable table, int idCatalogo) {
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
    public void VariacionCapital(JTable table, int idCatalogo) {
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

    public void variacionCapitalRetiros(JTable table, int idCatalogo) {
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

    //Balance General
    public void balanceGeneralActivos(JTable table, int idcatalogo) {
        try {
            Statement sentencia = con.conectar().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM cuenta WHERE (catalogo_idcatalogo ='" + idcatalogo + "') AND (idpadre > 10) AND (idpadre < 20) ORDER BY idcuenta asc");
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Nombre");
            model.addColumn("Valor");
            int valor = 0;
            
            String tipo = "";

            //Valindando depreciacion...
            while (resultado.next()) {
                tipo = resultado.getString("saldo_deudor");
                if (tipo.equals("1")) {
                    model.addRow(new Object[]{});
                    model.setValueAt(resultado.getString("nombre"), valor, 0);
                    model.setValueAt("$" + resultado.getString("valor"), valor, 1);
                    valor++;
                    setSaldoActivos(getSaldoActivos() - Double.parseDouble(resultado.getString("valor")));
                } else {
                    model.addRow(new Object[]{});
                    model.setValueAt(resultado.getString("nombre"), valor, 0);
                    model.setValueAt("$" + resultado.getString("valor"), valor, 1);
                    valor++;
                    setSaldoActivos(getSaldoActivos() + Double.parseDouble(resultado.getString("valor")));
                }

            }
//            while (resultado.next()) {
//                model.addRow(new Object[]{});
//                model.setValueAt(resultado.getString("nombre"), valor, 0);
//                model.setValueAt("$" + resultado.getString("valor"), valor, 1);
//                valor++;
//                setSaldoActivos(getSaldoActivos() + Double.parseDouble(resultado.getString("valor")));
//            }
            table.setModel(model);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        con.desconectar();
    }

    public void balanceGeneralPasivos(JTable table, int idcatalogo) {
        try {
            Statement sentencia = con.conectar().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM cuenta WHERE (catalogo_idcatalogo = '" + idcatalogo + "') AND (idpadre > 19) AND (idpadre < 30) ORDER BY idcuenta asc");
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Nombre");
            model.addColumn("Valor");
            int valor = 0;
            while (resultado.next()) {
                model.addRow(new Object[]{});
                model.setValueAt(resultado.getString("nombre"), valor, 0);
                model.setValueAt("$" + resultado.getString("valor"), valor, 1);
                valor++;
                setSaldoPasivo(getSaldoPasivo() + Double.parseDouble(resultado.getString("valor")));
            }
            table.setModel(model);
        } catch (Exception e) {
        }
        con.desconectar();
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
