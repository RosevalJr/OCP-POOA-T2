package br.ufscar.dc.pooa.java.getnews;

import java.util.ArrayList;

// Segunda extensao!
/* Classe abstrata utilizada para implementar o método que define como serao utilizados
 * os htmlAttributeValues encontrados de cada um dos htmlAttribute especificados. */
abstract class ModelHtmlAttributeUse {
    abstract public void use(ArrayList<HtmlAttribute> htmlAttributes);
}
