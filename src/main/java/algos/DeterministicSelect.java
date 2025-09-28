import java.util.Arrays;

public class Main {

    public static int deterministicSelect(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k < 0 || k >= arr.length) {
            throw new IllegalArgumentException("Invalid input");
        }
        return select(arr, 0, arr.length - 1, k);
    }

    private static int select(int[] arr, int left, int right, int k) {
        while (true) {
            if (left == right) {
                return arr[left];
            }

            int pivotIndex = medianOfMedians(arr, left, right);
            pivotIndex = partition(arr, left, right, pivotIndex);

            if (k == pivotIndex) {
                return arr[k];
            } else if (k < pivotIndex) {
                right = pivotIndex - 1;
            } else {
                left = pivotIndex + 1;
            }
        }
    }

    private static int medianOfMedians(int[] arr, int left, int right) {
        int n = right - left + 1;
        if (n <= 5) {
            insertionSort(arr, left, right);
            return left + n / 2;
        }

        int numMedians = 0;
        for (int i = left; i <= right; i += 5) {
            int subRight = Math.min(i + 4, right);
            insertionSort(arr, i, subRight);
            int medianIndex = i + (subRight - i) / 2;
            swap(arr, left + numMedians, medianIndex);
            numMedians++;
        }
        return medianOfMedians(arr, left, left + numMedians - 1);
    }

    private static int partition(int[] arr, int left, int right, int pivotIndex) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, right);
        return storeIndex;
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
                {42},
                {7, 7, 7, 7, 7, 7}
        };

        int[] ks = {0, 2, 5};

        for (int[] test : testArrays) {
            System.out.println("Array: " + Arrays.toString(test));
            for (int k : ks) {
                if (k < test.length) {
                    int result = deterministicSelect(Arrays.copyOf(test, test.length), k);
                    System.out.println("k = " + k + " -> " + result);
                }
            }
            System.out.println();
        }
    }
}
