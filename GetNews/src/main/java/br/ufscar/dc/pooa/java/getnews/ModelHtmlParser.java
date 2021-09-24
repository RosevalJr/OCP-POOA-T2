package br.ufscar.dc.pooa.java.getnews;

import java.util.ArrayList;

// Primeira extensao
/* Classe abstrata modelo para a implementacao de classes que baixam HtmlAttributesValues
 * de sites especificados e aplicam um metodo previamente implementado para utilizar esses
 * HtmlAttributeValues como desejado. */
abstract class ModelHtmlParser {
    
    // Lista dos HtmlAttributes que serao definidos, cada argumento tambem contem
    // os HtmlAttributeValue encontrados em uma lista de strings.
    protected final ArrayList<HtmlAttribute> htmlAttributes;

    
    // Inicializa o vetor de htmlAttributes, seta quais sao os HtmlAttributes,
    // seta qual e o modelo de utilizacao dos HtmlAttributesValues (Strings) que
    // serao retornados e por fim busca por todas as strings de cada um dos HtmlAttributes.
    public ModelHtmlParser(){
        htmlAttributes = new ArrayList<>();
    }
    
    // Seta quais serao cada um dos HtmlAttributes (qual site sera baixado e qual
    // tag/className sera selecionada).
    protected abstract void setHtmlAttributes();
    // Busca por todas as strings de cada um dos HtmlAttributes.
    protected abstract void selectAttributesValues();
    
    // Aplica o modelo de utilizacao dos dados selecionados neste parser.
    // Dessa forma e possivel aplicar diversos modelUse implementados em uma mesma
    // instancia ModelHtmlParser.
    public final void useHtmlAttributesValues(ModelHtmlAttributeUse modelUse){
        // Primeira execucao deve setar os HtmlAttributes e selecionar os AttributesValues.
        if(htmlAttributes.isEmpty()){
            this.setHtmlAttributes();
            this.selectAttributesValues();
        }
        modelUse.use(htmlAttributes);
    }
    
}
