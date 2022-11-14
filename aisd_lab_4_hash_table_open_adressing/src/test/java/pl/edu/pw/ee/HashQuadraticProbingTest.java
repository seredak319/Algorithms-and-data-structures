package pl.edu.pw.ee;

import org.junit.Test;
import pl.edu.pw.ee.services.HashTable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class HashQuadraticProbingTest {

    private final double delta = 0.01;

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenInitialSizeIsLowerThanOne() {
        // given
        int initialSize = 0;

        // when
        HashTable<Double> unusedHash = new HashQuadraticProbing<>(initialSize,2137,69);

        // then
        assert false;
    }

    @Test
    public void should_CorrectlyAddNewElems_WhenNotExistInHashTable() {
        // given
        HashTable<String> emptyHash = new HashQuadraticProbing<>();
        String newEleme = "nothing special";

        // when
        int nOfElemsBeforePut = getNumOfElems(emptyHash);
        emptyHash.put(newEleme);
        int nOfElemsAfterPut = getNumOfElems(emptyHash);

        // then
        assertEquals(0, nOfElemsBeforePut);
        assertEquals(1, nOfElemsAfterPut);
    }

    @Test(expected = IllegalArgumentException.class)
    public void init_ConstansEqualsZeroTest(){
        //then
        HashTable<?> emptyHash = new HashQuadraticProbing<>(3,0,0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void put_NullValueTest(){
        //given
        HashTable<Integer> emptyHash = new HashQuadraticProbing<>();

        //when
        emptyHash.put(null);

        //then
        assert false;
    }

    @Test
    public void put_TheSameValueTest(){
        //given
        HashTable<Integer> emptyHash = new HashQuadraticProbing<>(3,0.5,0.5);

        //when
        emptyHash.put(1);
        emptyHash.put(1);
        emptyHash.put(1);
        emptyHash.put(1);
        emptyHash.put(1);
        int nOfElemsAfterPut = getNumOfElems(emptyHash);

        //then
        assertEquals(1,nOfElemsAfterPut);
    }

    @Test
    public void put_TheSameValueManyElements_PrimeNumberSizeTest(){
        //given
        HashTable<Integer> emptyHash = new HashQuadraticProbing<>(7,5,2);

        //when
        emptyHash.put(0);
        emptyHash.put(1);
        emptyHash.put(2);
        emptyHash.put(3);
        emptyHash.put(4);
        emptyHash.put(3);
        emptyHash.put(4);
        emptyHash.put(3);
        int nOfElemsAfterPut = getNumOfElems(emptyHash);

        //then
        assertEquals(5,nOfElemsAfterPut);
    }

    @Test
    public void put_TheSameValueManyElements_PowerOfTwoSizeTest(){
        //given
        HashTable<Integer> emptyHash = new HashQuadraticProbing<>(2,0.5,0.5);

        //when
        emptyHash.put(0);
        emptyHash.put(1);
        emptyHash.put(2);
        emptyHash.put(3);
        emptyHash.put(4);
        emptyHash.put(3);
        emptyHash.put(4);
        emptyHash.put(3);
        int nOfElemsAfterPut = getNumOfElems(emptyHash);

        //then
        assertEquals(5,nOfElemsAfterPut);
    }

    @Test
    public void put_TheSameHashCode_PrimeNumberSizeTest(){
        //given
        HashTable<Integer> emptyHash = new HashQuadraticProbing<>(3,0.5,0.5);

        //when
        emptyHash.put(1);
        emptyHash.put(4);
        int nOfElemsAfterPut = getNumOfElems(emptyHash);

        //then
        assertEquals(2, nOfElemsAfterPut);
    }

    @Test
    public void put_TheSameHashCode_PowerOfTwoTest(){
        //given
        HashTable<Integer> emptyHash = new HashQuadraticProbing<>(4,0.5,0.5);

        //when
        emptyHash.put(0);
        emptyHash.put(4);
        int nOfElemsAfterPut = getNumOfElems(emptyHash);

        //then
        assertEquals(2, nOfElemsAfterPut);
    }

    @Test
    public void put_HashingWithIntegersAtBorderTest(){
        //given
        HashTable<Integer> emptyHash = new HashQuadraticProbing<>(4,5,2);

        //when
        emptyHash.put(0);
        emptyHash.put(4);
        int nOfElemsAfterPut = getNumOfElems(emptyHash);

        //then
        assertEquals(2, nOfElemsAfterPut);
    }

    @Test
    public void put_CheckIfHashSizeIsDoublingCorrectlyTest(){
        //given
        HashTable<Integer> emptyHash = new HashQuadraticProbing<>(3,3,6);

        //when
        emptyHash.put(2);
        emptyHash.put(0);
        emptyHash.put(3);
        emptyHash.put(4);
        emptyHash.put(5);
        emptyHash.put(20137);
        int nOfElemsAfterPut = getNumOfElems(emptyHash);

        //then
        assertEquals(6, nOfElemsAfterPut);
    }

    @Test (expected = IllegalArgumentException.class)
    public void get_NullValueTest(){
        //given
        HashTable<Integer> emptyHash = new HashQuadraticProbing<>(3,3,6);

        //when
        emptyHash.put(1);
        emptyHash.put(3);
        emptyHash.get(null);

        //then
        assert false;
    }

    @Test (expected = NoSuchElementException.class)
    public void get_UnreachableValueTest(){
        //given
        HashTable<Integer> emptyHash = new HashQuadraticProbing<>(4,0.5,0.5);

        //when
        emptyHash.put(1);
        emptyHash.put(3);
        emptyHash.get(2137);

        //then
        assert false;
    }

    @Test
    public void get_TheSameHashCode_PrimeNumberSizeTest(){
        //given
        HashTable<Integer> emptyHash = new HashQuadraticProbing<>(13,0.5,0.5);

        //when
        emptyHash.put(13);
        emptyHash.put(26);
        emptyHash.put(39);
        emptyHash.put(52);

        //then
        assertEquals(52,emptyHash.get(52),delta);
        assertEquals(39,emptyHash.get(39),delta);
        assertEquals(13,emptyHash.get(13),delta);
    }

    @Test
    public void get_TheSameHashCode_PowerOfTwoTest(){
        //given
        HashTable<Integer> emptyHash = new HashQuadraticProbing<>(16,0.5,0.5);

        //when
        emptyHash.put(16);
        emptyHash.put(32);
        emptyHash.put(48);
        emptyHash.put(64);

        //then
        assertEquals(64,emptyHash.get(64),delta);
        assertEquals(32,emptyHash.get(32),delta);
        assertEquals(48,emptyHash.get(48),delta);
    }

    @Test
    public void get_AfterResizingTest(){
        //given
        HashTable<Integer> emptyHash = new HashQuadraticProbing<>(1,0.5,0.5);

        //when
        emptyHash.put(0);
        emptyHash.put(1);
        emptyHash.put(2);
        emptyHash.put(4);

        //then
        assertEquals(0,emptyHash.get(0),delta);
        assertEquals(1,emptyHash.get(1),delta);
        assertEquals(2,emptyHash.get(2),delta);
        assertEquals(4,emptyHash.get(4),delta);
    }

    @Test (expected = IllegalArgumentException.class)
    public void delete_NullValueTest(){
        //given
        HashTable<Integer> emptyHash = new HashQuadraticProbing<>(3,3,6);

        //when
        emptyHash.put(0);
        emptyHash.delete(null);
    }

    @Test
    public void delete_ExistingValueTest(){
        //given
        HashTable<Integer> emptyHash = new HashQuadraticProbing<>(3,0.5,0.5);

        //when
        emptyHash.put(0);
        emptyHash.put(1);
        int nOfElemsBeforeDelete = getNumOfElems(emptyHash);
        emptyHash.delete(0);
        int nOfElemsAfterDelete = getNumOfElems(emptyHash);

        //then
        assertEquals(1,nOfElemsBeforeDelete-nOfElemsAfterDelete,delta);
    }

    @Test
    public void delete_ExistingValueAfterResizeTest(){
        //given
        HashTable<Integer> emptyHash = new HashQuadraticProbing<>(3,0.5,0.5);

        //when
        emptyHash.put(0);
        emptyHash.put(1);
        emptyHash.put(2);
        int nOfElemsBeforeDelete = getNumOfElems(emptyHash);
        emptyHash.delete(0);
        int nOfElemsAfterDelete = getNumOfElems(emptyHash);

        //then
        assertEquals(1,nOfElemsBeforeDelete-nOfElemsAfterDelete,delta);
    }

    @Test
    public void delete_ExistingValueWithTheSameHash_NumberOfElementsTest(){
        //given
        HashTable<Integer> emptyHash = new HashQuadraticProbing<>(11,0.5,0.5);

        //when
        emptyHash.put(0);
        emptyHash.put(11);
        emptyHash.put(22);
        emptyHash.put(33);
        int nOfElemsBeforeDelete = getNumOfElems(emptyHash);
        emptyHash.delete(33);
        int nOfElemsAfterDelete = getNumOfElems(emptyHash);

        //then
        assertEquals(1,nOfElemsBeforeDelete-nOfElemsAfterDelete,delta);
    }

    @Test (expected = NoSuchElementException.class)
    public void delete_ExistingValueWithTheSameHash_GetElementAfterDeletingTest(){
        //given
        HashTable<Integer> emptyHash = new HashQuadraticProbing<>(11,0.5,0.5);

        //when
        emptyHash.put(0);
        emptyHash.put(11);
        emptyHash.put(22);
        emptyHash.put(33);
        emptyHash.delete(33);
        emptyHash.get(33);

        //then
        assert false;
    }

    @Test
    public void delete_ExistingValueWithTheSameHash_ElementBetweenThemWasDeletedTest(){
        //given
        HashTable<Integer> emptyHash = new HashQuadraticProbing<>(11,3,5);
        Integer result;

        //when
        emptyHash.put(0);
        emptyHash.put(11);
        emptyHash.put(22);
        emptyHash.put(33);
        emptyHash.delete(11);
        result = emptyHash.get(33);

        //then
        assertEquals(33,result,delta);
    }

    @Test
    public void delete_DeletingAndPuttingTheSameValueTest(){
        //given
        HashTable<Integer> emptyHash = new HashQuadraticProbing<>(11,0.5,0.5);
        Integer result;

        //when
        emptyHash.put(0);
        emptyHash.put(11);
        emptyHash.put(22);
        emptyHash.put(33);
        emptyHash.delete(11);
        emptyHash.put(11);
        emptyHash.delete(11);
        emptyHash.put(11);
        result = emptyHash.get(11);

        //then
        assertEquals(11,result,delta);
    }

    @Test
    public void delete_DeletingAndPuttingTheSameHash_DEL_MARK_NumberOfElementsTest(){
        //given
        HashTable<Integer> emptyHash = new HashQuadraticProbing<>(11,0.5,0.5);
        Integer result;

        //when
        emptyHash.put(0);
        emptyHash.put(11);
        emptyHash.put(22);
        emptyHash.put(33);
        emptyHash.delete(11);
        emptyHash.put(22);
        emptyHash.put(22);
        result = emptyHash.get(22);
        int nOfElemsAfterDelete = getNumOfElems(emptyHash);


        //then
        assertEquals(22,result,delta);
        assertEquals(3,nOfElemsAfterDelete,delta);
    }

    @Test
    public void delete_DeletingAndPuttingTheSameHash_PutElementInDEL_MARK_Test(){
        //given
        HashTable<Integer> emptyHash = new HashQuadraticProbing<>(11,0.5,0.5);
        Integer result;

        //when
        emptyHash.put(0);
        emptyHash.put(11);
        emptyHash.put(22);
        emptyHash.put(33);
        emptyHash.delete(11);
        emptyHash.put(44);
        emptyHash.put(44);
        result = emptyHash.get(44);
        int nOfElemsAfterDelete = getNumOfElems(emptyHash);


        //then
        assertEquals(44,result,delta);
        assertEquals(4,nOfElemsAfterDelete,delta);
    }

    @Test (expected = NoSuchElementException.class)
    public void delete_ValueWitchDoesNotExistTest(){
        //given
        HashTable<Integer> emptyHash = new HashQuadraticProbing<>(16,0.5,0.5);

        //when
        emptyHash.put(0);
        emptyHash.put(11);
        emptyHash.put(22);
        emptyHash.put(33);
        emptyHash.delete(2137);

        //then
        assert false;
    }

    @Test (expected = NoSuchElementException.class)
    public void delete_EmptyHashTest(){
        //given
        HashTable<Integer> emptyHash = new HashQuadraticProbing<>(11,11,22);

        //when
        emptyHash.delete(11);
    }

    @Test
    public void delete_ValuesWithTheSameHashCode_NumberOfElements_PowerOfTwoSizeTest(){
        //given
        HashTable<Integer> emptyHash = new HashQuadraticProbing<>(8,0.5,0.5);

        //when
        emptyHash.put(0);
        emptyHash.put(11);
        emptyHash.put(22);
        emptyHash.put(33);
        emptyHash.put(44);
        emptyHash.put(66);
        emptyHash.put(77);
        emptyHash.delete(11);
        emptyHash.delete(66);
        emptyHash.delete(77);
        emptyHash.delete(44);
        emptyHash.delete(33);
        emptyHash.delete(0);
        emptyHash.delete(22);
        int nOfElemsAfterDeletes = getNumOfElems(emptyHash);

        //then
        assertEquals(0,nOfElemsAfterDeletes);
    }

    @Test
    public void delete_ValuesWithTheSameHashCode_NumberOfElements_PrimeSizeTest(){
        //given
        HashTable<Integer> emptyHash = new HashQuadraticProbing<>(11,2137,2137);

        //when
        emptyHash.put(0);
        emptyHash.put(11);
        emptyHash.put(22);
        emptyHash.put(33);
        emptyHash.put(44);
        emptyHash.put(66);
        emptyHash.put(77);
        emptyHash.delete(11);
        emptyHash.delete(66);
        emptyHash.delete(77);
        emptyHash.delete(44);
        emptyHash.delete(33);
        emptyHash.delete(0);
        emptyHash.delete(22);
        int nOfElemsAfterDeletes = getNumOfElems(emptyHash);

        //then
        assertEquals(0,nOfElemsAfterDeletes);
    }

    @Test
    public void delete_ValuesWithTheSameHashCodeAfterResize_NumberOfElementsTest(){
        //given
        HashTable<Integer> emptyHash = new HashQuadraticProbing<>(5,5,10);

        //when
        emptyHash.put(0);
        emptyHash.put(5);
        emptyHash.put(10);
        emptyHash.put(225);
        emptyHash.delete(5);
        emptyHash.delete(0);
        emptyHash.delete(225);
        emptyHash.delete(10);
        int nOfElemsAfterDeletes = getNumOfElems(emptyHash);

        //then
        assertEquals(0,nOfElemsAfterDeletes);
    }

    @Test
    public void putGetDelete_StandardHashTableSize_100kStringsTest(){
        //given
        HashTable<String> hashTable = new HashQuadraticProbing<>();
        String[] words = prepareWords();

        //when
        for (String value : words) {
            hashTable.put(value);
        }
        for (String s : words) {
            hashTable.get(s);
        }
        for (String word : words) {
            hashTable.delete(word);
        }
        int nOElements = getNumOfElems(hashTable);

        //then
        assertEquals(0,nOElements);
    }

    @Test
    public void resize_ResizeDoesNotPutDEL_MARK_Test(){
        //given
        HashTable<Integer> emptyHash = new HashQuadraticProbing<>(5,5,10);

        //when
        emptyHash.put(0);
        emptyHash.put(1);
        emptyHash.put(2);
        emptyHash.delete(1);
        emptyHash.delete(2);
        emptyHash.put(12);
        emptyHash.put(2137);
        emptyHash.put(634);
        int nOfElemsAfterPutsAndDeletes = getNumOfElems(emptyHash);

        //then
        assertEquals(4,nOfElemsAfterPutsAndDeletes);
    }

    private int getNumOfElems(HashTable<?> hash) {
        String fieldNumOfElems = "nElems";
        try {
            System.out.println(hash.getClass().getSuperclass().getName());
            Field field = hash.getClass().getSuperclass().getDeclaredField(fieldNumOfElems);
            field.setAccessible(true);

            int numOfElems = field.getInt(hash);

            return numOfElems;

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private String[] prepareWords() {
        List<String> words = new ArrayList<>();
        String WORDS_FILEPATH = "src/main/resources/wordlist_100_000.txt";
        String line;
        int counter = 0;
        int maxNumWords = 100_000;
        try (FileReader fReader = new FileReader(WORDS_FILEPATH);
             BufferedReader buffReader = new BufferedReader(fReader)) {

            while ((line = buffReader.readLine()) != null && counter < maxNumWords) {
                words.add(line);
                counter++;
            }
        } catch (IOException ignored) {
        }
        return words.toArray(new String[0]);
    }
}
