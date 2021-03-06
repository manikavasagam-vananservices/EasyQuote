package com.vanan.Common;

public class PriceCalculator implements Pricedetails {


    public double getMailingNotary(String option) {
        double value = 0;
        switch (option) {
            case "Standard":
                value = mailingNotaryFee[0];
                break;
            case "2 - 3 days":
                value = mailingNotaryFee[1];
                break;
            case "Overnight":
                value = mailingNotaryFee[2];
                break;
        }
        return value;
    }

    private double getNotarizationFee() {
        return notarizationFee;
    }

    public double getTransactionFee() {
        return transactionFee;
    }

    private double getTranscriptionCertificationFee(String language, String purpose) {
        double value = 0;
        if (!language.equals("English") && purpose.equals("Legal")) {
            value = certificateFee;
        }
        return value;
    }

    private double getTranslationCertificationFee(String language) {
        double value = 0;
        if (!language.equals("English")) {
            value = certificateFee;
        }
        return value;
    }

    private double getQATranscriptionFee(double unit, String fileType) {
        double value = 0;
        if (fileType.equals("Audio") || fileType.equals("Video")) {
            value = unit * qcTranscriptionFee[0];
        } else if (fileType.equals("Document")) {
            value = unit * qcTranscriptionFee[1];
        }
        return value;
    }

    private double getQATranslationFee(double unit, String fileType) {
        double value = 0;
        if (fileType.equals("Audio") || fileType.equals("Video")) {
            value = unit * qcTranslationFee[0];
        } else if (fileType.equals("Document")) {
            value = unit * qcTranslationFee[1];
        }
        return value;
    }

    private double getTimeCode(String option) {
        double value = 0;
        switch (option) {
            case "Every 3 sec ($0.50 per minute)":
                value = timeCodeFee[0];
                break;
            case "Every 30 sec ($0.50 per minute)":
                value = timeCodeFee[0];
                break;
            case "Every 1 minute ($0.25 per minute)":
                value = timeCodeFee[1];
                break;
            case "Speaker change ($0.50 per minute)":
                value = timeCodeFee[0];
                break;
        }
        return value;
    }

    private double getSpeakerCount(String option) {
        double value = 0;
        switch (option) {
            case "1 Speaker ($0.00 per minute)":
                value = speakerCount[0];
                break;
            case "2 Speakers ($0.10 per minute)":
                value = speakerCount[1];
                break;
            case "3 to 5 Speakers ($0.25 per minute)":
                value = speakerCount[2];
                break;
            case "6 to 10 Speakers ($0.35 per minute)":
                value = speakerCount[3];
                break;
            case "10+ Speakers ($0.50 per minute)":
                value = speakerCount[4];
                break;
        }
        return value;
    }

    public double getTranscriptionFee(String tier) {
        double value = 0;
        switch (tier) {
            case "tr1":
            case "tr2":
                value = transcriptionTierFees[0];
                break;
            case "tr3":
                value = transcriptionTierFees[1];
                break;
            case "tr4":
                value = transcriptionTierFees[2];
                break;
            case "tr5":
                value = transcriptionTierFees[3];
                break;
        }
        return value;
    }

    private double getDocumentTranslationFee(String option) {
        double value = 0;
        switch (option) {
            case "tr1":
                value = translationDocumentTierFees[0];
                break;
            case "tr2":
                value = translationDocumentTierFees[1];
                break;
            case "tr3":
                value = translationDocumentTierFees[2];
                break;
            case "tr4":
                value = translationDocumentTierFees[3];
                break;
        }
        return value;
    }

    public double getAVTranslationFee(String tier) {
        double value = 0;
        switch (tier) {
            case "tr1":
            case "tr2":
                value = translationAVTierFees[0];
                break;
            case "tr3":
                value = translationAVTierFees[1];
                break;
            case "tr4":
                value = translationAVTierFees[2];
                break;
            case "tr5":
                value = translationAVTierFees[3];
                break;
        }
        return value;
    }

