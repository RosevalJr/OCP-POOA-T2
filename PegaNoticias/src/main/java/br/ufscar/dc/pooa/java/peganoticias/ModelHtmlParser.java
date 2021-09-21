package br.ufscar.dc.pooa.java.peganoticias;

import java.util.ArrayList;

// Primeira extensao
/* Classe abstrata modelo para a implementacao de classes que baixam HtmlAttributesValues
 * de sites especificados e aplicam um metodo previamente implementado para utilizar esses
 * HtmlAttributeValues como desejado. */
abstract class ModelHtmlParser {
    
    // Lista dos HtmlAttributes que serao definidos, cada argumento tambem contem
    // os HtmlAttributeValue encontrados em uma lista de strings.
    protected final ArrayList<HtmlAttribute> htmlAttributes;
    // Define como serao utilizadas as strings encontradas.
    private ModelHtmlAttributeUse modelUse;
    
    // Inicializa o vetor de htmlAttributes, seta quais sao os HtmlAttributes e por fim
    // seta qual e o modelo de utilizacao dos HtmlAttributesValues (Strings) que serao retornados.
    public ModelHtmlParser(ModelHtmlAttributeUse modelUse){
        htmlAttributes = new ArrayList<HtmlAttribute>();
        this.setHtmlAttributes();
        this.modelUse = modelUse; 
    }
    
    // Seta quais serao cada um dos HtmlAttributes (qual site sera baixado e qual
    // tag/className sera selecionada).
    protected abstract void setHtmlAttributes();
    // Busca por todas as strings de cada um dos HtmlAttributes.
    protected abstract void selectAttributesValues();
    
    // Busca pelos htmlAttribuesValues e os utiliza com o metodo passado para esse
    // modelo.
    public final void useHtmlAttributesValues(){
        this.selectAttributesValues();
        modelUse.use(htmlAttributes);
    }
    
    // Possivel alterar como os atributos serao utilizados, caso seja implementado
    // outro metodo.
    public final void setModelUse(ModelHtmlAttributeUse modelUse){
        this.modelUse = modelUse;
    }
}
