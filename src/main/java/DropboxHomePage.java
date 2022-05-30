import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// https://www.dropbox.com/home
public class DropboxHomePage {

    @FindBy(xpath = "//button[contains(@aria-label, 'Create,')]")
    public WebElement createButton;

    @FindBy(xpath = "/html/body/div[9]/div/nav/div/div/div[1]")
    public WebElement folderButton;

    @FindBy(xpath = "//input[@id='new_folder_name_input']")
    public WebElement folderNameInput;

    @FindBy(xpath = "/html/body/div[9]/div/div/div/div[3]/button[2]/span")
    public WebElement createFolderButton;

    @FindBy(xpath = "//*[@id=\"files-view-table-container\"]/tr[1]/td[4]/div/div")
   // @FindBy(xpath = "//*[@id=\"files-view-table-container\"]/tr[1]/td[4]/div/div/span/button")
    //@FindBy(css = "div.rc-inline-action-bar-container.brws-file-row-inline-action-bar > div.dig-Menu > div.dig-ClickOutside > button.dig-IconButton.dig-IconButton--transparent.dig-IconButton--standard > span.dig-IconButton-content > svg.dig-UIIcon.dig-UIIcon--standard")
    public WebElement showMoreButton;

    @FindBy(xpath = "(.//*[normalize-space(text()) and normalize-space(.)='Copy link'])[1]/following::*[name()='svg'][2]")
   // @FindBy(xpath = "//*[@id=\"files-view-table-container\"]/tr[1]/td[4]/div/div/span/button")
    //@FindBy(css = "div.rc-inline-action-bar-container.brws-file-row-inline-action-bar > div.dig-Menu > div.dig-ClickOutside > button.dig-IconButton.dig-IconButton--transparent.dig-IconButton--standard > span.dig-IconButton-content > svg.dig-UIIcon.dig-UIIcon--standard")
    public WebElement showOneMoreButton;

    @FindBy(xpath = "(.//*[normalize-space(text()) and normalize-space(.)='Add automation'])[1]/following::div[5]")
    public WebElement deleteButton;

    @FindBy(xpath = "//td[2]/div[2]/div/div/form/span/input")
    public WebElement renameInput;

    @FindBy(xpath = "/html/body/div[9]/div/div/div/div[3]/button[2]/span")
    public WebElement deletePopupButton;

    @FindBy(xpath = "//*[@id=\"sidebar-portal\"]/div/div/div/div/div[1]/div[2]/button/span")
    public WebElement removeStupidPanelButton;

    @FindBy(xpath = "//tbody[@id='files-view-table-container']/tr/td[5]/div/button/span")
    public WebElement copyLinkButton;

    @FindBy(xpath = "//*[@id=\"files-view-table-container\"]/tr[1]/td[3]/div/div")
    public WebElement copyLinkContaiter;

    @FindBy(xpath = "/html/body/div[10]/div/div[2]/span/input")
    public WebElement copiedLink;

    @FindBy(xpath = "//*[@id=\"files-view-table-container\"]/tr[1]/td[2]/div[2]/div/div[2]")
    public WebElement starButton;

    @FindBy(xpath = "//*[@id=\"files-table-label-0\"]")
    public WebElement staredItem;

    @FindBy(xpath = "(.//*[normalize-space(text()) and normalize-space(.)='Share'])[1]/preceding::*[name()='svg'][2]")
    public WebElement showMore;

    @FindBy(xpath = "(.//*[normalize-space(text()) and normalize-space(.)='Delete'])[2]/following::div[4]")
    public WebElement rename;


    public DropboxHomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}