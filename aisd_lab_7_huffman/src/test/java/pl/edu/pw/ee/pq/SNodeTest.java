package pl.edu.pw.ee.pq;

import org.junit.Assert;
import org.junit.Test;

public class SNodeTest {

    private final double delta = 0.001;

    @Test(expected = NullPointerException.class)
    public void should_ThrowException_When_NullInitiation_FourArguments_RightChildIsNull() {
        //when
        new SNode('c', 2, null, new SNode('c', 4));

        //then
        assert false;

    }

    @Test(expected = NullPointerException.class)
    public void should_ThrowException_When_NullInitiation_FourArguments_LeftChildIsNull() {
        //when
        new SNode('c', 2, new SNode('c', 4), null);

        //then
        assert false;

    }

    @Test(expected = NullPointerException.class)
    public void should_ThrowException_When_NullInitiation_FourArguments_AllAreNulls() {
        //when
        new SNode('c', 3, null, null);

        //then
        assert false;

    }

    @Test
    public void should_ThrowException_When_GetChildren_AllAreNulls() {
        //when
        SNode sn = new SNode('c', 3);

        //then
        Assert.assertNull(sn.getRight());
        Assert.assertNull(sn.getLeft());
    }

    @Test(expected = NullPointerException.class)
    public void should_ThrowException_When_SetChildIsNull_Right() {
        //when
        SNode sn = new SNode('c', 3);
        sn.setRight(null);

        //then
        assert false;

    }

    @Test(expected = NullPointerException.class)
    public void should_ThrowException_When_SetChildIsNull_Left() {
        //when
        SNode sn = new SNode('c', 3);
        sn.setRight(null);

        //then
        assert false;

    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_When_SetValueIsNegative() {
        //when
        SNode sn = new SNode('c', 3);
        sn.setFreq(-3);

        //then
        assert false;

    }

    @Test
    public void should_CorrectlySetAndGetValue_OneValueGiven() {
        //when
        SNode sn = new SNode('c', '\n');
        int result = sn.getFreq();

        //then
        Assert.assertEquals(10.0, result, this.delta);
    }

    @Test
    public void should_CorrectlySetAndGetValue_SetMethod() {
        //given
        SNode sn = new SNode('c', '\n');

        //when
        sn.setFreq('s');
        int result = sn.getFreq();

        //then
        Assert.assertEquals(115.0, result, this.delta);
    }

    @Test
    public void should_CorrectlySetAndGetValueAndChildren_Constructor() {
        //given
        SNode right = new SNode('c', 'r');
        SNode left = new SNode('c', 'l');

        //when
        SNode sn = new SNode('c', '\t', left, right);

        ///then
        Assert.assertEquals(9.0, sn.getFreq(), this.delta);
        Assert.assertEquals(114.0, sn.getRight().getFreq(), this.delta);
        Assert.assertEquals(108.0, sn.getLeft().getFreq(), this.delta);
    }

    @Test
    public void should_CorrectlySetAndGetValueAndChildren_WhenSet() {
        //given
        SNode right = new SNode('c', 'r');
        SNode left = new SNode('c', 'l');
        SNode sn = new SNode('c', '\t');

        //when
        sn.setRight(right);
        sn.setLeft(left);

        //then
        Assert.assertEquals(9.0, sn.getFreq(), this.delta);
        Assert.assertEquals(114.0, sn.getRight().getFreq(), this.delta);
        Assert.assertEquals(108.0, sn.getLeft().getFreq(), this.delta);
    }

    @Test
    public void should_CorrectlySetAndGetValueAndKey_WithChildren() {
        //given
        SNode sNode = new SNode('a', 3, new SNode('f', 2), new SNode('b', 12));

        //then
        Assert.assertEquals('a', sNode.getKey());
        Assert.assertEquals('b', sNode.getRight().getKey());
        Assert.assertEquals('f', sNode.getLeft().getKey());
        Assert.assertEquals(3, sNode.getFreq(), delta);
        Assert.assertEquals(12, sNode.getRight().getFreq(), delta);
        Assert.assertEquals(2, sNode.getLeft().getFreq(), delta);
    }
}
