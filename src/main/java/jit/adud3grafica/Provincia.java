package jit.adud3grafica;


public class Provincia {

    private int id;
    private String nome;

    public Provincia() {

    }

    public Provincia(int id, String nombre) {
        this.id = id;
        this.nome = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
