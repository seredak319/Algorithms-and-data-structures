package pl.sereda;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;

public class HeapSortPerformanceTest {

    private static HeapSort heapSort;

    @BeforeClass
    public static void setUpClass() {
        heapSort = new HeapSort();
    }

    @Test
    public void heapSortPerformanceConduciveNumbers() {

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
                nums = getConduciveNumbersHeapSort(i);
                start = System.nanoTime();
                heapSort.sort(nums);
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
    public void heapSortPerformanceUnfavorableNumbers() {

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
                nums = getUnfavorableNumbersHeapSort(i);
                start = System.nanoTime();
                heapSort.sort(nums);
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
    public void heapSortPerformanceRandomNumbers() {

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
                heapSort.sort(nums);
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

    private double[] getConduciveNumbersHeapSort(int howManyNumbers) {
        double[] nums = new double[howManyNumbers];
        for (int i = 0; i < howManyNumbers; i++) {
            nums[i] = 5;
        }
        return nums;
    }

    private double[] getUnfavorableNumbersHeapSort(int howManyNumbers) {
        double[] nums = new double[howManyNumbers];
        nums[0] = 0;
        for (int i = 0; i < (howManyNumbers - 1) / 2; i++) {
            if (2 * i + 1 < howManyNumbers) {
                nums[2 * i + 1] = 10 * i + .0 + 6;
            }
            if (2 * i + 2 < howManyNumbers) {
                nums[2 * i + 2] = 10 * i + .0 + 7;
            }
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
