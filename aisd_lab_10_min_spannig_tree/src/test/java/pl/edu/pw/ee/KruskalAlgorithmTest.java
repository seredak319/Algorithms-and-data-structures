package pl.edu.pw.ee;

import org.junit.Test;

public class KruskalAlgorithmTest {

    @Test
    public void shouldCorrectlyFind_sth(){
        //given
        String path = "src/main/resources/correct_small_data_own.txt";
        KruskalAlgorithm kruskalAlgorithm = new KruskalAlgorithm();

        //when
        String result = kruskalAlgorithm.findMST(path);

        //then
        System.out.println(result);

    }
}