    public double getTranslationFee(String tier, String fileType) {

        double value = 0;
        if (fileType.equals("Audio") || fileType.equals("Video")) {

            value = getAVTranslationFee(tier);
        } else if (fileType.equals("Document")) {

            value = getDocumentTranslationFee(tier);
        }
        return value;
    }


    public double getTypingFee(String language, String purpose, String serviceName) {

        double value = 0;
        if (language.equals(supportedTypingLang[0])) {

            if (purpose.equals("General") && serviceName.contains("Formatting")) {
                value = typingTierFees[1];
            } else {
                value = typingTierFees[0];
            }
            if (purpose.equals("Legal")) {
                value = typingTierFees[2];
            }
        } else if (findLanguage(language)) {

            if ((purpose.equals("General")&& serviceName.equals("Formatting"))
                    || (purpose.equals("Legal") && serviceName.equals("Formatting"))) {
                value = typingTierFees[3];
            } else {
                value = typingTierFees[2];
            }
        } else {
            value = typingTierFees[4];
        }
        return value;
    }

    public double getTypingTotalUnitFee(String language, String purpose, String serviceName, double unit) {

        return (unit * getTypingFee(language, purpose, serviceName));
    }

    private boolean findLanguage(String language) {
        boolean value = false;
        for (int i = 1; i < supportedTypingLang.length; i++) {
            if (language.contains(supportedTypingLang[i])) {
                value = true;
                break;
            }
        }
        return value;
    }

    public double getTranslationTotalUnit(double unit, String tier, String fileType) {

        double value = 0;
        if (fileType.equals("Audio") || fileType.equals("Video")) {

            value = unit * getAVTranslationFee(tier);
        } else if (fileType.equals("Document")) {

            value = unit * getDocumentTranslationFee(tier);
        }
        return value;
    }


    public double getTranscriptionTotalUnit(double unit, String tier,
                                            String purpose, String language, boolean speaker) {
        double value = 0;
        if (language.equals("English") && purpose.equals("General")) {
            if (speaker) {
                value = unit * ustranscriber;
            } else {
                value = unit * transcriptionEnglishFees[0];
            }

        } else if (language.equals("English") && purpose.equals("Legal")) {
            value = unit * transcriptionEnglishFees[1];
        } else {
            value = unit * getTranscriptionFee(tier);
        }

        return value;
    }

    public double getTranscriptionVerbatim(double unit, String language) {
        double value = 0;
        if (language.equals("English")) {
            value = (unit * verbatimFee);
        }
        return value;
    }

    public double getTranscriptionDiscount(String tier, double unit,
                                           String language, String purpose, boolean nativeSpeaker) {
        double value = 0;
        if (!purpose.equals("Legal") && nativeSpeaker == false) {
            if (tier.equals("tr1")) {
                if (language.equals("English")) {
                    if (unit >= 1200) {
                        value = (unit * transcriptionEnglishFees[0]) * 0.3;
                    } else if (unit >= 600 && unit <= 1119) {
                        value = (unit * transcriptionEnglishFees[0]) * 0.2;
                    } else if (unit >= 300 && unit <= 599) {
                        value = (unit * transcriptionEnglishFees[0]) * 0.1;
                    } else {
                        value = 0;
                    }
                } else {
                    if (unit >= 720) {
                        value = (unit * getTranscriptionFee(tier)) * 0.3;
                    } else if (unit >= 360 && unit <= 719) {
                        value = (unit * getTranscriptionFee(tier)) * 0.2;
                    } else if (unit >= 180 && unit <= 359) {
                        value = (unit * getTranscriptionFee(tier)) * 0.1;
                    } else {
                        value = 0;
                    }
                }

            } else {
                value = 0;
            }
        }
        return value;
    }


