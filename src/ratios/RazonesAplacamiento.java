/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ratios;

/**
 *
 * @author Tablo
 */
public class RazonesAplacamiento {
    
    public double endeudamiento(double totPasivo, double totActivo){
        double deuda ;
        deuda = totPasivo/totActivo;
        return deuda;
    }
    
    public double endeudamientoCorto(double pasCir, double totActivo){
        double deudaCorto ;
        deudaCorto = pasCir/totActivo;
        return deudaCorto;
    }
    
    public double endeudamientoLargo(double pasLargo, double totActivo){
        double deudaLargo = 0.00;
        deudaLargo = pasLargo/totActivo;
        return deudaLargo;
    }
     
    public double autonomia(double patrimonio, double totActivo){
        double auto ;
        auto = patrimonio/totActivo;
        return auto;
    }
    
    public double apalancamientoExterno(double totPasivo, double patrimonio){
        double apaExt;
        apaExt = totPasivo/patrimonio;
        return apaExt;
    }
    
    public double apalancamientoInterno(double patrimonio, double totPasivo){
        double apaInt = 0.00;
        apaInt = patrimonio/totPasivo;
        return apaInt;
    }
    
    public double capitalizacionExterna(double DxPalp, double patrimonio){
        double capExt ;
        capExt = DxPalp / (patrimonio+DxPalp);
        return capExt;
    }
    
    public double capitalizacionInterna(double DxPalp, double patrimonio){
        double capInt ;
        capInt = patrimonio / (patrimonio + DxPalp);
        return capInt;
    }
}
