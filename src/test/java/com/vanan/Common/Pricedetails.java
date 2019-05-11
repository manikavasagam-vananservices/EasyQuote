package com.vanan.Common;

public interface Pricedetails {
    public double notarizationFee = 15;
    public double certificateFee = 10;
    public double mailingNotaryFee[] = {20,40,80};
    public double timeCodeFee[] = {0.5,0.25};
    public double speakerCount[] = {0,0.10,0.25,0.35,0.5};
    public double qcTranscriptionFee[] = {0.25,5};
    public double verbatimFee = 0.25;
    public double ustranscriber = 1.75;
    public double transcriptionTierFees[] = {5,7,16,25};
    public double transcriptionEnglishFees[] = {1,2};
    public  double transactionFee = 0.05;

    public double translationDocumentTierFees[] = {25,30,60,120};
    public double translationAVTierFees[] = {7,9,20,43};
    public double qcTranslationFee[] = {0.25,5};

    public double typingTierFees[] = {3.75,4.5,5,7,20};

    public String supportedTypingLang[] = {"English","Spanish (Latin-america)","Spanish","Canadian French","French",
            "Brazilian Portuguese","Portuguese","Russian","Italian","German","Masri","Arabic (Levantine)",
            "Arabic (Maghrebi)","Arabic","Chinese (Simplified)","Chinese (Traditional)","Ukrainian","Japanese"};
    // public double captioningTierFees[] = {0,0.10,0.25,0.35,0.5};
  //  public double typingTierFees[] = {0,0.10,0.25,0.35,0.5};
}
