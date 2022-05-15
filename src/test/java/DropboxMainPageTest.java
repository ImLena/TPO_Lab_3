import com.example.lab_3.utils.InitDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.*;

public class DropboxMainPageTest {
    private WebDriver driver;
    private final InitDriver initDriver = new InitDriver();
    private DropboxMainPage page;

    @BeforeEach
    public void setUp(){
        driver = initDriver.initWebDriver();
        page = new DropboxMainPage(driver);
        driver.get("https://www.dropbox.com/");
    }

    @Test
    public void paymentTestPlus() {
        page.getStartedButton.click();
        page.buyButton.click();
        assertEquals(driver.getCurrentUrl(), "https://www.dropbox.com/buy/plus");
    }

    @Test
    public void paymentTestFamily() {
        page.getStartedButton.click();
        page.familyButton.click();
        assertEquals(driver.getCurrentUrl(), "https://www.dropbox.com/buy/family");
    }

    @Test
    public void customerStoriesTest() {
        Actions actions = new Actions(driver);
        actions.moveToElement(page.whyDropboxButton).build().perform();
        page.customersButton.click();
        assertEquals(driver.getCurrentUrl(), "https://www.dropbox.com/business/customers");
    }

    @Test
    public void changeLanguageTest() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        Thread.sleep(5000);
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        page.changeLanguage.click();
        page.russian.click();
        Thread.sleep(5000);
        assertEquals(page.html.getAttribute("lang"), "ru");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
