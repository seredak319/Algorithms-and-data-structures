package pl.edu.pw.ee;

import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

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
    public void emptyArrayTert(){
        //given
        double[] nums;
        nums = new double[4];
        
        //when
        selectionSort.sort(nums);
        
        //then
        assert false;
        
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void oneNumberArray(){
        //given
        double[] nums;
        nums = new double[1];
        nums[0] = 1;
        double [] goodResult = new double[1];
        goodResult[0] = 1;
        
        
        //when
        selectionSort.sort(nums);
        
        //then
        assert false;
    }
}
