package com.sample.auto.configs;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

@Configuration
public class WebDriverConfig {

    public Map<String, ThreadLocal<WebDriver>> drivers = new LinkedHashMap<>();
    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public void setDriver(WebDriver driver, String appName) {
        appName = appName.toLowerCase(Locale.ROOT);
        if (appName.equals("web")) {
            webDriver.set(driver);
            drivers.put(appName, webDriver);
        } else {
            throw new RuntimeException("No definition found for this app [" + appName + "] type. Please add yours here.");
            //There might be another app like android app, ios app etc.
            //Create a condition for you app.
        }
    }

    @Bean
    @Qualifier(value = "web")
    @Scope("prototype")
    public WebDriver webDriver() {
        if (drivers.containsKey("web"))
            return drivers.get("web").get();
        throw new RuntimeException("No WebDriver instance found. Please invoke one by calling " +
                "'Launch the browser' step.");
    }

    /**
     * Add you app bean here with specific Qualifier.
     * Same as above defined for 'web'
     */

}
