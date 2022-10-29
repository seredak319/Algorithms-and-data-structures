package pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

public class HashListChainingTest {

    private HashListChaining<Integer> hashListChainingInt;
    private HashListChaining<String> hashListChainingStr;

    private final int typicalTestInitiation = 5;
    private final int delta = 0;

    @Test(expected = NegativeArraySizeException.class)
    public void hashListNegativeSizeAndPutTest() {

        //given
        hashListChainingInt = new HashListChaining<>(-2);

        //when
        hashListChainingInt.add(3);

        //then
        assert false;
    }

    @Test(expected = ArithmeticException.class)
    public void hashListZeroSizeAndPutTest() {

        //given
        hashListChainingInt = new HashListChaining<>(0);

        //when
        hashListChainingInt.add(3);

        //then
        assert false;
    }

    @Test(expected = NullPointerException.class)
    public void hashListAddingNullValueTest() {

        //given
        hashListChainingInt = new HashListChaining<>(typicalTestInitiation);

        //when
        hashListChainingInt.add(null);

        //then
        assert false;
    }

    @Test(expected = NullPointerException.class)
    public void hashListGettingNullValueTest() {

        //given
        hashListChainingInt = new HashListChaining<>(typicalTestInitiation);

        //when
        hashListChainingInt.get(null);

        //then
        assert false;
    }

    @Test
    public void hashListGettingNonNullValueWitchHashListDoesNotContain() {

        //given
        hashListChainingInt = new HashListChaining<>(typicalTestInitiation);

        //then
        Assert.assertNull(hashListChainingInt.get(3));
    }

    @Test(expected = NullPointerException.class)
    public void hashListDeletingNullValueTest() {

        //given
        hashListChainingInt = new HashListChaining<>(typicalTestInitiation);

        //when
        hashListChainingInt.delete(null);

        //then
        assert false;
    }

    @Test(expected = NoSuchElementException.class)
    public void hashListWithoutValuesDeletingNonexistentValueTest() {

        //given
        hashListChainingInt = new HashListChaining<>(typicalTestInitiation);

        //when
        hashListChainingInt.delete(3);

        //then
        assert false;
    }

    @Test(expected = NoSuchElementException.class)
    public void hashListDeletingNonExistentValueTest() {

        //given
        hashListChainingInt = new HashListChaining<>(typicalTestInitiation);
        hashListChainingInt.add(3);
        hashListChainingInt.add(4);

        //when
        hashListChainingInt.delete(5);

        //then
        assert false;
    }

    @Test(expected = NoSuchElementException.class)
    public void hashListDeletingNonExistentValueButThereAreValuesInSuchHashCodeTest() {

        //given
        hashListChainingInt = new HashListChaining<>(typicalTestInitiation);
        hashListChainingInt.add(5);
        hashListChainingInt.add(10);
        hashListChainingInt.add(15);
        hashListChainingInt.add(20);

        //when
        hashListChainingInt.delete(25);

        //then
        assert false;
    }

    @Test
    public void hashListWithSizeOneCorrectDeletingFirstElementTest() {

        //given
        hashListChainingInt = new HashListChaining<>(1);
        hashListChainingInt.add(3);
        hashListChainingInt.add(4);
        hashListChainingInt.add(5);

        //when
        hashListChainingInt.delete(3);

        //then
        Assert.assertEquals((Integer) 4, hashListChainingInt.get(4));
        Assert.assertEquals((Integer) 5, hashListChainingInt.get(5));
        Assert.assertNull(hashListChainingInt.get(3));
    }

    @Test
    public void hashListWithSizeOneCorrectDeletingMiddleElementTest() {

        //given
        hashListChainingInt = new HashListChaining<>(1);
        hashListChainingInt.add(3);
        hashListChainingInt.add(4);
        hashListChainingInt.add(5);

        //when
        hashListChainingInt.delete(4);

        //then
        Assert.assertEquals((Integer) 3, hashListChainingInt.get(3));
        Assert.assertEquals((Integer) 5, hashListChainingInt.get(5));
        Assert.assertNull(hashListChainingInt.get(4));
    }

    @Test
    public void hashListWithSizeOneCorrectDeletingLastElementTest() {

        //given
        hashListChainingInt = new HashListChaining<>(1);
        hashListChainingInt.add(3);
        hashListChainingInt.add(4);
        hashListChainingInt.add(5);

        //when
        hashListChainingInt.delete(5);

        //then
        Assert.assertEquals((Integer) 3, hashListChainingInt.get(3));
        Assert.assertEquals((Integer) 4, hashListChainingInt.get(4));
        Assert.assertNull(hashListChainingInt.get(5));
    }

    @Test(expected = NoSuchElementException.class)
    public void hashListDeletingTwoTimesTheSameElementTest() {

        //given
        hashListChainingInt = new HashListChaining<>(typicalTestInitiation);
        hashListChainingInt.add(2137);

        //when
        hashListChainingInt.delete(2137);
        hashListChainingInt.delete(2137);

        //then
        assert false;
    }

