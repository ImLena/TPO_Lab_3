package com.example.lab_3.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class InitDriver {
    private final Properties property = new Properties();
    private WebDriver driver;
    private DriverManager driverManager;
    private FileInputStream fileInputStream;
    private JavascriptExecutor jse;

    public InitDriver() {
        try {
            fileInputStream = new FileInputStream("/Users/lena/IdeaProjects/TPO/Lab_3/src/test/resources/config.poperties");
            property.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WebDriver initWebDriver() {
        if (property.getProperty("driverType").equals("CHROME")) {
            System.setProperty("webdriver.chrome.driver", "/Users/lena/IdeaProjects/TPO/Lab_3/src/test/resources/chromedriver");
            driverManager = DriverFactory.valueOf("CHROME").getDriverManager();
            driver = driverManager.getDriver();
        } else if (property.getProperty("driverType").equals("FIREFOX")) {
            System.setProperty("webdriver.gecko.driver", "/Users/lena/IdeaProjects/TPO/Lab_3/src/test/resources/geckodriver");
            driverManager = DriverFactory.valueOf("FIREFOX").getDriverManager();
            driver = driverManager.getDriver();
        }
        driver.manage().window().maximize();
        return driver;
    }

    public void login() throws InterruptedException {
        driver.get("https://www.dropbox.com/login");
        jse = (JavascriptExecutor) driver;
        Thread.sleep(1000);
        jse.executeScript("window.scrollBy(0,250)");
        driver.findElement(By.xpath("//div[@id='login-or-register-page-content']/div/div[3]/div/div/div[2]/div/div/form/div/div/div[2]/input")).click();
        driver.findElement(By.xpath("//div[@id='login-or-register-page-content']/div/div[3]/div/div/div[2]/div/div/form/div/div/div[2]/input")).sendKeys("");
        driver.findElement(By.xpath("//div[2]/div[2]/input")).click();
        driver.findElement(By.xpath("//div[2]/div[2]/input")).clear();
        driver.findElement(By.xpath("//div[2]/div[2]/input")).sendKeys("12345678");
        driver.findElement(By.xpath("//div[@id='login-or-register-page-content']/div/div[3]/div/div/div[2]/div/div/form/div[2]/button/div")).click();
    }
}
