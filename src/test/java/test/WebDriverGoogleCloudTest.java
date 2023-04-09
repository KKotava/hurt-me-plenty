package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import page.CalculatorPage;
import page.CloudHomePage;
import page.SearchResultsPage;

public class WebDriverGoogleCloudTest {
    private WebDriver driver;
    private CalculatorPage calculatorPage;
    private static final String TERMS = "Google Cloud Pricing Calculator";

    @BeforeClass(alwaysRun = true)
    public void browserSetUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*",
                "--disable-blink-features=AutomationControlled",
                "incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        makeCalculatorPage();
    }

    public void makeCalculatorPage() {
        this.calculatorPage = new CloudHomePage(driver)
                .openPage()
                .searchForTerms(TERMS)
                .clickMatchingResult()
                .chooseSection()
                .fillForms()
                .addGpu()
                .calculate();
    }

    @Test (description = "Hurt Me Plenty")
    public void isRegionRight() {
        Assert.assertTrue(calculatorPage.checkRegion(), "Region is wrong");
    }

    @Test
    public void isProvisioningModelRight() {
        Assert.assertTrue(calculatorPage.checkProvisioningModel(), "Provisioning model is wrong");
    }

    @Test
    public void isInstanceTypeRight() {
        Assert.assertTrue(calculatorPage.checkInstanceType(), "Instance type is wrong");
    }

    @Test
    public void isLocalSsdRight() {
        Assert.assertTrue(calculatorPage.checkLocalSsd(), "Local SSD is wrong");
    }

    @Test
    public void isCommitmentTermRight() {
        Assert.assertTrue(calculatorPage.checkCommitmentTerm(), "Commitment term is wrong");
    }

    @Test
    public void isTotalPriceMatchingManualTestingPrice() {
        Assert.assertTrue(calculatorPage.checkTotalPrice(), "Total price is not matching with manual testing price");
    }

    @AfterClass(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
