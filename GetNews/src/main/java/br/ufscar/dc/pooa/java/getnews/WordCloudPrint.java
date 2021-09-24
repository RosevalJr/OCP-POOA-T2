package br.ufscar.dc.pooa.java.getnews;

import java.util.ArrayList;
import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.font.scale.LinearFontScalar;
import com.kennycason.kumo.palette.ColorPalette;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordCloudPrint extends ModelHtmlAttributeUse{
    
    private String[] extractHtmlAttributeValues(ArrayList<HtmlAttribute> htmlAttributes){
        ArrayList<String> dynamicStrings = new ArrayList<String>();
        String[] aux;
        for(int i = 0 ; i < htmlAttributes.size(); i++){
            for(int j = 0 ; j < htmlAttributes.get(i).getAttributeValuesSize(); j++){
                aux = htmlAttributes.get(i).getAttributeValue(j).split(" ");
                for(int k = 0; k < aux.length ; k++ ){
                    dynamicStrings.add(aux[k]);
                }
            }
        }
        String[] finalWords = dynamicStrings.toArray(new String[dynamicStrings.size()]);
        return finalWords;
    }
    
    @Override
    public void use(ArrayList<HtmlAttribute> htmlAttributes) {
        String [] words = this.extractHtmlAttributeValues(htmlAttributes);
        
        final List<WordFrequency> wordFrequencies = buildWordFrequencies(words);
        final Dimension dimension = new Dimension(600, 600);
        final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.RECTANGLE);
        wordCloud.setPadding(0);
        wordCloud.setBackground(new CircleBackground(300));
        wordCloud.setColorPalette(new ColorPalette(new Color(0xABEDFF), new Color(0x82E4FF), new Color(0x55D6FA)));
        wordCloud.setFontScalar(new LinearFontScalar(10, 40));
        wordCloud.build(wordFrequencies);
        wordCloud.writeToFile("wordcloud.png");
    }
    
    private List<WordFrequency> buildWordFrequencies(String[] words) {
        final List<WordFrequency> wordFrequencies = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        
        for (String word : words) {
            Integer count = map.get(word);
            if(count == null)
                count = 1;
            else
                count++;
            map.put(word, count);
        }
        
        for (final String word : map.keySet()) {
            wordFrequencies.add(new WordFrequency(word, map.get(word)));
        }
        
        return wordFrequencies;
    }
    
}
