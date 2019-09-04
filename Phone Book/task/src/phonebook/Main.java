package phonebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    //private static
    private static Scanner sc;

    public static void main(String[] args) {
        File inputNameFile = new File("C:\\Users\\lpapi\\IdeaData\\PHONE_BOOK\\find.txt");
        File inputPhoneBook = new File("C:\\Users\\lpapi\\IdeaData\\PHONE_BOOK\\directory.txt");
        String[] inputNameArray = getArray(inputNameFile, 500);
        String[] inputDirectoryArray = getArray(inputPhoneBook, 1014130);
        int countSimple = 0;
        System.out.println("Start searching (linear search)...");
        long startLsearch = System.currentTimeMillis();
        for(int i=0; i<inputNameArray.length; i++) {
            if(simplestSearch(inputDirectoryArray, inputNameArray[i] )> -1){
                countSimple++;
            }
        }
        long endLsearch = System.currentTimeMillis();
        long deltaS = endLsearch - startLsearch;
        showResults(countSimple, deltaS);

        System.out.println("Start searching (bubble sort + jump search)...");
        long startBsort = System.currentTimeMillis();
        String[] sortedArray = bubbleSort(inputDirectoryArray, deltaS);
        long endBsort = System.currentTimeMillis();
        long deltaB = endBsort - startBsort;
        String sortingSentence = returnResults(deltaB, "Sorting");
        String searchingSentence = "";
        int finalCount=0;
        long finalDelta=0;
        if(deltaB > 1000000000 * deltaS){
            countSimple = 0;
            sortingSentence+=" - STOPPED, moved to linear search";
            startLsearch = System.currentTimeMillis();
            for(int i=0; i<inputNameArray.length; i++) {
                if(simplestSearch(sortedArray, inputNameArray[i] )> -1){
                    countSimple++;
                }
            }
            endLsearch = System.currentTimeMillis();
            long newDeltaS = endLsearch - startLsearch;
            searchingSentence = returnResults(newDeltaS, "Searching");
            finalCount = countSimple;
            finalDelta = deltaB + newDeltaS;
        }else {
            int countJsearch =0;
            long startJsearch = System.currentTimeMillis();
            for (String s : sortedArray) {
                if(jumpSearch(sortedArray, s) >-1){
                    countJsearch++;
                }
            }
            long endJsearch = System.currentTimeMillis();
            long deltaJ = endJsearch - startJsearch;
            searchingSentence = returnResults(deltaJ, "Searching");
            finalCount = countJsearch;
            finalDelta = deltaB + deltaJ;
        }
        showResults(finalCount, finalDelta);
        System.out.println(sortingSentence);
        System.out.println(searchingSentence);


        System.out.println("Start searching (quick sort + binary search)...");
        long startQsort = System.currentTimeMillis();
        inputDirectoryArray = getArray(inputPhoneBook, 1014130);
        String[] sortedQArray = quickSort(inputDirectoryArray, 0, 1014129);
        long endQsort = System.currentTimeMillis();
        long deltaQ = endQsort - startQsort;
         sortingSentence = returnResults(deltaQ, "Sorting");
         searchingSentence = "";
         finalCount=0;
         finalDelta=0;
        int countBsearch =0;
        long startBsearch = System.currentTimeMillis();
        for (String s : sortedQArray) {
            if(binarySearch(sortedQArray, s, 0, 1014129) >-1){
                countBsearch++;
            }
        }
        long endBsearch = System.currentTimeMillis();
        long deltaBin = endBsearch - startBsearch;
        searchingSentence = returnResults(deltaBin, "Searching");
        finalCount = countBsearch;
        finalDelta = deltaBin + deltaQ;
        showResults(finalCount, finalDelta);
        System.out.println(sortingSentence);
        System.out.println(searchingSentence);

    }

    private static int binarySearch(String[] sortedQArray,String elem, int left, int right) {

        while (left <= right) {
            int mid = left + (right - left) / 2; // the index of the middle element

            if (elem == sortedQArray[mid]) {
                return mid; // the element is found, return its index
            } else if (elem.compareTo(sortedQArray[mid]) < 0) {
                right = mid - 1; // go to the left subarray
            } else {
                left = mid + 1;  // go the the right subarray
            }
        }
        return -1; // the element is not found
    }
    private static String[] quickSort(String[] inputDirectoryArray, int left, int right) {

        if (left < right) {
            int pivotIndex = partition(inputDirectoryArray, left, right); // the pivot is already on its place
            quickSort(inputDirectoryArray, left, pivotIndex - 1);  // sort the left subarray
            quickSort(inputDirectoryArray, pivotIndex + 1, right); // sort the right subarray
        }

        return inputDirectoryArray;
    }

    private static int partition(String[] array, int left, int right) {
        String pivot = array[right];  // choose the rightmost element as the pivot
        int partitionIndex = left; // the first element greater than the pivot

        /* move large values into the right side of the array */
        for (int i = left; i < right; i++) {
            if (array[i].compareTo(pivot) <= 0) { // may be used '<' as well
                swap(array, i, partitionIndex);
                partitionIndex++;
            }
        }

        swap(array, partitionIndex, right); // put the pivot on a suitable position

        return partitionIndex;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    private static String[] getArray(File inputFile, int size) {
        scanFile(inputFile);
        String[] inputArray = new String[size];
        int i=0;
        while(sc.hasNextLine()){
            inputArray[i] = sc.nextLine();
            i++;
        }
        sc.close();
        return inputArray;
    }

 /*   private static void simplestSearch(File dir, String names) {
        scanFile(dir);
        int count = 0;
        System.out.println("Start searching (linear search)...");
        long start = System.currentTimeMillis();
        while (sc.hasNextLine()) {
            if (sc.nextLine().contains(names)) {
                count++;
            }
        }
        sc.close();
        long end = System.currentTimeMillis();
        showResults(count, start, end);
    }*/
 private static int simplestSearch(String[] dir, String name) {
     int l=dir.length;
     for(int i=0; i<l; i++){
         if(dir[i].contains(name)){
             return i;
         }
     }
     return -1;
 }

    private static void scanFile(File f) {
        try {
            sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void showResults(int count,  long delta) {
        int minutes = (int) (delta/60000);
        int seconds = (int) ((delta%60000)/1000);
        int milliseconds = (int) (delta%1000);
        System.out.println("Found "+count+" / 500 entries. Time taken: "+minutes+" min. "+seconds+" sec. "+milliseconds+" ms.");
    }
    private static String returnResults(long delta, String action) {//Sorting or Searching
        int minutes = (int) (delta/60000);
        int seconds = (int) ((delta%60000)/1000);
        int milliseconds = (int) (delta%1000);
        return action+" time: "+minutes+" min. "+seconds+" sec. "+milliseconds+" ms.";
    }

    private static String[] bubbleSort(String[] arr, long timereference) {
       int l = arr.length;
       long beg = System.currentTimeMillis();
        for (int i = 0; i < l - 1; i++) {
            for (int j = 0; j < l - i - 1; j++) {
                /* if a pair of adjacent elements has the wrong order it swaps them */
                if (arr[j].compareTo(arr[j+1]) > 0) {
                    swap(arr, j, j+1);
                }
            }
            long inter = System.currentTimeMillis();
            if(inter - beg > 10 * timereference){
                break;
            }
        }

        return arr;
    }
    private static void swap(String[] array, int i, int j) {
        String temp = new String(array[i]);
        array[i] = array[j];
        array[j] = temp;
    }

    public static int jumpSearch(String[] array, String target) {

        int currentRight = 0; // right border of the current block
        int prevRight = 0; // right border of the previous block

        /* If array is empty, the element is not found */
        if (array.length == 0) {
            return -1;
        }

        /* Check the first element */
        if (array[currentRight].equals(target)) {
            return 0;
        }

        /* Calculating the jump length over array elements */
        int jumpLength = (int) Math.sqrt(array.length);

        /* Finding a block where the element may be present */
        while (currentRight < array.length - 1) {

            /* Calculating the right border of the following block */
            currentRight = Math.min(array.length - 1, currentRight + jumpLength);

            if (array[currentRight].compareTo(target) >=0) {
                break; // Found a block that may contain the target element
            }

            prevRight = currentRight; // update the previous right block border
        }

        /* If the last block is reached and it cannot contain the target value => not found */
        if (currentRight == array.length - 1 && target.compareTo(array[currentRight]) >0) {
            return -1;
        }

        /* Doing linear search in the found block */
        return backwardSearch(array, target, prevRight, currentRight);
    }

    public static int backwardSearch(String[] array, String target, int leftExcl, int rightIncl) {
        for (int i = rightIncl; i > leftExcl; i--) {
            if (array[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }
}
