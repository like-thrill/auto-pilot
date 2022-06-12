package com.sample.auto.stepdefinitions;


import com.sample.auto.configs.AppConfig;
import com.sample.auto.configs.CustomConfig;
import com.sample.auto.configs.WebDriverConfig;
import com.sample.auto.drivers.WebDriverBuilder;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.test.context.ContextConfiguration;

import java.net.MalformedURLException;

@RequiredArgsConstructor
@CucumberContextConfiguration
@ContextConfiguration(classes = {AppConfig.class})
public class DriverHook {

    private final AppConfig appConfig;
    private final CustomConfig customConfig;
    private WebDriver driver;
    private Scenario scenario;
    private final WebDriverBuilder webDriverBuilder;
    private final WebDriverConfig webDriverConfig;


    @Given("Launch the browser")
    public void launchTheBrowser() throws MalformedURLException {
        this.driver = webDriverBuilder.setupDriver(appConfig.getPlatFormName());
        webDriverConfig.setDriver(this.driver, "web");
        this.driver.get(appConfig.getUrl());
    }

    @Before
    public void setScenario(Scenario scenario) {
        customConfig.setScenario(scenario);
        this.scenario = scenario;
    }

    @After
    public void addScreenshotsToReport(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenBytes = null;
            screenBytes = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenBytes, "image/png", scenario.getName());
        }
    }

    @After(order = 0)
    public void killDriver() {
        try {
            if (this.driver != null)
                driver.quit();
        } catch (Exception e) {
            //Do nothing. Enjoy your day!!
        }
    }
}
