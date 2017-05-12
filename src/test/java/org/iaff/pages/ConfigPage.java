package org.iaff.pages;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ConfigPage {

    private WebDriver driver;
    private String baseUrl;

    public ConfigPage() {
        File file = new File("driverDeTeste/chromedriver.exe");
        String absolutePath = file.getAbsolutePath();
        System.setProperty("webdriver.chrome.driver", absolutePath);

        this.driver = new ChromeDriver();
        this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        this.baseUrl = "http://localhost:8080";
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getBaseUrl() {
        return baseUrl;
    }


}
