import java.util.*;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String[] arrStr = sc.nextLine().trim().split("\\s+");
        int l = arrStr.length;
        int[] arrInt = new int[l];
        for(int i=0; i<l; i++){
            arrInt[i] = Integer.parseInt(arrStr[i]);
        }
        System.out.println(bubbleSortSwaps(arrInt));
    }
    //descending
    public static int[] bubbleSort(int[] array) {

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                /* if a pair of adjacent elements has the wrong order it swaps them */
                if (array[j] < array[j + 1]) {
                    swap(array, j, j+1);
                }
            }
        }

        return array;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    //descending
    public static int bubbleSortSwaps(int[] array) {
        int swapCounts = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                /* if a pair of adjacent elements has the wrong order it swaps them */
                if (array[j] < array[j + 1]) {
                    swapCounts++;
                    swap(array, j, j+1);
                }
            }
        }

        return swapCounts;
    }
}