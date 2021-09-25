package br.ufscar.dc.pooa.java.getnews;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/* Classe utilizada para baixar titulos das noticias principais do site bbc london,
 * para que elas sejam utilizadas dado um algoritmo de utilização (ModelHtmlAttributeUse). */
public class BbcLondonParser extends ModelHtmlParser{
    
    // Todos os titulos estao em tags "h3.gs-c-promo-heading__title".
    @Override
    protected void setHtmlAttributes() {
        // Unico htmlAttribute para os titulos principais.
        htmlAttributes.add(new HtmlAttribute("https://www.bbc.com/news/england/london", 
                "h3", "gs-c-promo-heading__title", null, null, "text"));
    }
    
    // Abaixa a pagina especificada, retornando-a como um Document, onde sera feita
    // uma selecao dado uma tag, className e attribute especificados. Armazenando a lista
    // de Strings resultantes em cada um dos HtmlAttributes.
    @Override
    protected void selectAttributesValues(){
        for(int i = 0 ; i < htmlAttributes.size(); i++){
            
            // Possivel que o HandShake nao de certo!
            Document doc = null;
            try {
                doc = Jsoup.connect(htmlAttributes.get(i).getUrl()).get();
            } catch (IOException ex) {
                Logger.getLogger(GloboParser.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Seleciona todos elementos dado uma tag e um class name especificado.
            Elements elements = doc.select(htmlAttributes.get(i).getTag() + "." +
                    htmlAttributes.get(i).getClassName());
            
            // Nao e necessario realizar uma busca hierquica!
            // Caso o nome do atributo seja "text" e necessario acessar o conteudo.
            if(htmlAttributes.get(i).getAttributeName().equals("text"))
                for(Element element: elements)
                    htmlAttributes.get(i).addAttributeValue(element.text());
            else
                for(Element element: elements)
                    htmlAttributes.get(i).addAttributeValue(element.attr(htmlAttributes.get(i).getAttributeName()));
        }
    }
    
}
