# Instituto Alfredo Ferreira Filho - IAFF

O presente projeto tem por objetivo o desenvolvimento de sistema para dar suporte aos processos de trabalho do centro social IAFF.

## Arquitetura de desenvolvimento
* Spring Boot MVC
* JPA
* Thymeleaf
* Bootstrap
* Devtools
* LiveReload

## Instruções

* Instale o **Spring Tool Suit - STS** no IDE Eclipse.
* Baixe a pasta **src** e o arquivo **pom.xml** em uma pasta apropriada no **workspace** do Eclpipse.
* Importe os arquivos como **Existing Maven Projects**.
* Crie um esquema denominado **iaff** no banco de dados MySQL: usuário **root**, senha em branco.
* Rode a aplicação.
* Faça o login com o e-mail **admin@iaff.org** e senha **admin**

### Testando a aplicação (teste de integração)

* Rode a aplicação (banco de dados + servidor).
* Selecione o arquivo **RodarTodosOsTestes.java** para rodar todos os testes.
* Rode-o com **Run as** -> **JUnit Test**.
* Após rodar o teste, verifique na aba "**JUnit**" do Eclipse se houve algum erro ou falha. 
* Você pode rodar cada teste individualmente também.