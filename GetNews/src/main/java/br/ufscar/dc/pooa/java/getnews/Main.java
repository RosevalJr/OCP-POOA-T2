package br.ufscar.dc.pooa.java.getnews;

/* Exemplificando a utilizacao das classes implementadas para a extracao de titulos
 * e links de noticias do Oul, Globo e Bbc de londres, realizando o print na tela,
 *  geracao de um csv file e geracao de uma imagem de um wordCloud. */
public class Main {

    public static void main(String[] args){
        printScreenOul();
        printCsvGlobo();
        printWordCloudBbc();
    }
    
    public static void printCsvGlobo(){
        // Instanciando classe que dita o modelo de como serão utilizadas
        // as strings retornadas do globo.
        CsvPrint use = new CsvPrint();
        
        // Instanciando a classe que define de onde e como serão retiradas as strings
        GloboParser globo = new GloboParser();
        
        // Utiliza as strings retornadas nesta classe.
        globo.useHtmlAttributesValues(use);
    }
    
    public static void printScreenOul(){
        ScreenPrint use = new ScreenPrint();
        OulParser oul = new OulParser();
        oul.useHtmlAttributesValues(use);
    }
    
    public static void printWordCloudBbc(){
        WordCloudPrint use = new WordCloudPrint();
        BbcLondonParser bbc = new BbcLondonParser();
        bbc.useHtmlAttributesValues(use);
    }
}
