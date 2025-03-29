package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ManagerPage;
import utils.HelperUtils;

public class AddCustomerTest extends BaseTest {

    @Test
    @Epic("Banking Application")
    @Feature("Customer Management")
    @Story("Add Customer")
    @Description("Verify that a customer can be added successfully.")
    public void testAddCustomer() {
        String startUrl = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager/addCust";
        openUrl(startUrl);

        ManagerPage managerPage = new ManagerPage(driver);

        String postCode = HelperUtils.generatePostCode();
        String firstName = HelperUtils.generateFirstName(postCode);
        String lastName = "lastName";

        //managerPage.clickAddCustomer();
        managerPage.fillCustomerDetails(firstName, lastName, postCode);
        managerPage.submitCustomerForm();

        String alertText = driver.switchTo().alert().getText();
        Assert.assertTrue(alertText.contains("Customer added successfully"), "Customer was not added.");
        driver.switchTo().alert().accept();
    }
}