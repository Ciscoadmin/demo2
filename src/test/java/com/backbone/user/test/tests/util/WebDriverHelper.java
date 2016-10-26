package com.backbone.user.test.tests.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebDriverHelper {

    public static boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } // try
        catch (Exception e) {
            return false;
        } // catch
    }


    public static void highLight(WebElement webElement, WebDriver driver) {
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", webElement);
            try {
                Thread.sleep(300);
            } catch (Exception ex) { }
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border=''", webElement);
            try {
                Thread.sleep(300);
            } catch (Exception ex) { }
        }
    }





}

