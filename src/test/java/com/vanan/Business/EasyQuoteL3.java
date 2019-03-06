package com.vanan.Business;

import com.vanan.Common.FileProcessing;
import com.vanan.Common.TestBase;
import com.vanan.POM.EasyQuotePage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Basic Price with Quality Check
 */
public class EasyQuoteL3 extends TestBase {

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
        fileProcessing.setExcelFile(level3, service1);
        for (int i = 1; i <= fileProcessing.getRowUsed(); i++) {
            System.out.println("Source : " + fileProcessing.getCellData(i, 0));
            System.out.println("Purpose : " + fileProcessing.getCellData(i, 1));
            System.out.println("Content : " + fileProcessing.getCellData(i, 2));
            System.out.println("Pages : " + (int) fileProcessing.getNumericCellData(i, 3));
            System.out.println("Unit Cost : " + (int) fileProcessing.getNumericCellData(i, 4));
            driver.get(APP_URL1);
            enterCustomerInfo();
            easyQuotePage.selectPurpose(fileProcessing.getCellData(i, 1));
            easyQuotePage.selectContent(fileProcessing.getCellData(i, 2));
            easyQuotePage.selectWebsite("vananservices.com");
            easyQuotePage.clickCallYes();
            easyQuotePage.clickAddFiles();
            easyQuotePage.setSingleFileDetail("", service1 + i, fileProcessing.getCellData(i, 0),
                    fileProcessing.getCellData(i, 0), (int) fileProcessing.getNumericCellData(i, 3) + "",
                    fileProcessing.getNumericCellData(i, 4) + "", "", "Test", 1, (int) fileProcessing.getNumericCellData(i, 6));
            easyQuotePage.clickQualityCheck();
            waitingTime(5);
            double totalUnitCost = (fileProcessing.getNumericCellData(i, 3) * fileProcessing.getNumericCellData(i, 4));
            double total = totalUnitCost + ((fileProcessing.getNumericCellData(i, 5) * (int) fileProcessing.getNumericCellData(i, 3)));
            double transactionFee = total * 0.05;
            double orderTotal = total + transactionFee;
            fileProcessing.setCellData(easyQuotePage.getBasePriceValue() + "", i, 7);
            fileProcessing.setCellData(easyQuotePage.getAdditionalServicePriceValue() + "", i, 8);
            fileProcessing.setCellData(easyQuotePage.getSubTotalPriceValue() + "", i, 9);
            fileProcessing.setCellData(easyQuotePage.getTransactionPriceValue() + "", i, 10);
            fileProcessing.setCellData(easyQuotePage.getOrderTotalValue() + "", i, 11);
            fileProcessing.setCellData(easyQuotePage.getOrderValue() + "", i, 12);
            String BasePrice = checkStatus(easyQuotePage.getBasePriceValue(), totalUnitCost, "BasePrice");
            String AdditionalServicePrice = checkStatus(easyQuotePage.getAdditionalServicePriceValue(),
                    (fileProcessing.getNumericCellData(i, 5) * (int) fileProcessing.getNumericCellData(i, 3)), "Quality Check");
            String SubTotalPrice = checkStatus(easyQuotePage.getSubTotalPriceValue(), total, "SubTotalPrice");
            String TransactionPrice = checkStatus(easyQuotePage.getTransactionPriceValue(), transactionFee, "TransactionPrice");
            String OrderTotal = checkStatus(easyQuotePage.getOrderTotalValue(), orderTotal, "OrderTotal");
            String OrderValue = checkStatus(easyQuotePage.getOrderValue(), orderTotal, "OrderValue");
            fileProcessing.setCellData(BasePrice, i, 13);
            fileProcessing.setCellData(AdditionalServicePrice, i, 14);
            fileProcessing.setCellData(SubTotalPrice, i, 15);
            fileProcessing.setCellData(TransactionPrice, i, 16);
            fileProcessing.setCellData(OrderTotal, i, 17);
            fileProcessing.setCellData(OrderValue, i, 18);
            String overAllStatus = "";
            if (BasePrice.equals("Pass") && SubTotalPrice.equals("Pass") && TransactionPrice.equals("Pass")
                    && OrderTotal.equals("Pass") && OrderValue.equals("Pass") && AdditionalServicePrice.equals("Pass")) {

                overAllStatus = "Pass";

            } else {
                overAllStatus = "Fail";
            }
            fileProcessing.setCellData(overAllStatus, i, 19);
        }
        fileProcessing.writeFileContent(level3);
    }

    @Test(priority = 1)
    public void runTranslationTest() {
        fileProcessing.setExcelFile(level3, service2);
        for (int i = 1; i <= fileProcessing.getRowUsed(); i++) {
            System.out.println("Source : " + fileProcessing.getCellData(i, 0));
            System.out.println("Target : " + fileProcessing.getCellData(i, 1));
            System.out.println("Purpose : " + fileProcessing.getCellData(i, 2));
            System.out.println("Content : " + fileProcessing.getCellData(i, 3));
            System.out.println("Pages : " + (int) fileProcessing.getNumericCellData(i, 4));
            System.out.println("Unit Cost : " + (int) fileProcessing.getNumericCellData(i, 5));
            driver.get(APP_URL1);
            enterCustomerInfo();
            easyQuotePage.clickTranslation();
            easyQuotePage.selectPurpose(fileProcessing.getCellData(i, 2));
            easyQuotePage.selectContent(fileProcessing.getCellData(i, 3));
            easyQuotePage.selectWebsite("vananservices.com");
            easyQuotePage.clickCallYes();
            easyQuotePage.clickAddFiles();
            easyQuotePage.setSingleFileDetail("Document", service2 + i, fileProcessing.getCellData(i, 0),
                    fileProcessing.getCellData(i, 1), (int) fileProcessing.getNumericCellData(i, 4) + "",
                    fileProcessing.getNumericCellData(i, 5) + "", "", "Test", 1, (int) fileProcessing.getNumericCellData(i, 7));
            easyQuotePage.clickQualityCheck();
            waitingTime(5);
            double totalUnitCost = (fileProcessing.getNumericCellData(i, 4) * fileProcessing.getNumericCellData(i, 5));
            double total = totalUnitCost + ((fileProcessing.getNumericCellData(i, 6) * (int) fileProcessing.getNumericCellData(i, 4)));
            double transactionFee = total * 0.05;
            double orderTotal = total + transactionFee;
            fileProcessing.setCellData(easyQuotePage.getBasePriceValue() + "", i, 8);
            fileProcessing.setCellData(easyQuotePage.getAdditionalServicePriceValue() + "", i, 9);
            fileProcessing.setCellData(easyQuotePage.getSubTotalPriceValue() + "", i, 10);
            fileProcessing.setCellData(easyQuotePage.getTransactionPriceValue() + "", i, 11);
            fileProcessing.setCellData(easyQuotePage.getOrderTotalValue() + "", i, 12);
            fileProcessing.setCellData(easyQuotePage.getOrderValue() + "", i, 13);
            String BasePrice = checkStatus(easyQuotePage.getBasePriceValue(), totalUnitCost, "BasePrice");
            String AdditionalServicePrice = checkStatus(easyQuotePage.getAdditionalServicePriceValue(),
                    (fileProcessing.getNumericCellData(i, 6) * (int) fileProcessing.getNumericCellData(i, 4)), "Quality Check");
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
                    && OrderTotal.equals("Pass") && OrderValue.equals("Pass") && AdditionalServicePrice.equals("Pass")) {

                overAllStatus = "Pass";

            } else {
                overAllStatus = "Fail";
            }
            fileProcessing.setCellData(overAllStatus, i, 20);
        }
        fileProcessing.writeFileContent(level3);
    }

    @AfterTest
    public void teardown() {
        tearDown();
    }

    public void enterCustomerInfo() {

        easyQuotePage.enterCustomerInfo("automation.vananservices@gmail.com", "AUTOMATION", "TESTING", "9876543210", "India");
    }

}
