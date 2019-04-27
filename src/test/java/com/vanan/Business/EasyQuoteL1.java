package com.vanan.Business;

import com.vanan.Common.FileProcessing;
import com.vanan.POM.EasyQuotePage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import com.vanan.Common.TestBase;

/**
 * Basic Price calculation(Transcription, Translation, Captioning, Typing)
 */
public class EasyQuoteL1 extends TestBase {

    EasyQuotePage easyQuotePage;
    FileProcessing fileProcessing;

    //@BeforeTest
    public void configureBrowser() {

        setDriver();
        easyQuotePage = new EasyQuotePage(driver);
        fileProcessing = new FileProcessing();

    }

    @Test(priority = 0)
    public void runTranscriptionTest() {
        easyQuotePage = new EasyQuotePage(driver);
        fileProcessing = new FileProcessing();
        fileProcessing.setExcelFile(level1, service1);
        for (int i = 1; i <= fileProcessing.getRowUsed(); i++) {
            System.out.println("Source : " + fileProcessing.getCellData(i, 0));
            System.out.println("Purpose : " + fileProcessing.getCellData(i, 1));
            System.out.println("Content : " + fileProcessing.getCellData(i, 2));
            System.out.println("Pages : " + (int) fileProcessing.getNumericCellData(i, 3));
            System.out.println("Unit Cost : " + fileProcessing.getFloatCellData(i, 4));

            enterCustomerInfo();
            easyQuotePage.selectPurpose(fileProcessing.getCellData(i, 1));
            easyQuotePage.selectContent(fileProcessing.getCellData(i, 2));
            easyQuotePage.selectWebsite("vananservices.com");
            easyQuotePage.clickCallYes();
            easyQuotePage.clickAddFiles();
            easyQuotePage.setSingleFileDetail("", service1 + i, fileProcessing.getCellData(i, 0),
                    fileProcessing.getCellData(i, 0), (int) fileProcessing.getFloatCellData(i, 3) + "",
                    fileProcessing.getFloatCellData(i, 4) + "", "", "Test", 1, (int) fileProcessing.getFloatCellData(i, 5));
            waitingTime(5);
            double totalUnitCost = roundValues(fileProcessing.getFloatCellData(i, 3) * fileProcessing.getFloatCellData(i, 4));
            double transactionFee = roundValues(totalUnitCost * 0.05);
            double orderTotal = roundValues(totalUnitCost + transactionFee);
            fileProcessing.setCellData(easyQuotePage.getBasePriceValue() + "", i, 6);
            fileProcessing.setCellData(easyQuotePage.getSubTotalPriceValue() + "", i, 7);
            fileProcessing.setCellData(easyQuotePage.getTransactionPriceValue() + "", i, 8);
            fileProcessing.setCellData(easyQuotePage.getOrderTotalValue() + "", i, 9);
            fileProcessing.setCellData(easyQuotePage.getOrderValue() + "", i, 10);
            String BasePrice = checkStatus(easyQuotePage.getBasePriceValue(), totalUnitCost, "BasePrice");
            String SubTotalPrice = checkStatus(easyQuotePage.getSubTotalPriceValue(), totalUnitCost, "SubTotalPrice");
            String TransactionPrice = checkStatus(easyQuotePage.getTransactionPriceValue(), transactionFee, "TransactionPrice");
            String OrderTotal = checkStatus(easyQuotePage.getOrderTotalValue(), orderTotal, "OrderTotal");
            String OrderValue = checkStatus(easyQuotePage.getOrderValue(), orderTotal, "OrderValue");
            fileProcessing.setCellData(BasePrice, i, 11);
            fileProcessing.setCellData(SubTotalPrice, i, 12);
            fileProcessing.setCellData(TransactionPrice, i, 13);
            fileProcessing.setCellData(OrderTotal, i, 14);
            fileProcessing.setCellData(OrderValue, i, 15);
            String overAllStatus = "";
            if (BasePrice.equals("Pass") && SubTotalPrice.equals("Pass") && TransactionPrice.equals("Pass")
                    && OrderTotal.equals("Pass") && OrderValue.equals("Pass")) {

                overAllStatus = "Pass";

            } else {
                overAllStatus = "Fail";
            }
            fileProcessing.setCellData(overAllStatus, i, 16);
            driver.get(APP_URL1);
        }
        fileProcessing.writeFileContent(level1);
    }

