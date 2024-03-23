# Automação de Coleta de Dados do Site Bases AN

Este projeto consiste em uma automação desenvolvida em Java 17 utilizando Selenium para coletar dados do site [Bases AN](https://bases.an.gov.br/rv/Menu_Externo/). O objetivo principal é extrair informações sobre emigrantes que vieram para o Brasil e armazená-las em um arquivo CSV para análises posteriores.

## Funcionalidades
- A automação navega pelo site Bases AN fazendo buscas e coletando dados sobre os emigrantes.
- Os dados são organizados e armazenados em um arquivo CSV.

## Requisitos
- Java 17 ou superior
- Docker Compose

## Instalação e Execução
Clone este repositório:
```bash
git clone https://github.com/jaomanoel/estrangeiros-arquivos-nacional.git
```
Navegue até o diretório do projeto:
```bash
cd estrangeiros-arquivos-nacional
```
Execute o Docker Compose para iniciar o ambiente:
```bash
docker-compose up -d
```
Adicione quantas Query quiser no arquivo Main.java na função main:
```bash
queries.add(new Query("NOME CONTEM", "NACAO/NACIONALIDADE, exemplo: Italiana"));
queries.add(new Query("NOME CONTEM", "NACAO/NACIONALIDADE, exemplo: Portuguesa"));
```
Execute o script Java para iniciar a automação:
```bash
mvn clean package
java -cp target/estrangeiros-arquivos-nacional-1.0-SNAPSHOT.jar online.digital_business.Main
```
## Configuração
Certifique-se de que o arquivo docker-compose.yml esteja configurado corretamente para suas necessidades. É possível ajustar configurações como versão do Selenium, 
navegador utilizado, entre outras.

## Licença
Este projeto está licenciado sob a MIT License.
