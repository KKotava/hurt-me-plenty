package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchResultsPage extends BasePage {
    private final String terms;

    public SearchResultsPage(WebDriver driver, String terms) {
        super(driver);
        this.terms = terms;
        PageFactory.initElements(driver, this);
    }

    public CalculatorPage clickMatchingResult() {
        String defaultLocator = "//a/b[contains(text(), '%s')]";
        String locatorForSearch = String.format(defaultLocator, terms);
        new WebDriverWait(driver, Duration.ofSeconds(60))
                .until(ExpectedConditions
                        .presenceOfElementLocated(By.xpath(locatorForSearch)));
        WebElement matchingResult = driver.findElement(By.xpath(locatorForSearch));
        matchingResult.click();
        return new CalculatorPage(driver);
    }

}
