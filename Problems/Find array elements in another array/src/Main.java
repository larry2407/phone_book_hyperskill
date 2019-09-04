import java.util.Scanner;

class Main {

  private static Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    int n = Integer.parseInt(sc.nextLine().trim());
    int[] reference = new int[n];
    String [] line2 = sc.nextLine().trim().split(" \\s+");
    for(int i=0; i<n; i++){
      reference[i] =Integer.parseInt(line2[i]);
    }
/*
    int n = 5;
    int[] reference = new int[]{1, 5, 8, 12, 13};
    int m= 5;
    int[] searchFrom = new int[]{8, 1, 23, 1, 11};
*/
    int m = Integer.parseInt(sc.nextLine().trim());
    int[] searchFrom = new int[m];
    String [] line3 = sc.nextLine().trim().split(" \\s+");
    for(int i=0; i<m; i++){
      searchFrom[i] =Integer.parseInt(line3[i]);
    }

    int res = 0;
    for(int j=0; j<m; j++) {
      res = binarySearch(reference, searchFrom[j], 0, n - 1);
      res = res > -1 ? res + 1 : res;
      System.out.print(res + " ");
    }

  }

  public static int binarySearch(int[] array, int elem, int left, int right) {

    while (left <= right) {
      int mid = left + (right - left) / 2; // the index of the middle element
      if (elem == array[mid]) {
        return mid; // the element is found, return its index
      } else if (elem < array[mid]) {
        right = mid - 1; // go to the left subarray
      } else {
        left = mid + 1;  // go the the right subarray
      }
    }
    return -1; // the element is not found
  }
}