    @Test(priority = 1)
    public void runTranslationTest() {
        fileProcessing.setExcelFile(level1, service2);
        for (int i = 1; i <= fileProcessing.getRowUsed(); i++) {
            System.out.println("Source : " + fileProcessing.getCellData(i, 0));
            System.out.println("Target : " + fileProcessing.getCellData(i, 1));
            System.out.println("Purpose : " + fileProcessing.getCellData(i, 2));
            System.out.println("Content : " + fileProcessing.getCellData(i, 3));
            System.out.println("Pages : " + (int) fileProcessing.getNumericCellData(i, 4));
            System.out.println("Unit Cost : " + fileProcessing.getFloatCellData(i, 5));
            driver.get(APP_URL1);
            enterCustomerInfo();
            easyQuotePage.clickTranslation();
            easyQuotePage.selectPurpose(fileProcessing.getCellData(i, 2));
            easyQuotePage.selectContent(fileProcessing.getCellData(i, 3));
            easyQuotePage.selectWebsite("vananservices.com");
            easyQuotePage.clickCallYes();
            easyQuotePage.clickAddFiles();
            easyQuotePage.setSingleFileDetail("Document", service2 + i, fileProcessing.getCellData(i, 0),
                    fileProcessing.getCellData(i, 1), (int) fileProcessing.getFloatCellData(i, 4) + "",
                    fileProcessing.getFloatCellData(i, 5) + "", "", "Test", 1, (int) fileProcessing.getFloatCellData(i, 6));
            waitingTime(5);
            double totalUnitCost = roundValues(fileProcessing.getFloatCellData(i, 4) * fileProcessing.getFloatCellData(i, 5));
            double transactionFee = roundValues(totalUnitCost * 0.05);
            double orderTotal = roundValues(totalUnitCost + transactionFee);
            fileProcessing.setCellData(easyQuotePage.getBasePriceValue() + "", i, 7);
            fileProcessing.setCellData(easyQuotePage.getSubTotalPriceValue() + "", i, 8);
            fileProcessing.setCellData(easyQuotePage.getTransactionPriceValue() + "", i, 9);
            fileProcessing.setCellData(easyQuotePage.getOrderTotalValue() + "", i, 10);
            fileProcessing.setCellData(easyQuotePage.getOrderValue() + "", i, 11);
            String BasePrice = checkStatus(easyQuotePage.getBasePriceValue(), totalUnitCost, "BasePrice");
            String SubTotalPrice = checkStatus(easyQuotePage.getSubTotalPriceValue(), totalUnitCost, "SubTotalPrice");
            String TransactionPrice = checkStatus(easyQuotePage.getTransactionPriceValue(), transactionFee, "TransactionPrice");
            String OrderTotal = checkStatus(easyQuotePage.getOrderTotalValue(), orderTotal, "OrderTotal");
            String OrderValue = checkStatus(easyQuotePage.getOrderValue(), orderTotal, "OrderValue");
            fileProcessing.setCellData(BasePrice, i, 12);
            fileProcessing.setCellData(SubTotalPrice, i, 13);
            fileProcessing.setCellData(TransactionPrice, i, 14);
            fileProcessing.setCellData(OrderTotal, i, 15);
            fileProcessing.setCellData(OrderValue, i, 16);
            String overAllStatus = "";
            if (BasePrice.equals("Pass") && SubTotalPrice.equals("Pass") && TransactionPrice.equals("Pass")
                    && OrderTotal.equals("Pass") && OrderValue.equals("Pass")) {

                overAllStatus = "Pass";

            } else {
                overAllStatus = "Fail";
            }
            fileProcessing.setCellData(overAllStatus, i, 17);
        }
        fileProcessing.writeFileContent(level1);
    }

