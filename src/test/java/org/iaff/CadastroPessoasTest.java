package org.iaff;


import org.iaff.pages.BasePage;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CadastroPessoasTest extends BasePage {

    private WebDriver driver;
    private String baseUrl;

    public CadastroPessoasTest() {
        this.driver = getDriver();
        this.baseUrl = getBaseUrl();
    }

    @Test
    public void testeFluxoPrincipal() throws Exception {
        testLogin();
        testCadastroPessoa();
        testExclusaoPessoa();
        testLogout();
    }

    public void testCadastroPessoa() throws Exception {
        // Acessando menu - Cadastro -> Pessoas
        driver.get(baseUrl + "/pessoas/nova");

        // Preenchendo o formulário
        driver.findElement(By.id("nome")).clear();
        driver.findElement(By.id("nome")).sendKeys("Usuário Teste 01/01/2017");
        driver.findElement(By.id("dataNascimento")).clear();
        driver.findElement(By.id("dataNascimento")).sendKeys("14/01/1990");
        driver.findElement(By.id("telefone")).clear();
        driver.findElement(By.id("telefone")).sendKeys("(61) 12345-6789");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Verificando se foi incluído com sucesso
        String msgSucesso = driver.findElement(By.cssSelector("form div.alert.alert-success.alert-dismissible > span"))
                .getText();
        assertEquals("Pessoa salva com sucesso!", msgSucesso);
    }

    public void testExclusaoPessoa() throws Exception {
        // Abrindo Cadastros de Pessoas
        driver.get(baseUrl + "/pessoas");

        // Pesquisando Pessoas
        driver.findElement(By.id("nome")).clear();
        driver.findElement(By.id("nome")).sendKeys("Usuário Teste 01/01/2017");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Removendo
        excluirDado(driver);
    }

    @After
    public void tearDown() throws Exception {
        sairDosTestes();
    }

}
