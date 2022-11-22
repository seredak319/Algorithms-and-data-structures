package pl.sereda;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;

public class SelectionSortPerformanceTest {

    private static SelectionSort selectionSort;

    @BeforeClass
    public static void setUpClass() {
        selectionSort = new SelectionSort();
    }

    @Test
    public void selectionSortPerformanceConduciveNumbers() {

        //given
        int maxNumber = 25000;
        int startingNumber = 1;
        int numbersDelay = 1;
        int howManyTests = 3;
        long start;
        long finish;
        long time;
        double[] nums;

        //when
        for (int i = startingNumber; i <= maxNumber; i += numbersDelay) {
            time = 0;
            for (int j = 0; j < howManyTests; j++) {
                nums = getConduciveNumbersSelectionSort(i);
                start = System.nanoTime();
                selectionSort.sort(nums);
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
    public void selectionSortPerformanceUnfavorableNumbers() {

        //given
        int maxNumber = 25000;
        int startingNumber = 1;
        int numbersDelay = 1;
        int howManyTests = 3;
        long start;
        long finish;
        long time;
        double[] nums;

        //when
        for (int i = startingNumber; i <= maxNumber; i += numbersDelay) {
            time = 0;
            for (int j = 0; j < howManyTests; j++) {
                nums = getUnfavorableNumbersSelectionSort(i);
                start = System.nanoTime();
                selectionSort.sort(nums);
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
    public void selectionSortPerformanceRandomNumbers() {

        //given
        int maxNumber = 25000;
        int startingNumber = 1;
        int numbersDelay = 1;
        int howManyTests = 3;
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
                selectionSort.sort(nums);
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

    private double[] getConduciveNumbersSelectionSort(int howManyNumbers) {
        double[] nums = new double[howManyNumbers];
        for (int i = 0; i < howManyNumbers; i++) {
            nums[i] = i + .0;
        }
        return nums;
    }

    private double[] getUnfavorableNumbersSelectionSort(int howManyNumbers) {
        double[] nums = new double[howManyNumbers];
        for (int i = 0; i < howManyNumbers; i++) {
            nums[i] = howManyNumbers - i + .0;
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
