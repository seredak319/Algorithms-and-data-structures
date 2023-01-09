package pl.edu.pw.ee;

import org.junit.Test;

public class PrimAlgorithmTest {


    @Test
    public void shouldCorrectlyFind_sth(){
        //given
        String path = "src/main/resources/correct_small_data_own.txt";
        PrimAlgorithm primAlgorithm = new PrimAlgorithm();
        
        //when
        String result = primAlgorithm.findMST(path);
        
        //then
        System.out.println(result);
                
    }
}
