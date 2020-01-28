package jit.adud3grafica;

public class Noticia {

    private String titulo;

    /*
     * Constructor 1
     */
    public Noticia() {
        this.titulo = new String("");
    }

    /*
     * Constructor 2
     *
     * @param   titulo  Título de la noticia
     */
    public Noticia(String titulo) {
        this.titulo = titulo;
    }

    /*
     * Devuelve el título de una noticia
     *
     * @return  Título de la noticia
     */
    public String getTitulo() {
        return titulo;
    }

    /*
     * Guarda el título de una noticia
     *
     * @param   titulo  Título de la noticia
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
