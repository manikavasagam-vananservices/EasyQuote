package com.vanan.NewBusiness;

import com.vanan.Common.*;
import com.vanan.POM.EasyQuotePage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

/**
 * Typing service
 * Basic Price calculation (No Tat)
 * Individual and Multiscenario
 * "Mailing and Notary", "Other Services","Formatting","Hand Written"
 */
public class Typing extends TestBase implements AppData {

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
    public void runTypingTest() {

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
        fileProcessing.setExcelFile(typing, service4);

        String[] services = new String[0];
        if (fileType.equals("Audio")) {
            services = audioTyping;
        } else if (fileType.equals("Video")) {
            services = videoTyping;
        } else if (fileType.equals("Document")) {
            services = documentTyping;
        } else if (fileType.equals("Script")) {
            services = scriptTyping;
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
        fileProcess.createExcelSheet(typing, sheetName + j);
        fileProcess.setCellHeaderData(typingFileHeading);
        if (multiScenario) {
            System.out.println("=======" + scenarios.get(j) + "=========");
        } else {
            System.out.println("=======" + serviceName + "=========");
        }

        String BasePrice = null;
        String Discount = null;
        String addionalServices = null;
        String subTotal = null;
        String TransactionFee = null;
        String OrderTotal = null;
        String OrderValue = null;
        String overAllStatus = null;
        boolean formatting = false;
        for (int i = 1; i <= fileProcessing.getRowUsed(); i++) {
            try {
                System.out.println("Source : " + fileProcessing.getCellData(i, 0));
                System.out.println("Tier : " + fileProcessing.getCellData(i, 1).toUpperCase());
                System.out.println("Purpose : " + fileProcessing.getCellData(i, 2));
                System.out.println("Content : " + fileProcessing.getCellData(i, 3));
                System.out.println("Unit : " + fileProcessing.getFloatCellData(i, 4));

                enterCustomerInfo();
                easyQuotePage.clickTyping();
                easyQuotePage.selectPurpose(fileProcessing.getCellData(i, 2));
                easyQuotePage.selectContent(fileProcessing.getCellData(i, 3));
                easyQuotePage.selectWebsite("vananservices.com");
                easyQuotePage.clickCallYes();
                waitingTime(1);
                easyQuotePage.clickAddFiles();
                String[] singleScenario = new String[0];
                String formatingStatus="";
                boolean serviceStatus = false;
                if (multiScenario) {
                    singleScenario = scenarios.get(j).split(",");
                    for (int k = 0; k < singleScenario.length; k++) {
                        if (singleScenario[k].equals("Formatting")) {
                            formatingStatus = singleScenario[k];
                            serviceStatus = true;
                            break;
                        }
                    }
                    easyQuotePage.setSingleFileDetail(fileType, service4 + i, fileProcessing.getCellData(i, 0),
                            fileProcessing.getCellData(i, 0), fileProcessing.getFloatCellData(i, 4) + "",
                            priceCalculator.getTypingFee(fileProcessing.getCellData(i, 0), fileProcessing.getCellData(i, 2), formatingStatus) + "", "", "Test", 1, (int) fileProcessing.getFloatCellData(i, 5));
                } else if (fileType.equals("Document")) {
                    if (serviceName.equals("Formatting")) {
                        formatingStatus = serviceName;
                    }

                    easyQuotePage.setSingleFileDetail(fileType, service4 + i, fileProcessing.getCellData(i, 0),
                            fileProcessing.getCellData(i, 0), fileProcessing.getFloatCellData(i, 4) + "",
                            priceCalculator.getTypingFee(fileProcessing.getCellData(i, 0), fileProcessing.getCellData(i, 2), formatingStatus) + "", "", "Test", 1, (int) fileProcessing.getFloatCellData(i, 5));
                }
                waitingTime(5);


                if (multiScenario) {


                    for (int k = 0; k < singleScenario.length; k++) {
                        selectAdditionalServices(singleScenario[k], 1, 1);
                    }
                } else {
                    selectAdditionalServices(serviceName, 1, 1);
                }
                waitingTime(5);
                double basePrice = roundValues(fileProcessing.getFloatCellData(i, 4) * priceCalculator.getTypingFee(fileProcessing.getCellData(i, 0),
                        fileProcessing.getCellData(i, 2), formatingStatus));
                double discount = 0;
                double additionalService = 0;
                if(serviceName.equals("Mailing and Notary")|| serviceStatus) {
                    additionalService = roundValues(priceCalculator.getMailingNotary(getMailingNotaryOption(1)));
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
                String[] datas = {fileProcessing.getCellData(i, 0),
                        fileProcessing.getCellData(i, 1).toUpperCase(), fileProcessing.getCellData(i, 2),
                        fileProcessing.getCellData(i, 3), fileProcessing.getFloatCellData(i, 4) + "",
                        BasePrice,
                        Discount, addionalServices, subTotal,
                        TransactionFee, OrderTotal, OrderValue,
                        overAllStatus};
                setData(fileProcess, datas, i);
            } catch (Exception ex) {
                ex.printStackTrace();
                takeSnapShot(driver, rand.nextInt(50) + ".png");
                String[] datas = {fileProcessing.getCellData(i, 0),
                        fileProcessing.getCellData(i, 1).toUpperCase(), fileProcessing.getCellData(i, 2),
                        fileProcessing.getCellData(i, 3), fileProcessing.getFloatCellData(i, 4) + "",
                        BasePrice,
                        Discount, addionalServices, subTotal,
                        TransactionFee, OrderTotal, OrderValue,
                        overAllStatus};
                setData(fileProcess, datas, i);
            }
            driver.get(APP_URL1);
        }
        fileProcess.writeFileContent(typing);
        System.out.println("=======Completed" + (j + 1) + "=========");
    }

    private void setData(FileProcessing fileProcessing, String[] datas, int row) {
        fileProcessing.setCellData(row, datas);
    }

    void selectAdditionalServices(String name, int mposition, int oposition) {
        switch (name) {

            case "Mailing and Notary":
                easyQuotePage.enterMailingHardCopyDetails("AUTOMATION", "TESTING", "562 Spencer",
                        "", "", "", "01071", getMailingNotaryOption(mposition));
                break;
            case "Hand Written":
                easyQuotePage.clickNeedHandWritten();
                break;
            case "Formatting":
                easyQuotePage.clickFormattingYes();
                break;
            case "Other Services":
                easyQuotePage.enterOthers(getOtherOption(oposition));
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

  //  @AfterClass
    public void teardown() {
        tearDown();
    }
}
