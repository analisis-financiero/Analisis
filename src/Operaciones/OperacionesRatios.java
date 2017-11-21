package Operaciones;

import conexion.conexion;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tablo
 */
public class OperacionesRatios {

    conexion con = new conexion();
    private double saldoCapital = 0;
    private double saldoPasivo = 0;
    private double saldoActivos = 0;
    private double saldoPasivosCirculantes = 0;
    private double saldoPasivosNoCirculantes = 0;
    private double saldoActivosCirculantes = 0;
    private double saldoActivosNoCirculantes = 0;
    private double totalIngresos = 0;
    private double totalGastos = 0;
    private double totalRetiros = 0;
    private double saldoInventario = 0;
    private double saldoVentas = 0;
    private double saldoCxC = 0;
    private double saldoCxP = 0;
    private double saldoCompras = 0;
    private double utilidadneta = 0;
    private double saldocostoventas = 0;

    public void TotalPasivos(int idCatalogo) {
        try {
            Statement sentencia = con.conectar().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM cuenta WHERE (catalogo_idcatalogo = '" + idCatalogo + "') AND (idpadre = 2) ORDER BY idcuenta asc");

            int valor = 0;
            while (resultado.next()) {
                valor++;
                setSaldoPasivo(getSaldoPasivo() + Double.parseDouble(resultado.getString("valor")));
            }
        } catch (Exception e) {
        }
        con.desconectar();
    }

    public void TotalActivos(int idCatalogo) {
        try {
            Statement sentencia = con.conectar().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM cuenta WHERE (catalogo_idcatalogo = '" + idCatalogo + "') AND (idpadre = 1) ORDER BY idcuenta asc");
            int valor = 0;
            while (resultado.next()) {
                valor++;
                setSaldoActivos(getSaldoActivos() + Double.parseDouble(resultado.getString("valor")));
            }
        } catch (Exception e) {
        }
        con.desconectar();
    }

    public void TotalActivosCirculantes(int idCatalogo) {
        try {
            Statement sentencia = con.conectar().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM cuenta WHERE (catalogo_idcatalogo = '" + idCatalogo + "') AND (idcuenta = 11) ORDER BY idcuenta asc");
            int valor = 0;
            while (resultado.next()) {
                valor++;
                setSaldoActivosCirculantes(getSaldoActivosCirculantes() + Double.parseDouble(resultado.getString("valor")));
            }
        } catch (Exception e) {
        }
        con.desconectar();
    }

    public void TotalActivosNoCirculantes(int idCatalogo) {
        try {
            Statement sentencia = con.conectar().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM cuenta WHERE (catalogo_idcatalogo = '" + idCatalogo + "') AND (idcuenta = 12) ORDER BY idcuenta asc");
            int valor = 0;
            while (resultado.next()) {
                valor++;
                setSaldoActivosNoCirculantes(getSaldoActivosNoCirculantes() + Double.parseDouble(resultado.getString("valor")));
            }
        } catch (Exception e) {
        }
        con.desconectar();
    }

    public void TotalPasivosCirculantes(int idCatalogo) {
        try {
            Statement sentencia = con.conectar().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM cuenta WHERE (catalogo_idcatalogo = '" + idCatalogo + "') AND (idcuenta = 21) ORDER BY idcuenta asc");
            int valor = 0;
            while (resultado.next()) {
                valor++;
                setSaldoPasivosCirculantes(getSaldoPasivosCirculantes() + Double.parseDouble(resultado.getString("valor")));
            }
        } catch (Exception e) {
        }
        con.desconectar();
    }

    public void TotalPasivosNoCirculantes(int idCatalogo) {
        try {
            Statement sentencia = con.conectar().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM cuenta WHERE (catalogo_idcatalogo = '" + idCatalogo + "') AND (idcuenta = 22) ORDER BY idcuenta asc");
            int valor = 0;
            while (resultado.next()) {
                valor++;
                setSaldoPasivosNoCirculantes(getSaldoPasivosNoCirculantes() + Double.parseDouble(resultado.getString("valor")));
            }
        } catch (Exception e) {
        }
        con.desconectar();
    }

