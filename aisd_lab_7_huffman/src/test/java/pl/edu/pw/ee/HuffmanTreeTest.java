package pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.pq.SNode;

import java.util.HashMap;

public class HuffmanTreeTest {

    private HuffmanTree huffmanTree;

    @Before
    public void before() {
        huffmanTree = new HuffmanTree();
    }

    @Test(expected = NullPointerException.class)
    public void buildCode_Should_ThrowException_When_NullFrequencies() {
        //when
        huffmanTree.buildTree(null);

        //then
        assert false;
    }

    @Test
    public void buildCode_Should_ReturnNull_When_EmptyFrequencies() {
        //when
        HashMap<Character, String> result = huffmanTree.buildTree(new HashMap<>());

        //then
        Assert.assertNull(result);
    }

    @Test(expected = NullPointerException.class)
    public void buildCode_Should_ThrowException_When_ValuesAreNull() {
        //given
        HashMap<Character, Integer> hashMap = new HashMap<>();
        hashMap.put(null, null);
        hashMap.put('c', 1);
        hashMap.put('a', 10);

        //then
        huffmanTree.buildTree(hashMap);

        //when
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void buildCode_Should_ThrowException_When_ValueNotValid_Character() {
        //given
        HashMap<Character, Integer> hashMap = new HashMap<>();
        hashMap.put('ą', 2);
        hashMap.put('c', 1);
        hashMap.put('a', 10);

        //then
        huffmanTree.buildTree(hashMap);

        //when
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void buildCode_Should_ThrowException_When_ValueNotValid_Integer() {
        //given
        HashMap<Character, Integer> hashMap = new HashMap<>();
        hashMap.put('d', 0);
        hashMap.put('c', 1);
        hashMap.put('a', 10);

        //then
        huffmanTree.buildTree(hashMap);

        //when
        assert false;
    }

    @Test
    public void buildCode_Should_CorrectlyBuildCode_When_OneLetter_InputsValid() {
        //given
        HashMap<Character, Integer> hashMap = new HashMap<>();
        hashMap.put('a', 10);

        //when
        HashMap<Character, String> result = huffmanTree.buildTree(hashMap);

        //then
        Assert.assertEquals("0", result.get('a'));
    }

    @Test
    public void buildCode_Should_CorrectlyBuildCode_When_MoreThanOneLetter_InputsValid() {
        //given
        HashMap<Character, Integer> hashMap = new HashMap<>();
        hashMap.put('b', 2);
        hashMap.put('c', 1);
        hashMap.put('a', 10);

        //when
        HashMap<Character, String> result = huffmanTree.buildTree(hashMap);

        //then
        Assert.assertEquals("1", result.get('a'));
        Assert.assertEquals("01", result.get('b'));
        Assert.assertEquals("00", result.get('c'));
    }

    @Test(expected = NullPointerException.class)
    public void encodeHuffmanTree_Should_ThrowException_When_DidNotBeBuiltBefore() {
        //when
        huffmanTree.encodeHuffmanTree();

        //then
        assert false;
    }

    @Test
    public void encodeHuffmanTree_Should_CorrectlyCreateCode_When_ValidBuild_OneElement() {
        //given
        HashMap<Character, Integer> frequencies = new HashMap<>();
        frequencies.put('a', 1);
        huffmanTree.buildTree(frequencies);

        //when
        String result = huffmanTree.encodeHuffmanTree();

        //then
        Assert.assertEquals("101100001", result);
    }

    @Test
    public void encodeHuffmanTree_Should_CorrectlyCreateCode_When_ValidBuild_MoreThanOneElement() {
        //given
        HashMap<Character, Integer> frequencies = new HashMap<>();
        frequencies.put('a', 1);
        frequencies.put('b', 1);
        frequencies.put('c', 1);
        huffmanTree.buildTree(frequencies);

        //when
        String result = huffmanTree.encodeHuffmanTree();

        //then
        Assert.assertEquals("01011000100101100001101100011", result);
    }

    @Test(expected = NullPointerException.class)
    public void decodeHuffmanTree_Should_ThrowException_When_NullCode() {
        //when
        huffmanTree.decodeHuffmanTree(null);

        //then
        assert false;
    }

    @Test
    public void decodeHuffmanTree_Should_ThrowException_When_EmptyCode() {
        //when
        SNode sNode = huffmanTree.decodeHuffmanTree("");

        //then
        Assert.assertNull(sNode);
    }

    @Test(expected = IllegalArgumentException.class)
    public void decodeHuffmanTree_Should_ThrowException_When_InvalidCode_IncorrectRegex() {
        //given
        String invalidCode = "0111101001010010010112";

        //when
        huffmanTree.decodeHuffmanTree(invalidCode);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void decodeHuffmanTree_Should_ThrowException_When_InvalidCode_IncorrectLength() {
        //given
        String invalidCode = "00011111111111111111111111111111111111";

        //when
        huffmanTree.decodeHuffmanTree(invalidCode);

        //then
        assert false;
    }

    @Test
    public void decodeHuffmanTree_Should_CorrectlyCreateCode_When_ValidCode() {
        //given
        HashMap<Character, Integer> freq = new HashMap<>();
        freq.put('a', 20);
        freq.put('b', 2);
        huffmanTree.buildTree(freq);
        String validCode = huffmanTree.encodeHuffmanTree();

        //when
        SNode sNode = huffmanTree.decodeHuffmanTree(validCode);

        //then
        Assert.assertEquals('a', sNode.getRight().getKey());
        Assert.assertEquals('b', sNode.getLeft().getKey());
    }
}
