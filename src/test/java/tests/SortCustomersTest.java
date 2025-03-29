package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CustomerPage;

import java.util.List;

public class SortCustomersTest extends BaseTest {

    @Test
    @Epic("Banking Application")
    @Feature("Customer Management")
    @Story("Sort Customers")
    @Description("Verify that customers can be sorted by First Name.")
    public void testSortCustomersByFirstName() {
        String startUrl = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager/list";
        openUrl(startUrl);

        CustomerPage customerPage = new CustomerPage(driver);

        List<String> initialNames = customerPage.getCustomerFirstNames();

        customerPage.sortCustomersByFirstName();

        List<String> sortedNames = customerPage.getCustomerFirstNames();

        List<String> expectedSortedNames = initialNames.stream()
                .sorted()
                .toList();

        Assert.assertEquals(sortedNames, expectedSortedNames, "Customers are not sorted correctly.");
    }
}