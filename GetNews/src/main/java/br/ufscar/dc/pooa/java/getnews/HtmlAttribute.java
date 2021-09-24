package br.ufscar.dc.pooa.java.getnews;

import java.util.ArrayList;

/* Classe que encapsula os elementos que serao que definem uma busca de um "atributo"
 * em uma pagina web dado uma url, tag, className e o attributeName. Alem disso, mantem
 * uma estrutura para os elementos encontrados.*/
public class HtmlAttribute {
    
    // Argumentos para selecionar um htmlAttributeValue.
    private final String url;
    private final String tag; 
    private final String className;
    // Esses argumentos serao setados, caso seja feita uma busca por hierarquia.
    // Caso contrario eles podem ser nulos.
    private final String familyTag;
    private final String familyClassName;
    // -------------------------------------------------------------------------
    private final String attributeName; 
    
    // Estrutura para armazenar os atributosValues encontrados.
    private final ArrayList<String> attributeValues; 
    
    // Inicializa todas as variaveis finais.
    public HtmlAttribute(String url, String tag, String className, String
            familyTag, String familyClassName, String attribute){
        this.url = url;
        this.tag = tag;
        this.className = className;
        this.familyTag = familyTag;
        this.familyClassName = familyClassName;
        this.attributeName = attribute;
        this.attributeValues = new ArrayList<String>();
        
    }
    
    public String getAttributeValue(int i){
        return attributeValues.get(i);
    }
    
    public void addAttributeValue(String element){
        this.attributeValues.add(element);
    }
    
    public int getAttributeValuesSize(){
        return attributeValues.size();
    }
    
    public String getUrl(){
        return this.url;
    }
     
    public String getTag(){
        return this.tag;
    }
    
    public String getClassName(){
        return this.className;
    }
    
    public String getFamilyTag(){
        return this.familyTag;
    }
    
    public String getFamilyClassName(){
        return this.familyClassName;
    }
    
    public String getAttributeName(){
        return this.attributeName;
    }
}
