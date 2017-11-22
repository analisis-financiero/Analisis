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
    
    private double saldoPasivosCorrientes = 0;
    private double saldoPasivosFijos = 0;
    private double saldoActivosCorrientes = 0;
    private double saldoActivosFijos = 0;
//    private double saldoPasivosCorrientesAV = 0;
//    private double saldoPasivosFijosAV = 0;
//    private double saldoActivosCorrientesAV = 0;
//    private double saldoActivosFijosAV = 0;
//    
//DE momento
    private double saldoActivos = 0;
    private double saldoPasivo = 0;

    
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

     public void balanceGeneralActivosCorrientes(JTable table, int idcatalogo) {
         saldoActivosCorrientes = 0;
         saldoActivosFijos = 0;
         saldoPasivosCorrientes = 0;
         saldoPasivosFijos = 0;
//Total activos corrientes
        try {
            Statement sentencia = con.conectar().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM cuenta WHERE (catalogo_idcatalogo ='" + idcatalogo + "') AND (idpadre = 11) ORDER BY idcuenta asc");
            DefaultTableModel modelAC = new DefaultTableModel();
            modelAC.addColumn("Nombre");
            modelAC.addColumn("Valor");
            int valor = 0;
            
            String tipo = "";

            //Valindando depreciacion...
            while (resultado.next()) {
                tipo = resultado.getString("saldo_deudor");
                if (tipo.equals("1")) {
                    modelAC.addRow(new Object[]{});
                    modelAC.setValueAt(resultado.getString("nombre"), valor, 0);
                    modelAC.setValueAt("$" + resultado.getString("valor"), valor, 1);
                    valor++;
                    setSaldoActivosCorrientes(getSaldoActivosCorrientes()- Double.parseDouble(resultado.getString("valor")));
                } else {
                    modelAC.addRow(new Object[]{});
                    modelAC.setValueAt(resultado.getString("nombre"), valor, 0);
                    modelAC.setValueAt("$" + resultado.getString("valor"), valor, 1);
                    valor++;
                    setSaldoActivosCorrientes(getSaldoActivosCorrientes()+ Double.parseDouble(resultado.getString("valor")));
                }

            }

            table.setModel(modelAC);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        con.desconectar();
    }
    
    public void balanceGeneralActivosFijos(JTable table, int idcatalogo) {
         saldoActivosCorrientes = 0;
         saldoActivosFijos = 0;
         saldoPasivosCorrientes = 0;
         saldoPasivosFijos = 0;
//Total activos fijos        
        try {
            Statement sentencia = con.conectar().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM cuenta WHERE (catalogo_idcatalogo ='" + idcatalogo + "') AND (idpadre = 12) ORDER BY idcuenta asc");
            DefaultTableModel modelAF = new DefaultTableModel();
            modelAF.addColumn("Nombre");
            modelAF.addColumn("Valor");
            int valor = 0;
            
            String tipo = "";

            //Valindando depreciacion...
            while (resultado.next()) {
                tipo = resultado.getString("saldo_deudor");
                if (tipo.equals("1")) {
                    modelAF.addRow(new Object[]{});
                    modelAF.setValueAt(resultado.getString("nombre"), valor, 0);
                    modelAF.setValueAt("$" + resultado.getString("valor"), valor, 1);
                    valor++;
                    setSaldoActivosFijos(getSaldoActivosFijos()- Double.parseDouble(resultado.getString("valor")));
                } else {
                    modelAF.addRow(new Object[]{});
                    modelAF.setValueAt(resultado.getString("nombre"), valor, 0);
                    modelAF.setValueAt("$" + resultado.getString("valor"), valor, 1);
                    valor++;
                    setSaldoActivosFijos(getSaldoActivosFijos()+ Double.parseDouble(resultado.getString("valor")));
                }

            }

            table.setModel(modelAF);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        con.desconectar();
        
        
        
       
        
        
        
        
        
    }

    //Para el AVBG
    public void balanceGeneralPasivosCorrientes(JTable table, int idcatalogo) {
         saldoActivosCorrientes = 0;
         saldoActivosFijos = 0;
         saldoPasivosCorrientes = 0;
         saldoPasivosFijos = 0;
        
        try {
            Statement sentencia = con.conectar().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM cuenta WHERE (catalogo_idcatalogo = '" + idcatalogo + "') AND (idpadre=21) ORDER BY idcuenta asc");
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Nombre");
            model.addColumn("Valor");
            int valor = 0;
            while (resultado.next()) {
                model.addRow(new Object[]{});
                model.setValueAt(resultado.getString("nombre"), valor, 0);
                model.setValueAt("$" + resultado.getString("valor"), valor, 1);
                valor++;
                setSaldoPasivosCorrientes(getSaldoPasivosCorrientes()+ Double.parseDouble(resultado.getString("valor")));
            }
            table.setModel(model);
        } catch (Exception e) {
        }
        con.desconectar();
    }

    
    public void balanceGeneralPasivosFijos(JTable table, int idcatalogo) {
         saldoActivosCorrientes = 0;
         saldoActivosFijos = 0;
         saldoPasivosCorrientes = 0;
         saldoPasivosFijos = 0;
        try {
            Statement sentencia = con.conectar().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM cuenta WHERE (catalogo_idcatalogo = '" + idcatalogo + "') AND (idpadre = 22) ORDER BY idcuenta asc");
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Nombre");
            model.addColumn("Valor");
            int valor = 0;
            while (resultado.next()) {
                model.addRow(new Object[]{});
                model.setValueAt(resultado.getString("nombre"), valor, 0);
                model.setValueAt("$" + resultado.getString("valor"), valor, 1);
                valor++;
                setSaldoPasivosFijos(getSaldoPasivosFijos()+ Double.parseDouble(resultado.getString("valor")));
            }
            table.setModel(model);
        } catch (Exception e) {
        }
        con.desconectar();
    }

     public void allCuentas(JTable table, int idCatalogo) {
        try {
            Statement sentencia = null;
            ResultSet resultado = null;

            sentencia = con.conectar().createStatement();
            resultado = sentencia.executeQuery("SELECT idcuenta, idpadre, nombre, valor, saldo_deudor FROM cuenta WHERE catalogo_idcatalogo = "+idCatalogo+";");
            DefaultTableModel model = new DefaultTableModel();

            model.addColumn("Codigo");
            model.addColumn("Codigo Padre");
            model.addColumn("Nombre");
            model.addColumn("Saldo");
            model.addColumn("Deudor/Acrededor");
            
            int valor = 0;

            while (resultado.next()) {
                model.addRow(new Object[]{});
                model.setValueAt(resultado.getString("idcuenta"), valor, 0);
                model.setValueAt(resultado.getString("idpadre"), valor, 1);
                model.setValueAt(resultado.getString("nombre"), valor, 2);
                model.setValueAt("$" + resultado.getString("valor"), valor, 3);
                if(resultado.getString("saldo_deudor").equals("0")){
                    model.setValueAt("Deudor", valor, 4);
                }else {
                    model.setValueAt("Acrededor", valor, 4);
                }
                valor++;
            }

            table.setModel(model);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
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
    public double getSaldoPasivosCorrientes() {
        return saldoPasivosCorrientes;
    }

    /**
     * @param saldoPasivo the saldoPasivo to set
     */
    public void setSaldoPasivosCorrientes(double saldoPasivosCorrientes) {
        this.saldoPasivosCorrientes = saldoPasivosCorrientes;
    }
    
    
       /**
     * @return the saldoPasivo
     */
    public double getSaldoPasivosFijos() {
        return saldoPasivosFijos;
    }

    /**
     * @param saldoPasivo the saldoPasivo to set
     */
    public void setSaldoPasivosFijos(double saldoPasivosFijos) {
        this.saldoPasivosFijos = saldoPasivosFijos;
    }
    
    /**
     * @return the saldoActivos
     */
    public double getSaldoActivosCorrientes() {
        return saldoActivosCorrientes;
    }

    /**
     * @param saldoActivos the saldoActivos to set
     */
    public void setSaldoActivosCorrientes(double saldoActivosCorrientes) {
        this.saldoActivosCorrientes = saldoActivosCorrientes;
    }

    public double getSaldoPasivo() {
        return saldoPasivo;
    }

    public void setSaldoPasivo(double saldoPasivo) {
        this.saldoPasivo = saldoPasivo;
    }

    
    /**
     * @return the saldoActivos
     */
    public double getSaldoActivosFijos() {
        return saldoActivosFijos;
    }

    /**
     * @param saldoActivos the saldoActivos to set
     */
    public void setSaldoActivosFijos(double saldoActivosFijos) {
        this.saldoActivosFijos = saldoActivosFijos;
    }
    /**
     * @return the totalIngresos
     */
    
    
    public double getSaldoActivos() {
        return saldoActivos;
    }

    public void setSaldoActivos(double saldoActivos) {
        this.saldoActivos = saldoActivos;
    }
    
    
    
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
