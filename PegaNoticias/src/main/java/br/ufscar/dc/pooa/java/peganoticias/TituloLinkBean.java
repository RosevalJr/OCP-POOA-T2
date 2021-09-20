package br.ufscar.dc.pooa.java.peganoticias;

public class TituloLinkBean {
    private String titulo;
    private String link;
    
    public TituloLinkBean(String titulo, String link){
        this.titulo = titulo;
        this.link = link;
    }
    
    public String getTitulo(){
        return this.titulo;
    }
    
    public String getLink(){
        return this.link;
    }
    
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    
    public void setLink(String link){
        this.link = link;
    }
}
