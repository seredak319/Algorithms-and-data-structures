package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

public class QuickSortTest {

    QuickSort quickSort;

    @Before
    public void setUp() {
        quickSort = new QuickSort();
    }

    @Test(expected = IllegalArgumentException.class)
    public void parametersNullCheck() {

        //when
        quickSort.sort(null);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyArrayTest() {

        //given
        double[] nums = new double[0];

        //when
        quickSort.sort(nums);

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
        quickSort.sort(nums);

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
        quickSort.sort(nums);

        //then
        assertArrayEquals(nums, goodResult, 0);
    }

    @Test
    public void sortedArrayTest() {

        //given
        double[] nums = new double[]{1, 2, 3, 4, 5};
        double[] goodResult = new double[]{1, 2, 3, 4, 5};

        //when
        quickSort.sort(nums);

        //then
        assertArrayEquals(nums, goodResult, 0);
    }

    @Test
    public void randomNumbersTest() {

        //given
        double[] nums = new double[]{4, 3, 8, 7, 1, 100, -21, 42, 0.42, 6, 5, 6};
        double[] goodResult = new double[]{-21, 0.42, 1, 3, 4, 5, 6, 6, 7, 8, 42, 100};

        //when
        quickSort.sort(nums);

        //then
        assertArrayEquals(nums, goodResult, 0);
    }

    @Test
    public void unsortedArrayTest() {

        //given
        double[] nums = new double[]{5, 4, 3, 2, 1};
        double[] goodResult = new double[]{1, 2, 3, 4, 5};

        //when
        quickSort.sort(nums);

        //then
        assertArrayEquals(nums, goodResult, 0);
    }

    @Test
    public void allNumbersInArrayTheSameTest() {

        //given
        double[] nums = new double[]{5, 5, 5, 5, 5};
        double[] goodResult = new double[]{5, 5, 5, 5, 5};

        //when
        quickSort.sort(nums);

        //then
        assertArrayEquals(nums, goodResult, 0);
    }

    @Test
    public void arrayWithNegativeDoubleNumbers() {

        //given
        double[] nums = new double[]{-1.23, 3.21, -53.322, 0.002, -69.69, -100.1};
        double[] goodResult = new double[]{-100.1, -69.69, -53.322, -1.23, 0.002, 3.21};

        //when
        quickSort.sort(nums);

        //then
        assertArrayEquals(nums, goodResult, 0);
    }

    @Test
    public void randomWithSeedTest() {

        //given
        int initSize = 100;
        int seed = 2;
        double[] nums = new double[initSize];
        double[] goodResult = new double[initSize];
        Random random = new Random(seed);
        SelectionSort selectionSort = new SelectionSort();
        selectionSort.sort(goodResult);
        for (int i = 0; i < initSize; i++) {
            nums[i] = random.nextDouble();
        }

        //when
        quickSort.sort(nums);

        //then
        assertArrayEquals(nums, goodResult, 1);
    }
}
