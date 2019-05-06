package com.vanan.Common;

import java.util.ArrayList;
import java.util.List;

public class ScenarioGenerator {

    List<String> original = new ArrayList<String>();
    List<String> required = new ArrayList<String>();

    /* arr[]  ---> Input Array
    data[] ---> Temporary array to store current combination
    start & end ---> Staring and Ending indexes in arr[]
    index  ---> Current index in data[]
    r ---> Size of a combination to be printed */
    void combinationGenerator(String arr[], int arrLength, int scenarioSize, int index,
                              String data[], int i) {

        // Current combination is ready to be printed, print it
        if (index == scenarioSize) {
            for (int j = 0; j < scenarioSize; j++) {
                original.add(data[j] + ",");
                System.out.print(data[j] + ",");
            }
            System.out.println("");
            return;
        }

        // When no more elements are there to put in data[]
        if (i >= arrLength) {

            return;
        }

        // current is included, put next at next location
        data[index] = arr[i];

        combinationGenerator(arr, arrLength, scenarioSize, index + 1, data, i + 1);

        // current is excluded, replace it with next (Note that
        // i+1 is passed, but index is not changed)
        combinationGenerator(arr, arrLength, scenarioSize, index, data, i + 1);


    }

    // The main function that prints all combinations of size r
    // in arr[] of size n. This function mainly uses combinationUtil()
    void multipleScenarioCombinationGenerator(String arr[], int arrLength, int scenarioSize) {
        // A temporary array to store all combination one by one
        String data[] = new String[scenarioSize];

        // Print all combination using temprary array 'data[]'
        combinationGenerator(arr, arrLength, scenarioSize, 0, data, 0);
    }

    void regeneratePosition(int scenarioSize) {
        int temp = 0;
        if (scenarioSize == 2) {
            while (temp < original.size()) {
                required.add(original.get(temp) + original.get(temp + 1));
                temp = temp + scenarioSize;
            }
        } else if (scenarioSize == 3) {
            while (temp < original.size()) {
                required.add(original.get(temp) + original.get(temp + 1) + original.get(temp + 2));
                temp = temp + scenarioSize;
            }
        } else if (scenarioSize == 4) {
            while (temp < original.size()) {
                required.add(original.get(temp) + original.get(temp + 1) + original.get(temp + 2) + original.get(temp + 3));
                temp = temp + scenarioSize;
            }
        } else if (scenarioSize == 5) {
            while (temp < original.size()) {
                required.add(original.get(temp) + original.get(temp + 1) + original.get(temp + 2) + original.get(temp + 3) + original.get(temp + 4));
                temp = temp + scenarioSize;
            }
        } else if (scenarioSize == 6) {
            while (temp < original.size()) {
                required.add(original.get(temp) + original.get(temp + 1) + original.get(temp + 2) + original.get(temp + 3) + original.get(temp + 4) + original.get(temp + 5));
                temp = temp + scenarioSize;
            }
        } else if (scenarioSize == 7) {
            while (temp < original.size()) {
                required.add(original.get(temp) + original.get(temp + 1) + original.get(temp + 2) + original.get(temp + 3) + original.get(temp + 4) + original.get(temp + 5) + original.get(temp + 6));
                temp = temp + scenarioSize;
            }
        } else if (scenarioSize == 8) {
            while (temp < original.size()) {
                required.add(original.get(temp) + original.get(temp + 1) + original.get(temp + 2) + original.get(temp + 3) + original.get(temp + 4) + original.get(temp + 5) + original.get(temp + 6) + original.get(temp + 7));
                temp = temp + scenarioSize;
            }
        } else if (scenarioSize == 9) {
            while (temp < original.size()) {
                required.add(original.get(temp) + original.get(temp + 1) + original.get(temp + 2) + original.get(temp + 3) + original.get(temp + 4) + original.get(temp + 5) + original.get(temp + 6) + original.get(temp + 7) + original.get(temp + 8));
                temp = temp + scenarioSize;
            }
        } else if (scenarioSize == 10) {
            while (temp < original.size()) {
                required.add(original.get(temp) + original.get(temp + 1) + original.get(temp + 2) + original.get(temp + 3) + original.get(temp + 4) + original.get(temp + 5) + original.get(temp + 6) + original.get(temp + 7) + original.get(temp + 8) + original.get(temp + 9));
                temp = temp + scenarioSize;
            }
        } else if (scenarioSize == 11) {
            while (temp < original.size()) {
                required.add(original.get(temp) + original.get(temp + 1) + original.get(temp + 2) + original.get(temp + 3) + original.get(temp + 4) + original.get(temp + 5) + original.get(temp + 6) + original.get(temp + 7) + original.get(temp + 8) + original.get(temp + 9) + original.get(temp + 10));
                temp = temp + scenarioSize;
            }
        } else if (scenarioSize == 12) {
            while (temp < original.size()) {
                required.add(original.get(temp) + original.get(temp + 1) + original.get(temp + 2) + original.get(temp + 3) + original.get(temp + 4) + original.get(temp + 5) + original.get(temp + 6) + original.get(temp + 7) + original.get(temp + 8) + original.get(temp + 9) + original.get(temp + 10) + original.get(temp + 11));
                temp = temp + scenarioSize;
            }
        } else if (scenarioSize == 13) {
            while (temp < original.size()) {
                required.add(original.get(temp) + original.get(temp + 1) + original.get(temp + 2) + original.get(temp + 3) + original.get(temp + 4) + original.get(temp + 5) + original.get(temp + 6) + original.get(temp + 7) + original.get(temp + 8) + original.get(temp + 9) + original.get(temp + 10) + original.get(temp + 11) + original.get(temp + 12));
                temp = temp + scenarioSize;
            }
        }
    }

    public List<String> getScenarios(String arr[], int scenarioSize) {

        multipleScenarioCombinationGenerator(arr, arr.length, scenarioSize);
        regeneratePosition(scenarioSize);
        return required;
    }
}