    public void TotalCapital(int idCatalogo) {
        try {
            Statement sentencia = con.conectar().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM cuenta WHERE (catalogo_idcatalogo ='" + idCatalogo + "') AND (idpadre = 3) ORDER BY idcuenta asc");

            int valor = 0;

            while (resultado.next()) {
                valor++;
                setSaldoCapital(getSaldoCapital() + Double.parseDouble(resultado.getString("valor")));
            }
        } catch (Exception e) {
        }
        con.desconectar();
    }
     public void SaldoInventario(int idCatalogo) {
        try {
            Statement sentencia = con.conectar().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT valor FROM cuenta WHERE (catalogo_idcatalogo ='" + idCatalogo + "') AND (idpadre = 11) AND nombre like '%Inventario%'");
            int valor = 0;

            while (resultado.next()) {
                valor++;
                setSaldoInventario(getSaldoInventario() + Double.parseDouble(resultado.getString("valor")));
            }

        } catch (Exception e) {
        }
        con.desconectar();
    }

    public void SaldoVentas(int idCatalogo) {
        try {
            Statement sentencia = con.conectar().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT valor FROM cuenta WHERE (catalogo_idcatalogo ='" + idCatalogo + "') AND (idpadre = 4) AND nombre like '%Ingreso%'");
            int valor = 0;

            while (resultado.next()) {
                valor++;
                setSaldoVentas(getSaldoVentas() + Double.parseDouble(resultado.getString("valor")));
            }

        } catch (Exception e) {
        }
        con.desconectar();
    }

    public void SaldoCxC(int idCatalogo) {
        try {
            Statement sentencia = con.conectar().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT valor FROM cuenta WHERE (catalogo_idcatalogo ='" + idCatalogo + "') AND (idpadre = 11) AND nombre like '%Cobrar%'");
            int valor = 0;

            while (resultado.next()) {
                valor++;
                setSaldoCxC(getSaldoCxC() + Double.parseDouble(resultado.getString("valor")));
            }

        } catch (Exception e) {
        }
        con.desconectar();
    }

    public void SaldoCxP(int idCatalogo) {
        try {
            Statement sentencia = con.conectar().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT valor FROM cuenta WHERE (catalogo_idcatalogo ='" + idCatalogo + "') AND (idpadre = 21) AND nombre like '%Pagar%'");
            int valor = 0;

            while (resultado.next()) {
                valor++;
                setSaldoCxP(getSaldoCxP() + Double.parseDouble(resultado.getString("valor")));
            }

        } catch (Exception e) {
        }
        con.desconectar();
    }

    public void SaldoCompras(int idCatalogo) {
        try {
            Statement sentencia = con.conectar().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT valor FROM cuenta WHERE (catalogo_idcatalogo ='" + idCatalogo + "') AND (idpadre = 7) AND nombre like '%Compras%'");
            int valor = 0;

            while (resultado.next()) {
                valor++;
                setSaldoCompras(getSaldoCompras() + Double.parseDouble(resultado.getString("valor")));
            }

        } catch (Exception e) {
        }
        con.desconectar();
    }

    public void UtilidadNeta(int idCatalogo) {
        try {
            Statement sentencia = con.conectar().createStatement();

            ResultSet resultado = sentencia.executeQuery("SELECT SUM(valor) FROM cuenta WHERE (catalogo_idcatalogo = " + idCatalogo + ") AND (idcuenta =5)");
            resultado.last();
            double total_gastos;
            total_gastos = resultado.getDouble("SUM(valor)");

            resultado = sentencia.executeQuery("SELECT SUM(valor) FROM cuenta WHERE (catalogo_idcatalogo = " + idCatalogo + ") AND (idcuenta =4)");
            resultado.last();
            double total_ingresos;
            total_ingresos = resultado.getDouble("SUM(valor)");
            
            utilidadneta=total_ingresos - total_gastos;
            int valor = 0;
            while (resultado.next()) {
                valor++;
                setUtilidadneta(getUtilidadneta() + utilidadneta );
            }

        } catch (Exception e) {
        }
    }
    
