package com.sample.auto.drivers;

import com.sample.auto.configs.AppConfig;
import com.sample.auto.configs.CustomConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

import static org.openqa.selenium.remote.Browser.*;

@Component
public class WebDriverBuilder {

    @Autowired(required = false)
    private CustomConfig customConfig;
    @Autowired(required = false)
    private AppConfig appConfig;
    @Autowired
    private CapabilitiesBuilder capabilitiesBuilder;
    private Browser browser;

    public WebDriver setupDriver(String platFormName) throws MalformedURLException {
        WebDriver driver = null;
        this.browser = getBrowser(platFormName);

        if (appConfig.getExecutionOn().equalsIgnoreCase("local"))
            driver = getLocalWebDriver();
        else if (appConfig.getExecutionOn().equalsIgnoreCase("docker-grid"))
            driver = getRemoteWebDriver();
        else
            throw new RuntimeException("");

        driver.manage().window().maximize();
        return driver;
    }

    private Browser getBrowser(String platformName) {
        switch (platformName.toLowerCase(Locale.ROOT)) {
            case "chrome":
                return CHROME;
            case "firefox":
                return FIREFOX;
            default:
                throw new RuntimeException("");
        }
    }

    private WebDriver getLocalWebDriver() {
        if (CHROME.equals(this.browser)) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver(capabilitiesBuilder.getChromeOptions());
        } else if (FIREFOX.equals(this.browser)) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver(capabilitiesBuilder.getFirefoxOptions());
        }
        throw new RuntimeException("");
    }

    private WebDriver getRemoteWebDriver() throws MalformedURLException {
        if (CHROME.equals(this.browser) || FIREFOX.equals(this.browser) || EDGE.equals(this.browser))
            return new RemoteWebDriver(new URL(customConfig.getSeleniumGridUrl()), capabilitiesBuilder.getCapabilities(this.browser));
        else
            throw new RuntimeException("");
    }
}
