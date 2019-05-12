package com.vanan.NewBusiness;

import com.vanan.Common.*;
import com.vanan.POM.EasyQuotePage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

/**
 * Translation service
 * Basic Price calculation (No Tat)
 * Individual and Multiscenario
 * "Notarization","Additional Acceptance Testing","Certificate","Mailing and Notary","Time code","Speaker Count", "Other Services","Need Transcription","Need Captioning", "Hand Written"
 */
public class Translation extends TestBase implements AppData {

    List<String> scenarios;
    Random rand = new Random();
    private EasyQuotePage easyQuotePage;
    private FileProcessing fileProcessing;
    private String fileType;
    private PriceCalculator priceCalculator;
    private FileProcessing fileProcess;

    @BeforeTest
    public void LoginCRM() {
        Login login = new Login();
        login.configureBrowser();
    }

    @Test(priority = 1)
    public void runTranslationTest() {


        fileType = System.getProperty("fileType");
        int possibility = Integer.parseInt(System.getProperty("serviceCount"));
        int start = Integer.parseInt(System.getProperty("start"));
        int end = Integer.parseInt(System.getProperty("end"));
        boolean multiScenarios = System.getProperty("multi").contains("yes");
        String serviceNames = System.getProperty("serviceName");

        easyQuotePage = new EasyQuotePage(driver);
        fileProcessing = new FileProcessing();
        ScenarioGenerator scenarioGenerator = new ScenarioGenerator();
        priceCalculator = new PriceCalculator();
        fileProcessing.setExcelFile(translation, service2);

        String[] services = new String[0];
        if (fileType.equals("Audio")) {
            services = audioTranslation;
        } else if (fileType.equals("Video")) {
            services = videoTranslation;
        } else if (fileType.equals("Document")) {
            services = documentTranslation;
        } else if (fileType.equals("Script")) {
            services = scriptTranslation;
        }

        scenarios = scenarioGenerator.getScenarios(services, possibility);

        System.out.println("Total services : " + possibility + " = Total scenarios : " + scenarios.size());
        if (multiScenarios) {
            for (int j = start; j <= end; j++) {
                String sheetName = scenarios.get(j).replace(",", "-");
                performScenario(sheetName, j, multiScenarios, "");
            }
        } else {
            performScenario(serviceNames, 0, multiScenarios, serviceNames);
        }

    }