    public void SaldoCostodeVentas(int idCatalogo) {
        try {
            Statement sentencia = con.conectar().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT valor FROM cuenta WHERE (catalogo_idcatalogo ='" + idCatalogo + "') AND (idpadre = 5) AND nombre like '%Costo%'");
            int valor = 0;

            while (resultado.next()) {
                valor++;
                setSaldocostoventas(getSaldocostoventas() + Double.parseDouble(resultado.getString("valor")));
            }

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

    /**
     * @return the saldoPasivosCirculantes
     */
    public double getSaldoPasivosCirculantes() {
        return saldoPasivosCirculantes;
    }

    /**
     * @param saldoPasivosCirculantes the saldoPasivosCirculantes to set
     */
    public void setSaldoPasivosCirculantes(double saldoPasivosCirculantes) {
        this.saldoPasivosCirculantes = saldoPasivosCirculantes;
    }

    /**
     * @return the saldoPasivosNoCirculantes
     */
    public double getSaldoPasivosNoCirculantes() {
        return saldoPasivosNoCirculantes;
    }

    /**
     * @param saldoPasivosNoCirculantes the saldoPasivosNoCirculantes to set
     */
    public void setSaldoPasivosNoCirculantes(double saldoPasivosNoCirculantes) {
        this.saldoPasivosNoCirculantes = saldoPasivosNoCirculantes;
    }

    public double getSaldoActivosCirculantes() {
        return saldoActivosCirculantes;
    }

    public void setSaldoActivosCirculantes(double saldoActivosCirculantes) {
        this.saldoActivosCirculantes = saldoActivosCirculantes;
    }

    public double getSaldoActivosNoCirculantes() {
        return saldoActivosNoCirculantes;
    }

    public void setSaldoActivosNoCirculantes(double saldoActivosNoCirculantes) {
        this.saldoActivosNoCirculantes = saldoActivosNoCirculantes;
    }

    /**
     * @return the saldoInventario
     */
    public double getSaldoInventario() {
        return saldoInventario;
    }

    /**
     * @param saldoInventario the saldoInventario to set
     */
    public void setSaldoInventario(double saldoInventario) {
        this.saldoInventario = saldoInventario;
    }

    /**
     * @return the saldoVentas
     */
    public double getSaldoVentas() {
        return saldoVentas;
    }

    /**
     * @param saldoVentas the saldoVentas to set
     */
    public void setSaldoVentas(double saldoVentas) {
        this.saldoVentas = saldoVentas;
    }

    /**
     * @return the saldoCxC
     */
    public double getSaldoCxC() {
        return saldoCxC;
    }

    /**
     * @param saldoCxC the saldoCxC to set
     */
    public void setSaldoCxC(double saldoCxC) {
        this.saldoCxC = saldoCxC;
    }

    /**
     * @return the saldoCxP
     */
    public double getSaldoCxP() {
        return saldoCxP;
    }

    /**
     * @param saldoCxP the saldoCxP to set
     */
    public void setSaldoCxP(double saldoCxP) {
        this.saldoCxP = saldoCxP;
    }

    /**
     * @return the saldoCompras
     */
    public double getSaldoCompras() {
        return saldoCompras;
    }

    /**
     * @param saldoCompras the saldoCompras to set
     */
    public void setSaldoCompras(double saldoCompras) {
        this.saldoCompras = saldoCompras;
    }

    /**
     * @return the utilidadneta
     */
    public double getUtilidadneta() {
        return utilidadneta;
    }

    /**
     * @param utilidadneta the utilidadneta to set
     */
    public void setUtilidadneta(double utilidadneta) {
        this.utilidadneta = utilidadneta;
    }

    /**
     * @return the saldocostoventas
     */
    public double getSaldocostoventas() {
        return saldocostoventas;
    }

    /**
     * @param saldocostoventas the saldocostoventas to set
     */
    public void setSaldocostoventas(double saldocostoventas) {
        this.saldocostoventas = saldocostoventas;
    }
}
