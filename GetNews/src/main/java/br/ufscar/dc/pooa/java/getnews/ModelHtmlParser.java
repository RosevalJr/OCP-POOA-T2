package br.ufscar.dc.pooa.java.getnews;

import java.util.ArrayList;

// Primeira extensao!
/* Classe abstrata para a implementacao de classes que baixam HtmlAttributesValues
 * de sites especificados, aplicando um metodo previamente implementado para utilizar esses
 * HtmlAttributeValues como desejado. */
abstract class ModelHtmlParser {
    
    // Lista dos HtmlAttributes que serao definidos, cada argumento tambem contem
    // o HtmlAttributeValues (atributo) como uma lista de strings.
    protected final ArrayList<HtmlAttribute> htmlAttributes;
    
    // Inicializa o vetor de htmlAttributes.
    public ModelHtmlParser(){
        htmlAttributes = new ArrayList<>();
    }
    
    // Seta quais serao cada um dos HtmlAttributes (qual site sera baixado e qual
    // tag, className e/ou familyTag,familyClassName e attributeName sera selecionada).
    protected abstract void setHtmlAttributes();
    
    // Busca por todas as strings (htmlAttributeValue) de cada um dos HtmlAttributes.
    protected abstract void selectAttributesValues();
    
    // Aplica o modelo de utilizacao dos dados selecionados neste parser.
    // Dessa forma e possivel aplicar diversos modelUse implementados em uma mesma
    // instancia ModelHtmlParser, sendo que conteudo sera baixado e armazenado nas
    // estruturas apenas uma vez.
    public final void useHtmlAttributesValues(ModelHtmlAttributeUse modelUse){
        // Primeira execucao deve setar os HtmlAttributes e selecionar os AttributesValues.
        if(htmlAttributes.isEmpty()){
            this.setHtmlAttributes();
            this.selectAttributesValues();
        }
        modelUse.use(htmlAttributes);
    }
    
}
