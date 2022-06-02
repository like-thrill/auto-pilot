package com.sample.auto.utilities;

import com.sample.auto.configs.AppConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class CustomWebDriverWait extends LocatorConverter {

    protected WebDriver driver;
    private AppConfig appConfig;

    public CustomWebDriverWait(WebDriver driver) {
        this.driver = driver;
        this.appConfig = AppConfig.getBean(AppConfig.class);
    }

    /**
     * Waits for an Element by with explicit wait and returns true if found
     *
     * @param by By value of element
     * @return boolean
     */
    protected boolean _isElementVisible(By by) {
        try {
            new FluentWait<WebDriver>(driver)
                    .withTimeout(Duration.ofSeconds(appConfig.getSeleniumTimeOut()))
                    .pollingEvery(Duration.ofMillis(10))
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(ElementNotInteractableException.class)
                    .ignoring(InvalidElementStateException.class)
                    .ignoring(NotFoundException.class)
                    .ignoring(InvalidSelectorException.class)
                    .ignoring(ElementClickInterceptedException.class)
                    .until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Waits for an Element by with explicit wait and returns true if found
     *
     * @param element WebElement value of element
     * @return boolean
     */
    protected boolean _isElementVisible(WebElement element) {
        try {
            By eId = _convertElemToBy(element);
            new FluentWait<WebDriver>(driver)
                    .withTimeout(Duration.ofSeconds(appConfig.getSeleniumTimeOut()))
                    .pollingEvery(Duration.ofMillis(10))
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(ElementNotInteractableException.class)
                    .ignoring(InvalidElementStateException.class)
                    .ignoring(NotFoundException.class)
                    .ignoring(InvalidSelectorException.class)
                    .ignoring(ElementClickInterceptedException.class)
                    .until(ExpectedConditions.visibilityOfElementLocated(eId));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Waits for a By element with explicit wait and returns true if found
     *
     * @param element WebElement value of element
     * @return boolean
     */
    protected boolean _isElementPresent(WebElement element) {
        try {
            By eID = _convertElemToBy(element);
            new FluentWait<WebDriver>(driver)
                    .withTimeout(Duration.ofSeconds(appConfig.getSeleniumTimeOut()))
                    .pollingEvery(Duration.ofMillis(10))
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(ElementNotInteractableException.class)
                    .ignoring(InvalidElementStateException.class)
                    .ignoring(NotFoundException.class)
                    .ignoring(InvalidSelectorException.class)
                    .ignoring(ElementClickInterceptedException.class)
                    .until(ExpectedConditions.presenceOfElementLocated(eID));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Waits for a By element with explicit wait and returns true if found
     *
     * @param by WebElement value of element
     * @return boolean
     */
    protected boolean _isElementPresent(By by) {
        try {
            new FluentWait<WebDriver>(driver)
                    .withTimeout(Duration.ofSeconds(appConfig.getSeleniumTimeOut()))
                    .pollingEvery(Duration.ofMillis(10))
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(ElementNotInteractableException.class)
                    .ignoring(InvalidElementStateException.class)
                    .ignoring(NotFoundException.class)
                    .ignoring(InvalidSelectorException.class)
                    .ignoring(ElementClickInterceptedException.class)
                    .until(ExpectedConditions.presenceOfElementLocated(by));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    protected void _sleep(int seconds) {
        try {
            Thread.sleep(seconds);
        } catch (Exception e) {
            //Do Nothing
        }
    }

    protected void setImplicitWait(int seconds) throws InterruptedException {
        this.driver.manage().wait(seconds);
    }

    protected void reSetImplicitWait() throws InterruptedException {
        this.driver.manage().wait(0);
    }

    //Add more conditions if required for wait statement
}
