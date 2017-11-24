/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;


public class Empresa {

   private String nombre;
    private String email;
    private String direccion;

    public Empresa() {
        nombre = null;
        email = null;
        direccion = null;
    }

    public Empresa(String nombre, String email, String direccion) {
        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
    }

    /**
     * @return the name
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param name the name to set
     */
    public void setNombre(String name) {
        this.nombre = name;
    }

    /**
     * @return the pass
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param pass the pass to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the passrepeat
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param passrepeat the passrepeat to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
}
