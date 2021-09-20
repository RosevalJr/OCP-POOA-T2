package br.ufscar.dc.pooa.java.peganoticias;

import java.util.ArrayList;

public class HtmlAttribute {
    
    // Argumentos para selecionar htmlAttribute.
    private final String url;
    private final String type;
    private final String className;
    private final String argument;
    
    // Estrutura para armazenar os atributos encontrados.
    private final ArrayList<String> attributes;
    
    // Inicializa todas as variaveis finais.
    public HtmlAttribute(String url, String type, String className, String argument){
        this.url = url;
        this.type = type;
        this.className = className;
        this.argument = argument;
        this.attributes = new ArrayList<String>();
        
    }
    
    public String getAttribute(int i){
        return attributes.get(i);
    }
    
    public void addAttribute(String attribute){
        this.attributes.add(attribute);
    }
    
    public int getAttributesSize(){
        return attributes.size();
    }
    
    public String getUrl(){
        return this.url;
    }
     
    public String getType(){
        return this.type;
    }
    
    public String getClassName(){
        return this.className;
    }
    
    public String getArgument(){
        return this.argument;
    }
}
