/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.pooa.java.peganoticias;

import java.util.ArrayList;

/**
 *
 * @author moleke
 */
public class TelaPrint extends ModelPrint{

    @Override
    public void Print(ArrayList<HtmlAttribute> attributes) {
        System.out.println("Atributos da URL: " + attributes.get(0).getUrl());
        for(int i = 0 ; i < attributes.get(0).getAttributesSize(); i++){
            System.out.println(attributes.get(0).getAttribute(i));
        }
    }
    
}
