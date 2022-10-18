package pl.edu.pw.ee;

import java.util.Arrays;
import pl.edu.pw.ee.services.Sorting;

public class RefAlgorithm implements Sorting {

    @Override
    public void sort(double[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Nums array cannot be null");
        }

        if (nums.length == 0) {
            throw new IllegalArgumentException("Nums have to contains number");
        }
        Arrays.sort(nums);
    }

}