    @Test(priority = 2)
    public void runCaptioningTest() {
        fileProcessing.setExcelFile(level1, service3);
        for (int i = 1; i <= fileProcessing.getRowUsed(); i++) {
            System.out.println("Source : " + fileProcessing.getCellData(i, 0));
            System.out.println("Target : " + fileProcessing.getCellData(i, 1));
            System.out.println("Purpose : " + fileProcessing.getCellData(i, 2));
            System.out.println("Content : " + fileProcessing.getCellData(i, 3));
            System.out.println("Pages : " + fileProcessing.getNumericCellData(i, 4));
            System.out.println("Unit Cost : " + fileProcessing.getFloatCellData(i, 5));
            driver.get(APP_URL1);
            enterCustomerInfo();
            easyQuotePage.clickCaptioning();
            easyQuotePage.selectPurpose(fileProcessing.getCellData(i, 2));
            easyQuotePage.selectContent(fileProcessing.getCellData(i, 3));
            easyQuotePage.selectWebsite("vananservices.com");
            easyQuotePage.clickCallYes();
            easyQuotePage.clickAddFiles();
            easyQuotePage.setSingleFileDetail("Video", service3 + i,
                    fileProcessing.getCellData(i, 0), fileProcessing.getCellData(i, 1),
                    fileProcessing.getFloatCellData(i, 4) + "",
                    fileProcessing.getFloatCellData(i, 5)+ "", "", "Test", 1,(int)fileProcessing.getFloatCellData(i, 6));
            waitingTime(5);
            double totalUnitCost = roundValues(fileProcessing.getFloatCellData(i, 4) * fileProcessing.getFloatCellData(i, 5));
            double transactionFee = roundValues(totalUnitCost * 0.05);
            double orderTotal = roundValues(totalUnitCost + transactionFee);
            fileProcessing.setCellData(easyQuotePage.getBasePriceValue() + "", i, 7);
            fileProcessing.setCellData(easyQuotePage.getSubTotalPriceValue() + "", i, 8);
            fileProcessing.setCellData(easyQuotePage.getTransactionPriceValue() + "", i, 9);
            fileProcessing.setCellData(easyQuotePage.getOrderTotalValue() + "", i, 10);
            fileProcessing.setCellData(easyQuotePage.getOrderValue() + "", i, 11);
            String BasePrice = checkStatus(easyQuotePage.getBasePriceValue(), totalUnitCost, "BasePrice");
            String SubTotalPrice = checkStatus(easyQuotePage.getSubTotalPriceValue(), totalUnitCost, "SubTotalPrice");
            String TransactionPrice = checkStatus(easyQuotePage.getTransactionPriceValue(), transactionFee, "TransactionPrice");
            String OrderTotal = checkStatus(easyQuotePage.getOrderTotalValue(), orderTotal, "OrderTotal");
            String OrderValue = checkStatus(easyQuotePage.getOrderValue(), orderTotal, "OrderValue");
            fileProcessing.setCellData(BasePrice, i, 12);
            fileProcessing.setCellData(SubTotalPrice, i, 13);
            fileProcessing.setCellData(TransactionPrice, i, 14);
            fileProcessing.setCellData(OrderTotal, i, 15);
            fileProcessing.setCellData(OrderValue, i, 16);
            String overAllStatus = "";
            if (BasePrice.equals("Pass") && SubTotalPrice.equals("Pass") && TransactionPrice.equals("Pass")
                    && OrderTotal.equals("Pass") && OrderValue.equals("Pass")) {

                overAllStatus = "Pass";

            } else {
                overAllStatus = "Fail";
            }
            fileProcessing.setCellData(overAllStatus, i, 17);
        }
        fileProcessing.writeFileContent(level1);
    }

