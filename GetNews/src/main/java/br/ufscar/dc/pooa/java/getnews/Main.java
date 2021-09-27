package br.ufscar.dc.pooa.java.getnews;

/* Exemplificando a utilizacao das classes implementadas para a extracao de titulos
 * e links de noticias do Oul, Globo e Bbc de londres, realizando o print na tela,
 *  geracao de um csv file e geracao de uma imagem de um wordCloud. */
public class Main {

    // Possivel que ocorra um erro de conexao, caso o website recuse o handshake!
    public static void main(String[] args){
        // printScreenOul(); // Printa na tela.
        // printCsvGlobo(); // Gera um arquivo CSV na raiz do projeto.
        printWordCloudBbc(); // Gera uma imagem de um wordCloud na raiz do projeto.
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
