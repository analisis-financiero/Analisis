/*
 * Clase Cuenta.
 */
package clases;


public class Cuenta {
    
    private double valor;
    private String nombre;
    private int id;
    private int idPadre;
    private int idcatalogo;
    private int tipo_saldo;

    public Cuenta() {
        this.valor = 0.0;
        this.id = 0;
        this.idPadre = 0;
        this.nombre = "";
        this.idcatalogo = 0;
        this.tipo_saldo=0;
    }

    public Cuenta(int id, String nombre, double valor, int padre, int idcatalogo, int tipo_saldo) {
        this.id = id;
        this.idPadre = padre;
        this.nombre = nombre;
        this.valor = valor;
        this.idcatalogo = idcatalogo;
        this.tipo_saldo= tipo_saldo;
    }

    /**
     * @return the valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the idPadre
     */
    public int getIdPadre() {
        return idPadre;
    }

    /**
     * @param idPadre the idPadre to set
     */
    public void setIdPadre(int idPadre) {
        this.idPadre = idPadre;
    }

    /**
     * @return the idcatalogo
     */
    public int getIdcatalogo() {
        return idcatalogo;
    }

    /**
     * @param idcatalogo the idcatalogo to set
     */
    public void setIdcatalogo(int idcatalogo) {
        this.idcatalogo = idcatalogo;
    }
      public int getTipo_saldo() {
        return tipo_saldo;
    }

    /**
     * @param tipo_saldo the tipo_saldo to set
     */
    public void setTipo_saldo(int tipo_saldo) {
        this.tipo_saldo = tipo_saldo;
    }
    
}
