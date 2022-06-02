package com.sample.auto.utilities;

import com.sample.auto.configs.AppConfig;
import org.openqa.selenium.*;

public class CommonUtils extends CustomWebDriverWait {
    private AppConfig appConfig;

    public CommonUtils(WebDriver driver) {
        super(driver);
        this.appConfig = AppConfig.getBean(AppConfig.class);
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
                    + appConfig.getCurrentOS() + " seconds");
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
                    + appConfig.getSeleniumTimeOut() + " seconds");
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
                    + appConfig.getSeleniumTimeOut() + " seconds");
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
                    + appConfig.getSeleniumTimeOut() + " seconds");
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
                    + appConfig.getSeleniumTimeOut() + " seconds");
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
                    + appConfig.getSeleniumTimeOut() + " seconds");
        driver.findElement(by).click();
    }
}
