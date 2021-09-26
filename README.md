# Trabalho 02 - Princípio Aberto-Fechado
- Universidade Federal de São Carlos - Departamento de Computação
- Programação Orientada a Objetos Avançada
- Aluno: Roseval Donisete Malaquias Junior - RA: 758597
- Professor: Delano Medeiros Beder

---

> Para o maior detalhamento desta ferramenta, com explicações mais detalhadas de suas funcionalidades e especificações para sua extensão, o seguinte vídeo tutorial foi gravado e postado no youtube: <https://youtu.be/mXFNUSFyVDY>

Neste repositório, é apresentada uma ferramenta que exemplifica a aplicação do princípio Aberto-Fechado (OCP) no contexto da programação orientada a objetos, respeitando também o princípio da responsabilidade única (SRP). Sendo assim, é possível estender a ferramenta sem que sejam feitas mudanças em código já funcional, e todos módulos de código da ferramenta possuem apenas uma vertente de mudança, apresentando alta flexibilidade, facilidade de manutenção e reutilização.

A ferramenta ``GetNews`` foi programada em Java, e tem como principal objetivo possibilitar que informações de websites sejam baixadas e utilizadas dado algoritmos previamente implementados. Entretanto, o foco das demonstrações da ferramenta serão na extração e utilização de informações de sites de notícias, como títulos e links.

A fim de evitar o design especulativo, 2 extensões são previstas pelo projeto.

- Extensão 1: Deve ser possível incluir novos sites de notícias (ex: UOL, Estadão, etc)
- Extensão 2: Deve ser possível incluir novos algoritmos, além de salvar no formato csv:
-- Exemplos: imprimir na tela, baixar os conteúdos das notícias, seguindo os links, criar
uma nuvem de palavras com todos os títulos de notícias do dia, aplicar um algoritmo de
aprendizado de máquina para detectar tendências e diferenças entre os sites

Diante disso, o projeto da ferramenta apresenta 2 classes abstratas que servem de modelo para possibilitar a extensão do projeto caso necessário. A classe ``ModelHtmlParser`` cobre a extensão 1, e a classe ``ModelHtmlAttributeUse`` cobre a extensão 2. A fim de exemplificar a utilização dessas 2 classes e a possibilidade de extensão por elas providas, dentro deste projeto há 3 classes que herdam da classe ``ModelHtmlAttributeUse`` (``CsvPrint``, ``ScreenPrint`` e ``WordCloudPrint``) e 3 classes que herdam da classe ``ModelHtmlParser`` (``GloboParser``, ``OulParser`` e ``BbcLondonParser``), implementando seus métodos abstratos. Por fim, dentro da classe ``Main`` do projeto é demonstrado o funcionamento destas 6 classes, exemplificando suas funcionalidades, flexibilidade e possibilidade de extensão.

**"Como eu faço para incluir um novo site de notícias? Onde eu tenho que mexer?"**

Para incluir um novo site de notícias, você deve implementar uma nova classe que herda da classe abstrata ``ModelHtmlParser``, implementando os métodos abstratos ``setHtmlAttributes`` e ``selectAttributesValues``.

Na implementação do método ``setHtmlAttributes``, você deve setar os HtmlAttributes que definem de onde será tirado as informações das notícias e quais informações serão extraidas. Para cada HtmlAttribute setado será feita uma busca na pagina html do site especificado no htmlAttribute, sendo que dentro de cada htmlAttribute há um ArrayList<String> para armazenam as informações extraidas. Um exemplo de implementação deste método na classe ```` para extrair títulos das notícias e links da noticias pode ser observado a seguir:

```Java
// Tanto o titulo quanto o link se encontram dentro da mesma tag a.post__link
// so muda o argumento.
@Override
protected void setHtmlAttributes() {
    // Nao e necessario realizar uma busca hierquica para encontrar os links.
    // Entao setei o familyTag e familyClassName como nulos.
    htmlAttributes.add(new HtmlAttribute("https://www.globo.com/", "a", 
            "post__link", null, null,"title"));
    htmlAttributes.add(new HtmlAttribute("https://www.globo.com/", "a", 
            "post__link", null, null,"href"));
}
```
Na implementação do método ``selectAttributesValues``, você deve especificar como serão extraidos as informações dos sites de notícias (dado os htmlAttributes setados pelo método ``setHtmlAttributes``) e popular os vetores de Strings (htmlAttributeValues) de cada htmlAttribute. Um exemplo de implementação deste método na classe ``GloboParser``, que utiliza o ``Jsoup`` para baixar as paginas html e realizar a seleção, pode ser observado a seguir:

```Java
// Abaixa a pagina especificada (Jsoup), retornando-a como um Document, onde sera feita
// uma selecao dado uma tag, className e attributeName de cada htmlAttribute setado.
// Por fim, o resultado da selecao (uma lista de Strings) e armazenando na estrutura
// ArrayList<String> htmlAttributeValues do seu respectivo htmlAttribute.
@Override
protected void selectAttributesValues(){
    // Busca para cada um dos htmlAttributes.
    for(int i = 0 ; i < htmlAttributes.size(); i++){

        // Possivel que o HandShake nao de certo (site da globo costuma recusar mais)!
        Document doc = null;
        try {
            doc = Jsoup.connect(htmlAttributes.get(i).getUrl()).get();
        } catch (IOException ex) {
            Logger.getLogger(GloboParser.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Seleciona todos elementos dado uma tag e um className especificado.
        Elements elements = doc.select(htmlAttributes.get(i).getTag() + "." +
                htmlAttributes.get(i).getClassName());

        // Nao e necessario realizar uma busca hierquica para encontrar os links!
        // Caso o nome do atributo seja "text" e necessario acessar o conteudo.
        if(htmlAttributes.get(i).getAttributeName().equals("text"))
            for(Element element: elements)
                htmlAttributes.get(i).addAttributeValue(element.text());
        else
            for(Element element: elements)
                htmlAttributes.get(i).addAttributeValue(element.attr(htmlAttributes.get(i).getAttributeName()));
    }
}
```

Importante destacar que, dado essa estratégia utilizada para prever a extensão 1 especificado pelo professor, a ferramenta não esta limitada a utilizar uma determinada ferramenta para realizar o donwload das paginas html. Com a implementação deste dois método abstrado é preciso apenas setar o que será extraido das paginas html seguindo os atributos da classe ``HtmlAttribute`` e realizar as buscas de cada um dos HtmlAttributes populando o ArrayList<String> de cada um dos HtmlAttributes. Além disso, dado essa implementação a ferramenta não está limitada a extrair informações de sites de notícias, ela pode extrair informações de qualquer site desejado.

**"Como eu faço para incluir um algoritmo para processar as notícias extraidas? Onde eu tenho que mexer?"**


