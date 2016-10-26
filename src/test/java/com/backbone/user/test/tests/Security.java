package com.backbone.user.test.tests;

import com.backbone.user.test.tests.pages.UserPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by dbolgarov on 10/26/2016.
 */
public class Security extends TestNgTestBase {

    private UserPage userPage;

    @BeforeMethod
    public void initPageObjects() {
        userPage = PageFactory.initElements(driver, UserPage.class);
    }


    @Test(priority = 1)
    public void CheckXSS() {
        driver.get(baseUrl);
        String exampleXSS="<script>alert('This is bad')</script>";
        assertTrue("XSS attack  was  successful",  userPage.NewUser(exampleXSS,""));
    }

    @Test(priority = 2)
    public void ClearListOfUsersAfterXSSAttack() {
     userPage.deleteButton.click();
    }



}
