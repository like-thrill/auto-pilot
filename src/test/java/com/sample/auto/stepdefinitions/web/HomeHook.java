package com.sample.auto.stepdefinitions.web;

import com.sample.auto.pagefactory.web.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.Assert;

public class HomeHook {

    @Autowired
    private WebDriver web;
    private HomePage homePage;

    @When("search for TV")
    public void searchForTV() throws Exception {
        homePage = new HomePage(web);
       homePage.clickOnHamburgerIcon();
       ////*[text()='shop by department']/ancestor::ul[contains(@class,'hmenu')]//*[text()='TV, Appliances, Electronics']
       // Assert.fail();
    }

    @Then("select range filter high to low")
    public void selectRangeFilterHighToLow() {

    }

    @And("check product with {int}nd highest price")
    public void checkProductWithNdHighestPrice(int arg0) {

    }
}