    public double getTranslationDiscount(String tier, double unit, String fileType) {
        double value = 0;
        if (tier.equals("tr1")) {
            if (fileType.contains("Audio") || fileType.contains("Video")) {
                if (unit >= 720) {
                    value = (unit * getAVTranslationFee(tier)) * 0.3;
                } else if (unit >= 360 && unit <= 719) {
                    value = (unit * getAVTranslationFee(tier)) * 0.2;
                } else if (unit >= 180 && unit <= 359) {
                    value = (unit * getAVTranslationFee(tier)) * 0.1;
                } else {
                    value = 0;
                }
            } else if (fileType.contains("Document")) {
                if ((unit >= 100 && unit <= 149) || unit >= 149) {
                    value = (unit * getAVTranslationFee(tier)) * 0.3;
                } else if (unit >= 50 && unit <= 99) {
                    value = (unit * getAVTranslationFee(tier)) * 0.2;
                } else if (unit >= 20 && unit <= 49) {
                    value = (unit * getAVTranslationFee(tier)) * 0.1;
                } else {
                    value = 0;
                }
            }

        }
        return value;
    }

    private double calculateTranscriptionAdditionalServices(String service, double unit, String langauge,
                                                            String timeCodeOption, String SpeakerOption, String mailingNotaryOption, String purpose,
                                                            String fileType) {
        double value = 0;
        switch (service) {
            case "Notarization":
                value = getNotarizationFee();
                break;
            case "Additional Acceptance Testing":
                value = getQATranscriptionFee(unit, fileType);
                break;
            case "Certificate":
                value = getTranscriptionCertificationFee(langauge, purpose);
                break;
            case "Verbatim":
                value = getTranscriptionVerbatim(unit, langauge);
                break;
            case "Mailing and Notary":
                value = getMailingNotary(mailingNotaryOption);
                break;
            case "Time code":
                value = unit * getTimeCode(timeCodeOption);
                break;
            case "Speaker Count":
                value = unit * getSpeakerCount(SpeakerOption);
                break;
        }
        return value;
    }

    public double getAdditionalPriceForTranscription(String services[], double unit, String langauge,
                                                     String timeCodeOption, String SpeakerOption, String mailingNotaryOption, String purpose,
                                                     String fileType) {
        double value = 0;
        for (int i = 0; i < services.length; i++) {
            value = value + calculateTranscriptionAdditionalServices(services[i], unit, langauge,
                    timeCodeOption, SpeakerOption, mailingNotaryOption, purpose,
                    fileType);
        }
        return value;
    }

    public double getSingleAdditionalPriceForTranscription(String services, double unit, String langauge,
                                                           String timeCodeOption, String SpeakerOption, String mailingNotaryOption, String purpose,
                                                           String fileType) {

        return calculateTranscriptionAdditionalServices(services, unit, langauge,
                timeCodeOption, SpeakerOption, mailingNotaryOption, purpose,
                fileType);


    }

    public double getAdditionalPriceForTranslation(String services[], double unit, String langauge,
                                                   String timeCodeOption, String SpeakerOption, String mailingNotaryOption,
                                                   String fileType) {
        double value = 0;
        for (int i = 0; i < services.length; i++) {
            value = value + calculateTranslationAdditionalServices(services[i], unit, langauge,
                    timeCodeOption, SpeakerOption, mailingNotaryOption,
                    fileType);
        }
        return value;
    }

    public double getSingleAdditionalPriceForTranslation(String services, double unit, String langauge,
                                                         String timeCodeOption, String SpeakerOption, String mailingNotaryOption,
                                                         String fileType) {

        return calculateTranslationAdditionalServices(services, unit, langauge,
                timeCodeOption, SpeakerOption, mailingNotaryOption,
                fileType);


    }

    private double calculateTranslationAdditionalServices(String service, double unit, String langauge,
                                                          String timeCodeOption, String SpeakerOption, String mailingNotaryOption,
                                                          String fileType) {
        double value = 0;
        switch (service) {
            case "Notarization":
                value = getNotarizationFee();
                break;
            case "Additional Acceptance Testing":
                value = getQATranslationFee(unit, fileType);
                break;
            case "Certificate":
                value = getTranslationCertificationFee(langauge);
                break;
            case "Mailing and Notary":
                value = getMailingNotary(mailingNotaryOption);
                break;
            case "Time code":
                value = unit * getTimeCode(timeCodeOption);
                break;
            case "Speaker Count":
                value = unit * getSpeakerCount(SpeakerOption);
                break;
        }
        return value;
    }
}
