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
public class SiteOul extends ModelHtmlParser{

    public SiteOul(){
        super();
    }
    
    // Sera retornado os titulos de element e os links das element.
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

            if(htmlAttributes.get(i).getFamilyTag() == null){
                // Caso o nome do atributo seja "text" e necessario acessar o conteudo.
                if(htmlAttributes.get(i).getAttributeName().equals("text"))
                    for(int j = 0 ; j < elements.size(); j++)
                        htmlAttributes.get(i).addAttributeValue(elements.get(j).text());
                else
                    for(int j = 0 ; j < elements.size(); j++)
                        htmlAttributes.get(i).addAttributeValue(elements.get(j).attr(htmlAttributes.get(i).getAttributeName()));
            }else{
                // Busca hierarquiaca. (Procurando o primerio tag "a" na hierarquia do "h1.titulo")
                for(Element e : elements ){
                    Element parent = e.parent(); 
                    // Para cada elemento encontrar o "a" em sua hierarquia.
                    while(parent != null && !parent.tagName().equals(htmlAttributes.get(i).getFamilyTag()))
                        parent = parent.parent();
                    // Caso tenha sido encontrado!
                    if(parent != null && parent.tagName().equals(htmlAttributes.get(i).getFamilyTag())){
                        if (htmlAttributes.get(i).getAttributeName().equals("text"))
                            // Caso o nome do atributo seja "text" e necessario acessar o conteudo.
                            htmlAttributes.get(i).addAttributeValue(parent.text());
                        else
                            htmlAttributes.get(i).addAttributeValue(parent.attr(htmlAttributes.get(i).getAttributeName()));
                    }
                        
                }
            }
        }
    }
    
}
