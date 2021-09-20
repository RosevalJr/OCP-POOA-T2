package br.ufscar.dc.pooa.java.peganoticias;

import java.io.IOException;
import java.util.ArrayList;

/* Duvidas gerais a se levar pro Delano. */
// A utilizacao do bean e ruim?
// Seria a montagem dessa classe dinamica o suficiente?
// Como saber o quao tem que especificar?

// A classe abstrata com certeza leva a listaTituloLink.
// Na verdade modelPrint tem que ter a lista das noticias.
public class Principal {

    public static void main(String[] args) throws IOException{
        printJsonGlobo();
        printJsonG1();
        printTelaGlobo();
        printTelaG1();
    }
    
    public static void printJsonGlobo() throws IOException{
        HtmlAttribute tituloNoticiaGlobo = new HtmlAttribute("https://www.globo.com/","a", "post__link","title");
        tituloNoticiaGlobo.getHtmlAttribute(); // Busca e armazena em um atributo da classe.
        JsonPrint printer = new JsonPrint();
        printer.Print(tituloNoticiaGlobo);
    }
    
    public static void printJsonG1(){
        HtmlAttribute tituloNoticiaG1 = new HtmlAttribute("https://g1.globo.com/","a", "feed-post-link","text");
        tituloNoticiaG1.getHtmlAttribute(); // Busca e armazena em um atributo da classe.
        JsonPrint printer = new JsonPrint();
        printer.Print(tituloNoticiaG1);
    }
    
    public static void printTelaGlobo() throws IOException{
        HtmlAttribute tituloNoticiaGlobo = new HtmlAttribute("https://www.globo.com/","a", "post__link","title");
        tituloNoticiaGlobo.getHtmlAttribute(); // Busca e armazena em um atributo da classe.
        TelaPrint printer = new TelaPrint();
        printer.Print(tituloNoticiaGlobo);
    }
    
    public static void printTelaG1(){
        HtmlAttribute tituloNoticiaG1 = new HtmlAttribute("https://g1.globo.com/","a", "feed-post-link","text");
        tituloNoticiaG1.getHtmlAttribute(); // Busca e armazena em um atributo da classe.
        TelaPrint printer = new TelaPrint();
        printer.Print(tituloNoticiaG1);
    }
    
}
