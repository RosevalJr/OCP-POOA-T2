package br.ufscar.dc.pooa.java.peganoticias;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class HtmlAttribute {
    
    // Argumentos para selecionar htmlAttribute.
    private final String url;
    private final String type;
    private final String className;
    private final String argument;
    
    // Onde sera armazenado os atributos encontrados.
    private final ArrayList<String> attributes;
    
    // Inicializa todas as variaveis finais.
    public HtmlAttribute(String url, String type, String className, String argument){
        this.url = url;
        this.type = type;
        this.className = className;
        this.argument = argument;
        this.attributes = new ArrayList<>();
        
    }
    
    // Preenche a estrutura com os argumentos encontrados.
    public void getHtmlAttribute(){
        Document doc = null;
        try {
            doc = Jsoup.connect(this.url).get();
        } catch (IOException ex) {
            Logger.getLogger(HtmlAttribute.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Elements noticias = doc.select(this.type + "." + this.className);
        
        if(this.argument.equals("text"))
            noticias.forEach(n -> {
                attributes.add(n.text());
            });
        else
            noticias.forEach(n -> {
                attributes.add(n.attr(this.argument));
            });
            
    }
    
    public String getAttribute(int i){
        return attributes.get(i);
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
