package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class QuickSortPerformanceTest {

    private QuickSort quickSort;

    @Before
    public void setUpClass() {
        quickSort = new QuickSort();
    }

    @Test
    public void quickSortPerformanceConduciveNumbers() {

        //given
        int maxNumber = 25000;
        int startingNumber = 1;
        int numbersDelay = 1;
        int howManyTests = 10;
        long start;
        long finish;
        long time;
        double[] nums;

        //when
        for (int i = startingNumber; i <= maxNumber; i += numbersDelay) {
            time = 0;
            for (int j = 0; j < howManyTests; j++) {
                nums = getConduciveNumbersQuickSort(i);
                start = System.nanoTime();
                quickSort.sort(nums);
                finish = System.nanoTime();
                time += finish - start;
            }
            System.out.println(" " + time / howManyTests);

            if (i == 100) {
                numbersDelay = 10;
            }
            if (i == 1000) {
                numbersDelay = 100;
            }
            if (i == 3000) {
                numbersDelay = 1000;
            }
            if (i == 10000) {
                numbersDelay = 5000;
            }
        }
    }

    @Test
    public void quickSortPerformanceUnfavorableNumbers() {

        //given
        int maxNumber = 25000;
        int startingNumber = 1;
        int numbersDelay = 1;
        int howManyTests = 10;
        long start;
        long finish;
        long time;
        double[] nums;

        //when
        for (int i = startingNumber; i <= maxNumber; i += numbersDelay) {
            time = 0;
            for (int j = 0; j < howManyTests; j++) {
                nums = getUnfavorableNumbersQuickSort(i);
                start = System.nanoTime();
                quickSort.sort(nums);
                finish = System.nanoTime();
                time += finish - start;
            }
            System.out.println(" " + time / howManyTests);

            if (i == 100) {
                numbersDelay = 10;
            }
            if (i == 1000) {
                numbersDelay = 100;
            }
            if (i == 3000) {
                numbersDelay = 1000;
            }
            if (i == 10000) {
                numbersDelay = 5000;
            }
        }
    }

    @Test
    public void quickSortPerformanceRandomNumbers() {

        //given
        int maxNumber = 25000;
        int startingNumber = 1;
        int numbersDelay = 1;
        int howManyTests = 10;
        long start;
        long finish;
        long time;
        double[] nums;

        //when
        for (int i = startingNumber; i <= maxNumber; i += numbersDelay) {
            time = 0;
            for (int j = 0; j < howManyTests; j++) {
                nums = getRandomNumbers(i);
                start = System.nanoTime();
                quickSort.sort(nums);
                finish = System.nanoTime();
                time += finish - start;
            }
            System.out.println(" " + time / howManyTests);

            if (i == 100) {
                numbersDelay = 10;
            }
            if (i == 1000) {
                numbersDelay = 100;
            }
            if (i == 3000) {
                numbersDelay = 1000;
            }
            if (i == 10000) {
                numbersDelay = 5000;
            }
        }
    }

    public double[] getConduciveNumbersQuickSort(int howManyNumbers) {
        double[] nums = new double[howManyNumbers];
        setMiddleValueMax(nums, 0, howManyNumbers - 1, howManyNumbers, howManyNumbers);
        return nums;
    }

    private int setMiddleValueMax(double[] nums, int left, int right, int value, int howManyNumbers) {

        if (howManyNumbers == 1) {
            nums[left] = value--;
            return value;
        }
        if (howManyNumbers == 2) {
            nums[right] = value--;
            nums[left] = value--;
            return value;
        }

        int p = howManyNumbers / 2;
        p += left;
        nums[p] = value--;
        value = setMiddleValueMax(nums, left, p - 1, value, p - left);
        value = setMiddleValueMax(nums, p + 1, right, value, right - p);

        return value;
    }

    private double[] getUnfavorableNumbersQuickSort(int howManyNumbers) {
        double[] nums = new double[howManyNumbers];
        for (int i = 0; i < howManyNumbers; i++) {
            nums[i] = i + .0;
        }
        return nums;
    }

    private double[] getRandomNumbers(int howManyNumbers) {
        int seed = 1;
        double[] nums = new double[howManyNumbers];
        Random random = new Random(seed);
        for (int i = 0; i < howManyNumbers; i++) {
            nums[i] = random.nextDouble();
        }
        return nums;
    }
}
