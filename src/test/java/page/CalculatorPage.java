package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CalculatorPage extends BasePage {

    private final static String INNER_FRAME_NAME = "myFrame";
    private final static String NUMBERS_OF_INSTANCES_XPATH = "//input[@type='number' and @name='quantity']";

    @FindBy(xpath = "//iframe[contains(@src,'https://cloud.google.com/frame/products/calculator')]")
    private WebElement outerFrame;

    @FindBy(xpath = "//div[@title='Compute Engine']")
    private WebElement computeEngineSection;

    @FindBy(xpath = NUMBERS_OF_INSTANCES_XPATH)
    private WebElement numberOfInstancesSelector;

    @FindBy(xpath = "//md-select[contains(@ng-model,'computeServer') and contains(@aria-label,'Operating System')]")
    private WebElement softwareSelector;

    @FindBy(xpath = "//md-option[@value='free']")
    private WebElement selectFree;

    @FindBy(xpath = "//md-select[@placeholder='VM Class']")
    private WebElement provisioningModelSelector;

    @FindBy(xpath = "//md-option[@value='regular']")
    private WebElement selectRegular;

    @FindBy(xpath = "//md-select[@placeholder='Series']")
    private WebElement seriesSelector;

    @FindBy(xpath = "//md-option[@value='n1']")
    private WebElement selectN1;

    @FindBy(xpath = "//md-select[@placeholder='Instance type']")
    private WebElement machineTypeSelector;

    @FindBy(xpath = "//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8']")
    private WebElement selectStandard8;

    @FindBy(xpath = "//md-checkbox[@aria-label='Add GPUs']")
    private WebElement addGpusCheckbox;

    @FindBy(xpath = "//md-select[@placeholder='GPU type']")
    private WebElement gpuTypeSelector;

    @FindBy(xpath = "//md-option[@value='NVIDIA_TESLA_P100']")
    private WebElement selectNvidiaTeslaP100;

    @FindBy(xpath = "//md-select[@placeholder='Number of GPUs']")
    private WebElement numberOfGpusSelector;

    @FindBy(xpath = "//md-option[contains(@ng-repeat, 'supportedGpuNumbers') and @value='1']")
    private WebElement select1Gpu;

    @FindBy(xpath = "//md-select[@placeholder='Local SSD' and contains(@class, 'ng-pristine')]")
    private WebElement localSsdSelector;

    @FindBy(xpath = "//md-option[contains(@ng-repeat, 'dynamicSsd') and @value='2']")
    private WebElement select2x375Gb;

    @FindBy(xpath = "//md-select[@placeholder='Datacenter location' and @md-container-class='cpc-region-select']")
    private List<WebElement> datacenterLocationSelector;

    @FindBy(xpath = "//md-option[@value='europe-west3' and contains(@ng-repeat,'computeServer')]")
    private WebElement selectFrankfurt;

    @FindBy(xpath = "//md-select[@placeholder='Committed usage']")
    private List<WebElement> committedUsageSelector;

    @FindBy(xpath = "//md-option[@ng-value='1' and @value='1']")
    private List<WebElement> select1Year;

    @FindBy(xpath = "//form[@name='ComputeEngineForm']//button[contains(text(),'Add to Estimate')]")
    private WebElement addToEstimate;

    @FindBy(xpath = "//md-list-item[1]/div[@class='md-list-item-text ng-binding']")
    private WebElement estimatedRegion;

    @FindBy(xpath = "//md-list-item[4]/div[@class='md-list-item-text ng-binding']")
    private WebElement provisioningModel;

    @FindBy(xpath = "//md-list-item[5]/div[@class='md-list-item-text ng-binding cpc-cart-multiline flex']")
    private WebElement instanceType;

    @FindBy(xpath = "//md-list-item[8]/div[@class='md-list-item-text ng-binding cpc-cart-multiline flex']")
    private WebElement localSsd;

    @FindBy(xpath = "//md-list-item[3]/div[@class='md-list-item-text ng-binding']")
    private WebElement commitmentTerm;

    @FindBy(xpath = "//h2/b[@class='ng-binding']")
    private WebElement totalPrice;

    public CalculatorPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        driver.switchTo().frame(outerFrame.getAttribute("name"));
        driver.switchTo().frame(INNER_FRAME_NAME);
    }

    public CalculatorPage chooseSection() {
        clickThis(computeEngineSection);
        return this;
    }

    public CalculatorPage fillForms() {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions
                        .presenceOfElementLocated(By.xpath(NUMBERS_OF_INSTANCES_XPATH)));
        numberOfInstancesSelector.sendKeys("4");
        clickThis(softwareSelector);
        clickThis(selectFree);
        clickThis(provisioningModelSelector);
        clickThis(selectRegular);
        clickThis(seriesSelector);
        clickThis(selectN1);
        clickThis(machineTypeSelector);
        clickThis(selectStandard8);
        clickThis(addGpusCheckbox);
        return this;
    }

    public CalculatorPage addGpu() {
        clickThis(gpuTypeSelector);
        clickThis(selectNvidiaTeslaP100);
        clickThis(numberOfGpusSelector);
        clickThis(select1Gpu);
        clickThis(localSsdSelector);
        clickThis(select2x375Gb);
        clickThis(datacenterLocationSelector.get(0));
        clickThis(selectFrankfurt);
        clickThis(committedUsageSelector.get(0));
        clickThis(select1Year.get(1));
        return this;
    }

    public CalculatorPage calculate() {
        clickThis(addToEstimate);
        return this;
    }

    public boolean checkRegion() {
        return estimatedRegion.getText().contains("Frankfurt");
    }

    public boolean checkProvisioningModel() {
        return provisioningModel.getText().contains("Regular");
    }

    public boolean checkInstanceType() {
        return instanceType.getText().contains("n1-standard-8");
    }

    public boolean checkLocalSsd() {
        return localSsd.getText().contains("2x375 GiB");
    }

    public boolean checkCommitmentTerm() {
        return commitmentTerm.getText().contains("1 Year");
    }

    public boolean checkTotalPrice() {
        return totalPrice.getText().contains("USD 4,024.56 per 1 month");
    }


}
