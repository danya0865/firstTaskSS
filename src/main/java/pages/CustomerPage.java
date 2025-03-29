package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CustomerPage extends BasePage {

    @FindBy(xpath = "//button[contains(text(), 'Customers')]")
    private WebElement customersButton;

    @FindBy(xpath = "//a[contains(text(), 'First Name')]")
    private WebElement firstNameHeader;

    @FindBy(xpath = "//tbody/tr/td[1]")
    private List<WebElement> firstNames;

    @FindBy(xpath = "//tbody/tr/td[5]/button[text()='Delete']")
    private List<WebElement> deleteButtons;

    public CustomerPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Открываем вкладку "Customers".
     */
    public void openCustomersTab() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(customersButton));
        customersButton.click();
    }

    /**
     * Сортируем клиентов по имени.
     */
    public void sortCustomersByFirstName() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(firstNameHeader));

        firstNameHeader.click();
        firstNameHeader.click();
    }

    /**
     * Возвращает список имен клиентов из таблицы.
     */
    public List<String> getCustomerFirstNames() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(firstNames));

        return firstNames.stream()
                .map(WebElement::getText)
                .toList();
    }

    /**
     * Удаляет всех клиентов, чьи имена имеют длину, близкую к средней.
     */
    public void deleteCustomersByAverageNameLength() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        List<String> customerNames = getCustomerFirstNames();

        double averageLength = customerNames.stream()
                .mapToInt(String::length)
                .average()
                .orElse(0);

        List<String> namesToDelete = customerNames.stream()
                .filter(name -> Math.abs(name.length() - averageLength) < 1)
                .toList();

        for (String nameToDelete : namesToDelete) {
            deleteCustomerByName(nameToDelete);
        }
    }

    /**
     * Удаляет клиента по имени.
     */
    public void deleteCustomerByName(String nameToDelete) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfAllElements(firstNames));

        for (int i = 0; i < firstNames.size(); i++) {
            String currentName = firstNames.get(i).getText();
            if (currentName.equals(nameToDelete)) {
                WebElement deleteButton = deleteButtons.get(i);

                wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
                deleteButton.click();

                wait.until(ExpectedConditions.visibilityOfAllElements(firstNames));
                break;
            }
        }
    }
}