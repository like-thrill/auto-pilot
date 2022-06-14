package com.sample.auto.utilities;

import com.sample.auto.configs.CustomConfig;
import lombok.extern.java.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;

import java.util.Set;

@Log
public class CommonUtils extends CustomWebDriverWait {
    private CustomConfig customConfig;
    private long timeOut;

    public CommonUtils(WebDriver driver) {
        super(driver);
        this.customConfig = CustomConfig.getBean(CustomConfig.class);
        this.timeOut = customConfig.getSeleniumTimeOut();
    }

    /**
     *
     * @param textValue
     * @return true if given text present in dom else false
     */
    public boolean isTextContainsPresent(String textValue) {
        try {
            By eID = getByOfText(textValue, true, false);
            return isElementPresent(eID);
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     *
     * @param textValue
     * @param wildCard
     * @return true if given text present in dom else false with wildcard string
     */
    public boolean isTextContainsPresent(String textValue, boolean wildCard) {
        try {
            By eID = getByOfText(textValue, true, wildCard);
            return isElementPresent(eID);
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     *
     * @param textValue
     * @return
     */
    public boolean isTextPresent(String textValue) {
        try {
            By eID = getByOfText(textValue, false, false);
            return isElementPresent(eID);
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * Performs sendKeys in an element with explicit wait
     *
     * @param element      WebElement
     * @param keysSequence Text to type-in
     */
    public void sendKeys(WebElement element, String keysSequence) throws Exception {
        By eId = convertElemToBy(element);
        if (!isElementVisible(eId))
            throw new RuntimeException("Unable to locate the element even after waiting for "
                    + timeOut + " seconds");
        driver.findElement(eId).clear();
        driver.findElement(eId).sendKeys(keysSequence);
    }

    /**
     * Performs sendKeys in an element with explicit wait
     *
     * @param element WebElement
     * @param keys    Text to type-in
     */
    public void sendKeys(WebElement element, Keys keys) throws Exception {
        By eId = convertElemToBy(element);
        if (!isElementVisible(eId))
            throw new RuntimeException("Unable to locate the element even after waiting for "
                    + timeOut + " seconds");
        driver.findElement(eId).clear();
        driver.findElement(eId).sendKeys(keys);
    }

    /**
     * Performs sendKeys in an element with explicit wait
     *
     * @param by   WebElement
     * @param keys Text to type-in
     */
    public void sendKeys(By by, Keys keys) {
        if (!isElementVisible(by))
            throw new RuntimeException("Unable to locate the element even after waiting for "
                    + timeOut + " seconds");
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(keys);
    }

    /**
     * Performs sendKeys in an element with explicit wait
     *
     * @param element      By
     * @param keysSequence Text to type-in
     */
    public void sendKeys(By element, String keysSequence) {
        if (!isElementVisible(element))
            throw new RuntimeException("Unable to locate the element even after waiting for "
                    + timeOut + " seconds");
        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(keysSequence);
    }

    /**
     * Performs a click operation on a WebElement with explicit wait
     *
     * @param element WebElement
     */
    public void click(WebElement element) throws Exception {
        By eID = convertElemToBy(element);
        if (!isElementVisible(eID))
            throw new RuntimeException("Unable to locate the element even after waiting for "
                    + timeOut + " seconds");
        driver.findElement(eID).click();
    }

    /**
     * Performs a click operation on a WebElement with explicit wait
     *
     * @param by WebElement
     */
    public void click(By by) {
        if (!isElementVisible(by))
            throw new RuntimeException("Unable to locate the element even after waiting for "
                    + timeOut + " seconds");
        driver.findElement(by).click();
    }

    /**
     * Performs a click operation on a By element with explicit wait
     *
     * @param text String
     */
    public void clickOnTextContains(String text) throws Exception {
        By element = getByOfText(text, true, false);
        if (!isElementVisible(element))
            throw new RuntimeException("Unable to locate the element even after waiting for " + timeOut + " seconds");
        driver.findElement(element).click();
    }

    /**
     * Performs a click operation on a By element with explicit wait
     *
     * @param text String
     */
    public void clickOnText(String text) throws Exception {
        By element = getByOfText(text, false, false);
        if (!isElementVisible(element))
            throw new RuntimeException("Unable to locate the element even after waiting for " + timeOut + " seconds");
        driver.findElement(element).click();
    }

    /**
     * Performs a scroll on a web page based on the web element co-ordinates
     *
     * @param element WebElement
     */
    public void scrollToElement(WebElement element) {
        Coordinates cor = ((Locatable) element).getCoordinates();
        cor.inViewPort();
    }

    /**
     * Performs a scroll on a web page based on the web element co-ordinates
     *
     * @param by By
     */
    public void scrollToElement(By by) {
        WebElement element = driver.findElement(by);
        Coordinates cor = ((Locatable) element).getCoordinates();
        cor.inViewPort();
    }

    /**
     * Performs a scroll within the provided web element
     *
     * @param webElement WebElement
     */
    public void scrollWithinElement(WebElement webElement) {
        ((JavascriptExecutor) this.driver).executeScript("arguments[0].scrollTop=500;", webElement);
    }

    /**
     *
     * @param by
     * @return true if given element locator is selected.
     */
    public boolean isElementSelected(By by) {
        isElementPresent(by);
        return driver.findElement(by).isSelected();
    }

    /**
     * Switch to next tab.
     * throw exception if more than two tabs open at same driver instance
     */
    public void switchTheNextTab() {
        var currentWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();
        if (windows.size() > 2)
            throw new RuntimeException("More than 2 tabs opened.");
        for (var window : windows) {
            if (!window.equals(currentWindow))
                driver.switchTo().window(window);
        }
    }

    /**
     *
     * @param element
     * @return string value of given element
     * @throws Exception
     */
    public String getText(WebElement element) throws Exception {
        By eID = convertElemToBy(element);
        return driver.findElement(eID).getText();
    }

    /**
     * Append logs in Scenarios steps.
     * @param message
     */
    public void log(Object message) {
        log.info(String.valueOf(message));
        if (customConfig.getScenario() != null)
            customConfig.getScenario().log("\t> " + message.toString());
        else
            log.warning("Scenario not found.");
    }
}
