package com.sample.auto.pages.web;

import com.sample.auto.utilities.CommonUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends CommonUtils {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "nav-logo")
    private WebElement appLogo;
    @FindBy(id = "nav-hamburger-menu")
    private WebElement hamburger;
    @FindBy(xpath = "//*[contains(@class,'results-header')]//*[text()='RESULTS']")
    private WebElement searchResultHeader;
    @FindBy(id = "productTitle")
    private WebElement individualProductTitle;
    private By tvAppliances = By.xpath("//*[text()='shop by department']/ancestor::ul[contains(@class,'hmenu')]//*[text()='TV, Appliances, Electronics']");

    private By productByBrand(String brand) {
        return By.xpath("//*[text()='Brands']/../..//*[contains(text(),'" + brand + "')]");
    }

    private By productByBrandInput(String brand) {
        return By.xpath("//*[text()='Brands']/../..//*[contains(text(),'" + brand + "')]/parent::a//*[@type='checkbox']");
    }

    private By featuredCategory(String category) {
        return By.xpath("//*[contains(text(),'" + category + "') and contains(@class,'dropdown')]");
    }

    private By featuredCategoryOption(String category) {
        return By.xpath("//*[contains(@class,'dropdown-prompt') and text()='Price: " + category + "']");
    }

    private By searchResults() {
        return By.xpath("//*[contains(@data-component-type,'search-result')]");
    }

    private By featuredCategoryDropDown() {
        return By.xpath("//*[contains(@class,'dropdown-label') and text()='Sort by:']");
    }

    private By itemList() {
        return By.xpath("//*[@id='feature-bullets']//li");
    }

    public void clickOnHamburgerIcon() throws Exception {
        click(hamburger);
    }

    public void scrollAndClickOnTVApplications() {
        scrollToElement(tvAppliances);
        click(tvAppliances);
    }

    public void selectSubCategoryAs(String category) throws Exception {
        clickOnText(category);
    }

    public boolean selectBrandAs(String brandName) {
        isElementVisible(productByBrand(brandName));
        scrollToElement(productByBrand(brandName));
        click(productByBrand(brandName));
        isElementVisible(searchResultHeader);
        return isElementSelected(productByBrandInput(brandName));
    }

    public boolean selectFilterRange(String category) {
        click(featuredCategoryDropDown());
        click(featuredCategory(category));
        isElementVisible(searchResultHeader);
        return isElementPresent(featuredCategoryOption(category));
    }

    public boolean selectNthProduct(int number) throws Exception {
        isElementVisible(searchResults());
        List<WebElement> items = driver.findElements(searchResults());
        items.get(number).click();
        var productName = items.get(number).getText();
        switchTheNextTab();
        var productTitle = getText(individualProductTitle).strip();
        return productName.contains(productTitle);
    }

    public boolean isAboutTheProductTitleDisplayed() {
        return isTextContainsPresent("About this item");
    }

    public String logDetails() {
        StringBuilder stringBuilder = new StringBuilder();
        List<WebElement> details = driver.findElements(itemList());
        for (var detail : details)
            stringBuilder.append(detail.getText() + "\n");
        return String.valueOf(stringBuilder);
    }
}
