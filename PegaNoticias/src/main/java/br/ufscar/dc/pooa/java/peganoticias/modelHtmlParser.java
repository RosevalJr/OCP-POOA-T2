package br.ufscar.dc.pooa.java.peganoticias;

import java.util.ArrayList;

abstract class modelHtmlParser {
    
    // Lista dos atributos que sera definida, dentro dela ha tambem
    // as strings que serao retornadas
    protected final ArrayList<HtmlAttribute> attributes;
    // Define como serao utilizadas as strings encontradas.
    private ModelHtmlAttributeUse howToUse;
    
    // Instancia o vetor de HtmlAttribute, seta dado a implementacao feita
    // seta tambem o modo de uso especificado como entrada do construtor.
    public modelHtmlParser(ModelHtmlAttributeUse howToUse){
        attributes = new ArrayList<HtmlAttribute>();
        this.setHtmlAttributes();
        this.howToUse = howToUse; // Sem certeza.
    }
    
    // Seta quais serao cada um dos HtmlAttributes.
    protected abstract void setHtmlAttributes();
    // Busca por todas as strings de cada um dos HtmlAttributes.
    protected abstract void selectAttributes();
    
    // pega os atributos e utiliza.
    public final void useAttributes(){
        this.selectAttributes();
        howToUse.use(attributes);
    }
    
    // Possivel alterar como os atributos seroa utilizados.
    public final void setHowToUse(ModelHtmlAttributeUse howToUse){
        this.howToUse = howToUse;
    }
}
