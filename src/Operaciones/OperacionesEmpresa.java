/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Operaciones;

import clases.Cuenta;
import clases.Empresa;
import clases.Encargado;
import conexion.conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Tablo
 */
public class OperacionesEmpresa {

    conexion con = new conexion();

    public void rellenarCuenta(String cuenta, int idCuenta) {
        try {
            Statement sentencia = null;
            ResultSet resultado = null;

            sentencia = con.conectar().createStatement();
            resultado = sentencia.executeQuery("SELECT nombre FROM cuenta WHERE idcuenta =" + idCuenta + " AND catalogo_idcatalogo = 1");

            System.out.println(resultado.getString("nombre"));

            cuenta = resultado.getString(0);

        } catch (SQLException ex) {
            System.out.println("Error de rellenado de cuenta : " + ex.getMessage());
        }
        con.desconectar();
    }

    public void almacenarCuenta(Cuenta cuenta) {
        try {

            Statement sentenc2 = con.conectar().createStatement();
            ResultSet result2 = sentenc2.executeQuery("SELECT idcuenta FROM cuenta WHERE catalogo_idcatalogo = " + cuenta.getIdcatalogo() + " AND idcuenta = " + cuenta.getId() + " ");

            if (result2.next()) {
                JOptionPane.showMessageDialog(null, "Ya existe una cuenta con ese ID", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                int updateRows = 0;
                PreparedStatement sentencia = con.conectar().prepareStatement("INSERT INTO cuenta (idcuenta, nombre, valor, idpadre, catalogo_idcatalogo, saldo_deudor) VALUES (?,?,?,?,?,?)");
                sentencia.setInt(1, cuenta.getId());
                sentencia.setString(2, cuenta.getNombre());
                sentencia.setDouble(3, cuenta.getValor());
                sentencia.setInt(4, cuenta.getIdPadre());
                sentencia.setInt(5, cuenta.getIdcatalogo());
                sentencia.setInt(6, cuenta.getTipo_saldo());

                updateRows = sentencia.executeUpdate();
                if (updateRows == 1) {
                    JOptionPane.showMessageDialog(null, "Cuenta agregada exitosamente");
                } else {
                    JOptionPane.showMessageDialog(null, "Error Critico: ¡¡¡Verificar Datos!!!");
                }
                con.desconectar();

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void UpdaCuenta(Cuenta cuenta) {
        try {

            Statement sentenc2 = con.conectar().createStatement();

            int updateRows = 0;
            PreparedStatement sentencia = con.conectar().prepareStatement("UPDATE cuenta SET nombre = ?,  valor = ?,  idpadre = ?,  saldo_deudor = ?  WHERE  idcuenta = ? and catalogo_idcatalogo = ?;");
            
            sentencia.setString(1, cuenta.getNombre());
            sentencia.setDouble(2, cuenta.getValor());
            sentencia.setInt(3, cuenta.getIdPadre());

            sentencia.setInt(4, cuenta.getTipo_saldo());
            sentencia.setInt(5, cuenta.getId());
            sentencia.setInt(6, cuenta.getIdcatalogo());

            updateRows = sentencia.executeUpdate();
            if (updateRows == 1) {
                JOptionPane.showMessageDialog(null, "Cuenta Modificada exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error Critico: ¡¡¡Verificar Datos!!!");
            }
            con.desconectar();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminarCuentar(int id, int idcatalogo) {
        try {
            int updateRows = 0;

            PreparedStatement sentencia = con.conectar().prepareStatement("DELETE FROM cuenta WHERE (idcuenta='" + id + "')AND (catalogo_idcatalogo ='" + idcatalogo + "')");
            updateRows = sentencia.executeUpdate();

            if (updateRows == 1) {
                JOptionPane.showMessageDialog(null, "Cuenta Eliminada Exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error Critico: ¡¡¡Verificar Datos!!!");
            }
            con.desconectar();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void CrearEmpresaEncargado(Empresa empresa, Encargado encargado, int empresaPadre, int encargadoPadre, int tipo) {
        try {
            int updateRows = 0;
            int updateeRows2 = 0;
            int updateRows3 = 0;
            int update2;

            PreparedStatement sentencia2 = con.conectar().prepareStatement("INSERT INTO encargado (nombre, apellidos, fechaNac, encargadoPadre) VALUES (?,?,?,?)");

            sentencia2.setString(1, encargado.getNombres());
            sentencia2.setString(2, encargado.getApellidos());
            sentencia2.setString(3, encargado.getFechanac());
            sentencia2.setInt(4, encargadoPadre);

            updateeRows2 = sentencia2.executeUpdate();
            Statement sentencia3 = con.conectar().createStatement();
            ResultSet resul = sentencia3.executeQuery("SELECT idencargado FROM encargado WHERE nombre = '" + encargado.getNombres() + "'");
            resul.last();
            int encarg = resul.getInt("idencargado");

            PreparedStatement sentencia = con.conectar().prepareStatement("INSERT INTO empresa (nombre, email, direccion, empresaPadre, encargado_idencargado) VALUES (?,?,?,?,?)");
            sentencia.setString(1, empresa.getNombre());
            sentencia.setString(2, empresa.getEmail());
            sentencia.setString(3, empresa.getDireccion());
            sentencia.setInt(4, empresaPadre);
            sentencia.setInt(5, encarg);
            updateRows = sentencia.executeUpdate();

            Statement sentenc = con.conectar().createStatement();
            ResultSet result = sentenc.executeQuery("SELECT idempresa FROM empresa WHERE nombre = '" + empresa.getNombre() + "'");
            result.last();
            int idempresa = result.getInt("idempresa");

            PreparedStatement sen = con.conectar().prepareStatement("INSERT INTO catalogo (nombre, empresa_idempresa) VALUES (?,?)");
            sen.setString(1, empresa.getNombre());
            sen.setInt(2, idempresa);
            int update = sen.executeUpdate();

            Statement sentenc2 = con.conectar().createStatement();
            ResultSet result2 = sentenc2.executeQuery("SELECT idcatalogo FROM catalogo WHERE empresa_idempresa = '" + idempresa + "'");
            result.last();
            while (result2.next()) {
                int idecatalogo = result2.getInt("idcatalogo");

                if (tipo == 0) {
                    PreparedStatement senc = con.conectar().prepareStatement("INSERT INTO cuenta (idcuenta, nombre, idpadre, catalogo_idcatalogo, saldo_deudor,valor) VALUES (1, 'Activos',0," + idecatalogo + ",0,0), (2,'Pasivos',0," + idecatalogo + ",1,0), (3, 'Capital',0," + idecatalogo + ",1,0), "
                            + "(4,'Ingresos',0," + idecatalogo + ",0,0),(5,'Gastos',0," + idecatalogo + ",0,0),(6,'Retiros',0," + idecatalogo + ",0,0),(11,'Activos Corrientes',1," + idecatalogo + ",0,0),(12,'Activos no Corrientes',1," + idecatalogo + ",0,0),(21,'Pasivos Corrientes',2," + idecatalogo + ",1,0),"
                            + "(22,'Pasivos no Corrientes',2," + idecatalogo + ",1,0),(31,'Capital Contable',3," + idecatalogo + ",1,0),(41,'Ingresos por Servicios',4," + idecatalogo + ",1,0),(111,'Efectivo',11," + idecatalogo + ",0,0),(112,'Cuentas por Cobrar',11," + idecatalogo + ",0,0),(113,'Suministros',11," + idecatalogo + ",0,0),(211,'Cuentas por Pagar',21," + idecatalogo + ",1,0),(212,'Documentos por Pagar',21," + idecatalogo + ",1,0),(221,'Documentos por Pagar a largo plazo',22," + idecatalogo + ",1,0),(121,'Terrenos',12," + idecatalogo + ",0,0),(122,'Edificios',12," + idecatalogo + ",0,0)");
                    update2 = senc.executeUpdate();
                } else {
                    PreparedStatement senc2 = con.conectar().prepareStatement("INSERT INTO cuenta (idcuenta, nombre, idpadre, catalogo_idcatalogo, saldo_deudor,valor) VALUES (1, 'Activos',0," + idecatalogo + ",0,0), (2,'Pasivos',0," + idecatalogo + ",1,0), (3, 'Capital',0," + idecatalogo + ",1,0), "
                            + "(4,'Ingresos',0," + idecatalogo + ",0,0),(5,'Gastos',0," + idecatalogo + ",0,0),(6,'Retiros',0," + idecatalogo + ",0,0),(11,'Activos Corrientes',1," + idecatalogo + ",0,0),(12,'Activos no Corrientes',1," + idecatalogo + ",0,0),(21,'Pasivos Corrientes',2," + idecatalogo + ",1,0),"
                            + "(22,'Pasivos no Corrientes',2," + idecatalogo + ",1,0),(31,'Capital Contable',3," + idecatalogo + ",1,0),(41,'Ingresos por Ventas',4," + idecatalogo + ",1,0),(51,'Costo mercaderia vendida',5," + idecatalogo + ",0,0),(111, 'Efectivo',11," + idecatalogo + ",0,0),(112, 'Cuentas por Cobrar',11," + idecatalogo + ",0,0),(121,'Terrenos',12," + idecatalogo + ",0,0),(122,'Edificios',12," + idecatalogo + ",0,0),"
                            + "(113,'Inventario',11," + idecatalogo + ",0,0),(114,'Suministros',11," + idecatalogo + ",0,0),(211,'Cuentas por Pagar',21," + idecatalogo + ",1,0),(212,'Ingresos no devengados',21," + idecatalogo + ",1,0),(213,'Documentos por Pagar',21," + idecatalogo + ",1,0),(221,'Documentos por Pagar a largo plazo',22," + idecatalogo + ",1,0)");
                    update2 = senc2.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error de creacion de la Empresa : " + ex.getMessage());
        }
        con.desconectar();
    }
}
