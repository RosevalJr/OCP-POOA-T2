package br.ufscar.dc.pooa.java.peganoticias;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonPrint extends ModelPrint{

    @Override
    public void Print(HtmlAttribute attributes) {
        // Define o formato da hora.
        String horaAgora = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss")
                    .format(LocalDateTime.now());
        
        // Abre um arquivo
        try(PrintWriter pw = new PrintWriter(new FileWriter(new File
            (String.format("%s-%s.csv",attributes.getArgument(), horaAgora))))){
           // Escreve o cabecalho.
           pw.println("Noticia");
           
            for(int i = 0 ; i < attributes.getAttributesSize(); i++){
               pw.print(String.format("%s;", attributes.getAttribute(i)));
               pw.print("\n");
            }
        } catch (IOException ex) {
            Logger.getLogger(JsonPrint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
