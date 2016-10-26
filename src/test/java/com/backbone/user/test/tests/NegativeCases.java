package com.backbone.user.test.tests;

import com.backbone.user.test.tests.pages.UserPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;


/**
 * Created by dbolgarov on 10/26/2016.
 */
public class NegativeCases extends TestNgTestBase {

    private UserPage userPage;

    @BeforeMethod
    public void initPageObjects() {
        userPage = PageFactory.initElements(driver, UserPage.class);
    }

    @Parameters({"name", "age"})
    @Test(priority = 1)
    public void AddNewUser(String name, String age) {
        driver.get(baseUrl);
        userPage.NewUser(name,age);
        assertTrue("We  cannot find  user with this  name "+name, userPage.CheckUser(name,age));
    }



    @Parameters({"name"})
    @Test(priority = 2)
    public void TryToSaveWithEmptyNameAndAgeAfterUpdate(String name) {
      userPage.UpdateUser(name,"","");
      assertFalse("We  cannot save the  user with empty name ",  userPage.CheckUser("",""));
    }


    @Test(priority = 3)
    public void ClearListOfUsers() {
        assertTrue("Incorrect name of  user  for  deleting  ... ",  userPage.DeleteUser(""));
    }

    //REGARDING AGE - I think is  it clear that user can save AGE  not as Integer due to wrong definition
    // in User.java
    //             @Column(name = "age")
    //             private String age;

    @Parameters({"name"})
    @Test(priority = 4)
    public void AddNewUserWithIncorrectAge(String name) {
        driver.get(baseUrl);
        userPage.NewUser(name,"Any String");
        assertFalse("We  cannot save the user with incorrect age ", userPage.CheckUser(name,"Any String"));
    }

    @Parameters({"name"})
    @Test(priority = 5)
    public void ClearListOfUsers2(String name) {
        assertTrue("Incorrect name of  user  for  deleting  ... ", userPage.DeleteUser(name));
    }

}
