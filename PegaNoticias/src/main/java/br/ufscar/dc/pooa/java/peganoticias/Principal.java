package br.ufscar.dc.pooa.java.peganoticias;


// Exemplificacao da implementacao de busca de titulo de noticia e link de noticia
// no site globo, printando o que foi encontrado na tela.
public class Principal {

    public static void main(String[] args){
        printTelaGlobo();
    }
    
    public static void printTelaGlobo(){
        TelaPrint printer = new TelaPrint();
        Globo globo = new Globo(printer);
        globo.useAttributes();
    }
}
