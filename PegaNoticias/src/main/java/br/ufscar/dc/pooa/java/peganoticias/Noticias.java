package br.ufscar.dc.pooa.java.peganoticias;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


// O ideal seria printa ja a noticia e o link.
// Entao a pessoa instancia um objeto dessa classe definindo ja
// uma url e str de selecao de noticia.
// depois do attr pro titulo e pro link.

// Por fim e chamado o metodo getNoticias, e isso retorna uma string
// de lista.

// Dado essa lista de strings retornadas, ai isso pode ser tratado melhor na
// classe abstrata la.
public class Noticias {
    
    private final String url;
    private final String strSelectNoticia;
    private final String attrTitulo;
    private final String attrLink;
    
    // Tudo que sera usado e definido aqui.
    public Noticias(String url, String strSelectNoticia, String attrTitulo,
            String attrLink){
        this.url = url;
        this.strSelectNoticia = strSelectNoticia;
        this.attrTitulo = attrTitulo;
        this.attrLink = attrLink;
    }
    
    // SO depois de definido esse metodo pode ser chamado.
    public ArrayList<TituloLinkBean> getNoticias() throws IOException{
        Document doc = Jsoup.connect(url).get();
        
        Elements noticias = doc.select(strSelectNoticia);
        
        ArrayList<TituloLinkBean> listaNoticias = new ArrayList<>();
        
        noticias.forEach(n -> {
            listaNoticias.add(new TituloLinkBean(n.attr(attrTitulo),
                    n.attr(attrLink)));
        });
        return listaNoticias;
    }
}
