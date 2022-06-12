package com.sample.auto.stepdefinitions.web;

import com.sample.auto.pagefactory.web.HomePage;
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
    private HomePage homePage;

    @When("search for TV")
    public void searchForTV() throws Exception {
        homePage = new HomePage(web);

        homePage._log("Click on HamburgerIcon.");
        homePage.clickOnHamburgerIcon();

        homePage._log("Scroll in menu till TV, Applications");
        homePage.scrollAndClickOnTVApplications();

        homePage._log("Select subcategory as 'Televisions'.");
        homePage.selectSubCategoryAs("Televisions");

        homePage._log("Select brand as Samsung.");
        boolean flag = homePage.selectBrandAs("Samsung");
        Assert.assertTrue(flag, "Samsung as a brand not selected.");
    }

    @Then("select range filter high to low")
    public void selectRangeFilterHighToLow() {
        homePage._log("Select Sort By: feature as High to Low.");
        boolean flag = homePage.selectFilterRange("High to Low");
        Assert.assertTrue(flag, "Price: High to Low featured not selected.");
    }

    @And("check product with {int}nd highest price")
    public void checkProductWithNdHighestPrice(int arg0) throws Exception {
        homePage._log("Select " + arg0 + "nd highest product in new tab.");
        Assert.assertTrue(homePage.selectNthProduct(arg0), arg0 + "nd highest product title not matched in new tab. ");
    }

    @Then("verify about product and log details")
    public void verifyAboutProductAndLogDetails() {
        boolean flag = homePage.isAboutTheProductTitleDisplayed();
        Assert.assertTrue(flag, "About the product title not displayed.");
        homePage._log("Here are the product details: \n");
        var details = homePage.logDetails();
        homePage._log(details);
    }
}
