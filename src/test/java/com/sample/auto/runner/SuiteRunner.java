package com.sample.auto.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.*;

@CucumberOptions(
        features = {"src/test/resources/features/"},
        plugin = {"json:target/cucumber/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        glue = {"com.sample.auto.stepdefinations"},
        tags = "@amazon"
)
public class SuiteRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @BeforeSuite
    public void beforeSuite() {
        //Add conditions to run before suite.
    }

    @BeforeTest
    public void beforeTest() {
        //Add conditions to run before test.
    }

    @AfterTest
    public void afterTest() {
        //Add conditions to run after suite.
    }

    @AfterSuite
    public void afterSuite() {
        //Add conditions to run after suite.
    }
}
