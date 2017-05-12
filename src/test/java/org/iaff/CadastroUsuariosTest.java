package org.iaff;

import org.iaff.pages.BasePage;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CadastroUsuariosTest extends BasePage {

    private WebDriver driver;
    private String baseUrl;

    public CadastroUsuariosTest() {
        this.driver = getDriver();
        this.baseUrl = getBaseUrl();
    }

    @Test
    public void testeFluxoPrincipal() throws Exception {
        testLogin();
        testCadastroUsuario();
        testExlusaoUsuario();
        testLogout();
    }

    public void testCadastroUsuario() throws Exception {
        // Acessando menu - Cadastro -> Usuarios
        driver.get(baseUrl + "/usuarios/novo");

        // Preenchendo o formulário
        driver.findElement(By.id("nome")).clear();
        driver.findElement(By.id("nome")).sendKeys("Josué teste 01-05-2017");
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("josue_teste@gmail.com");

        // Selecionando uma pessoa
        driver.findElement(By.cssSelector("body > div > section > div.container-fluid > form > div:nth-child(4) > div > div > span > button:nth-child(1)")).click();
        driver.findElement(By.id("nomePessoaModal")).sendKeys("Fabiano");
        driver.findElement(By.cssSelector("#pesquisaRapidaPessoas > div > div > div.modal-body > form > button")).click();
        driver.findElement(By.cssSelector("#containerTabelaPesquisaRapidaPessoas > div > table > tbody > tr")).click();

        driver.findElement(By.id("senha")).clear();
        driver.findElement(By.id("senha")).sendKeys("josue123");
        driver.findElement(By.id("confirmacaoSenha")).clear();
        driver.findElement(By.id("confirmacaoSenha")).sendKeys("josue123");
        Thread.sleep(1000);
        driver.findElement(By.id("grupos1")).click();
        driver.findElement(By.cssSelector("body > div > section > div.container-fluid > form > button")).click();

        // Verificando se foi incluído com sucesso
        Thread.sleep(1000);
        String msgSucesso = driver.findElement(By.cssSelector("form div.alert.alert-success.alert-dismissible > span"))
                .getText();
        assertEquals("Usuário salvo com sucesso", msgSucesso);
    }

    public void testExlusaoUsuario() throws Exception {
        // Abrindo Cadastros de Usuarios
        driver.get(baseUrl + "/usuarios");

        // Pesquisando Usuarios
        driver.findElement(By.id("nome")).clear();
        driver.findElement(By.id("nome")).sendKeys("Josué teste 01-05-2017");
        driver.findElement(By.cssSelector("body > div > section > div.container-fluid > form > button")).click();

        // Removendo
        excluirDado(driver);
    }

    @After
    public void tearDown() throws Exception {
        sairDosTestes();
    }


}
