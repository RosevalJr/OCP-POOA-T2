package br.ufscar.dc.pooa.java.peganoticias;

import java.util.ArrayList;

/* Classe utilizada para especificar como serao utilizadas os HtmlAttributValues (String)
 * retornadas, nesta classe as Strings sao apenas printadas na tela, antes especificando
 * de qual URL elas foram retiradas. */
public class ScreenPrint extends ModelHtmlAttributeUse{

    // Apenas percorre todos os htmlAttributes e printa os htmlAttributeValues (Strings)
    // para cada um deles na tela, especificando antes em qual URL foi retirado.
    @Override
    public void use(ArrayList<HtmlAttribute> htmlAttributes) {
        for(int i = 0 ; i < htmlAttributes.size(); i++){
            System.out.println("Retirado da URL: " + htmlAttributes.get(i).getUrl());
            for(int j = 0 ; j < htmlAttributes.get(i).getAttributeValuesSize(); j++){
                System.out.println(htmlAttributes.get(i).getAttributeValue(j));
            }
        }
    }
    
    
    
}
