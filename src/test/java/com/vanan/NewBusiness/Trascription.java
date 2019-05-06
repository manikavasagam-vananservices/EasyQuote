package com.vanan.NewBusiness;

import com.vanan.Common.*;
import com.vanan.POM.EasyQuotePage;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

/**
 * Transcription service
 * Basic Price calculation (No Tat)
 * Individual and Multiscenario
 * "Notarization","Additional Acceptance Testing","Certificate","Verbatim","Mailing and Notary","US transcriber","Time code","Speaker Count", "Other Services","Need Translation","Need Captioning"
 */
public class Trascription extends TestBase implements AppData {

    private EasyQuotePage easyQuotePage;
    private FileProcessing fileProcessing;
    private String fileType;
    List<String> scenarios;
    Random rand = new Random();

    @Test(priority = 1)
    public void runTranscriptionTest() {
        
        easyQuotePage = new EasyQuotePage(driver);
        fileProcessing = new FileProcessing();
            fileType = System.getProperty("fileType");
        ScenarioGenerator scenarioGenerator = new ScenarioGenerator();
        String[] services = new String[0];
        if (fileType.equals("Audio")) {
            services = audioTranscription;
        } else if (fileType.equals("Video")) {
            services = videoTranscription;
        } else if (fileType.equals("Document")) {
            services = documentTranscription;
        } else if (fileType.equals("Script")) {
            services = scriptTranscription;
        }

        int possibility =Integer.parseInt(System.getProperty("serviceCount"));
        scenarios = scenarioGenerator.getScenarios(services, possibility);
        PriceCalculator priceCalculator = new PriceCalculator();
        fileProcessing.setExcelFile(transcription, service1);
        try {
            for (int j=0; j<scenarios.size();j++) {
                System.out.println("======="+scenarios.get(j)+"=========");
                for (int i = 1; i <= fileProcessing.getRowUsed(); i++) {
                    System.out.println("Source : " + fileProcessing.getCellData(i, 0));
                    System.out.println("Tier : " + fileProcessing.getCellData(i, 1).toUpperCase());
                    System.out.println("Purpose : " + fileProcessing.getCellData(i, 2));
                    System.out.println("Content : " + fileProcessing.getCellData(i, 3));
                    System.out.println("Unit : " + fileProcessing.getFloatCellData(i, 4));

                    enterCustomerInfo();
                    easyQuotePage.selectPurpose(fileProcessing.getCellData(i, 2));
                    easyQuotePage.selectContent(fileProcessing.getCellData(i, 3));
                    easyQuotePage.selectWebsite("vananservices.com");
                    easyQuotePage.clickCallYes();
                    waitingTime(1);
                    easyQuotePage.clickAddFiles();
                    easyQuotePage.setSingleFileDetail(fileType, service1 + i, fileProcessing.getCellData(i, 0),
                            fileProcessing.getCellData(i, 0), fileProcessing.getFloatCellData(i, 4) + "",
                            priceCalculator.getTranscriptionFee(fileProcessing.getCellData(i, 1)) + "", "", "Test", 1, (int) fileProcessing.getFloatCellData(i, 5));
                    waitingTime(5);
                    String[] singleScenario = scenarios.get(j).split(",");
                    boolean languageStatus = false;
                    for (int k = 0; k < singleScenario.length; k++) {
                        selectAdditionalServices(singleScenario[k], 1, 1, 1, 1, fileProcessing.getCellData(i, 0), fileProcessing.getCellData(i, 2));
                        if (singleScenario[k].equals("US transcriber")) {
                            languageStatus = true;
                        }
                    }
                    waitingTime(5);
                    double basePrice = roundValues(priceCalculator.getTranscriptionTotalUnit(fileProcessing.getFloatCellData(i, 4),
                            fileProcessing.getCellData(i, 1), fileProcessing.getCellData(i, 2),
                            fileProcessing.getCellData(i, 0), languageStatus));
                    double discount = roundValues(priceCalculator.getTranscriptionDiscount(
                            fileProcessing.getCellData(i, 1), fileProcessing.getFloatCellData(i, 4),
                            fileProcessing.getCellData(i, 0), fileProcessing.getCellData(i, 2),
                            languageStatus));
                    double additionalService = roundValues(priceCalculator.getAdditionalPriceForTranscription(singleScenario, fileProcessing.getFloatCellData(i, 4),
                            fileProcessing.getCellData(i, 0), getTimecodeOption(1), getSpeakerCountOption(1),
                            getMailingNotaryOption(1), fileProcessing.getCellData(i, 2), fileType));
                    double subtotal = roundValues((basePrice - discount) + additionalService);
                    double transactionFee = roundValues(subtotal * priceCalculator.getTransactionFee());
                    double orderTotal = roundValues(subtotal + transactionFee);
                    String BasePrice = checkStatus(easyQuotePage.getBasePriceValue(),
                            basePrice, "BasePrice");
                    String Discount = checkStatus(easyQuotePage.getDiscountValue(), discount, "Discount");
                    String addionalServices = checkStatus(easyQuotePage.getAdditionalServicePriceValue(),
                            additionalService, "Additional Services");
                    String subTotal = checkStatus(easyQuotePage.getSubTotalPriceValue(),
                            subtotal, "Sub Total");
                    String TransactionFee = checkStatus(easyQuotePage.getTransactionPriceValue(),
                            transactionFee, "Transaction Fee");
                    String OrderTotal = checkStatus(easyQuotePage.getOrderTotalValue(),
                            orderTotal, "Order Total");
                    String OrderValue = checkStatus(easyQuotePage.getOrderValue(),
                            orderTotal, "Order Value");
                    String overAllStatus;
                    if (BasePrice.equals("Pass") && subTotal.equals("Pass") && TransactionFee.equals("Pass")
                            && Discount.equals("Pass") && addionalServices.equals("Pass")
                            && OrderTotal.equals("Pass") && OrderValue.equals("Pass")) {
                        overAllStatus = "Pass";

                    } else {
                        overAllStatus = "Fail";
                    }
                    driver.get(APP_URL1);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            takeSnapShot(driver,rand.nextInt(50)+".png");
        }

    }

    void selectAdditionalServices(String name, int mposition, int tposition, int sposition, int oposition,
           String language, String purpose) {
        switch (name) {
            case "Notarization":
                easyQuotePage.clickNotarization();
                break;
            case "Additional Acceptance Testing":
                easyQuotePage.clickQualityCheck();
                break;
            case "Certificate":
                easyQuotePage.clickCertificate();
                break;
            case "Verbatim":
                if (language.equals("English")) {
                    easyQuotePage.clickVerbatim();
                }
                break;
            case "Mailing and Notary":
                easyQuotePage.enterMailingHardCopyDetails("AUTOMATION", "TESTING", "562 Spencer",
                        "", "", "", "01071", getMailingNotaryOption(mposition));
                break;
            case "US transcriber":
                if (language.equals("English") && purpose.equals("General")) {
                    easyQuotePage.clickUSTranscriber();
                }
                break;
            case "Time code":
                easyQuotePage.selectTimecode(getTimecodeOption(tposition));
                break;
            case "Speaker Count":
                easyQuotePage.selectSpeakerCount(getSpeakerCountOption(sposition));
                break;
            case "Other Services":
                easyQuotePage.selectOthers(getOtherOption(oposition));
                break;
            case "Need Translation":
                easyQuotePage.clickIHaveTranslation();
                break;
            case "Need Captioning":
                easyQuotePage.clickIHaveTranslation();
                break;
        }
    }
    private String getMailingNotaryOption(int position) {
        return mailingOption[position];
    }

    private String getTimecodeOption(int position) {
        return timeCodeOption[position];
    }

    private String getSpeakerCountOption(int position) {
        return speakerCountOption[position];
    }

    private String getOtherOption(int position) {
        return othersOption[position];
    }

    public void enterCustomerInfo() {

        easyQuotePage.enterCustomerInfo("automation.vananservices@gmail.com", "AUTOMATION", "TESTING", "9876543210", "India");
    }
    
    @AfterClass
    public void teardown() {
        tearDown();
    }
}
