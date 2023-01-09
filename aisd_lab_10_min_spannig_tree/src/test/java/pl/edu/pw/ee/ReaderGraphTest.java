package pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Test;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class ReaderGraphTest {

    @Test (expected = NullPointerException.class)
    public void should_ThrowException_WhenEmptyPath(){
        //given
        ReaderGraph readerGraph = new ReaderGraph();

        //when
        readerGraph.readFile(null);

        //then
        assert false;
    }

    @Test
    public void should_CorrectlyRead_CorrectFile_ProperParentNodeNames(){
        //given
        String path = "src/main/resources/correct_small_data.txt";
        ReaderGraph readerGraph = new ReaderGraph();

        //when
        LinkedList<Node>[] matrix = readerGraph.readFile(path);

        //then
        Assert.assertEquals("A",matrix[0].getFirst().getName());
        Assert.assertEquals("B",matrix[1].getFirst().getName());
        Assert.assertEquals("C",matrix[2].getFirst().getName());
        Assert.assertEquals("D",matrix[3].getFirst().getName());
        Assert.assertEquals("E",matrix[4].getFirst().getName());
    }

    @Test
    public void should_CorrectlyRead_CorrectFile_ProperParentNodeChildren(){
        //given
        String path = "src/main/resources/correct_small_data.txt";
        ReaderGraph readerGraph = new ReaderGraph();

        //when
        LinkedList<Node>[] matrix = readerGraph.readFile(path);

        //then
        Assert.assertEquals("A",matrix[0].get(0).getName());
        Assert.assertEquals("B",matrix[0].get(1).getName());
        Assert.assertEquals("C",matrix[0].get(2).getName());
        Assert.assertEquals("D",matrix[0].get(3).getName());
        Assert.assertEquals("B",matrix[1].get(0).getName());
        Assert.assertEquals("A",matrix[1].get(1).getName());
        Assert.assertEquals("C",matrix[1].get(2).getName());
    }

    @Test
    public void should_CorrectlyRead_CorrectFile_ProperParentChildrenWeights(){
        //given
        String path = "src/main/resources/correct_small_data.txt";
        ReaderGraph readerGraph = new ReaderGraph();

        //when
        LinkedList<Node>[] matrix = readerGraph.readFile(path);

        //then
        Assert.assertEquals(3,matrix[0].get(1).getValue());
        Assert.assertEquals(5,matrix[0].get(2).getValue());
        Assert.assertEquals(7,matrix[0].get(3).getValue());
        Assert.assertEquals(3,matrix[1].get(1).getValue());
        Assert.assertEquals(1,matrix[1].get(2).getValue());
    }

}
