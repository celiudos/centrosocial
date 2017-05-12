package org.iaff;

import org.iaff.pages.BasePage;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CadastroPacientesTest extends BasePage {

    private WebDriver driver;
    private String baseUrl;

    public CadastroPacientesTest() {
        this.driver = getDriver();
        this.baseUrl = getBaseUrl();
    }

    @Test
    public void testeFluxoPrincipal() throws Exception {
        testLogin();
        testCadastroPaciente();
        testExlusaoPaciente();
        testLogout();
    }

    public void testCadastroPaciente() throws Exception {
        // Acessando menu - Cadastro -> Pacientes
        driver.get(baseUrl + "/pacientes/novo");

        // Cadastro rápido de uma pessoa
        driver.findElement(By.cssSelector("button[data-target='#cadastroRapidoPessoa']")).click();
        driver.findElement(By.id("nomePessoaCadastro")).sendKeys("Emanuel da Silva Teste - 01-04-2017");
        driver.findElement(By.id("dataNascimentoCadastro")).sendKeys("14011990");
        driver.findElement(By.id("telefoneCadastro")).sendKeys("6181543465");
        driver.findElement(By.cssSelector("#cadastroRapidoPessoa > div > div > div.modal-body > form > button.btn.btn-primary.js-modal-cadastro-pessoa-salvar-btn")).click();
        driver.findElement(By.cssSelector("#containerTabelaCadastroRapidoPessoa > div > table > tbody > tr")).click();

        //Continuando o formulário
        driver.findElement(By.id("foneAdicional")).clear();
        driver.findElement(By.id("foneAdicional")).sendKeys("6122222222");
        driver.findElement(By.id("foneRecado")).clear();
        driver.findElement(By.id("foneRecado")).sendKeys("6133333333");
        driver.findElement(By.id("foneEmergencia")).clear();
        driver.findElement(By.id("foneEmergencia")).sendKeys("6144444444");
        driver.findElement(By.cssSelector("body > div > section > div.container-fluid > form > button")).click();

        // Verificando se foi incluído com sucesso
        String msgSucesso = driver.findElement(By.cssSelector("form div.alert.alert-success.alert-dismissible > span"))
                .getText();
        assertEquals("Paciente salvo com sucesso", msgSucesso);
    }

    public void testExlusaoPaciente() throws Exception {
        // Abrindo Cadastros de Pacientes
        driver.get(baseUrl + "/pacientes");

        // Pesquisando Pacientes
        driver.findElement(By.id("nome")).clear();
        driver.findElement(By.id("nome")).sendKeys("Emanuel da Silva Teste - 01-04-2017");
        driver.findElement(By.cssSelector("body > div > section > div.container-fluid > form > button")).click();

        // Removendo
        excluirDado(driver);
    }

    @After
    public void tearDown() throws Exception {
        sairDosTestes();
    }

}
