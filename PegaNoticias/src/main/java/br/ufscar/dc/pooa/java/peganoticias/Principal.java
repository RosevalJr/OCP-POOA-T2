package br.ufscar.dc.pooa.java.peganoticias;
/* Duvidas gerais a se levar pro Delano. */
// A utilizacao do bean e ruim?
// Seria a montagem dessa classe dinamica o suficiente?
// Como saber o quao tem que especificar?

// A classe abstrata com certeza leva a listaTituloLink.
// Na verdade modelPrint tem que ter a lista das noticias.
public class Principal {

    public static void main(String[] args){
        printJsonGlobo();
    }
    
    public static void printJsonGlobo(){
        Noticias not = new Noticias("https://www.globo.com/","a.post__link","title","href");
        JsonPrint printer = new JsonPrint();
        printer.Print(not);
    }
    
}
