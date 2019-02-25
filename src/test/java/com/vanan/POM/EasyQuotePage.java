package com.vanan.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EasyQuotePage extends AccessingElement implements EasyQuoteElements {

    private WebDriver driver;
    private JavascriptExecutor js;

    @FindBy(id = emailId)
    private WebElement emailIdElement;
    @FindBy(id = firstName)
    private WebElement firstNameElement;
    @FindBy(id = lastName)
    private WebElement lastNameElement;
    @FindBy(id = phoneNumber)
    private WebElement phoneNumberElement;
    @FindBy(id = country)
    private WebElement countryElement;
    @FindBy(xpath = chat)
    private WebElement chatElement;
    @FindBy(xpath = call)
    private WebElement callElement;

    @FindBy(id = purpose)
    private WebElement purposeElement;
    @FindBy(id = content)
    private WebElement contentElement;
    @FindBy(name = website)
    private WebElement websiteElement;
    @FindBy(xpath = individual)
    private WebElement individualElement;
    @FindBy(xpath = company)
    private WebElement companyElement;
    @FindBy(xpath = callYes)
    private WebElement callYesElement;
    @FindBy(xpath = callNo)
    private WebElement callNoElement;
    @FindBy(xpath = estimate)
    private WebElement estimateElement;
    @FindBy(xpath = uploadFiles)
    private WebElement uploadFilesElement;
    @FindBy(xpath = addFiles)
    private WebElement addFilesElement;
    @FindBy(xpath = uploadNewFiles)
    private WebElement uploadNewFilesElement;

    @FindBy(xpath = fileTableRow)
    private List<WebElement> fileTableRowElements;

    @FindBy(name = fileSpecifications)
    private WebElement fileSpecificationsElement;
    @FindBy(xpath = notarization)
    private WebElement notarizationElement;
    @FindBy(xpath = mailing)
    private WebElement mailingElement;
    @FindBy(xpath = speakerCount)
    private WebElement speakerCountElement;
    @FindBy(xpath = verbatim)
    private WebElement verbatimElement;
    @FindBy(xpath = timecode)
    private WebElement timecodeElement;
    @FindBy(xpath = nativeEmt)
    private WebElement nativeEmtA6Element;
    @FindBy(xpath = qualityCheck)
    private WebElement qualityCheckElement;
    @FindBy(xpath = otherServices)
    private WebElement otherServicesElement;
    @FindBy(xpath = certificate)
    private WebElement certificateElement;
    @FindBy(name = transcriptionYes)
    private WebElement transcriptionYesElement;
    @FindBy(xpath = transcriptionNo)
    private WebElement transcriptionNoElement;
    @FindBy(xpath = standalone)
    private WebElement standaloneElement;
    @FindBy(xpath = embeded)
    private WebElement embededElement;
    @FindBy(xpath = standaloneFileFormat)
    private WebElement standaloneFileFormatElement;
    @FindBy(xpath = audience)
    private WebElement audienceElement;
    @FindBy(xpath = needTranslation)
    private WebElement needTranslationElement;
    @FindBy(xpath = needScript)
    private WebElement needScriptElement;
    @FindBy(xpath = premiumCustomer)
    private WebElement premiumCustomerElement;
    @FindBy(xpath = rmCallYes)
    private WebElement rmCallYesElement;
    @FindBy(xpath = rmCallNo)
    private WebElement rmCallNoElement;
    @FindBy(xpath = keyword)
    private WebElement keywordElement;
    @FindBy(xpath = vocYes)
    private WebElement vocYesElement;
    @FindBy(name = vocNo)
    private WebElement vocNoElement;
    @FindBy(xpath = followupDate)
    private WebElement followupDateElement;
    @FindBy(xpath = followupMedium)
    private WebElement followupMediumElement;
    @FindBy(xpath = discountId)
    private WebElement discountIdElement;
    @FindBy(xpath = discountPercentage)
    private WebElement discountPercentageElement;
    @FindBy(xpath = specialRequest)
    private WebElement specialRequestElement;
    @FindBy(xpath = orderValue)
    private WebElement orderValueElement;
    @FindBy(xpath = basePrice)
    private WebElement basePriceElement;
    @FindBy(xpath = transcriptionTranslationPrice)
    private WebElement transcriptionTranslationPriceElement;
    @FindBy(xpath = discountPriceDisplay)
    private WebElement discountPriceDisplayElement;
    @FindBy(name = discountPercent)
    private WebElement discountPercentElement;
    @FindBy(xpath = subTotalPrice)
    private WebElement subTotalPriceElement;
    @FindBy(xpath = transactionFeePrice)
    private WebElement transactionFeePriceElement;
    @FindBy(xpath = orderTotal)
    private WebElement orderTotalElement;
    @FindBy(xpath = saveQuote)
    private WebElement saveQuoteElement;
    @FindBy(xpath = sendQuote)
    private WebElement sendQuoteElement;

    public EasyQuotePage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
    }

    public void enterEmailId(String email) {

        enterTestBoxValuesWithClear(emailIdElement, email);
    }

    public void enterFirstName(String firstName) {

        enterTestBoxValuesWithClear(firstNameElement, firstName);
    }

    public void enterLastName(String lastName) {

        enterTestBoxValuesWithClear(lastNameElement, lastName);
    }

    public void enterPhoneNo(String phoneNo) {

        enterTestBoxValuesWithClear(phoneNumberElement, phoneNo);
    }

    public void selectCountry(String country) {

        selectDropDownByVText(countryElement, country);
    }

    public void clickChat() {

        clickElement(chatElement);
    }

    public void clickCall() {

        clickElement(callElement);
    }

    public void clickTranslation() {

       clickJSElement(js, serviceTranslation);
    }

    public void clickTranscription() {

        clickJSElement(js, serviceTranscription);
    }

    public void clickCaptioning() {

        clickJSElement(js, serviceCaptioning);
    }

    public void clickTyping() {

        clickJSElement(js, serviceTyping);
    }

    public void clickOther() {

        clickJSElement(js, serviceOther);
    }

    public void clickIndividual() {

        clickElement(individualElement);
    }

    public void clickCompany() {

        clickElement(companyElement);
    }

    public void clickCallYes() {

        clickElement(callYesElement);
    }

    public void clickCallNo() {

        clickElement(callNoElement);
    }

    public void selectPurpose(String purpose) {

        selectDropDownByVText(purposeElement, purpose);
    }

    public void selectContent(String content) {

        selectDropDownByVText(contentElement, content);
    }

    public void selectWebsite(String website) {

        selectDropDownByVText(websiteElement, website);
    }

    public void clickEstimate() {

        clickElement(estimateElement);
    }

    public void clickAddFiles() {

        clickElement(addFilesElement);
    }

    public void clickUploadFiles() {

        clickElement(uploadFilesElement);
    }

    public void clickUploadNewFiles() {

        clickElement(uploadNewFilesElement);
    }

    public void enterSpecificationsForAllFiles(String message) {

        enterTestBoxValuesWithClear(fileSpecificationsElement, message);
    }

    public boolean setMultipleFileDetails(String fileTypes, String fileNames,
                                          String sourceLanguages, String targetLanguages, String pageMinutess,
                                          String costs, String totals, String fileCommnetss, int fileLocations) {
        int rowCount = fileTableRowElements.size();
        boolean status = false;
        for (int i = 1; i <= rowCount; i++) {
            if (i == fileLocations) {
                setSingleFileDetail(fileTypes, fileNames,
                        sourceLanguages, targetLanguages, pageMinutess,
                        costs, totals, fileCommnetss, i);
                break;
            }
        }
        return status;
    }

    public void setSingleFileDetail(String fileTypes, String fileNames,
                                    String sourceLanguages, String targetLanguages, String pageMinutess,
                                    String costs, String totals, String fileCommnetss, int i) {
        if (!fileTypes.equals("")) {
            selectDropDownByVText(driver.findElement(By.xpath(fileTableRow + "[" + i + "]" + fileType)), fileTypes);
            if (isAlertPresent(driver)) {
                cancelAlert(driver);
            }
        }
        enterTestBoxValuesWithClear(driver.findElement(By.xpath(fileTableRow + "[" + i + "]" + fileName)), fileNames);
        if (!sourceLanguages.equals("")) {
            selectDropDownByVText(driver.findElement(By.xpath(fileTableRow + "[" + i + "]" + sourceLanguage)), sourceLanguages);
        }
        if (!targetLanguages.equals("")) {
            selectDropDownByVText(driver.findElement(By.xpath(fileTableRow + "[" + i + "]" + targetLanguage)), targetLanguages);
            if (isAlertPresent(driver)) {
                cancelAlert(driver);
            }
        }
        enterTestBoxValuesWithClear(driver.findElement(By.xpath(fileTableRow + "[" + i + "]" + pagesMinutes)), pageMinutess);
        if (!costs.equals("")) {
            enterTestBoxValuesWithClear(driver.findElement(By.xpath(fileTableRow + "[" + i + "]" + costPerPageMinute)), costs);
        }
        if (!totals.equals("")) {
            enterTestBoxValuesWithClear(driver.findElement(By.xpath(fileTableRow + "[" + i + "]" + totalCost)), totals);
        }
        enterTestBoxValuesWithClear(driver.findElement(By.xpath(fileTableRow + "[" + i + "]" + comments)), fileCommnetss);
    }

    public void selectPremiumCustomer(String message) {

        selectDropDownByVText(premiumCustomerElement, message);
    }

    public void clickRMCallYes() {

        clickElement(rmCallYesElement);
    }

    public void clickRMCallNo() {

        clickElement(rmCallNoElement);
    }

    public void enterKeyword(String message) {

        enterTestBoxValuesWithClear(keywordElement, message);
    }

    public void clickVocYes() {

        clickElement(vocYesElement);
    }

    public void clickVocNo() {

        clickElement(vocNoElement);
    }

    public void enterFollowUpDate(String dates) {

        enterTestBoxValuesWithClear(followupDateElement, dates);
    }

    public void selectFollowUpMedium(String message) {

        selectDropDownByVText(followupMediumElement, message);
    }

    public void enterChatId(String id) {

        enterTestBoxValuesWithClear(chatElement, id);
    }

    public void selectDiscount(String message) {

        selectDropDownByVText(discountIdElement, message);
    }

    public void enterDiscountPercentage(String discount) {

        enterTestBoxValuesWithClear(discountPercentageElement, discount);
    }

    public void enterSpecialRequest(String message) {

        enterTestBoxValuesWithClear(specialRequestElement, message);
    }

    public double getOrderValue() {

        return convertAndGetValue(orderValueElement);
    }

    public double getBasePriceValue() {

        return convertAndGetValue(basePriceElement);
    }

    public double getTranslationTranscriptionPriceValue() {

        return convertAndGetValue(transcriptionTranslationPriceElement);
    }

    public double getDiscountValue() {

        return convertAndGetValue(discountPriceDisplayElement);
    }

    public double getSubTotalPriceValue() {

        return convertAndGetValue(subTotalPriceElement);
    }

    public double getTransactionPriceValue() {

        return convertAndGetValue(transactionFeePriceElement);
    }

    public double getOrderTotalValue() {

        return convertAndGetValue(orderTotalElement);
    }

    public void clickSaveQuote() {

        clickElement(saveQuoteElement);
    }

    public void clickSentQuote() {

        clickElement(sendQuoteElement);
    }

    public double convertAndGetValue(WebElement element) {

        if (element.isDisplayed()) {
            return Double.parseDouble(getElementValues(element));
        } else {
            return 0;
        }
    }
}
