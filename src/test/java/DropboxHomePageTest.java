import com.example.lab_3.utils.InitDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DropboxHomePageTest {
    private List<WebDriver> drivers;
    private final InitDriver initDriver = new InitDriver();
    private DropboxHomePage page;

    @BeforeEach
    void setUp() {
        drivers = initDriver.initWebDriver();
        System.out.println("DRIVERS SIZE:" + drivers.size());
    }


    @Test
    public void addFolderTest() {
        drivers.forEach(driver -> {
            try {
                initDriver.login(driver);
                page = new DropboxHomePage(driver);
                page.createButton.click();
                page.folderButton.click();
                String name = "newFolder";
                page.folderNameInput.sendKeys(name);
                page.createFolderButton.click();
                driverWaiter(driver);
                assertEquals(driver.getCurrentUrl(), "https://www.dropbox.com/home/" + name);
                deleteFolder(driver);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void renameTest() {
       drivers.forEach(driver -> {
            try {
                initDriver.login(driver);
                page = new DropboxHomePage(driver);
                Actions actions = new Actions(driver);
                try {
                    actions.moveToElement(page.copyLinkContaiter).click().build().perform();
                } catch (org.openqa.selenium.StaleElementReferenceException ex) {
                    actions.moveToElement(page.copyLinkContaiter).click().build().perform();
                }
                page.showMore.click();
                page.showMore.click();
                page.rename.click();
                page.renameInput.sendKeys("new1.png");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void copyLink() {
        drivers.forEach(driver -> {
            try {
                initDriver.login(driver);
                page = new DropboxHomePage(driver);
                Actions actions = new Actions(driver);
                try {
                    actions.moveToElement(page.copyLinkContaiter).click().build().perform();
                } catch (org.openqa.selenium.StaleElementReferenceException ex) {
                    actions.moveToElement(page.copyLinkContaiter).click().build().perform();
                }
                Thread.sleep(5000);
                page.copyLinkButton.click();
                String link = page.copiedLink.getAttribute("value");
                Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
                Clipboard systemClipboard = defaultToolkit.getSystemClipboard();
                DataFlavor dataFlavor = DataFlavor.stringFlavor;
                Object expected = "default";
                if (systemClipboard.isDataFlavorAvailable(dataFlavor)) {
                    expected = systemClipboard.getData(dataFlavor);
                }
                assertEquals((String) expected, link);
            } catch (IOException | UnsupportedFlavorException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }


    public void deleteFolder(WebDriver driver) throws InterruptedException {
        driver.get("https://www.dropbox.com/home");
        Actions actions = new Actions(driver);
        actions.moveToElement(page.showMoreButton).click().build().perform();
        page.deleteButton.click();
        Thread.sleep(2000);
        page.deletePopupButton.click();
        Thread.sleep(2000);
    }

    public void driverWaiter(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.urlMatches("https://www.dropbox.com/home/+"));
    }

    @AfterEach
    void tearDown() {
        drivers.forEach(WebDriver::quit);
    }
}