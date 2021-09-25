package br.ufscar.dc.pooa.java.getnews;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/* Classe utilizada para baixar titulo/link principal e secundarios titulos/links das
 * noticias do site oul, para que elas sejam utilizadas dado um algoritmo
 * de utilização (ModelHtmlAttributeUse). */
public class OulParser extends ModelHtmlParser{

    public OulParser(){
        super();
    }
    
    // Neste site o titulo principal esta em um "h1.titulo", os outros titulos 
    // se encontram em "h2.titulo", ja os links se encontram no primeiro "a"
    // acima na hierarquia. Sendo assim, nesta classe sera feita uma busca por
    // hierarquia tambem.
    @Override
    protected void setHtmlAttributes() {
        // Titulo da Noticia principal.
        htmlAttributes.add(new HtmlAttribute("https://www.uol.com.br/", "h1", 
                "titulo", null, null, "text"));
        // Link da Noticia principal (busca por hierarquia).
        htmlAttributes.add(new HtmlAttribute("https://www.uol.com.br/","h1",
                "titulo", "a", null, "href"));
        // Todos os outros Titulos.
        htmlAttributes.add(new HtmlAttribute("https://www.uol.com.br/","h2",
                "titulo", null, null, "text"));
        // Todos os outros links (busca por hierarquia).
        htmlAttributes.add(new HtmlAttribute("https://www.uol.com.br/","h2",
                "titulo", "a", null, "href"));
    }
    
    // Abaixa a pagina especificada, retornando-a como um Document, onde sera feita
    // uma selecao dado uma tag, className e attribute especificados. Caso o familyTag 
    // esteja setado sera feita tambem uma busca por hierarquia, buscando a cima da 
    // hierarquia a primeira tag especificada. Armazenando a lista de Strings 
    // resultantes em cada um dos HtmlAttributes.
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
            Elements elements = doc.select(htmlAttributes.get(i).getTag() + "." + htmlAttributes.get(i).getClassName());

            // Caso o familyTag nao seja nulo uma busca hierarquica tambem sera realizada.
            if(htmlAttributes.get(i).getFamilyTag() == null){
                // Caso o nome do atributo seja "text" e necessario acessar o conteudo.
            if(htmlAttributes.get(i).getAttributeName().equals("text"))
                for(Element element: elements)
                    htmlAttributes.get(i).addAttributeValue(element.text());
            else
                for(Element element: elements)
                    htmlAttributes.get(i).addAttributeValue(element.attr(htmlAttributes.get(i).getAttributeName()));
            }else{
                // Busca hierarquica. (Procurando o primerio tag "a" na hierarquia do "h1.titulo" e seu atributo)
                for(Element e : elements ){
                    Element parent = e.parent(); 
                    // Para cada elemento encontrar o primerio "a" acima em sua hierarquia.
                    while(parent != null && !parent.tagName().equals(htmlAttributes.get(i).getFamilyTag()))
                        parent = parent.parent();
                    // Caso tenha sido encontrado!
                    if(parent != null && parent.tagName().equals(htmlAttributes.get(i).getFamilyTag())){
                        // Caso o nome do atributo seja "text" e necessario acessar o conteudo.
                        if (htmlAttributes.get(i).getAttributeName().equals("text"))
                            htmlAttributes.get(i).addAttributeValue(parent.text());
                        else
                            htmlAttributes.get(i).addAttributeValue(parent.attr(htmlAttributes.get(i).getAttributeName()));
                    }
                        
                }
            }
        }
    }
    
}
