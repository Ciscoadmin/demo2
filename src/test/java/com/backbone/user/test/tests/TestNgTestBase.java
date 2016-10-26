package com.backbone.user.test.tests;


import com.backbone.user.test.tests.util.EventListener;
import com.backbone.user.test.tests.util.PropertyLoader;
import com.backbone.user.test.tests.util.WebDriverHelper;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.selenium.factory.WebDriverFactory;
import ru.stqa.selenium.factory.WebDriverFactoryMode;

import java.io.IOException;

/**
 * Base class for TestNG-based test classes
 */
public class TestNgTestBase {

    protected static String gridHubUrl;
    protected static String baseUrl;
    protected static Capabilities capabilities;

    protected WebDriver webDriver;
//    protected WebDriver driver;

    protected EventFiringWebDriver driver;

    @BeforeSuite
    public void initTestSuite() throws IOException {
        baseUrl = PropertyLoader.loadProperty("site.url");
        gridHubUrl = PropertyLoader.loadProperty("grid.url");
        if ("".equals(gridHubUrl)) {
            gridHubUrl = null;
        }
        capabilities = PropertyLoader.loadCapabilities();
        WebDriverFactory.setMode(WebDriverFactoryMode.THREADLOCAL_SINGLETON);
    }

    @BeforeMethod
    public void initWebDriver() {
        webDriver = WebDriverFactory.getDriver(gridHubUrl, capabilities);
//        driver = WebDriverFactory.getDriver(gridHubUrl, capabilities);
        setupEventListener();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        WebDriverFactory.dismissAll();
    }


    private void setupEventListener(){

        driver = new EventFiringWebDriver(webDriver);

        if (WebDriverHelper.isAlertPresent(driver)) {
            driver.switchTo().alert();
            driver.switchTo().alert().accept();
            driver.switchTo().defaultContent();
        }

        EventListener eventListener = new EventListener();
        driver.register(eventListener);
        driver.manage().window().maximize();
    }
}
