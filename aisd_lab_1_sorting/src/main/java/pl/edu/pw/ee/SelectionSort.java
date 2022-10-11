package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class SelectionSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("nums cant be null!");
        }

        if (nums.length == 0) {
                    System.out.println("kisiel");
            return;
    
        }

        int n = nums.length;
        int minValId;

        for (int i = 0; i < n; i++) {
            minValId = i;

            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[minValId]) {
                    minValId = j;
                }
            }
        swap(nums,i,minValId);
        }

    }

    
    private void swap(double[] nums, int firstId, int secondId){
        if(firstId != secondId){
            double firstVal = nums[firstId];
            nums[firstId] = nums[secondId];
            nums[secondId] = firstVal;
        }
    }
    
}
