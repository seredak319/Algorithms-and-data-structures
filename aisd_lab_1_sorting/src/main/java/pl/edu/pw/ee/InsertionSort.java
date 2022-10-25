package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class InsertionSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Nums array cannot be null");
        }
        if (nums.length == 0) {
            throw new IllegalArgumentException("Nums have to contains number");
        }

        insertionSort(nums);
    }

    private void insertionSort(double[] data) {
        int n = data.length;
        for (int i = 1; i < n; i++) {
            int j = i - 1;
            while (j >= 0 && data[j + 1] < data[j]) {
                swap(data, j, j + 1);
                j--;
            }
        }
    }

    private void swap(double[] data, int firstId, int secondId) {
        if (firstId != secondId) {
            double firstValue = data[firstId];
            data[firstId] = data[secondId];
            data[secondId] = firstValue;
        }
    }
}
