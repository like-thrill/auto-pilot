package com.sample.auto.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class WebDriverBuilder {

    public WebDriver setupDriver(String platFormName) {
        WebDriver driver = null;
        switch (platFormName.toLowerCase(Locale.ROOT)) {
            case "chrome": {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                driver = new ChromeDriver(options);
                break;
            }
            case "firefox": {
                //Dependencies for firefox browser;
                //break;
            }
            default:
                throw new RuntimeException("Please add you browser dependencies here.");
        }

        driver.manage().window().maximize();
        return driver;
    }
}
