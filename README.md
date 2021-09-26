# Trabalho 02 - Princípio Aberto-Fechado
- Universidade Federal de São Carlos - Departamento de Computação
- Programação Orientada a Objetos Avançada
- Aluno: Roseval Donisete Malaquias Junior - RA: 758597
- Professor: Delano Medeiros Beder

---

Neste repositório, é apresentada uma ferramenta que exemplifica a aplicação do princípio Aberto-Fechado (OCP) no contexto da programação orientada a objetos, respeitando também o princípio da responsabilidade única (SRP). Sendo assim, é possível estender a ferramenta sem que sejam feitas mudanças em código já funcional, e todos módulos de código da ferramenta possuem apenas uma vertente de mudança, apresentando alta flexibilidade, facilidade de manutenção e reutilização.

A ferramenta ``GetNews`` foi programada em Java, e tem como principal objetivo possibilitar que informações de websites sejam baixadas e utilizadas dado algoritmos previamente implementados. Entretanto, o foco das demonstrações da ferramenta serão na extração e utilização de informações de sites de notícias, como títulos e links.

A fim de evitar o design especulativo, 2 extensões são previstas pelo projeto.

- Extensão 1: Deve ser possível incluir novos sites de notícias (ex: UOL, Estadão, etc)
- Extensão 2: Deve ser possível incluir novos algoritmos, além de salvar no formato csv:
-- Exemplos: imprimir na tela, baixar os conteúdos das notícias, seguindo os links, criar
uma nuvem de palavras com todos os títulos de notícias do dia, aplicar um algoritmo de
aprendizado de máquina para detectar tendências e diferenças entre os sites

Diante disso, o projeto da ferramenta apresenta 2 classes abstratas que servem de modelo para possibilitar a extensão do projeto caso necessário. A classe ``ModelHtmlParser`` cobre a extensão 1, e a classe ``ModelHtmlAttributeUse`` cobre a extensão 2. A fim de exemplificar a utilização dessas 2 classes e a possibilidade de extensão por elas providas, dentro deste projeto há 3 classes que herdam da classe ``ModelHtmlAttributeUse`` (...) e 3 classes que herdam da classe ``ModelHtmlParser`` (...), implementando seus métodos abstratos. Por fim, dentro da classe ``Main`` do projeto é demonstrado o funcionamento destas 6 classes, exemplificando suas funcionalidades, flexibilidade e possibilidade de extensão.

Para o maior detalhamento desta ferramenta, com explicações mais detalhadas de suas funcionalidades e especificações para sua extensão, o seguinte vídeo tutorial foi gravado e postado no youtube: LINK
