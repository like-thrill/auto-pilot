package com.sample.auto.stepdefinitions.web;

import com.sample.auto.configs.AppConfig;
import com.sample.auto.pages.web.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.java.Log;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

@Log
public class HomeHook {

    @Autowired
    private WebDriver web;
    @Autowired
    private AppConfig appConfig;
    private HomePage homePage;

    @When("search for TV")
    public void searchForTV() throws Exception {
        homePage = new HomePage(web);

        homePage.log("Click on HamburgerIcon.");
        homePage.clickOnHamburgerIcon();

        homePage.log("Scroll in menu till TV, Applications");
        homePage.scrollAndClickOnTVApplications();

        homePage.log("Select subcategory as '" + appConfig.getCategory() + "'.");
        homePage.selectSubCategoryAs(appConfig.getCategory());

        homePage.log("Select brand as " + appConfig.getBrand() + ".");
        boolean flag = homePage.selectBrandAs(appConfig.getBrand());
        Assert.assertTrue(flag, appConfig.getBrand() + " as a brand not selected.");
    }

    @Then("select range filter high to low")
    public void selectRangeFilterHighToLow() {
        homePage.log("Select Sort By: feature as High to Low.");
        homePage.selectFilterRange("High to Low");
    }

    @And("check product with nth highest price")
    public void checkProductWithNdHighestPrice() throws Exception {
        var arg0 = appConfig.getNThDevice();
        homePage.log("Select " + arg0 + "nd highest product in new tab.");
        homePage.selectNthProduct(arg0);
    }

    @Then("verify about product and log details")
    public void verifyAboutProductAndLogDetails() {
        boolean flag = homePage.isAboutTheProductTitleDisplayed();
        Assert.assertTrue(flag, "About the product title not displayed.");
        homePage.log("Here are the product details: \n");
        var details = homePage.logDetails();
        homePage.log(details);
    }
}
