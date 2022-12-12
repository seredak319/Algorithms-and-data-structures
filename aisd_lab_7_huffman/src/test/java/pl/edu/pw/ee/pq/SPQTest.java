package pl.edu.pw.ee.pq;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class SPQTest {

    private final double delta = 0.001;

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_BadInitializationSize() {
        //when
        new SPQ(-3);

        //then
        assert false;

    }

    @Test(expected = NullPointerException.class)
    public void should_ThrowException_When_NullValuePut() {
        //when
        SPQ spq = new SPQ();
        spq.put(null);

        //then
        assert false;
    }

    @Test(expected = NullPointerException.class)
    public void should_ThrowException_When_PopEmptyElement() {
        //when
        SPQ spq = new SPQ();
        spq.pop();

        //then
        assert false;
    }

    @Test
    public void should_CorrectlyResize_AndGetSize_WhenValuesValid() {
        //given
        SPQ spq = new SPQ(2);

        //when
        spq.put(new SNode('c', 4));
        spq.put(new SNode('c', 2));
        spq.put(new SNode('c', 2));
        spq.put(new SNode('c', 1));
        int n = spq.getSize();

        //then
        Assert.assertEquals(4L, n);
    }

    @Test
    public void should_CorrectlyPutAndPop_WhenValuesValid() {
        //given
        SPQ spq = new SPQ();
        SNode sn = new SNode('c', 5);
        spq.put(sn);

        //when
        int result = sn.getFreq();

        //then
        Assert.assertEquals(5.0, result, delta);
    }

    @Test
    public void should_CorrectlyPutAndPop_WhenManyValidValues() {
        //given
        SPQ spq = new SPQ();
        int[] expected = new int[]{5, 18, 2, 1, 5};
        Arrays.sort(expected);
        SNode sn1 = new SNode('c', 5);
        SNode sn2 = new SNode('c', 18);
        SNode sn3 = new SNode('c', 2);
        SNode sn4 = new SNode('c', 1);
        SNode sn5 = new SNode('c', 5);

        //when
        spq.put(sn1);
        spq.put(sn2);
        spq.put(sn3);
        spq.put(sn4);
        spq.put(sn5);

        //then
        for (int i = 0; i < 5; ++i) {
            int val = spq.pop().getFreq();
            Assert.assertEquals(expected[i], val, delta);
        }
    }
}
