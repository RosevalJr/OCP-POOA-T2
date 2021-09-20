package br.ufscar.dc.pooa.java.peganoticias;

import java.util.ArrayList;

public class TelaPrint extends ModelHtmlAttributeUse{

    // Apenas percorre todos os atributos e printa as strings na tela
    // Especificando antes em qual URL foi retirado.
    @Override
    public void use(ArrayList<HtmlAttribute> atributos) {
        for(int i = 0 ; i < atributos.size(); i++){
            System.out.println("Retirado da URL: " + atributos.get(i).getUrl());
            for(int j = 0 ; j < atributos.get(i).getAttributesSize(); j++){
                System.out.println(atributos.get(i).getAttribute(j));
            }
        }
    }
    
    
    
}
