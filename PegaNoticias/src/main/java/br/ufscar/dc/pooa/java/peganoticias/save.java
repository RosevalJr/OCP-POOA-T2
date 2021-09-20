/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.pooa.java.peganoticias;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author moleke
 */
public class save {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        // Define o formato da hora.
        String horaAgora = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss")
                    .format(LocalDateTime.now());
        
        // Abre um arquivo
        try(PrintWriter pw = new PrintWriter(new FileWriter(new File
            (String.format("dump-%s.csv", horaAgora))))){
           // Escreve o cabecalho.
           pw.println("Notícia;Link");
           // Conecta com o link do site.
           Document doc = Jsoup.connect("https://www.globo.com/").get();
           // Seleciona todos os elementos do documento.
           Elements titles = doc.select("a.post__link");
           
           // Iteracao sobre todos os elementos
           for(Element t:titles){
               // Printa no formato.
               pw.print(String.format("%s;", t.attr("title")));
               pw.print(String.format("\"%s\"", t.attr("href")));
               pw.print("\n");
           }
        }
    }
    
}
