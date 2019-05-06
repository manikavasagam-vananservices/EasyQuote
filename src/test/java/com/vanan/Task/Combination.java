package com.vanan.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Combination {
    List<String> test = new ArrayList<String>();
    /* arr[]  ---> Input Array
    data[] ---> Temporary array to store current combination
    start & end ---> Staring and Ending indexes in arr[]
    index  ---> Current index in data[]
    r ---> Size of a combination to be printed */
    void combinationUtil(String arr[], int n, int r, int index,
                                String data[], int i)
    {

        // Current combination is ready to be printed, print it
        if (index == r)
        {
            for (int j=0; j<r; j++){
                test.add( data[j]+",");
               System.out.print(data[j]+",");
            }
            System.out.println("");
            return;
        }

        // When no more elements are there to put in data[]
        if (i >= n) {

            return;
        }

        // current is included, put next at next location
        data[index] = arr[i];

        combinationUtil(arr, n, r, index+1, data, i+1);

        // current is excluded, replace it with next (Note that
        // i+1 is passed, but index is not changed)
        combinationUtil(arr, n, r, index, data, i+1);



    }

    // The main function that prints all combinations of size r
    // in arr[] of size n. This function mainly uses combinationUtil()
    void printCombination(String arr[], int n, int r)
    {
        // A temporary array to store all combination one by one
        String data[]=new String[r];

        // Print all combination using temprary array 'data[]'
        combinationUtil(arr, n, r, 0, data, 0);
       // test.add( data[r]);
    }
    List <String> te = new ArrayList<String>();
    void tess(int r) {
        int t=0;
        if(r==2) {
            while(t<test.size()) {
                te.add(test.get(t) + test.get(t+1));
                t = t+r;
            }
        } else if(r==3) {
            while(t<test.size()) {
                te.add(test.get(t) + test.get(t+1) + test.get(t+2));
                t = t+r;
            }
        } else if(r==4) {
            while(t<test.size()) {
                te.add(test.get(t) + test.get(t+1) + test.get(t+2)+ test.get(t+3));
                t = t+r;
            }
        }else if(r==5) {
            while(t<test.size()) {
                te.add(test.get(t) + test.get(t+1) + test.get(t+2)+ test.get(t+3)+ test.get(t+4));
                t = t+r;
            }
        }else if(r==6) {
            while(t<test.size()) {
                te.add(test.get(t) + test.get(t+1) + test.get(t+2)+ test.get(t+3)+ test.get(t+4)+ test.get(t+5));
                t = t+r;
            }
        }else if(r==7) {
            while(t<test.size()) {
                te.add(test.get(t) + test.get(t+1) + test.get(t+2)+ test.get(t+3)+ test.get(t+4)+ test.get(t+5)+test.get(t+6));
                t = t+r;
            }
        }else if(r==8) {
            while(t<test.size()) {
                te.add(test.get(t) + test.get(t+1) + test.get(t+2)+ test.get(t+3)+ test.get(t+4)+ test.get(t+5)+test.get(t+6)+test.get(t+7));
                t = t+r;
            }
        }else if(r==9) {
            while(t<test.size()) {
                te.add(test.get(t) + test.get(t+1) + test.get(t+2)+ test.get(t+3)+ test.get(t+4)+ test.get(t+5)+test.get(t+6)+test.get(t+7)+test.get(t+8));
                t = t+r;
            }
        }else if(r==10) {
            while(t<test.size()) {
                te.add(test.get(t) + test.get(t+1) + test.get(t+2)+ test.get(t+3)+ test.get(t+4)+ test.get(t+5)+test.get(t+6)+test.get(t+7)+test.get(t+8)+test.get(t+9));
                t = t+r;
            }
        }else if(r==11) {
            while(t<test.size()) {
                te.add(test.get(t) + test.get(t+1) + test.get(t+2)+ test.get(t+3)+ test.get(t+4)+ test.get(t+5)+test.get(t+6)+test.get(t+7)+test.get(t+8)+test.get(t+9)+test.get(t+10));
                t = t+r;
            }
        }else if(r==12) {
            while(t<test.size()) {
                te.add(test.get(t) + test.get(t+1) + test.get(t+2)+ test.get(t+3)+ test.get(t+4)+ test.get(t+5)+test.get(t+6)+test.get(t+7)+test.get(t+8)+test.get(t+9)+test.get(t+10)+test.get(t+11));
                t = t+r;
            }
        }else if(r==13) {
            while(t<test.size()) {
                te.add(test.get(t) + test.get(t+1) + test.get(t+2)+ test.get(t+3)+ test.get(t+4)+ test.get(t+5)+test.get(t+6)+test.get(t+7)+test.get(t+8)+test.get(t+9)+test.get(t+10)+test.get(t+11)+test.get(t+12));
                t = t+r;
            }
        }
    }

    void teee(){
for(int t=0; t<te.size();t++) {
    System.out.println(te.get(t));
}
    }

    /*Driver function to check for above function*/
    public static void main (String[] args) {
//        String arr[] = {"Notarization", " + Mail hard copy", " + Speaker Count", " + Time Code", " + US Transcriber", " + Additional Acceptance Testing"," + Other Services"," + verbatim"};
        String arr[] = {"Notarization","Certificate", "Mail hard copy", "Speaker Count", "Time Code", "Need Transcription", "Additional Acceptance Testing","Other Services","Need Captioning"};
       // String arr[] = {"Mail hard copy","Other Services","Hand Written","Formatting"};
        int r = 5;
        int n = arr.length;
        Combination ns = new Combination();
        ns.printCombination(arr, n, r);

        ns.tess(r);
        ns.teee();
    }
}
