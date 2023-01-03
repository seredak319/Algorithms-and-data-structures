package pl.edu.pw.ee;

import pl.edu.pw.ee.services.MinSpanningTree;

public class PrimAlgorithm implements MinSpanningTree {

    private Integer[][] matrix;

    private void test_Init() {
        matrix = new Integer[3][3];
        matrix[0][0] = 0;
        matrix[0][1] = 1;
        matrix[0][1] = 12;
        matrix[1][2] = 1;
        matrix[1][2] = 2;
        matrix[1][2] = 13;
        matrix[0][2] = 0;
        matrix[0][2] = 2;
        matrix[0][2] = 15;
    }

    public String findMST(String pathToFile) {
        // TODO
        test_Init();

        return null;
    }

}