    private void performScenario(String sheetName, int j, boolean multiScenario, String serviceName) {

        fileProcess = new FileProcessing();
        fileProcess.createExcelSheet(translation, sheetName + j);
        fileProcess.setCellHeaderData(translationFileHeading);
        if (multiScenario) {
            System.out.println("=======" + scenarios.get(j) + "=========");
        } else {
            System.out.println("=======" + serviceName + "=========");
        }
        double unit = 0;
        String BasePrice = null;
        String Discount = null;
        String addionalServices = null;
        String subTotal = null;
        String TransactionFee = null;
        String OrderTotal = null;
        String OrderValue = null;
        String overAllStatus = null;
        for (int i = 1; i <= fileProcessing.getRowUsed(); i++) {
            try {
                System.out.println("Source : " + fileProcessing.getCellData(i, 0));
                System.out.println("Target : " + fileProcessing.getCellData(i, 1));
                System.out.println("Tier : " + fileProcessing.getCellData(i, 2).toUpperCase());
                System.out.println("Purpose : " + fileProcessing.getCellData(i, 3));
                System.out.println("Content : " + fileProcessing.getCellData(i, 4));
                System.out.println("Length : " + fileProcessing.getFloatCellData(i, 5));
                System.out.println("Pages : " + fileProcessing.getFloatCellData(i, 6));
                enterCustomerInfo();
                easyQuotePage.clickTranslation();
                easyQuotePage.selectPurpose(fileProcessing.getCellData(i, 3));
                easyQuotePage.selectContent(fileProcessing.getCellData(i, 4));
                easyQuotePage.selectWebsite("vananservices.com");
                easyQuotePage.clickCallYes();
                waitingTime(1);
                easyQuotePage.clickAddFiles();

                if (fileType.equals("Audio") || fileType.equals("Video")) {
                    easyQuotePage.setSingleFileDetail(fileType, service2 + i, fileProcessing.getCellData(i, 0),
                            fileProcessing.getCellData(i, 1), fileProcessing.getFloatCellData(i, 5) + "",
                            priceCalculator.getTranslationFee(fileProcessing.getCellData(i, 2), fileType) + "",
                            "", "Test", 1, (int) fileProcessing.getFloatCellData(i, 7));
                    unit = fileProcessing.getFloatCellData(i, 5);
                } else if (fileType.equals("Document")) {
                    easyQuotePage.setSingleFileDetail(fileType, service2 + i, fileProcessing.getCellData(i, 0),
                            fileProcessing.getCellData(i, 1), fileProcessing.getFloatCellData(i, 6) + "",
                            priceCalculator.getTranslationFee(fileProcessing.getCellData(i, 2), fileType) + "",
                            "", "Test", 1, 0);
                    unit = fileProcessing.getFloatCellData(i, 6);
                }

                waitingTime(5);

                String[] singleScenario = new String[0];
                if (multiScenario) {
                    singleScenario = scenarios.get(j).split(",");

                    for (int k = 0; k < singleScenario.length; k++) {
                        selectAdditionalServices(singleScenario[k], 1, 1, 1, 1, fileProcessing.getCellData(i, 1), fileProcessing.getCellData(i, 2));
                    }
                } else {
                    selectAdditionalServices(serviceName, 1, 1, 1, 1, fileProcessing.getCellData(i, 1), fileProcessing.getCellData(i, 2));
                }
                waitingTime(5);
                double basePrice = roundValues(priceCalculator.getTranslationTotalUnit(unit,
                        fileProcessing.getCellData(i, 2), fileType));
                double discount = roundValues(priceCalculator.getTranslationDiscount(
                        fileProcessing.getCellData(i, 2), unit, fileType));
                double additionalService = 0;
                if (multiScenario) {
                    additionalService = roundValues(priceCalculator.getAdditionalPriceForTranslation(singleScenario, unit,
                            fileProcessing.getCellData(i, 1), getTimecodeOption(1), getSpeakerCountOption(1),
                            getMailingNotaryOption(1), fileType));
                } else {
                    additionalService = roundValues(priceCalculator.getSingleAdditionalPriceForTranslation(serviceName, unit,
                            fileProcessing.getCellData(i, 1), getTimecodeOption(1), getSpeakerCountOption(1),
                            getMailingNotaryOption(1), fileType));
                }

                double subtotal = roundValues((basePrice - discount) + additionalService);
                double transactionFee = roundValues(subtotal * priceCalculator.getTransactionFee());
                double orderTotal = roundValues(subtotal + transactionFee);
                BasePrice = checkStatus(easyQuotePage.getBasePriceValue(),
                        basePrice, "BasePrice");
                Discount = checkStatus(easyQuotePage.getDiscountValue(), discount, "Discount");
                addionalServices = checkStatus(easyQuotePage.getAdditionalServicePriceValue(),
                        additionalService, "Additional Services");
                subTotal = checkStatus(easyQuotePage.getSubTotalPriceValue(),
                        subtotal, "Sub Total");
                TransactionFee = checkStatus(easyQuotePage.getTransactionPriceValue(),
                        transactionFee, "Transaction Fee");
                OrderTotal = checkStatus(easyQuotePage.getOrderTotalValue(),
                        orderTotal, "Order Total");
                OrderValue = checkStatus(easyQuotePage.getOrderValue(),
                        orderTotal, "Order Value");

                if (BasePrice.equals("Pass") && subTotal.equals("Pass") && TransactionFee.equals("Pass")
                        && Discount.equals("Pass") && addionalServices.equals("Pass")
                        && OrderTotal.equals("Pass") && OrderValue.equals("Pass")) {
                    overAllStatus = "Pass";

                } else {
                    overAllStatus = "Fail";
                }
                String[] datas = {fileProcessing.getCellData(i, 0), fileProcessing.getCellData(i, 1),
                            fileProcessing.getCellData(i, 1).toUpperCase(), fileProcessing.getCellData(i, 2),
                            fileProcessing.getCellData(i, 3), unit + "", BasePrice, Discount, addionalServices,
                            subTotal, TransactionFee, OrderTotal, OrderValue, overAllStatus};

                setData(fileProcess, datas, i);
            } catch (Exception ex) {
                ex.printStackTrace();
                takeSnapShot(driver, rand.nextInt(50) + ".png");
                String[] datas = {fileProcessing.getCellData(i, 0), fileProcessing.getCellData(i, 1),
                        fileProcessing.getCellData(i, 1).toUpperCase(), fileProcessing.getCellData(i, 2),
                        fileProcessing.getCellData(i, 3), unit + "", BasePrice, Discount, addionalServices,
                        subTotal, TransactionFee, OrderTotal, OrderValue, overAllStatus};
                setData(fileProcess, datas, i);
            }
           driver.get(APP_URL1);
        }
        fileProcess.writeFileContent(translation);
        System.out.println("=======Completed" + (j + 1) + "=========");
    }

    private void setData(FileProcessing fileProcessing, String[] datas, int row) {
        fileProcessing.setCellData(row, datas);
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
                easyQuotePage.enterCertificateDetails("Testing");
                break;

            case "Mailing and Notary":
                easyQuotePage.enterMailingHardCopyDetails("AUTOMATION", "TESTING", "562 Spencer",
                        "", "", "", "01071", getMailingNotaryOption(mposition));
                break;

            case "Time code":
                easyQuotePage.selectTimecode(getTimecodeOption(tposition));
                break;

            case "Speaker Count":
                easyQuotePage.selectSpeakerCount(getSpeakerCountOption(sposition));
                break;

            case "Other Services":
                easyQuotePage.enterOthers(getOtherOption(oposition));
                break;

            case "Need Transcription":
                easyQuotePage.clickIHaveTranscript();
                break;

            case "Need Captioning":
                easyQuotePage.clickIHaveCaptioning();
                break;

            case "Hand Written":
                easyQuotePage.clickNeedHandWritten();
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
