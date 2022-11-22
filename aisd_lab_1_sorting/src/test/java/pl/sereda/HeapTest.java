package pl.sereda;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Random;

public class HeapTest {

    private Heap<Double> heap;
    private static RefAlgorithm refAlgorithm;

    @BeforeClass
    public static void setUpClass() {
        refAlgorithm = new RefAlgorithm();
    }

    @Test(expected = IllegalArgumentException.class)
    public void heapPutWithNullListGivenTest() {

        //given
        heap = new Heap<>(null);

        //when
        heap.put(69.420);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void heapPopWithNullListGivenTest() {

        //given
        heap = new Heap<>(null);

        //when
        heap.pop();

        //then
        assert false;
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void heapPopWithEmptyListTest() {

        //given
        ArrayList<Double> dataList = new ArrayList<>();
        heap = new Heap<>(dataList);

        //when
        heap.pop();

        //then
        assert false;
    }

    @Test
    public void checkIfPopGetsPutValue() {

        //given
        ArrayList<Double> dataList = new ArrayList<>();
        heap = new Heap<>(dataList);
        double expected = 2137.69;
        double result;

        //when
        heap.put(expected);
        result = heap.pop();

        //then
        Assert.assertEquals(result, expected, 0);
    }

    @Test
    public void checkIfPopGetsMaxValue() {

        //given
        ArrayList<Double> dataList = new ArrayList<>();
        heap = new Heap<>(dataList);
        int initValue = 1;
        double[] data = new double[initValue];
        double expected = makeRandomNumbers(data, initValue);
        double result;

        //when
        putData(data);
        result = heap.pop();

        //then
        Assert.assertEquals(result, expected, 0);
    }

    @Test
    public void checkIfPopsGetAllMaxValues() {

        //given
        ArrayList<Double> dataList = new ArrayList<>();
        heap = new Heap<>(dataList);
        int initValue = 100;
        double[] data = new double[initValue];
        double[] dataResult = new double[initValue];
        makeRandomNumbers(data, initValue);
        makeRandomNumbers(dataResult, initValue);
        refAlgorithm.sort(dataResult);

        //when
        putData(data);

        //then
        for (int i = initValue - 1; i >= 0; i--) {
            Double temp = heap.pop();
            assert temp.compareTo(dataResult[i]) == 0;
        }
        assert true;
    }

    @Test
    public void checkIfPutAllTheSameNumbersIsOkay() {

        //given
        ArrayList<Double> dataList = new ArrayList<>();
        heap = new Heap<>(dataList);
        int initValue = 100;
        double expected = 5;

        //when
        for (int i = 0; i < initValue; i++) {
            heap.put(5.0);
        }

        //then
        for (int i = 0; i < initValue; i++) {
            assert heap.pop().compareTo(expected) == 0;
        }
        assert true;
    }

    @Test
    public void heapWithUnsortedListInitiated() {

        //given
        ArrayList<Double> dataList = new ArrayList<>();
        double expected = 69.69;
        double result;
        dataList.add(-32.2);
        dataList.add(expected);
        heap = new Heap<>(dataList);

        //when
        result = heap.pop();

        //then
        Assert.assertEquals(result, expected, 0);
    }

    private void putData(double[] data) {
        for (double datum : data) {
            heap.put(datum);
        }
    }

    private double makeRandomNumbers(double[] data, int howManyNumbers) {
        double maxValue = -1;
        int seed = 1;
        Random random = new Random(seed);
        for (int i = 0; i < howManyNumbers; i++) {
            data[i] = random.nextDouble();
            if (data[i] > maxValue) {
                maxValue = data[i];
            }
        }
        return maxValue;
    }
}
