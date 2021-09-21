package br.ufscar.dc.pooa.java.peganoticias;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;

/* Classe utilizada para baixar titulos e links das noticias principais do site globo,
 * para que elas sejam utilizadas por um metodo especificado como entrada. */
public class SiteGlobo extends ModelHtmlParser{

    public SiteGlobo (ModelHtmlAttributeUse howToUse){
        super(howToUse);
    }
    
    // Sera retornado os titulos de element e os links das element.
    @Override
    protected void setHtmlAttributes() {
        htmlAttributes.add(new HtmlAttribute("https://www.globo.com/","a", "post__link","title"));
        htmlAttributes.add(new HtmlAttribute("https://www.globo.com/","a", "post__link","href"));
    }
    
    // Abaixa a pagina especificada, retornando-a como um Document, onde sera feita
    // uma selecao dado uma tag, className e attribute especificados. Armazenando a lista
    // de Strings resultantes em cada um dos HtmlAttribute.
    @Override
    protected void selectAttributesValues(){
        for(int i = 0 ; i < htmlAttributes.size(); i++){
            
            // Possivel que o HandShake nao de certo!
            Document doc = null;
            try {
                doc = Jsoup.connect(htmlAttributes.get(i).getUrl()).get();
            } catch (IOException ex) {
                Logger.getLogger(SiteGlobo.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Seleciona todos eles elementos dado uma tag e um class name especificado.
            Elements element = doc.select(htmlAttributes.get(i).getTag() + "." + htmlAttributes.get(i).getClassName());

            // Caso o nome do atributo seja "text" e necessario acessar o conteudo.
            if(htmlAttributes.get(i).getAttributeName().equals("text"))
                for(int j = 0 ; j < element.size(); j++)
                    htmlAttributes.get(i).addAttributeValue(element.get(j).text());
            else
                for(int j = 0 ; j < element.size(); j++)
                    htmlAttributes.get(i).addAttributeValue(element.get(j).attr(htmlAttributes.get(i).getAttributeName()));
        }
    }
    
}
