// !!!Falta as  duas primeiras metodos e a classe em si!!!
// Fazer isso primeiro -> Cogitar escrever no forum de novo -> Ajeitar giyhub
// -> Comecar a fazer o video -> chamar a galera pro trabalho de ES2 -> mexer em
// compiladores.
package br.ufscar.dc.pooa.java.getnews;

// Utilizado a biblioteca Kumo para criar o wordCloud (https://github.com/kennycason/kumo).
import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.font.scale.LinearFontScalar;
import com.kennycason.kumo.palette.ColorPalette;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/* Classe utilizada para gerar WordClouds para cada um dos HtmlAttributeValues 
 * (Strings) retornadas, nesta classe para todos HtmlAttributes seus
 * HtmlAttributeValues sao transformados em um grande conjunto de String, sendo
 * transferidos para um ArrayLis<String>. Entao, e realizado o calculo de frequencia
 * para cada uma das palavras encontradas. Por fim, e gerado um wordCloud com as 
 * palavras e suas frequencias. */
public class WordCloudPrint extends ModelHtmlAttributeUse{
    
    // Esse metodo extrai todos os htmlAttributeValues (Strings) de todos os htmlAttributes,
    // separando todas as Strings em palavras (.split(" ")) e colocando-as em um ArrayList<String>.
    private ArrayList<String> extractWords(ArrayList<HtmlAttribute> htmlAttributes){
        // Inicializa o ArraList<String> que tera todas as palavras encontradas.
        ArrayList<String> words = new ArrayList<String>();
        String[] auxStrings = null;
        
        // Para todos os htmlAttributes serao extraidas todas as palavras dos
        // htmlAttributeValues, sendo todas as palavras encontradas armazenadas
        // em um ArrayList<String>.
        for(int i = 0 ; i < htmlAttributes.size(); i++){
            for(int j = 0 ; j < htmlAttributes.get(i).getAttributeValuesSize(); j++){
                auxStrings = htmlAttributes.get(i).getAttributeValue(j).split(" ");
                for(int k = 0; k < auxStrings.length ; k++ ){
                    words.add(auxStrings[k]);
                }
            }
        }
        
        return words;
    }
    
    // Esse metodo calcula quantas vezes uma determinada String repetiu dentro de 
    // um ArrayListM<, retornando um Map<String, Integer> onde a chave e a propria String
    // e o valor o numero de repeticoes desta string.
    private Map<String, Integer> calcWordFrequencies(ArrayList<String> words){
        Map<String, Integer> map = new HashMap<>();
        
        for (String word: words) {
            Integer count = map.get(word);
            if(count == null) // Caso nulo, e a primeira ocorrencia.
                count = 1;
            else
                count++;
            map.put(word, count); // Atualiza o map.
        }
        
        return map;
    }
    
    // Esse metodo constroi uma List<WordFrequency> que apresenta cada uma das 
    // palavras e suas repeticoes nesta estrutura (o retorno e utilizado pela 
    // biblioteca Kumo).
    private List<WordFrequency> buildWordFrequencies(ArrayList<String> words) {
        final List<WordFrequency> wordFrequencies = new ArrayList<>();
        
        // Calcula o numero de repeticoes de cada palavra.
        Map<String, Integer> map = calcWordFrequencies(words);
        
        // Coloca cada palavra e seu numero de repeticoes na estrutura.
        for (String word: map.keySet())
            wordFrequencies.add(new WordFrequency(word, map.get(word)));
        
        return wordFrequencies;
    }
    
    // Esse metodo gera uma imagem de um wordCloud, dado um List<WordFrequency> 
    // passado como entrada.
    private void buildWorldCloud(List<WordFrequency> wordFrequencies){
        
        // Constroi o wordCloud.
        final Dimension dimension = new Dimension(600, 600);
        final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.RECTANGLE);
        wordCloud.setPadding(0);
        wordCloud.setBackground(new CircleBackground(300)); // Forma de circulo.
        wordCloud.setColorPalette(new ColorPalette(new Color(0xABEDFF), 
                new Color(0x82E4FF), new Color(0x55D6FA)));
        wordCloud.setFontScalar(new LinearFontScalar(10, 40));
        wordCloud.build(wordFrequencies); // Constroi o wordCloud dado a entrada.
        
        // Gera a imagem de saida com o tempo atual no nome.
        String strNow = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss").format(LocalDateTime.now());
        wordCloud.writeToFile(String.format("WC-%s.png", strNow)); 
    }
    
    // Metodo que define a utilizacao dos htmlAttributes, neste metodos os 
    // htmlAttributes serao utilizados para gerar uma imagem de um wordCloud.
    @Override
    public void use(ArrayList<HtmlAttribute> htmlAttributes) {
        // Extrai as palavras de cada um dos htmlAttributeValues.
        ArrayList<String> words = this.extractWords(htmlAttributes);
        
        // Gera as wordFrequencies para as palavras extraidas.
        List<WordFrequency> wordFrequencies = this.buildWordFrequencies(words);
        
        // Gera a imagem do wordCloud.
        this.buildWorldCloud(wordFrequencies);
    }
}
