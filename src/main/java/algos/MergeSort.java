import java.util.Arrays;

public class Main {

    private static final int CUTOFF = 10;

    public static void sort(int[] arr) {
        int[] buffer = new int[arr.length];
        mergeSort(arr, buffer, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int[] buffer, int left, int right) {
        if (right - left <= CUTOFF) {
            insertionSort(arr, left, right);
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSort(arr, buffer, left, mid);
        mergeSort(arr, buffer, mid + 1, right);

        if (arr[mid] <= arr[mid + 1]) {
            return;
        }

        merge(arr, buffer, left, mid, right);
    }

    private static void merge(int[] arr, int[] buffer, int left, int mid, int right) {
        System.arraycopy(arr, left, buffer, left, right - left + 1);

        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) {
            if (buffer[i] <= buffer[j]) {
                arr[k++] = buffer[i++];
            } else {
                arr[k++] = buffer[j++];
            }
        }

        while (i <= mid) {
            arr[k++] = buffer[i++];
        }
    }

    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[][] testArrays = {
                {5, 2, 9, 1, 5, 6},
                {3, 0, -1, 8, 7, 2, 4, 10},
                {10, 9, 8, 7, 6, 5, 4, 3, 2, 1}
        };

        for (int[] test : testArrays) {
            System.out.println("Original: " + Arrays.toString(test));
            sort(test);
            System.out.println("Sorted:   " + Arrays.toString(test));
            System.out.println();
        }
    }
}
