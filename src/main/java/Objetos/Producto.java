

package Objetos;


public class Producto {

    private int idProducto;
    private String nombre;
    private String descripcion;
    private float precio;

    /**
     * Constructor
     *
     * @param idProducto Clave primaria
     * @param nombre Nombre
     * @param descripcion Descripción
     * @param precio Importe
     */
    public Producto(int idProducto, String nombre, String descripcion, float precio) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    /*
     *  Getters 
     */
    public int getIdProducto() {
        return idProducto;
    }

    public String getNome() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getPrecio() {
        return precio;
    }
    
    
    

}
