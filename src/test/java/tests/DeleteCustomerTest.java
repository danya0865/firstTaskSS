package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CustomerPage;
import java.util.List;

public class DeleteCustomerTest extends BaseTest {

    @Test
    @Epic("Banking Application")
    @Feature("Customer Management")
    @Story("Delete Customer")
    @Description("Verify that a customer can be deleted successfully.")
    public void testDeleteCustomer() {
        String startUrl = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager/list";
        openUrl(startUrl);

        CustomerPage customerPage = new CustomerPage(driver);

        List<String> customerNames = customerPage.getCustomerFirstNames();
        Assert.assertFalse(customerNames.isEmpty(), "No customers found in the table.");

        double averageLength = customerNames.stream()
                .mapToInt(String::length)
                .average()
                .orElse(0);

        String nameToDelete = customerNames.stream()
                .min((name1, name2) -> {
                    double diff1 = Math.abs(name1.length() - averageLength);
                    double diff2 = Math.abs(name2.length() - averageLength);
                    return Double.compare(diff1, diff2);
                })
                .orElseThrow(() -> new AssertionError("Failed to find a customer to delete."));

        System.out.println("Deleting customer: " + nameToDelete);

        customerPage.deleteCustomerByName(nameToDelete);

        List<String> updatedCustomerNames = customerPage.getCustomerFirstNames();
        Assert.assertFalse(updatedCustomerNames.contains(nameToDelete), "Customer was not deleted.");
    }
}