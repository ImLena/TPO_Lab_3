import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// https://www.dropbox.com/
public class DropboxMainPage {

    @FindBy(xpath = "//*/text()[normalize-space(.)='Why Dropbox?']/parent::*")
    public WebElement whyDropboxButton;

    @FindBy(xpath = "//*[@id=\"controller-0.7718227389029615\"]/span")
    public WebElement dropboxHeader;

    @FindBy(xpath = "(.//*[normalize-space(text()) and normalize-space(.)='Why Dropbox?'])[1]/following::span[3]")
    public WebElement customersButton;

    @FindBy(xpath = "//*[@id=\"warp-metadata\"]/div/nav/div[1]/div[3]/a/span[1]")
    public WebElement getStartedButton;

    @FindBy(xpath = "//a[contains(text(),'Buy now')]")
    public WebElement buyButton;

    @FindBy(xpath = "//a[contains(@href, '/buy/family')]")
    public WebElement familyButton;

    @FindBy(xpath = "//*[@id=\"warp-metadata\"]/footer/div/div/div/div/div[2]/div/button")
    public WebElement changeLanguage;

    @FindBy(xpath = "//div[@id='locale-selector-modal']/div[2]/div/ul/li[15]/button/span")
    public WebElement russian;
    @FindBy(xpath = "/html")
    public WebElement html;

    public DropboxMainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}