package pl.edu.pw.ee;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;

public class RefAlgorithmPerformanceTest {

    private static RefAlgorithm refAlgorithm;

    @BeforeClass
    public static void setUpClass() {
        refAlgorithm = new RefAlgorithm();
    }

    @Test
    public void refAlgorithmPerformanceConduciveNumbers() {

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
                nums = getConduciveNumbersRefAlgorithm(i);
                start = System.nanoTime();
                refAlgorithm.sort(nums);
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
    public void refAlgorithmPerformanceUnfavorableNumbers() {

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
                nums = getUnfavorableNumbersRefAlgorithm(i);
                start = System.nanoTime();
                refAlgorithm.sort(nums);
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
    public void refAlgorithmPerformanceRandomNumbers() {

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
                refAlgorithm.sort(nums);
                finish = System.nanoTime();
                time += finish - start;
            }
            System.out.println(" "+i + "  " + time / howManyTests);

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

    private double[] getConduciveNumbersRefAlgorithm(int howManyNumbers) {
        double[] nums = new double[howManyNumbers];
        for (int i = 0; i < howManyNumbers; i++) {
            nums[i] = i + .0;
        }
        return nums;
    }

    private double[] getUnfavorableNumbersRefAlgorithm(int howManyNumbers) {
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
