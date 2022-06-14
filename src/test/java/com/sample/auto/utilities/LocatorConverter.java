package com.sample.auto.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LocatorConverter {
    protected By convertElemToBy(WebElement element) throws Exception {
        String locator;
        StringBuilder elem = new StringBuilder();
        String eleString = element.toString();
        if (eleString.contains("Proxy element for: DefaultElementLocator")) {
            String elemString = eleString.replace("Proxy element for: DefaultElementLocator ", "");
            String[] trimmedText = elemString.substring(1, elemString.length() - 1).split(":");
            locator = trimmedText[0].replace("By.", "").trim();
            elem = new StringBuilder(trimmedText[1].trim());
        } else if (eleString.contains("Located by By.chained")) {
            Matcher m = Pattern.compile("\\{(.*?)\\}").matcher(eleString);
            String matchedString = "";
            while (m.find()) {
                matchedString = m.group(1);
            }
            String[] trimmedText = matchedString.split(": ");
            locator = trimmedText[0].replace("By.", "").trim();
            elem = new StringBuilder(trimmedText[1].trim());
        } else if (eleString.contains("Located by By")) {
            String elemString = eleString.replace("Located by By.", "");
            String[] trimmedText = elemString.split(":");
            locator = trimmedText[0].trim();
            for (int i = 1; i < trimmedText.length; i++)
                if (i != trimmedText.length - 1)
                    elem.append(trimmedText[i].trim()).append(":");
                else
                    elem.append(trimmedText[i].trim());
            // elem = new StringBuilder(trimmedText[1].trim());
        } else {
            String[] trimmedText = (eleString.split("->")[1]).split(":");
            locator = trimmedText[0].trim();
            elem = new StringBuilder(trimmedText[1].trim().replaceAll(".$", ""));
        }
        locator = locator.replace("Appium", "");
        return this.getByElement(locator, elem.toString());
    }

    protected By getByElement(String locator, String element) throws Exception {
        switch (locator.toLowerCase()) {
            case "id": {
                return By.id(element);
            }
            case "name": {
                return By.name(element);
            }
            case "classname": {
                return By.className(element);
            }
            case "linktext": {
                return By.linkText(element);
            }
            case "partiallinktext": {
                return By.partialLinkText(element);
            }
            case "cssselector": {
                return By.cssSelector(element);
            }
            case "xpath": {
                return By.xpath(element);
            }
            default: {
                throw new RuntimeException("Unknown locator type '" + locator + "'");
            }
        }
    }

    /**
     * Convert plain text to By
     *
     * @param textValue
     * @param isContains
     * @param wildCard
     * @return
     */
    protected By getByOfText(String textValue, boolean isContains, boolean wildCard) {
        if (isContains) {
            if (wildCard)
                return By.xpath("//*[contains(.,\"" + textValue + "\")]");
            else
                return By.xpath("//*[contains(text(),\"" + textValue + "\")]");
        } else
            return By.xpath("//*[text()=\"" + textValue + "\"]");
    }
}
