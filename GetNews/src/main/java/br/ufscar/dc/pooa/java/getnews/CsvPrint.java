/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufscar.dc.pooa.java.getnews;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author moleke
 */
public class CsvPrint extends ModelHtmlAttributeUse{

    @Override
    public void use(ArrayList<HtmlAttribute> htmlAttributes) {
        String strNow = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss").format(LocalDateTime.now());
        try (PrintWriter pw = new PrintWriter(new FileWriter(new File(String.format("dump-%s.csv", strNow))))) {
            for(int i = 0 ; i < htmlAttributes.size(); i++){
                if(i == htmlAttributes.size() - 1)
                    pw.print(String.format("%s\n", htmlAttributes.get(i).getAttributeName()));
                else
                    pw.print(String.format("%s;", htmlAttributes.get(i).getAttributeName()));
            }
            for(int i = 0 ; i < htmlAttributes.get(0).getAttributeValuesSize(); i++){
                for(int j = 0 ; j < htmlAttributes.size(); j++){
                if(j == htmlAttributes.size() - 1)
                    pw.print(String.format("%s\n", htmlAttributes.get(j).getAttributeValue(i)));
                else
                    pw.print(String.format("%s;", htmlAttributes.get(j).getAttributeValue(i)));
                }
            }
            
        }catch (IOException ex) {
            Logger.getLogger(CsvPrint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
