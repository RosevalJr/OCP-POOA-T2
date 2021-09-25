package br.ufscar.dc.pooa.java.getnews;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

// ATENCAO! O numero de htmlAttributeValues para cada um dos htmlAttributes DEVE SER IGUAL!
// Essa especificacao foi feita para que nao haja inconsistencia no numero de elementos de
// cada coluna

/* Classe utilizada para especificar como serao utilizadas os HtmlAttributValues (Strings)
 * retornadas, nesta classe para cada htmlAttribute sera feita uma coluna em um arquivo CVS
 * e posteriormente isso sera exportado para um arquivo na raiz do projeto.*/
public class CsvPrint extends ModelHtmlAttributeUse{
    
    private String separator;
    private String header;
    private String text;
    private String fileName;
    
    private void setSeparator(String separator){
        this.separator = separator;
    }
    
    private void setHeader(ArrayList<HtmlAttribute> htmlAttributes){
        this.header = "";
        
        // Utiliza os attributesNames como os nomes para cada uma das colunas.
        for(int i = 0 ; i < htmlAttributes.size(); i++){
            if(i == htmlAttributes.size() - 1)
                this.header += String.format("%s\n", htmlAttributes.get(i).getAttributeName());
            else // Caso nao seja o ultimo deve ser adicionado o separador na linha.
                this.header += String.format("%s" + this.separator, htmlAttributes.get(i).getAttributeName());
        }
    }
    
    private void setFileName(){
        // String do tempo atual para ser utilizada no nome do arquivo CSV.
        String dateNow = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss").format(LocalDateTime.now());
        this.fileName = String.format("dump-%s.csv",dateNow);
    }
    
    private void setText(ArrayList<HtmlAttribute> htmlAttributes){
        this.text = "";
        
        // Espera-se que o numero de attributeValues (Strings) para cada htmlAttribute 
        // seja igual, sendo assim e utilizado o primeiro (0) como tamanho para a iteracao.
        for(int i = 0 ; i < htmlAttributes.get(0).getAttributeValuesSize(); i++){
            
            for(int j = 0 ; j < htmlAttributes.size(); j++){
            if(j == htmlAttributes.size() - 1)
                this.text += String.format("%s\n", htmlAttributes.get(j).getAttributeValue(i));
            else// Caso nao seja o ultimo deve ser adicionado o separador na linha.
                this.text += String.format("%s" + this.separator, htmlAttributes.get(j).getAttributeValue(i));
            }
        }
    }
    
    // Os metodos que definem os metadados do csv file sao chamados, o header e o
    // conteudo do csv file e setado e por fim o header e o text sao impressos no
    // arquivo csv criado.
    @Override
    public void use(ArrayList<HtmlAttribute> htmlAttributes) {
        this.setFileName();
        
        this.setSeparator(";");
        this.setHeader(htmlAttributes);
        this.setText(htmlAttributes);
        
        try (PrintWriter pw = new PrintWriter(new FileWriter(new File(this.fileName)))) {
            pw.print(this.header);
            pw.print(this.text);
        }catch (IOException ex) {
            Logger.getLogger(CsvPrint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
