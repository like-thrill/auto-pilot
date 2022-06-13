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
    public boolean _isTextContainsPresent(String textValue) {
        try {
            By eID = _getByOfText(textValue, true, false);
            return _isElementPresent(eID);
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
    public boolean _isTextContainsPresent(String textValue, boolean wildCard) {
        try {
            By eID = _getByOfText(textValue, true, wildCard);
            return _isElementPresent(eID);
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     *
     * @param textValue
     * @return
     */
    public boolean _isTextPresent(String textValue) {
        try {
            By eID = _getByOfText(textValue, false, false);
            return _isElementPresent(eID);
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
    public void _sendKeys(WebElement element, String keysSequence) throws Exception {
        By eId = _convertElemToBy(element);
        if (!_isElementVisible(eId))
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
    public void _sendKeys(WebElement element, Keys keys) throws Exception {
        By eId = _convertElemToBy(element);
        if (!_isElementVisible(eId))
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
    public void _sendKeys(By by, Keys keys) {
        if (!_isElementVisible(by))
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
    public void _sendKeys(By element, String keysSequence) {
        if (!_isElementVisible(element))
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
    public void _click(WebElement element) throws Exception {
        By eID = _convertElemToBy(element);
        if (!_isElementVisible(eID))
            throw new RuntimeException("Unable to locate the element even after waiting for "
                    + timeOut + " seconds");
        driver.findElement(eID).click();
    }

    /**
     * Performs a click operation on a WebElement with explicit wait
     *
     * @param by WebElement
     */
    public void _click(By by) {
        if (!_isElementVisible(by))
            throw new RuntimeException("Unable to locate the element even after waiting for "
                    + timeOut + " seconds");
        driver.findElement(by).click();
    }

    /**
     * Performs a click operation on a By element with explicit wait
     *
     * @param text String
     */
    public void _clickOnTextContains(String text) throws Exception {
        By element = _getByOfText(text, true, false);
        if (!_isElementVisible(element))
            throw new RuntimeException("Unable to locate the element even after waiting for " + timeOut + " seconds");
        driver.findElement(element).click();
    }

    /**
     * Performs a click operation on a By element with explicit wait
     *
     * @param text String
     */
    public void _clickOnText(String text) throws Exception {
        By element = _getByOfText(text, false, false);
        if (!_isElementVisible(element))
            throw new RuntimeException("Unable to locate the element even after waiting for " + timeOut + " seconds");
        driver.findElement(element).click();
    }

    /**
     * Performs a scroll on a web page based on the web element co-ordinates
     *
     * @param element WebElement
     */
    public void _scrollToElement(WebElement element) {
        Coordinates cor = ((Locatable) element).getCoordinates();
        cor.inViewPort();
    }

    /**
     * Performs a scroll on a web page based on the web element co-ordinates
     *
     * @param by By
     */
    public void _scrollToElement(By by) {
        WebElement element = driver.findElement(by);
        Coordinates cor = ((Locatable) element).getCoordinates();
        cor.inViewPort();
    }

    /**
     * Performs a scroll within the provided web element
     *
     * @param webElement WebElement
     */
    public void _scrollWithinElement(WebElement webElement) {
        ((JavascriptExecutor) this.driver).executeScript("arguments[0].scrollTop=500;", webElement);
    }

    /**
     *
     * @param by
     * @return true if given element locator is selected.
     */
    public boolean _isElementSelected(By by) {
        _isElementPresent(by);
        return driver.findElement(by).isSelected();
    }

    /**
     * Switch to next tab.
     * throw exception if more than two tabs open at same driver instance
     */
    public void _switchTheNextTab() {
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
    public String _getText(WebElement element) throws Exception {
        By eID = _convertElemToBy(element);
        return driver.findElement(eID).getText();
    }

    /**
     * Append logs in Scenarios steps.
     * @param message
     */
    public void _log(Object message) {
        log.info(String.valueOf(message));
        if (customConfig.getScenario() != null)
            customConfig.getScenario().log("\t> " + message.toString());
        else
            log.warning("Scenario not found.");
    }
}
