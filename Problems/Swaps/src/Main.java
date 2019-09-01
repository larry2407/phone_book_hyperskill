import java.util.*;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String[] arrStr = sc.nextLine().trim().split(" ");
        int l = arrStr.length;
        int[] arrInt = new int[l];
        for(int i=0; i<l; i++){
            arrInt[i] = Integer.parseInt(arrStr[i]);
        }
        System.out.println(bubbleSortSwaps(arrInt));
    }
    public static int[] bubbleSort(int[] array) {

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                /* if a pair of adjacent elements has the wrong order it swaps them */
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

        return array;
    }
    public static int bubbleSortSwaps(int[] array) {
         int swapCounts = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                /* if a pair of adjacent elements has the wrong order it swaps them */
                if (array[j] > array[j + 1]) {
                    swapCounts++;
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

        return swapCounts;
    }
}