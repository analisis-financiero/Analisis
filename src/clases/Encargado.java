/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;


public class Encargado {

    private String nombres;
    private String apellidos;
    private String fechanac;

    public Encargado() {
        nombres = null;
        apellidos = null;
        fechanac = null;
    }

    public Encargado(String nombres, String apellidos, String fechanac) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechanac = fechanac;
    }

    /**
     * @return the nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * @param nombres the nombres to set
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return the fechanac
     */
    public String getFechanac() {
        return fechanac;
    }

    /**
     * @param fechanac the fechanac to set
     */
    public void setFechanac(String fechanac) {
        this.fechanac = fechanac;
    }
}
