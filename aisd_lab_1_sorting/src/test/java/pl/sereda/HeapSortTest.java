package pl.sereda;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

public class HeapSortTest {

    private static HeapSort heapSort;
    private static RefAlgorithm refAlgorithm;

    @BeforeClass
    public static void setUpClass() {
        refAlgorithm = new RefAlgorithm();
    }

    @Before
    public void setUpBeforeTests() {
        heapSort = new HeapSort();
    }

    @Test(expected = IllegalArgumentException.class)
    public void parametersNullCheck() {

        //when
        heapSort.sort(null);

        //then
        assert false;
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void emptyArrayTest() {

        //given
        double[] nums = new double[0];

        //when
        heapSort.sort(nums);

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
        heapSort.sort(nums);

        //then
        assertArrayEquals(goodResult, nums, 0);
    }

    @Test
    public void sortedArrayTest() {

        //given
        double[] nums = new double[]{1, 2, 3, 4, 5};
        double[] goodResult = new double[]{1, 2, 3, 4, 5};

        //when
        heapSort.sort(nums);

        //then
        assertArrayEquals(goodResult, nums, 0);
    }

    @Test
    public void unsortedArrayTest() {

        //given
        double[] nums = new double[]{5, 4, 3, 2, 1};
        double[] goodResult = new double[]{1, 2, 3, 4, 5};

        //when
        heapSort.sort(nums);

        //then
        assertArrayEquals(goodResult, nums, 0);
    }

    @Test
    public void allNumbersInArrayTheSameTest() {

        //given
        double[] nums = new double[]{5, 5, 5, 5, 5};
        double[] goodResult = new double[]{5, 5, 5, 5, 5};

        //when
        heapSort.sort(nums);

        //then
        assertArrayEquals(goodResult, nums, 0);
    }

    @Test
    public void arrayWithNegativeDoubleNumbers() {

        //given
        double[] nums = new double[]{-1.23, 3.21, -53.322, 0.002, -69.69, -100.1};
        double[] goodResult = new double[]{-100.1, -69.69, -53.322, -1.23, 0.002, 3.21};

        //when
        heapSort.sort(nums);

        //then
        assertArrayEquals(nums, goodResult, 0);
    }

    @Test
    public void randomWithSeedTest() {

        //given
        int initValue = 1000;
        double[] nums = new double[initValue];
        double[] goodResult = new double[initValue];
        makeRandomNumbers(nums, initValue);
        makeRandomNumbers(goodResult, initValue);
        refAlgorithm.sort(goodResult);

        //when
        heapSort.sort(nums);

        //then
        assertArrayEquals(goodResult, nums, 0);
    }

    private void makeRandomNumbers(double[] data, int howManyNumbers) {
        int seed = 1;
        Random random = new Random(seed);
        for (int i = 0; i < howManyNumbers; i++) {
            data[i] = random.nextDouble();
        }
    }
}
