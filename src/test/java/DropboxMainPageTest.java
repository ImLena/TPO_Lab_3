import com.example.lab_3.utils.InitDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class DropboxMainPageTest {
    private List<WebDriver> drivers;
    private final InitDriver initDriver = new InitDriver();
    private DropboxMainPage page;

    @BeforeEach
    public void setUp() {
        drivers = initDriver.initWebDriver();
        System.out.println("DRIVERS SIZE:" + drivers.size());
    }

    @Test
    public void paymentTestPlus() {
        drivers.forEach(driver -> {
            page = new DropboxMainPage(driver);
            driver.get("https://www.dropbox.com/");
            System.out.println("DRIVER RUNNING: " + driver.getCurrentUrl() + driver);
            page.getStartedButton.click();
            page.buyButton.click();
            assertEquals(driver.getCurrentUrl(), "https://www.dropbox.com/buy/plus");
        });
    }

    @Test
    public void paymentTestFamily() {
        drivers.forEach(driver -> {
            page = new DropboxMainPage(driver);
            driver.get("https://www.dropbox.com/");
            page.getStartedButton.click();
            page.familyButton.click();
            assertEquals(driver.getCurrentUrl(), "https://www.dropbox.com/buy/family");
        });
    }

    @Test
    public void customerStoriesTest() {
        drivers.forEach(driver -> {
            page = new DropboxMainPage(driver);
            driver.get("https://www.dropbox.com/");
            Actions actions = new Actions(driver);
            actions.moveToElement(page.whyDropboxButton).build().perform();
            page.customersButton.click();
            assertEquals(driver.getCurrentUrl(), "https://www.dropbox.com/business/customers");
        });
    }

    @Test
    public void changeLanguageTest() {
        drivers.forEach(driver -> {
            try {
                page = new DropboxMainPage(driver);
                driver.get("https://www.dropbox.com/");
                System.out.println("DRIVER RUNING: " + driver.getCurrentUrl() + driver);
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                Thread.sleep(5000);
                jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
                page.changeLanguage.click();
                page.russian.click();
                Thread.sleep(5000);
                assertEquals(page.html.getAttribute("lang"), "ru");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @AfterEach
    public void tearDown() {
        drivers.forEach(WebDriver::quit);
    }
}
