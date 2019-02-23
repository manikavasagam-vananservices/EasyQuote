package com.vanan.POM;

public interface EasyQuoteElements {

    /**
     * Customer info elements
     */
    public String emailId = "customer_email";
    public String firstName = "customer_name";
    public String lastName = "customer_lastname";
    public String phoneNumber = "phone_no";
    public String country = "country_id";

    /**
     * Service Info elements
     */
    public String chat = "//*[@id='type-chat']";
    public String call = "//*[@id='type-call']";
    public String serviceTranscription = "//*[@id='service-transcriptions']";
    public String serviceTranslation = "//*[@id='service-translation']";
    public String serviceCaptioning = "//*[@id='service-captioning']";
    public String serviceTyping = "//*[@id='service-typing']";
    public String serviceOther = "//*[@id='service-others']";
    public String purpose = "service-purpose";
    public String content = "purpose-content";
    public String website = "source_id";
    public String individual = "//*[@id='ct-individual']";
    public String company = "//*[@id='ct-company']";
    public String callYes = "//*[@id='call-yes']";
    public String callNo = "//*[@id='call-no']";

    /**
     * File Information
     */
    public String estimate = "//*[@id='file-info-rough']";
    public String uploadFiles = "//*[@id='file-info-upload']";
    public String addFiles = "//*[@id='roughFiles']";
    public String uploadNewFiles = "//*[@id='uploadNewFiles']";
    public String fileSpecifications = "file_specifications";

    /**
     * File Section
     */
    public String fileTableRow = "//table[@id='info']//tbody/tr";
    public String removeBtn = "//td[1]//a[@class='remove-row']";
    public String fileType = "//td[2]//select[contains(@name,'file_type')]";
        public String fileName = "//td[3]//input[contains(@name,'filename')]";
    public String sourceLanguage = "//td[4]//select[contains(@name,'source_lang')]";
    public String targetLanguage = "//td[5]//select[contains(@name,'target_lang')]";
    public String pagesMinutes = "//td[6]//input[contains(@name,'page_length')]";
    public String costPerPageMinute = "//td[7]//input[contains(@name,'cost')]";
    public String totalCost = "//td[8]//input[contains(@name,'total_cost')]";
    public String comments = "//td[9]//textarea[contains(@name,'comments')]";
    /**
     * Additional Services
     */
    public String notarization = "//*[@id='notarization']";
    public String mailing = "//*[@id='mailing']";
    public String speakerCount = "//*[@id='speakerCount']";
    public String verbatim = "//*[@id='verbatim']";
    public String timecode = "//*[@id='timecode']";
    public String nativeEmt = "//*[@id='native']";
    public String qualityCheck = "//*[@id='qualityCheck']";
    public String otherServices = "//*[@id='otherServices-selectized']";
    public String certificate = "//*[@id='certificate']";
    public String transcriptionYes = "//*[@id='transcription-yes']";
    public String transcriptionNo = "//*[@id='transcription-no']";
    public String standalone = "//*[@id='standalone']";
    public String embeded = "//*[@id='embeded']";
    public String standaloneFileFormat = "//*[@id='standalone-file-format']";
    public String audience = "//*[@id='audience']";
    public String needTranslation = "//*[@id='need-translation']";
    public String needScript = "//*[@id='need-script']";

    /**
     * Turnaround Time [EST]
     */
    public String standard = "//*[@id='standard']";
    public String expediteService = "//*[@id='expedite_service']";
    public String superRush = "//*[@id='super_rush']";
    public String expressRush = "//*[@id='express_rush']";
    public String customizedTatDate = "//*[@id='customized_tat_date']";

    /**
     * Additional Information
     */
    public String premiumCustomer = "//*[@name='premium_customer']";
    public String rmCallYes = "//*[@id='rm-call-yes']";
    public String rmCallNo = "//*[@id='rm-call-no']";
    public String keyword = "keyword";
    public String vocYes = "//*[@id='voc-yes']";
    public String vocNo = "//*[@id='voc-no']";
    public String followupDate = "//*[@id='followup-date']";
    public String followupMedium = "//*[@name='followup_medium']";
    public String discountId = "//*[@id='discount_id']";
    public String discountPercentage = "//*[@id='discount-percentage']";
    public String specialRequest = "//*[@id='special_request']";

    /**
     * ORDER VALUE
     */
    public String orderValue = "//h5//span[@class='orderValue']";
    public String basePrice = "//span[@class='basePrice']";
    public String discountPriceDisplay = "//*[@class='discountPriceDisplay']";
    public String discountPercent = "//*[@class='discount-percent']";
    public String subTotalPrice = "//*[@id='subTotalPrice']";
    public String transactionFeePrice = "//*[@id='transactionFeePrice']";
    public String orderTotal = "//span[contains(@class,'edit-price')]//span[@class='orderValue']";
    public String saveQuote = "//*[@name='submit_save']";
    public String sendQuote = "//*[@name='submit_save_send']";

}
