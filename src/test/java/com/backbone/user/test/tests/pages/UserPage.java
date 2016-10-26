package com.backbone.user.test.tests.pages;

import com.backbone.user.test.tests.util.WebDriverHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;


/**
 * PageObject for app
 */
public class UserPage extends Page {


    @FindBy(xpath = ".//button[@class='new']")
//    @CacheLookup
    public WebElement newUserButton;

    @FindBy(xpath = ".//button[@class='save']")
    public WebElement saveButton;

    @FindBy(xpath = ".//button[@class='delete']")
    public WebElement deleteButton;

    @FindBy(xpath = ".//input[@id='userId']")
    public WebElement idInput;

    @FindBy(xpath = ".//input[@id='name']")
    public WebElement nameInput;

    @FindBy(xpath = ".//input[@id='age']")
    public WebElement ageInput;

    @FindBys({@FindBy(xpath=".//ul[@id='userList']/li")})
    public List<WebElement> itemInUserList;



    public boolean NewUser(String username, String age) {
        this.newUserButton.click();
        this.nameInput.clear();
        this.nameInput.sendKeys(username);
        this.ageInput.clear();
        this.ageInput.sendKeys(age);
        this.saveButton.click();
        if (WebDriverHelper.isAlertPresent(driver)) {
            return false;
        } else  return true;
    }

    public boolean CheckUser(String name, String age) {
        for(WebElement item : this.itemInUserList) {
            if (item.getText().equals(name)) {
                item.click();
                System.out.println("We found it (for  check)!ID: " + this.idInput.getAttribute("value") );
                if (this.ageInput.getAttribute("value").equals(age)){
                    return true;
                }
            }
        }
     return false;
    }


    public  boolean DeleteUser(String nameForDelete) {
        for(WebElement item : this.itemInUserList) {

            if (item.getText().equals(nameForDelete)) {
                item.click();
                System.out.println("We found it (for delete) ! ID: " + this.idInput.getAttribute("value"));
                this.nameInput.sendKeys("");
                this.deleteButton.click();
                if (WebDriverHelper.isAlertPresent(driver)) {
                    driver.switchTo().alert();
                    driver.switchTo().alert().accept();
                    driver.switchTo().defaultContent();
                }
                return  true;
            }

        }
        return false;
    }


    public  void UpdateUser(String orig_name, String nameToUpdate, String ageToUpdate) {

        for(WebElement item : this.itemInUserList) {

            if (item.getText().equals(orig_name)) {
                item.click();
                System.out.println("We found it (for  update)! ID: " + this.idInput.getAttribute("value") );
                this.nameInput.clear();
                this.nameInput.sendKeys(nameToUpdate);
                this.ageInput.clear();
                this.ageInput.sendKeys(ageToUpdate);
                this.saveButton.click();

            }

        }

    }






    public UserPage(WebDriver driver) {
        super(driver);
    }
}
