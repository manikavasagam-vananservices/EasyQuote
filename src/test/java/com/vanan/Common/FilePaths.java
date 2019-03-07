package com.vanan.Common;

public interface FilePaths {
    public String screenshotParentPath = System.getProperty("user.dir") + "\\src\\screenshots\\";
    public String testDataParentPath = System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\";
    // Basic price calculation
    public String level1 = testDataParentPath + "EasyQuoteL1.xlsx";
    // Notarization
    public String level2 = testDataParentPath + "EasyQuoteL2.xlsx";
    //Quality Check
    public String level3 = testDataParentPath + "EasyQuoteL3.xlsx";
    //Certificate
    public String level4 = testDataParentPath + "EasyQuoteL4.xlsx";
    //Verbatim
    public String level5 = testDataParentPath + "EasyQuoteL5.xlsx";
    //Mailing
    public String level6 = testDataParentPath + "EasyQuoteL6.xlsx";
    //US transcriber
    public String level7 = testDataParentPath + "EasyQuoteL7.xlsx";
    //I have translation/transcription
    public String level8 = testDataParentPath + "EasyQuoteL8.xlsx";

    //Transcription

    // Notarization and US transcriber
    public String level9 = testDataParentPath + "EasyQuoteL9.xlsx";
    // Notarization and Quality Check
    public String level10 = testDataParentPath + "EasyQuoteL10.xlsx";
    // Notarization and Verbatim
    public String level11 = testDataParentPath + "EasyQuoteL11.xlsx";
    // Notarization and Mailing
    public String level12 = testDataParentPath + "EasyQuoteL12.xlsx";
}
