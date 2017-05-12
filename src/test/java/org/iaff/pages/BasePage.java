package org.iaff.pages;

import java.util.NoSuchElementException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage extends ConfigPage {

    private WebDriver driver;
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();

    public BasePage() {
        this.driver = getDriver();
        this.baseUrl = getBaseUrl();
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
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

    public void testLogout() throws Exception {
        // Saindo do sistema
        driver.get(baseUrl + "/pessoas");
        driver.findElement(By.cssSelector("#main-navbar > div > ul > li:nth-child(2) > a")).click();
        String tituloPaginaLogin = driver.getTitle();
        assertTrue(tituloPaginaLogin.contains("IAFF"));
    }

    public void sairDosTestes() {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    public void excluirDado(WebDriver driver) throws Exception {
        driver.findElement(By.cssSelector(
                ".table-responsive.bw-tabela-simples table.table.table-hover tbody tr:nth-child(1) td a.btn.js-exclusao-btn"))
                .click();
        for (int second = 0;; second++) {
            if (second >= 60) {
                fail("timeout");
            }
            try {
                if (isElementPresent(By.cssSelector(
                        "html body.stop-scrolling div.sweet-alert.showSweetAlert.visible div.sa-button-container div.sa-confirm-button-container button.confirm"))) {
                    break;
                }
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

}
