package com.sample.auto.drivers;

import com.sample.auto.configs.CustomConfig;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.CapabilityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;

@Component
public class CapabilitiesBuilder {

    @Autowired
    private CustomConfig customConfig;

    public Capabilities getCapabilities(Browser browserName) {
        if (Browser.CHROME.equals(browserName)) {
            return getChromeOptions();
        } else if (Browser.FIREFOX.equals(browserName)) {
            return getFirefoxOptions();
        }
        throw new IllegalStateException(String.format("%s is not a supported browser, or update this method.", browserName.toString()));
    }

    public ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setAcceptInsecureCerts(true);
        chromeOptions.setHeadless(customConfig.isBrowserHeadless());
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--window-size=1920,1080");
        chromeOptions.addArguments("start-maximized");
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        chromeOptions.addArguments("--enable-javascript");
        chromeOptions.addArguments("--disable-notifications");

        Map<String, Object> prefs = new LinkedHashMap<>();
        prefs.put("plugins.always_open_pdf_externally", true);
        prefs.put("download.default_directory", String.format("%s\\%s", System.getProperty("user.dir"), customConfig.getDownloadsDir()));
        prefs.put("profile.default_content_settings.cookies", 2);
        chromeOptions.setExperimentalOption("prefs", prefs);

        // To get error console logs
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.parse(customConfig.getSeleniumLog()));
        chromeOptions.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        return chromeOptions;
    }

    public FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        return firefoxOptions;
    }
}
