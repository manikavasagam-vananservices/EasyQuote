package com.vanan.Business;

import com.vanan.Common.FileProcessing;
import com.vanan.Common.TestBase;
import com.vanan.POM.EasyQuotePage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Basic Price with Verbatim
 */
public class TempFix extends TestBase {

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

            driver.get(APP_URL1);
            enterCustomerInfo();
            easyQuotePage.selectPurpose("Legal");
            easyQuotePage.selectContent("Court hearings");
            easyQuotePage.selectWebsite("vananservices.com");
            easyQuotePage.clickCallYes();
            easyQuotePage.clickAddFiles();
            easyQuotePage.setSingleFileDetail("", service1, "Wolof","Wolof",  "123",
                     "23", "", "Test", 1,1);
            easyQuotePage.clickVerbatim();
            waitingTime(5);
            double totalUnitCost = 123*23;
            double total = totalUnitCost + (123*0.25);
            double transactionFee = total * 0.05;
            double orderTotal = total + transactionFee;
            String BasePrice = checkStatus(easyQuotePage.getBasePriceValue(), totalUnitCost, "BasePrice");
            String AdditionalServicePrice = checkStatus(easyQuotePage.getAdditionalServicePriceValue(),
                    123*0.25, "Quality Check");
            String SubTotalPrice = checkStatus(easyQuotePage.getSubTotalPriceValue(), total, "SubTotalPrice");
            String TransactionPrice = checkStatus(easyQuotePage.getTransactionPriceValue(), transactionFee, "TransactionPrice");
            String OrderTotal = checkStatus(easyQuotePage.getOrderTotalValue(), orderTotal, "OrderTotal");
            String OrderValue = checkStatus(easyQuotePage.getOrderValue(), orderTotal, "OrderValue");

            String overAllStatus = "";
            if (BasePrice.equals("Pass") && SubTotalPrice.equals("Pass") && TransactionPrice.equals("Pass")
                    && OrderTotal.equals("Pass") && OrderValue.equals("Pass") && AdditionalServicePrice.equals("Pass")) {

                overAllStatus = "Pass";

            } else {
                overAllStatus = "Fail";
            }
            waitingTime(40);
    }

    @AfterTest
    public void teardown() {
        tearDown();
    }

    public void enterCustomerInfo() {

        easyQuotePage.enterCustomerInfo("automation.vananservices@gmail.com", "AUTOMATION", "TESTING", "9876543210", "India");
    }


}
