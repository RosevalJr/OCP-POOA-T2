/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.pooa.java.getnews;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author moleke
 */
public class SiteBbcLondon extends ModelHtmlParser{
    
    // Sera retornado os titulos de element e os links das element.
    @Override
    protected void setHtmlAttributes() {
        // Busca na hierarquia comeca no h3.gs-c-promo-heading__title e vai ate o
        // a.gs-c-promo-heading
        htmlAttributes.add(new HtmlAttribute("https://www.bbc.com/news/england/london", 
                "h3", "gs-c-promo-heading__title", null, null, "text"));
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
            Elements elements = doc.select(htmlAttributes.get(i).getTag() + "." + htmlAttributes.get(i).getClassName());
                // Caso o nome do atributo seja "text" e necessario acessar o conteudo.
                if(htmlAttributes.get(i).getAttributeName().equals("text"))
                    for(int j = 0 ; j < elements.size(); j++)
                        htmlAttributes.get(i).addAttributeValue(elements.get(j).text());
                else
                    for(int j = 0 ; j < elements.size(); j++)
                        htmlAttributes.get(i).addAttributeValue(elements.get(j).attr(htmlAttributes.get(i).getAttributeName()));
        }
    }
    
}
