import java.util.*;
public class selectionSort
{
public static void selectionSort(int[] arr) {
    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {
        int minIndex = i;
        for (int j = i + 1; j < n; j++) {
            // Greedy algorithm to find minimum element
            if (arr[j] < arr[minIndex]) {
                minIndex = j;
            }
        }
        // Swap minimum element with current element
        int temp = arr[i];
        arr[i] = arr[minIndex];
        arr[minIndex] = temp;
    }
}
public static void main(String[] args) {
    int[] arr = {4, 2, 9, 3, 6};
    System.out.println("Unsorted array: " + Arrays.toString(arr));
    selectionSort(arr);
    System.out.println("Sorted array: " + Arrays.toString(arr));
}

}




