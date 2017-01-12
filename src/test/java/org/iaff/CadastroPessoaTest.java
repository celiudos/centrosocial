package org.iaff;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CadastroPessoaTest {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		
		File file = new File("driverDeTeste/chromedriver.exe");
		String absolutePath = file.getAbsolutePath();
		
		System.setProperty("webdriver.chrome.driver", absolutePath);
		baseUrl = "http://localhost:8080/csiaff";
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testFluxoPrincipal() throws Exception {
		testLogin();
		testCadastroPessoa();
		testExclusaoPessoa();
		testLogout();
	}

	public void testLogin() throws Exception {
		driver.get(baseUrl + "/login");
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("admin@iaff.org");
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("admin");
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		assertTrue(driver.getTitle().contains("Estatística"));
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
		driver.findElement(By.cssSelector(
				".table-responsive.bw-tabela-simples table.table.table-hover tbody tr:nth-child(1) td a.btn.js-exclusao-btn"))
				.click();
		for (int second = 0;; second++) {
			if (second >= 60)
				fail("timeout");
			try {
				if (isElementPresent(By.cssSelector(
						"html body.stop-scrolling div.sweet-alert.showSweetAlert.visible div.sa-button-container div.sa-confirm-button-container button.confirm")))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}

		// Verificando se realmente foi excluído
		driver.findElement(By.cssSelector(
				"html body.stop-scrolling div.sweet-alert.showSweetAlert.visible div.sa-button-container div.sa-confirm-button-container button.confirm"))
				.click();
		Thread.sleep(1000);

		String msgExclusao = driver.findElement(By.cssSelector("div.sweet-alert.showSweetAlert.visible > p")).getText();
		assertEquals("Excluído com sucesso!", msgExclusao);
	}

	public void testLogout() throws Exception {
		// Saindo do sistema
		driver.get(baseUrl + "/pessoas");
		driver.findElement(By.xpath("//nav[@id='main-navbar']/div/ul/li[2]/a/em")).click();
		String tituloPaginaLogin = driver.getTitle();
		assertTrue(tituloPaginaLogin.contains("IAFF - login"));
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}
