package org.iaff;

import org.iaff.pages.BasePage;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CadastroCidadesTest extends BasePage {

    private WebDriver driver;
    private String baseUrl;

    public CadastroCidadesTest() {
        this.driver = getDriver();
        this.baseUrl = getBaseUrl();
    }

    @Test
    public void testeFluxoPrincipal() throws Exception {
        testLogin();
        testCadastroCidade();
        testPesquisaCidade();
        testLogout();
    }

    public void testCadastroCidade() throws Exception {
        // Acessando menu - Cadastro -> Cidades
        driver.get(baseUrl + "/cidades/nova");

        // Preenchendo o formulário
        driver.findElement(By.id("estado")).sendKeys("Distrito Federal");
        driver.findElement(By.id("nome")).clear();
        driver.findElement(By.id("nome")).sendKeys("Ceilândia teste 01-05-2017 - " + getUnixTime());
        driver.findElement(By.cssSelector("body > div > section > div.container-fluid > form > button")).click();

        // Verificando se foi incluído com sucesso
        String msgSucesso = driver.findElement(By.cssSelector("form div.alert.alert-success.alert-dismissible > span"))
                .getText();
        assertEquals("Cidade salva com sucesso!", msgSucesso);
    }

    public long getUnixTime(){
        long unixTime = System.currentTimeMillis() / 1000L;
        return unixTime;
    }

    public void testPesquisaCidade() throws Exception {
        // Abrindo Cadastros de Cidades
        driver.get(baseUrl + "/cidades");

        // Pesquisando Cidades
        driver.findElement(By.id("nome")).clear();
        driver.findElement(By.id("nome")).sendKeys("Ceilândia teste 01-05-2017");
        driver.findElement(By.cssSelector("body > div > section > div.container-fluid > form > button")).click();

        // Verificando se achou o elemento na 1º linha
        Boolean achouOElemento = isElementPresent(By.cssSelector(
                "body > div > section > div.container-fluid > div.table-responsive.bw-tabela-simples > table > tbody > tr:nth-child(1) > td.text-center > a:nth-child(2)"));

        assertTrue(achouOElemento);
    }

    @After
    public void tearDown() throws Exception {
        sairDosTestes();
    }

}
