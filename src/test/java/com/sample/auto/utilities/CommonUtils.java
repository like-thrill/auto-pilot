package com.sample.auto.utilities;

import com.sample.auto.configs.CustomConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;

public class CommonUtils extends CustomWebDriverWait {
    private CustomConfig customConfig;
    private long timeOut;

    public CommonUtils(WebDriver driver) {
        super(driver);
        this.customConfig = CustomConfig.getBean(CustomConfig.class);
        this.timeOut= customConfig.getSeleniumTimeOut();
    }

    public void _clear(WebElement element) throws Exception {
        By eID = _convertElemToBy(element);
        driver.findElement(eID).clear();
    }

    public boolean _isTextContainsPresent(String textValue) {
        try {
            By eID = _getByOfText(textValue, true, false);
            return _isElementPresent(eID);
        } catch (Exception exception) {
            return false;
        }
    }

    public boolean _isTextContainsPresent(String textValue, boolean wildCard) {
        try {
            By eID = _getByOfText(textValue, true, wildCard);
            return _isElementPresent(eID);
        } catch (Exception exception) {
            return false;
        }
    }

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
     * Performs a scroll within the provided web element
     *
     * @param webElement WebElement
     */
    public void _scrollWithinElement(WebElement webElement) {
        ((JavascriptExecutor) this.driver).executeScript("arguments[0].scrollTop=500;", webElement);
    }
}
