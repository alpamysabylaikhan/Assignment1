import java.util.Arrays;
import java.util.Random;

public class Main {

    private static final Random rand = new Random();

    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        while (low < high) {
            // Random pivot
            int pivotIndex = low + rand.nextInt(high - low + 1);
            int pivotNew = partition(arr, low, high, pivotIndex);

            // Always recurse on the smaller partition
            if (pivotNew - low < high - pivotNew) {
                quickSort(arr, low, pivotNew - 1);
                low = pivotNew + 1; // tail recursion elimination
            } else {
                quickSort(arr, pivotNew + 1, high);
                high = pivotNew - 1;
            }
        }
    }

    private static int partition(int[] arr, int low, int high, int pivotIndex) {
        int pivot = arr[pivotIndex];
        swap(arr, pivotIndex, high);
        int storeIndex = low;
        for (int i = low; i < high; i++) {
            if (arr[i] < pivot) {
                swap(arr, i, storeIndex);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, high);
        return storeIndex;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[][] testArrays = {
                {5, 2, 9, 1, 5, 6},
                {3, 0, -1, 8, 7, 2, 4, 10},
                {10, 9, 8, 7, 6, 5, 4, 3, 2, 1},
                {5, 5, 5, 5, 5, 5},
                new Random().ints(15, 0, 100).toArray()
        };

        for (int[] test : testArrays) {
            System.out.println("Original: " + Arrays.toString(test));
            sort(test);
            System.out.println("Sorted:   " + Arrays.toString(test));
            System.out.println();
        }
    }
}
