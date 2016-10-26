package com.backbone.user.test.tests;

import com.backbone.user.test.tests.pages.UserPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;


/**
 * Created by dbolgarov on 10/26/2016.
 */
public class Sanity extends TestNgTestBase {

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
//        assertThat("We  cannot find  user with this  name "+name,  true, is(userPage.CheckUser(name,age)));
    }

    @Test(priority = 2) // Just for check  that  we update/remove  correct  item in list
    public void AddTempNewUser() {
        driver.get(baseUrl);
        userPage.NewUser("temp","77");
        assertTrue("We  cannot find  user with this  name temp", userPage.CheckUser("temp","77"));
    }

    @Parameters({"name", "nameForDelete", "ageForUpdateUser"})
    @Test(priority = 3)
    public void UpdateUser(String name, String nameForDelete, String ageForUpdateUser) {
      userPage.UpdateUser(name,nameForDelete,ageForUpdateUser);
      assertTrue("We  cannot find  user with this  name "+name,  userPage.CheckUser(nameForDelete,ageForUpdateUser));

    }

    @Parameters({"nameForDelete"})
    @Test(priority = 4)
    public void DeleteUser(String nameForDelete) {
        assertTrue("Incorrect name of  user  for  deleting  ... ",  userPage.DeleteUser(nameForDelete));
    }

    @Test(priority = 5)
    public void DeleteTempUser() {
        assertTrue("Incorrect name of  user  for  deleting  ... ", userPage.DeleteUser("temp"));
    }
}
