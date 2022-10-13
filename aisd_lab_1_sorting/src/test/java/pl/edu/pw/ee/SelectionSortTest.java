package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class SelectionSortTest {

    SelectionSort selectionSort;

    @Before
    public void setUp() {
        selectionSort = new SelectionSort();
    }

    @Test(expected = IllegalArgumentException.class)
    public void parametersNullCheck() {

        //given
        double[] nums = null;

        //when
        selectionSort.sort(nums);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyArrayTest() {

        //given
        double[] nums = new double[0];

        //when
        selectionSort.sort(nums);

        //then
        assert false;
    }

    @Test
    public void oneNumberArray() {

        //given
        double[] nums = new double[1];
        nums[0] = 1;
        double[] goodResult = new double[1];
        goodResult[0] = 1;

        //when
        selectionSort.sort(nums);

        //then
        assertArrayEquals(nums, goodResult, 0);
    }

    @Test
    public void notAllDeclaredArrayContainsNumbers() {

        //given
        double[] nums = new double[5];
        nums[0] = 1;
        nums[1] = 2;
        nums[3] = 4;
        double[] goodResult = new double[]{0, 0, 1, 2, 4};

        //when
        selectionSort.sort(nums);

        //then
        assertArrayEquals(nums, goodResult, 0);
    }

    @Test
    public void sortedArrayTest() {

        //given
        double[] nums = new double[]{1, 2, 3, 4, 5};
        double[] goodResult = new double[]{1, 2, 3, 4, 5};

        //when
        selectionSort.sort(nums);

        //then
        assertArrayEquals(nums, goodResult, 0);
    }

    @Test
    public void unsortedArrayTest() {

        //given
        double[] nums = new double[]{5, 4, 3, 2, 1};
        double[] goodResult = new double[]{1, 2, 3, 4, 5};

        //when
        selectionSort.sort(nums);

        //then
        assertArrayEquals(nums, goodResult, 0);
    }

    @Test
    public void allNumbersInArrayTheSameTest() {

        //given
        double[] nums = new double[]{5, 5, 5, 5, 5};
        double[] goodResult = new double[]{5, 5, 5, 5, 5};

        //when
        selectionSort.sort(nums);

        //then
        assertArrayEquals(nums, goodResult, 0);
    }

    @Test
    public void arrayInitializedButWithOutNumbersTest() {

        //given
        double[] nums = new double[3];
        double[] goodResult = new double[]{0, 0, 0};

        //when
        selectionSort.sort(nums);

        //then
        assertArrayEquals(nums, goodResult, 0);
    }

    @Test
    public void arrayWithNegativeDoubleNumbers() {

        //given
        double[] nums = new double[]{-1.23, 3.21, -53.322, 0.002, -69.69, -100.1};
        double[] goodResult = new double[]{-100.1, -69.69, -53.322, -1.23, 0.002, 3.21};

        //when
        selectionSort.sort(nums);

        //then
        assertArrayEquals(nums, goodResult, 0);
    }

    @Test
    public void hugeNumbersTest() {

        //given
        double[] nums = new double[]{Double.MAX_VALUE, Double.MIN_VALUE};
        double[] goodResult = new double[]{Double.MIN_VALUE, Double.MAX_VALUE};

        //when
        selectionSort.sort(nums);

        //then
        assertArrayEquals(nums, goodResult, 0);
    }

    @Test
    public void randomWithSeedTest(){

        //given
        int initSize = 100;
        int seed = 2;
        double[] nums = new double[initSize];
        double[] goodResult = new double[initSize];
        Random random = new Random(seed);
        QuickSort quickSort = new QuickSort();
        quickSort.sort(goodResult);
        for (int i =0; i<initSize; i++){
            nums[i]=random.nextDouble();
        }

        //when
        selectionSort.sort(nums);

        //then
        assertArrayEquals(nums,goodResult,1);
    }
}
