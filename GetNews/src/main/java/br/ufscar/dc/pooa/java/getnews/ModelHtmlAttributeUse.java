package br.ufscar.dc.pooa.java.getnews;

import java.util.ArrayList;

// Segunda extensao.
/* Classe abstrata utilizada para implementar o método que define como sera utilizada
 * os htmlAttributeValues encontrados de cada um dos htmlAttribute especificados. Essa
 * classe e utilizada pela classe ModelHtmlParser. */
abstract class ModelHtmlAttributeUse {
    abstract public void use(ArrayList<HtmlAttribute> htmlAttributes);
}
