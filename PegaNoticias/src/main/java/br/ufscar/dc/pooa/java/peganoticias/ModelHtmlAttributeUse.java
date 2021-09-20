package br.ufscar.dc.pooa.java.peganoticias;

// Classe abstrata modelo para a utilizacao dos

import java.util.ArrayList;

// Classe abstrata utilizada para implementar o método que define como sera utilizada
// os atributos encontrados no modelHtmlParser.
abstract class ModelHtmlAttributeUse {
    abstract public void use(ArrayList<HtmlAttribute> atributos);
}
