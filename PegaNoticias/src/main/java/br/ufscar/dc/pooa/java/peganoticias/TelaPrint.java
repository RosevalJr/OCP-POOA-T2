/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.pooa.java.peganoticias;

/**
 *
 * @author moleke
 */
public class TelaPrint extends ModelPrint{

    @Override
    public void Print(HtmlAttribute attributes) {
        System.out.println("Atributos da URL: " + attributes.getUrl());
        for(int i = 0 ; i < attributes.getAttributesSize(); i++){
            System.out.println(attributes.getAttribute(i));
        }
    }
    
}
