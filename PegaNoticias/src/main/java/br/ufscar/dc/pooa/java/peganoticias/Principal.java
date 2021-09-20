package br.ufscar.dc.pooa.java.peganoticias;

import java.io.IOException;
import java.util.ArrayList;

// Talvez melhor fazer um classe que encapsula o Html...
public class Principal {

    public static void main(String[] args) throws IOException{
        printJsonGlobo();
        printJsonG1();
        printTelaGlobo();
        System.out.println("-----.-----");
        printTelaG1();
    }
    
    public static void printJsonGlobo() throws IOException{
        HtmlAttribute tituloNoticiaGlobo = new HtmlAttribute("https://www.globo.com/","a", "post__link","title");
        tituloNoticiaGlobo.getHtmlAttribute(); // Busca e armazena em um atributo da classe.

        // Inicializa a lista dos HtmlAtribute (necessario para o metodo Print).
        ArrayList<HtmlAttribute> attributes = new ArrayList<>();
        attributes.add(tituloNoticiaGlobo);
        
        JsonPrint printer = new JsonPrint();
        printer.Print(attributes);
    }
    
    public static void printJsonG1(){
        HtmlAttribute tituloNoticiaG1 = new HtmlAttribute("https://g1.globo.com/","a", "feed-post-link","text");
        tituloNoticiaG1.getHtmlAttribute(); // Busca e armazena em um atributo da classe.

        // Inicializa a lista dos HtmlAtribute (necessario para o metodo Print).
        ArrayList<HtmlAttribute> attributes = new ArrayList<>();
        attributes.add(tituloNoticiaG1);
        
        JsonPrint printer = new JsonPrint();
        printer.Print(attributes);
    }
    
    public static void printTelaGlobo() throws IOException{
        HtmlAttribute tituloNoticiaGlobo = new HtmlAttribute("https://www.globo.com/","a", "post__link","title");
        tituloNoticiaGlobo.getHtmlAttribute(); // Busca e armazena em um atributo da classe.

        // Inicializa a lista dos HtmlAtribute (necessario para o metodo Print).
        ArrayList<HtmlAttribute> attributes = new ArrayList<>();
        attributes.add(tituloNoticiaGlobo);
        
        TelaPrint printer = new TelaPrint();
        printer.Print(attributes);
    }
    
    public static void printTelaG1(){
        HtmlAttribute tituloNoticiaG1 = new HtmlAttribute("https://g1.globo.com/","a", "feed-post-link","text");
        tituloNoticiaG1.getHtmlAttribute(); // Busca e armazena em um atributo da classe.

        // Inicializa a lista dos HtmlAtribute (necessario para o metodo Print).
        ArrayList<HtmlAttribute> attributes = new ArrayList<>();
        attributes.add(tituloNoticiaG1);
        
        TelaPrint printer = new TelaPrint();
        printer.Print(attributes);
    }
    
}
