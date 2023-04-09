package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CalculatorPage extends BasePage {

    private final static String INNER_FRAME_NAME = "myFrame";

    @FindBy(xpath = "//iframe[contains(@src,'https://cloud.google.com/frame/products/calculator')]")
    private WebElement outerFrame;

    @FindBy(xpath = "//div[@title='Compute Engine']")
    private WebElement computeEngineSection;

    @FindBy(id = "input_95")
    private WebElement numberOfInstancesSelector;

    @FindBy(id = "select_108")
    private WebElement softwareSelector;

    @FindBy(xpath = "//md-option[@value='free']")
    private WebElement selectFree;

    @FindBy(id = "select_value_label_88")
    private WebElement provisioningModelSelector;

    @FindBy(xpath = "//md-option[@value='regular']")
    private WebElement selectRegular;

    @FindBy(id = "select_value_label_90")
    private WebElement seriesSelector;

    @FindBy(xpath = "//md-option[@value='n1']")
    private WebElement selectN1;

    @FindBy(id = "select_value_label_91")
    private WebElement machineTypeSelector;

    @FindBy(xpath = "//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8']")
    private WebElement selectStandard8;

    @FindBy(xpath = "//md-checkbox[@aria-label='Add GPUs']")
    private WebElement addGpusCheckbox;

    @FindBy(id = "select_475")
    private WebElement gpuTypeSelector;

    @FindBy(xpath = "//md-option[@value='NVIDIA_TESLA_P100']")
    private WebElement selectNvidiaTeslaP100;

    @FindBy(id = "select_value_label_474")
    private WebElement numberOfGpusSelector;

    @FindBy(xpath = "//md-option[@id='select_option_485' and @value='1']")
    private WebElement select1Gpu;

    @FindBy(id = "select_value_label_433")
    private WebElement localSsdSelector;

    @FindBy(xpath = "//md-option[@id='select_option_460' and @value='2']")
    private WebElement select2x375Gb;

    @FindBy(id = "select_value_label_93")
    private WebElement datacenterLocationSelector;

    @FindBy(xpath = "//md-option[@id='select_option_247' and @value='europe-west3']")
    private WebElement selectFrankfurt;

    @FindBy(id = "select_value_label_94")
    private WebElement committedUsageSelector;

    @FindBy(xpath = "//md-option[@id='select_option_133' and @value='1']")
    private WebElement select1Year;

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
                        .presenceOfElementLocated(By.id("input_95")));
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
        clickThis(datacenterLocationSelector);
        clickThis(selectFrankfurt);
        clickThis(committedUsageSelector);
        clickThis(select1Year);
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
