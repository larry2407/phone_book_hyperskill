import java.util.*;

public class Main {

    public static void moveThePivot(int[] array, int pivotIndex) {
        int l = array.length;
        List<Integer> lowList = new ArrayList<>();
        List<Integer> highList = new ArrayList<>();
        List<Integer> pivotList = new ArrayList<>();

        for(int i=0; i<l; i++){
            if(array[i] == array[pivotIndex] ){
                pivotList.add(array[i]);
            }else if(array[i] < array[pivotIndex]){
                lowList.add(array[i]);
            }else{
                highList.add(array[i]);
            }
        }
        int p = pivotList.size();
        int lo = lowList.size();
        int h = highList.size();
        for(int i=0; i<lo; i++){
            Arrays.fill(array, i, i+1, lowList.get(i));
        }
        for(int i=0; i<p; i++){
            Arrays.fill(array, lo + i, lo + i + 1, pivotList.get(i));
        }
        for(int i=0; i<h; i++){
            Arrays.fill(array, lo + p + i, lo + p + i + 1, highList.get(i));
        }

        pivotIndex = lo;

    }

    /*private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }*/
    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int pivotIndex = scanner.nextInt();
        moveThePivot(array, pivotIndex);
        Arrays.stream(array).forEach(e -> System.out.print(e + " "));
    }
}
