package br.ufscar.dc.pooa.java.getnews;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import org.jsoup.nodes.Element;

/* Classe utilizada para baixar titulos e links das noticias principais do site globo,
 * para que elas sejam utilizadas dado um algoritmo de utilização (ModelHtmlAttributeUse). */
public class GloboParser extends ModelHtmlParser{

    public GloboParser (){
        super();
    }
    
    // Tanto o titulo quanto o link se encontram dentro da mesma tag a.post__link
    // so muda o argumento.
    @Override
    protected void setHtmlAttributes() {
        // Nao e necessario realizar uma busca hierquica para encontrar os links.
        // Entao setei o familyTag e familyClassName como nulos.
        htmlAttributes.add(new HtmlAttribute("https://www.globo.com/", "a", 
                "post__link", null, null,"title"));
        htmlAttributes.add(new HtmlAttribute("https://www.globo.com/", "a", 
                "post__link", null, null,"href"));
    }
    
    // Abaixa a pagina especificada (Jsoup), retornando-a como um Document, onde sera feita
    // uma selecao dado uma tag, className e attributeName de cada htmlAttribute setado.
    // Por fim, o resultado da selecao (uma lista de Strings) e armazenando na estrutura
    // ArrayList<String> htmlAttributeValues do seu respectivo htmlAttribute.
    @Override
    protected void selectAttributesValues(){
        // Busca para cada um dos htmlAttributes.
        for(int i = 0 ; i < htmlAttributes.size(); i++){
            
            // Possivel que o HandShake nao de certo (site da globo costuma recusar mais)!
            Document doc = null;
            try {
                doc = Jsoup.connect(htmlAttributes.get(i).getUrl()).get();
            } catch (IOException ex) {
                Logger.getLogger(GloboParser.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Seleciona todos elementos dado uma tag e um className especificado.
            Elements elements = doc.select(htmlAttributes.get(i).getTag() + "." +
                    htmlAttributes.get(i).getClassName());
            
            // Nao e necessario realizar uma busca hierquica para encontrar os links!
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