    @Test(priority = 3)
    public void runTypingTest() {
        fileProcessing.setExcelFile(level1, service4);
        for (int i = 1; i <= fileProcessing.getRowUsed(); i++) {
            System.out.println("Source : " + fileProcessing.getCellData(i, 0));
            System.out.println("Purpose : " + fileProcessing.getCellData(i, 1));
            System.out.println("Content : " + fileProcessing.getCellData(i, 2));
            System.out.println("Pages : " + fileProcessing.getFloatCellData(i, 3));
            System.out.println("Unit Cost : " + fileProcessing.getFloatCellData(i, 4));
            driver.get(APP_URL1);
            enterCustomerInfo();
            easyQuotePage.clickTyping();
            easyQuotePage.selectPurpose(fileProcessing.getCellData(i, 1));
            easyQuotePage.selectContent(fileProcessing.getCellData(i, 2));
            easyQuotePage.selectWebsite("vananservices.com");
            easyQuotePage.clickCallYes();
            easyQuotePage.clickAddFiles();
            easyQuotePage.setSingleFileDetail("Document", service4 + i,
                    fileProcessing.getCellData(i, 0), fileProcessing.getCellData(i, 0),
                    fileProcessing.getFloatCellData(i, 3) + "", fileProcessing.getFloatCellData(i, 4)+"", "", "Test", 1,(int)fileProcessing.getFloatCellData(i, 5));

            waitingTime(5);
            double totalUnitCost = roundValues(fileProcessing.getFloatCellData(i, 3) * fileProcessing.getFloatCellData(i, 4));
            double transactionFee = roundValues(totalUnitCost * 0.05);
            double orderTotal = roundValues(totalUnitCost + transactionFee);
            fileProcessing.setCellData(easyQuotePage.getBasePriceValue() + "", i, 6);
            fileProcessing.setCellData(easyQuotePage.getSubTotalPriceValue() + "", i, 7);
            fileProcessing.setCellData(easyQuotePage.getTransactionPriceValue() + "", i, 8);
            fileProcessing.setCellData(easyQuotePage.getOrderTotalValue() + "", i, 9);
            fileProcessing.setCellData(easyQuotePage.getOrderValue() + "", i, 10);
            String BasePrice = checkStatus(easyQuotePage.getBasePriceValue(), totalUnitCost, "BasePrice");
            String SubTotalPrice = checkStatus(easyQuotePage.getSubTotalPriceValue(), totalUnitCost, "SubTotalPrice");
            String TransactionPrice = checkStatus(easyQuotePage.getTransactionPriceValue(), transactionFee, "TransactionPrice");
            String OrderTotal = checkStatus(easyQuotePage.getOrderTotalValue(), orderTotal, "OrderTotal");
            String OrderValue = checkStatus(easyQuotePage.getOrderValue(), orderTotal, "OrderValue");
            fileProcessing.setCellData(BasePrice, i, 11);
            fileProcessing.setCellData(SubTotalPrice, i, 12);
            fileProcessing.setCellData(TransactionPrice, i, 13);
            fileProcessing.setCellData(OrderTotal, i, 14);
            fileProcessing.setCellData(OrderValue, i, 15);
            String overAllStatus = "";
            if (BasePrice.equals("Pass") && SubTotalPrice.equals("Pass") && TransactionPrice.equals("Pass")
                    && OrderTotal.equals("Pass") && OrderValue.equals("Pass")) {

                overAllStatus = "Pass";

            } else {
                overAllStatus = "Fail";
            }
            fileProcessing.setCellData(overAllStatus, i, 16);
        }
        fileProcessing.writeFileContent(level1);
    }

    // @Test(priority = 4)
    public void runOthersTest() {

    }

    @AfterTest
    public void teardown() {
        tearDown();
    }

    public void enterCustomerInfo() {

        easyQuotePage.enterCustomerInfo("automation.vananservices@gmail.com", "AUTOMATION", "TESTING", "9876543210", "India");
    }
}
