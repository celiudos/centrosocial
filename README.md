# Instituto Alfredo Ferreira Filho - IAFF

O presente projeto tem por objetivo o desenvolvimento de sistema para dar suporte aos processos de trabalho do centro social IAFF.

## Arquitetura de desenvolvimento
* Spring Boot MVC
* JPA
* Thymeleaf
* Bootstrap
* Devtools
* LiveReload

## Instru��es

* Instale o **Spring Tool Suit - STS** no IDE Eclipse.
* Baixe a pasta **src** e o arquivo **pom.xml** em uma pasta apropriada no **workspace** do Eclpipse.
* Importe os arquivos como **Existing Maven Projects**.
* Crie um esquema denominado **iaff** no banco de dados MySQL: usu�rio **root**, senha em branco.
* Rode a aplica��o.
* Fa�a o login com o e-mail **admin@iaff.org** e senha **admin**

### Testando a aplica��o (teste de integra��o)

* Rode a aplica��o (banco de dados + servidor).
* Selecione o arquivo **RodarTodosOsTestes.java** para rodar todos os testes.
* Rode-o com **Run as** -> **JUnit Test**.
* Ap�s rodar o teste, verifique na aba "**JUnit**" do Eclipse se houve algum erro ou falha. 
* Voc� pode rodar cada teste individualmente tamb�m.