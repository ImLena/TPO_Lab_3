import com.example.lab_3.utils.InitDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class DropboxHomePageTest {
    private WebDriver driver;
    private final InitDriver initDriver = new InitDriver();
    private DropboxHomePage page;

    @BeforeEach
    void setUp() throws InterruptedException {
        driver = initDriver.initWebDriver();
        initDriver.login();
        page = new DropboxHomePage(driver);
    }


    @Test
    public void addFolderTest() throws InterruptedException {
        page.createButton.click();
        page.folderButton.click();
        String name = "newFolder";
        page.folderNameInput.sendKeys(name);
        page.createFolderButton.click();
        driverWaiter();
        assertEquals(driver.getCurrentUrl(), "https://www.dropbox.com/home/" + name);
        deleteFolder();
    }

    @Test
    public void addToStaredTest(){
        String lastFileName = "new1.png";
        page.starButton.click();
        System.out.println(lastFileName);
        driver.get("https://www.dropbox.com/starred?_tk=web_left_nav_bar&role=personal");
        assertEquals(page.staredItem.getText(), lastFileName);
    }

    @Test
    public void renameTest() {
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
    }

    @Test
    public void copyLink() throws InterruptedException, IOException, UnsupportedFlavorException {
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
        if (systemClipboard.isDataFlavorAvailable(dataFlavor))
        {
            expected = systemClipboard.getData(dataFlavor);
        }
        assertEquals((String) expected, link);
    }



    public void deleteFolder() throws InterruptedException {
        driver.get("https://www.dropbox.com/home");
        Actions actions = new Actions(driver);
        actions.moveToElement(page.showMoreButton).click().build().perform();
        page.deleteButton.click();
        Thread.sleep(2000);
        page.deletePopupButton.click();
    }

    public void driverWaiter() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.urlMatches("https://www.dropbox.com/home/+"));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}