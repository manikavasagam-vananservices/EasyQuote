package com.vanan.Common;

public interface Pricedetails {
    public double notarizationFee = 15;
    public double certificateFee = 10;
    public double mailingNotaryFee[] = {20,40,80};
    public double timeCodeFee[] = {0.5,0.25};
    public double speakerCount[] = {0,0.10,0.25,0.35,0.5};
    public double qcFee[] = {0.25,5};
    public double verbatimFee = 0.25;
    public double ustranscriber = 1.75;
    public double transcriptionTierFees[] = {5,7,16,25};
    public double transcriptionEnglishFees[] = {1,2};
    public  double transactionFee = 0.05;
    public double translaionTierFees[] = {25,30,60,120};

    //Audio
    public double translationDiscountFees[] = {4.90,5.60,6.30,7};

    //Audio
    public double transcriptionDiscountFees[] = {3.5,4,4.5,5};
   // public double captioningTierFees[] = {0,0.10,0.25,0.35,0.5};
  //  public double typingTierFees[] = {0,0.10,0.25,0.35,0.5};
}
