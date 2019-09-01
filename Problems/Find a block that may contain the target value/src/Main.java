import java.util.Scanner;

class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int l = sc.nextInt();
        int[] arr = new int[l];

        for (int i = 0; i < l; i++) {
            arr[i] = sc.nextInt();
        }
        int val = sc.nextInt();
        int[] block = identifyBlock(arr, val);
        System.out.println(block[0] == -1 ? block[0] : block[0] + " " + block[1]);
    }

    private static int[] identifyBlock(int[] arr, int val) {
        int[] result = new int[]{-1, -1};
        int lA = arr.length;
        int jumpLength = (int) Math.sqrt(lA);
        if (lA == 0 || arr[0] > val || arr[lA - 1] < val) {
            return result;
        }
        if (arr[0] == val) {
            result[0] = 0;
            result[1] = jumpLength - 1;
        } else {
            int i = 0;
            do {
                i += jumpLength;
            } while (i < lA && arr[i] < val);
            if (i >= lA) {
                int lLast = lA % jumpLength;
                if (lLast == 0) {
                    result[0] = -1;
                    result[1] = -1;
                } else {
                    result[0] = lA - lLast;
                    result[1] = lA - 1;
                }
            } else if (arr[i] >= val) {
                if (arr[i] > val || arr[i] == val && i >= 1 && arr[i - 1] < arr[i]) {
                    result[0] = arr[i - 1] >= val ? jumpLength * ((i-1) / jumpLength) : jumpLength * (i / jumpLength);
                    result[1] = Math.min(result[0] + jumpLength - 1, lA-1);
                } else {
                    int k = 1;
                    while (arr[i - k] == arr[i]) {
                        k++;
                    }
                    int j = i - (k - 1);
                    result[0] = jumpLength * (j / jumpLength);
                    result[1] = Math.min(result[0] + jumpLength - 1, lA-1);
                }
            }
        }
        return result;
    }
}
/*ref solution
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final int n = scanner.nextInt();
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        final int target = scanner.nextInt();

        int left = 0;
        int right = 0;

        int blockSize = (int) Math.sqrt(array.length);

        while (right < array.length - 1) {
            right = Math.min(array.length, left + blockSize) - 1;

            if (array[right] >= target) {
                System.out.println(left + " " + right);
                return;
            }

            left = right + 1;
        }

        System.out.println("-1");
    }
}
 */