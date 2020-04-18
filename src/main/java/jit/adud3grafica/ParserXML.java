package jit.adud3grafica;

import org.xml.sax.Attributes;              
import org.xml.sax.SAXException;            
import org.xml.sax.helpers.DefaultHandler;  
import java.util.ArrayList;                 

public class ParserXML extends DefaultHandler {

    private ArrayList<Noticia> noticias;
    private Noticia noticiaAux;
    private String cadenaTexto;

    /*
     * Constructor
     */
    public ParserXML() {
        super();
    }

    /*
     * Método que se ejecuta antes de parsear nada
     */
    @Override
    public void startDocument() throws SAXException {
    }

    /*
     * Método que se ejecuta al finalizar el  parseo
     *
     */
    @Override
    public void endDocument() throws SAXException {
    }

    /*
     * Metedo que se ejecuta al comenzar a leer una etiqueta
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        //Si encontramos la etiqueta channel creamos un nuevo ArrayList
        if (localName == "channel") {
            this.noticias = new ArrayList<Noticia>();
        } //Si encontramos la etiqueta item creamos un nuevo objeto del tipo Noticia
        else if (localName == "item") {
            this.noticiaAux = new Noticia();
        }

    }

    /*
     * Metedo que se ejecuta al comenzar al terminar de leer una etiqueta
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        //Terminamos de leer la etiqueta title, por lo que podemos grabar el texto que continene
        if (localName == "title") {
            //this.noticiaAux.setTitulo(cadenaTexto);
            //Ojo antes de añadirlo comprobamos que exista el objeto, esto lo hacemos porque existen etiquetas title fuera de item
            if (this.noticiaAux != null) {
                this.noticiaAux.setTitulo(cadenaTexto);
            }
        } //Terminamos de leer la etiqueta item, por lo que podemos guardar el objeto auxiliar de tipo Noticia en el ArrayList
        else if (localName == "item") {
            this.noticias.add(this.noticiaAux);

        }
    }

    /*
     * Este metodo se ejecuta cuando lee una cadena de texto
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // Guardamos todo el texto entre caracteres en la cadena de texto auxiliar
        this.cadenaTexto = new String(ch, start, length);
    }

 
    public ArrayList<Noticia> getNoticias() {
        return noticias;
    }

}
