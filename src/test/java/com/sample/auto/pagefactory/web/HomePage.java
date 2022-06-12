package com.sample.auto.pagefactory.web;

import com.sample.auto.utilities.CommonUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends CommonUtils {

    private WebDriver driver;

    public HomePage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "nav-logo")
    private WebElement appLogo;
    @FindBy(id = "nav-hamburger-menu")
    private WebElement hamburger;

    public void clickOnHamburgerIcon() throws Exception {
        _click(hamburger);
    }

    public void searchSubCategory()
    {
        _scrollToElement(null);
    }

}
