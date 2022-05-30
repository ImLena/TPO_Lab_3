package com.example.lab_3.utils;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class InitDriver {
    private DriverManager driverManager;
    private JavascriptExecutor jse;
    private FileInputStream fileInputStream;
    private final Properties property = new Properties();

    @Getter
    private final List<WebDriver> drivers = new ArrayList<>();

    public InitDriver() {
        try {
            fileInputStream = new FileInputStream("src/main/resources/config.properties");
            property.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<WebDriver> initWebDriver() {
        if (property.getProperty("webdriver.chrome.driver").equals("true")) {
            driverManager = DriverFactory.valueOf("CHROME").getDriverManager();
            drivers.add(driverManager.getDriver());
        }
        if (property.getProperty("webdriver.gecko.driver").equals("true")) {
            driverManager = DriverFactory.valueOf("FIREFOX").getDriverManager();
            drivers.add(driverManager.getDriver());
        }
        drivers.forEach(driver -> driver.manage().window().maximize());
        return drivers;
    }

    public void login(WebDriver driver) throws InterruptedException {
        driver.get("https://www.dropbox.com/login");
        jse = (JavascriptExecutor) driver;
        Thread.sleep(1000);
        jse.executeScript("window.scrollBy(0,250)");
        driver.findElement(By.xpath("//div[@id='login-or-register-page-content']/div/div[3]/div/div/div[2]/div/div/form/div/div/div[2]/input")).click();
        driver.findElement(By.xpath("//div[@id='login-or-register-page-content']/div/div[3]/div/div/div[2]/div/div/form/div/div/div[2]/input")).sendKeys("evkhodosova@gmail.com");
        driver.findElement(By.xpath("//div[2]/div[2]/input")).click();
        driver.findElement(By.xpath("//div[2]/div[2]/input")).clear();
        driver.findElement(By.xpath("//div[2]/div[2]/input")).sendKeys("12345678");
        driver.findElement(By.xpath("//div[@id='login-or-register-page-content']/div/div[3]/div/div/div[2]/div/div/form/div[2]/button/div")).click();
    }
}
