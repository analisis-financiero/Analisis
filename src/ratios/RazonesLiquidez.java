/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ratios;


public class RazonesLiquidez {

    public double capitalTrabajo(double activoCirculante, double pasivoCirculante) {
        double capitalTrabajo = 0.00;
        capitalTrabajo = (activoCirculante) - (pasivoCirculante);
        
        System.out.println("ACT CIR: " + activoCirculante);
        System.out.println("PAS CIR: " + pasivoCirculante);
        return capitalTrabajo;
    }

    public double razonCirculante(double activoCirculante, double pasivoCirculante) {
        double razonCir ;
        razonCir = activoCirculante / pasivoCirculante;
        return razonCir;
    }

    public double razonAcida(double activoCirculante, double pasivoCirculante, double inventarios) {
        double pAcida ;
        pAcida = (activoCirculante - inventarios) / pasivoCirculante;
        return pAcida;
    }

    public double superAcida(double efec_equi, double CxC, double pasivoCirculante) {
        double supAcida ;
        supAcida = (efec_equi + CxC) / pasivoCirculante;
        return supAcida;
    }

    public double fondoManiobra(double actCirculante,double pasivoCirculante,double totActivo){
        double fonMani ;
        fonMani = (actCirculante - pasivoCirculante)/totActivo;
        return fonMani;
    }
}
