package br.ufscar.dc.pooa.java.getnews;

/* Exemplificando a utilizacao das classes implementadas para a extracao de titulos
 * de noticias principais do globo e seus links, printando na tela o que foi encontrado.*/
public class Main {

    public static void main(String[] args){
        printScreenGlobo();
    }
    
    public static void printScreenGlobo(){
        // Classe que define como sera utilizado as strings retornadas foi implementada.
        // Ela agora precisa ser passada junto com o contrutor da classe que realiza a selecao.
        // Instanciando classe que dita o modelo de como serão utilizadas as strings retornadas do globo.
        ScreenPrint printer = new ScreenPrint();
        // Instanciando a classe que define de onde e como serão retiradas as strings
        SiteGlobo globo = new SiteGlobo();
        // Utiliza as strings retornadas nesta classe.
        globo.useHtmlAttributesValues(printer);
    }
    
    public static void printScreenBBC(){
        
    }
    
    public static void printScreenOUL(){
        
    }
}