    @Test
    public void addAndGetValueFromHashListTestWithNegativeValues() {

        //given
        HashListChaining<Double> hashListChainingDbl = new HashListChaining<>(typicalTestInitiation);

        //when
        hashListChainingDbl.add(-7.123);
        hashListChainingDbl.add(-3.44);
        hashListChainingDbl.add(-8.21);

        //then
        Assert.assertEquals(new Double(-7.123), hashListChainingDbl.get(-7.123));
        Assert.assertEquals(new Double(-8.21), hashListChainingDbl.get(-8.21));
    }

    @Test
    public void hashListCheckNumberOfElementsAfterAddingTest() {

        //given
        int expected = 3;
        hashListChainingInt = new HashListChaining<>(1);

        //when
        hashListChainingInt.add(3);
        hashListChainingInt.add(4);
        hashListChainingInt.add(5);

        //then
        Assert.assertEquals(expected, hashListChainingInt.countLoadFactor(), delta);
    }

    @Test
    public void hashListCheckNumberOfElementsAfterDeletingTest() {

        //given
        int expected = 2;
        hashListChainingInt = new HashListChaining<>(1);

        //when
        hashListChainingInt.add(3);
        hashListChainingInt.add(4);
        hashListChainingInt.add(5);
        hashListChainingInt.add(6);
        hashListChainingInt.delete(4);
        hashListChainingInt.delete(3);

        //then
        Assert.assertEquals(expected, hashListChainingInt.countLoadFactor(), delta);
    }

    @Test
    public void hashListCheckNumberOfElementsAfterAddingTheSameElementsTest() {

        //given
        int expected = 2;
        hashListChainingInt = new HashListChaining<>(1);

        //when
        hashListChainingInt.add(3);
        hashListChainingInt.add(4);
        hashListChainingInt.add(3);
        hashListChainingInt.add(4);

        //then
        Assert.assertEquals(expected, hashListChainingInt.countLoadFactor(), delta);
    }

    @Test
    public void hashListAddDeleteAndAgainAddTheSameElementTest() {

        //given
        Integer expected = 69;
        hashListChainingInt = new HashListChaining<>(typicalTestInitiation);

        //when
        hashListChainingInt.add(2137);
        hashListChainingInt.add(expected);
        hashListChainingInt.delete(expected);
        hashListChainingInt.add(expected);

        //then
        Assert.assertEquals(expected, hashListChainingInt.get(expected));
    }

    @Test
    public void hashListStringAddingAndGettingTest() {

        //given
        hashListChainingStr = new HashListChaining<>(typicalTestInitiation);
        String expected = "Jan Krzysztof Duda";
        String example1 = "Hikaru Nakamura";
        String example2 = "Magnus Carlsen";

        //when
        hashListChainingStr.add(example1);
        hashListChainingStr.add(expected);
        hashListChainingStr.add(example2);

        //then
        Assert.assertEquals(expected, hashListChainingStr.get(expected));
    }

    @Test
    public void hashListStringDeletingTest() {

        //given
        hashListChainingStr = new HashListChaining<>(typicalTestInitiation);
        String expected = "Jan Krzysztof Duda";
        String example1 = "Hikaru Nakamura";
        String example2 = "Magnus Carlsen";

        //when
        hashListChainingStr.add(example1);
        hashListChainingStr.add(expected);
        hashListChainingStr.add(example2);
        hashListChainingStr.delete(example1);
        hashListChainingStr.delete(example2);

        //then
        Assert.assertEquals(expected, hashListChainingStr.get(expected));
        Assert.assertNull(hashListChainingStr.get(example1));
        Assert.assertNull(hashListChainingStr.get(example2));
    }

    @Test
    public void hashListStringNumberOfElementsTest() {

        //given
        int expected = 1;
        hashListChainingStr = new HashListChaining<>(expected);
        String example = "Jan Krzysztof Duda";
        String example1 = "Hikaru Nakamura";
        String example2 = "Magnus Carlsen";

        //when
        hashListChainingStr.add(example1);
        hashListChainingStr.add(example);
        hashListChainingStr.add(example2);
        hashListChainingStr.delete(example1);
        hashListChainingStr.delete(example2);

        //then
        Assert.assertEquals(expected, hashListChainingStr.countLoadFactor(), delta);
    }

    private static class Cat implements Comparable<Cat> {

        private final int age;

        public Cat(int age) {
            this.age = age;
        }

        public int getAge() {
            return age;
        }

        @Override
        public int compareTo(Cat o) {
            return 0;
        }
    }

    @Test
    public void hashListWithCatClassGetTest() {

        //given
        int expected = 4;
        Cat cat1 = new Cat(5);
        Cat cat2 = new Cat(expected);
        HashListChaining<Cat> hashListChaining = new HashListChaining<>(typicalTestInitiation);

        //when
        hashListChaining.add(cat1);
        hashListChaining.add(cat2);
        Cat catTemp = hashListChaining.get(cat2);

        //then
        Assert.assertEquals(expected, catTemp.getAge());
    }

    @Test
    public void hashListWithCatClassDeleteTest() {

        //given
        Cat cat1 = new Cat(5);
        Cat cat2 = new Cat(4);
        HashListChaining<Cat> hashListChaining = new HashListChaining<>(typicalTestInitiation);

        //when
        hashListChaining.add(cat1);
        hashListChaining.add(cat2);
        hashListChaining.delete(cat2);

        //then
        Assert.assertNull(hashListChaining.get(cat2));
    }

}
