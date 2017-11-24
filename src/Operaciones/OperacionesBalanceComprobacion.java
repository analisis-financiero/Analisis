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


public class OperacionesBalanceComprobacion {

    private double deber_total;
    private double haber_total;
    conexion con = new conexion();

    public void mostrarBalanceComprobacion(JTable tablita, int idcatalogo) {

        try {
            Statement sentencia = con.conectar().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM cuenta WHERE (catalogo_idcatalogo = " + idcatalogo + ") AND (idcuenta > 30) AND(idcuenta not like '7%') ORDER BY idcuenta asc");
            DefaultTableModel modelo = new DefaultTableModel();

            modelo.addColumn("Cuenta");
            modelo.addColumn("Deber");
            modelo.addColumn("Haber");

            int valor = 0;
            String saldo2;
            String tipo_saldo;
            while (resultado.next()) {
                tipo_saldo = resultado.getString("saldo_deudor");
                
                char inicial = tipo_saldo.charAt(0);

                if (tipo_saldo.equals("0")) {
                    modelo.addRow(new Object[]{});
                    modelo.setValueAt(resultado.getString("nombre"), valor, 0);
                    modelo.setValueAt("$" + resultado.getString("valor"), valor, 1);
                    modelo.setValueAt(" ", valor, 2);
                    valor++;
                    saldo2 = (resultado.getString("valor"));
                    setDeber_total(getDeber_total() + Double.parseDouble(saldo2));
                } else {
                    modelo.addRow(new Object[]{});
                    modelo.setValueAt(resultado.getString("nombre"), valor, 0);
                    modelo.setValueAt(" ", valor, 1);
                    modelo.setValueAt("$" + resultado.getString("valor"), valor, 2);

                    valor++;
                    saldo2 = (resultado.getString("valor"));
                    setHaber_total(getHaber_total() + Double.parseDouble(saldo2));
                }
            }

            tablita.setModel(modelo);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        con.desconectar();
    }

    /**
     * @return the deber_total
     */
    public double getDeber_total() {
        return deber_total;
    }

    /**
     * @param deber_total the deber_total to set
     */
    public void setDeber_total(double deber_total) {
        this.deber_total = deber_total;
    }

    /**
     * @return the haber_total
     */
    public double getHaber_total() {
        return haber_total;
    }

    /**
     * @param haber_total the haber_total to set
     */
    public void setHaber_total(double haber_total) {
        this.haber_total = haber_total;
    }
}
