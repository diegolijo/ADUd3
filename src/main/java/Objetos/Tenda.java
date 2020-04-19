
package Objetos;


public class Tenda {
    
    private int idTenda;
    private String nome;
    private String cidade;
    private int idProvincia;


    public Tenda(int idTienda, String nome, String ciudad, int idProvincia){
        this.idTenda=idTienda;
        this.nome=nome;
        this.cidade=ciudad;   
        this.idProvincia=idProvincia;     
    }

    /*
     *  Getters 
     */
    
    public int getIdTenda() {
        return idTenda;
    }

    public String getNome() {
        return nome;
    }

    public String getCidade() {
        return cidade;
    }

    public int getIdProvincia() {
        return idProvincia;
    }
    
    // setters

    public void setIdTenda(int idTenda) {
        this.idTenda = idTenda;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setIdProvincia(int provincia) {
        this.idProvincia = provincia;
    }
    
    

//    @Override
//    public String toString(){       
//        return nome + " " +cidade + " "+ provincia.getNome();        
//    }
  
}
