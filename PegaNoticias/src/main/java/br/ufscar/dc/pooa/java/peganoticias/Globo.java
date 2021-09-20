package br.ufscar.dc.pooa.java.peganoticias;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;

public class Globo extends modelHtmlParser{

    public Globo (ModelHtmlAttributeUse howToUse){
        super(howToUse);
    }
    
    @Override
    protected void setHtmlAttributes() {
        // Quero dois atributos. O titulo da noticia e o link dela.
        attributes.add(new HtmlAttribute("https://www.globo.com/","a", "post__link","title"));
        attributes.add(new HtmlAttribute("https://www.globo.com/","a", "post__link","href"));
    }
    
    @Override
    protected void selectAttributes(){
        // Para os dois atributos que eu quero, vou conectar no site e buscar pela estrutura especificada.
        // Para cada um encontrado é colocado dentro da lista de string de cada um dos HtmlAttribute.
        for(int i = 0 ; i < attributes.size(); i++){
            Document doc = null;
            try {
                doc = Jsoup.connect(attributes.get(i).getUrl()).get();
            } catch (IOException ex) {
                Logger.getLogger(Globo.class.getName()).log(Level.SEVERE, null, ex);
            }

            Elements noticias = doc.select(attributes.get(i).getType() + "." + attributes.get(i).getClassName());

            if(attributes.get(i).getArgument().equals("text"))
                for(int j = 0 ; j < noticias.size(); j++)
                    attributes.get(i).addAttribute(noticias.get(j).text());
            else
                for(int j = 0 ; j < noticias.size(); j++)
                    attributes.get(i).addAttribute(noticias.get(j).attr(attributes.get(i).getArgument()));
        }
    }
    
}
