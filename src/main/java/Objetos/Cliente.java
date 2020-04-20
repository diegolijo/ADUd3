/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author jit
 */
public class Cliente {

    private int idCliente;
    private String nombre;
    private String apellidos;
    private String email;

    public Cliente(int idCliente, String nombre, String apellidos, String email) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
    }

    /*
     *  Getters 
     */
    public int getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEmail() {
        return email;
    }

}
