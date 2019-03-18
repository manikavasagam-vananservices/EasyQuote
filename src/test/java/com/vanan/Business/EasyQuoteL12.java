package com.vanan.Business;

import com.vanan.Common.FileProcessing;
import com.vanan.Common.TestBase;
import com.vanan.POM.EasyQuotePage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Basic Price with Notarization + Verbatim(Transcription)
 */
public class EasyQuoteL12 extends TestBase {

    EasyQuotePage easyQuotePage;
    FileProcessing fileProcessing;

    @BeforeTest
    public void configureBrowser() {

        setDriver();
        easyQuotePage = new EasyQuotePage(driver);
        fileProcessing = new FileProcessing();

    }

    @Test(priority = 0)
    public void runTranscriptionTest() {
        fileProcessing.setExcelFile(level12, service1);

        for (int i = 1; i <= fileProcessing.getRowUsed(); i++) {
            System.out.println("Source : " + fileProcessing.getCellData(i, 0));
            System.out.println("Purpose : " + fileProcessing.getCellData(i, 1));
            System.out.println("Content : " + fileProcessing.getCellData(i, 2));
            System.out.println("Pages : " + (int) fileProcessing.getFloatCellData(i, 3));
            System.out.println("Unit Cost : " + (int) fileProcessing.getFloatCellData(i, 4));
            driver.get(APP_URL1);
            enterCustomerInfo();
            easyQuotePage.selectPurpose(fileProcessing.getCellData(i, 1));
            easyQuotePage.selectContent(fileProcessing.getCellData(i, 2));
            easyQuotePage.selectWebsite("vananservices.com");
            easyQuotePage.clickCallYes();
            easyQuotePage.clickAddFiles();
            easyQuotePage.setSingleFileDetail("", service1 + i, fileProcessing.getCellData(i, 0),
                    fileProcessing.getCellData(i, 0), (int) fileProcessing.getFloatCellData(i, 3) + "",
                    fileProcessing.getFloatCellData(i, 4) + "", "", "Test", 1, (int) fileProcessing.getFloatCellData(i, 6));
            easyQuotePage.clickNotarization();
            double total;
            double verbatim;
            String AdditionalServicePrice = "";
            double totalUnitCost = roundValues((fileProcessing.getFloatCellData(i, 3) * fileProcessing.getFloatCellData(i, 4)));
            if (fileProcessing.getCellData(i, 0).equals("English") && fileProcessing.getCellData(i, 1).equals("General")) {

                easyQuotePage.clickVerbatim();
                verbatim = roundValues((fileProcessing.getFloatCellData(i, 5) * fileProcessing.getFloatCellData(i, 7)) + fileProcessing.getFloatCellData(i, 5));
                total = roundValues(totalUnitCost + verbatim);
            } else {
                verbatim = fileProcessing.getFloatCellData(i, 5);
                total = totalUnitCost + verbatim;
            }
            waitingTime(5);

            double transactionFee = roundValues(total * 0.05);
            double orderTotal = roundValues(total + transactionFee);
            fileProcessing.setCellData(easyQuotePage.getBasePriceValue() + "", i, 8);
            fileProcessing.setCellData(easyQuotePage.getAdditionalServicePriceValue() + "", i, 9);
            fileProcessing.setCellData(easyQuotePage.getSubTotalPriceValue() + "", i, 10);
            fileProcessing.setCellData(easyQuotePage.getTransactionPriceValue() + "", i, 11);
            fileProcessing.setCellData(easyQuotePage.getOrderTotalValue() + "", i, 12);
            fileProcessing.setCellData(easyQuotePage.getOrderValue() + "", i, 13);
            String BasePrice = checkStatus(easyQuotePage.getBasePriceValue(), totalUnitCost, "BasePrice");
            if (fileProcessing.getCellData(i, 1).equals("General")) {
                AdditionalServicePrice = checkStatus(easyQuotePage.getAdditionalServicePriceValue(),
                        verbatim, "Verbatim");
            } else {
                AdditionalServicePrice = checkStatus(easyQuotePage.getAdditionalServicePriceValue(),
                        verbatim, "No Verbatim");
            }

            String SubTotalPrice = checkStatus(easyQuotePage.getSubTotalPriceValue(), total, "SubTotalPrice");
            String TransactionPrice = checkStatus(easyQuotePage.getTransactionPriceValue(), transactionFee, "TransactionPrice");
            String OrderTotal = checkStatus(easyQuotePage.getOrderTotalValue(), orderTotal, "OrderTotal");
            String OrderValue = checkStatus(easyQuotePage.getOrderValue(), orderTotal, "OrderValue");
            fileProcessing.setCellData(BasePrice, i, 14);
            fileProcessing.setCellData(AdditionalServicePrice, i, 15);
            fileProcessing.setCellData(SubTotalPrice, i, 16);
            fileProcessing.setCellData(TransactionPrice, i, 17);
            fileProcessing.setCellData(OrderTotal, i, 18);
            fileProcessing.setCellData(OrderValue, i, 19);
            String overAllStatus = "";
            if (BasePrice.equals("Pass") && SubTotalPrice.equals("Pass") && TransactionPrice.equals("Pass")
                    && OrderTotal.equals("Pass") && OrderValue.equals("Pass")) {
                overAllStatus = "Pass";

            } else {
                overAllStatus = "Fail";
            }
            fileProcessing.setCellData(overAllStatus, i, 20);
        }
        fileProcessing.writeFileContent(level12);
    }

    @AfterTest
    public void teardown() {
        tearDown();
    }

    public void enterCustomerInfo() {

        easyQuotePage.enterCustomerInfo("automation.vananservices@gmail.com", "AUTOMATION", "TESTING", "9876543210", "India");
    }
}
