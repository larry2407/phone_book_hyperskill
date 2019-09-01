import java.util.*;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int arrLength = sc.nextInt();
        int[] arr = new int[arrLength];
       // int[] arr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        for(int i=0; i<arrLength; i++){
            arr[i] = sc.nextInt();
        }
        int val = sc.nextInt();
        System.out.println(jumpSearch(arr,val)[1]);
    }
    //transform the implementation in order to return the number of comparisons with the index result
    public static int[] jumpSearch(int[] array, int target) {

        int currentRight = 0; // right border of the current block
        int prevRight = 0; // right border of the previous block
        int numberOfComparisons = 0;

        /* If array is empty, the element is not found */
        if (array.length == 0) {
            //return -1;
            return new int[]{-1, 0};
        }

        /* Check the first element */
        numberOfComparisons++;
        if (array[currentRight] == target) {
            //return 0;
            return new int[]{0, numberOfComparisons};
        }

        /* Calculating the jump length over array elements */
        int jumpLength = (int) Math.sqrt(array.length);

        /* Finding a block where the element may be present */
        while (currentRight < array.length - 1) {

            /* Calculating the right border of the following block */
            currentRight = Math.min(array.length - 1, currentRight + jumpLength);
            numberOfComparisons++;
            if (array[currentRight] >= target) {
                break; // Found a block that may contain the target element
            }

            prevRight = currentRight; // update the previous right block border
        }

        /* If the last block is reached and it cannot contain the target value => not found */
        if (currentRight == array.length - 1 && target > array[currentRight]) {
            //return -1;
            return new int[]{-1, numberOfComparisons};

        }

        /* Doing linear search in the found block */
        return backwardSearch(array, target, prevRight, currentRight, numberOfComparisons);
    }

    public static int[] backwardSearch(int[] array, int target, int leftExcl, int rightIncl, int numOfComparisons) {
        for (int i = rightIncl; i > leftExcl; i--) {
            if(i != rightIncl){
                numOfComparisons++;
            }
            if(array[i]<target){
                break;
            }
            else if (array[i] == target) {
                //return i;
                return new int[]{i, numOfComparisons};
            }
        }
        //return -1;
        return new int[]{-1, numOfComparisons};

    }
}