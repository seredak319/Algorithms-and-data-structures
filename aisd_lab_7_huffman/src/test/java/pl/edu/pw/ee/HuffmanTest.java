package pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HuffmanTest {

    private final String path = "src/main/resources/test/";

    private Huffman huffman;

    @Before
    public void before() {
        huffman = new Huffman();
    }

    @Test(expected = NullPointerException.class)
    public void huffman_Should_ThrowException_When_NullPath() {
        //when
        huffman.huffman(null, true);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void huffman_Should_ThrowException_When_WrongPath() {
        //when
        huffman.huffman("home/jp2/", true);

        //then
        assert false;
    }

    @Test
    public void huffman_Should_DoNothing_When_EmptyFile() {
        //when
        int result = huffman.huffman(path + "testEmptyFile/", true);

        //then
        Assert.assertEquals(0, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void huffman_Should_ThrowException_When_NotAscii() {
        //when
        huffman.huffman(path + "testNotAscii/", true);

        //then
        assert false;
    }

    @Test
    public void huffman_Should_CorrectlyCompressAndDecompress_When_OneLetterFile() {
        try {
            //given
            String tempPath = path + "testOneLetterFile/";
            String expectedText = Files.readString(Path.of(tempPath + "text.txt"));

            //when
            int result1 = huffman.huffman(tempPath, true);
            int result2 = huffman.huffman(tempPath, false);
            String actualText = Files.readString(Path.of(tempPath + "result.txt"));

            //then
            Assert.assertEquals(40, result1);
            Assert.assertEquals(1, result2);
            Assert.assertEquals(expectedText, actualText);
        } catch (IOException e) {
            throw new RuntimeException("Files required for the test can not be found or read properly.");
        }
    }

    @Test
    public void huffman_Should_CorrectlyCompressAndDecompress_When_TestNiemanieRefren() {
        try {
            //given
            String tempPath = path + "testNiemanieRefrain/";
            String exptectedText = Files.readString(Path.of(tempPath + "text.txt"));

            //when
            int result1 = huffman.huffman(tempPath, true);
            int result2 = huffman.huffman(tempPath, false);
            String actualText = Files.readString(Path.of(tempPath + "result.txt"));
            File resultFileCode = new File(tempPath + "text_code.txt");
            File resultFileTree = new File(tempPath + "text_tree.txt");
            File resultFileResult = new File(tempPath + "result.txt");

            //then
            Assert.assertEquals((resultFileCode.length() + resultFileTree.length()) * 8, result1);
            Assert.assertEquals(resultFileResult.length(), result2);
            Assert.assertEquals(exptectedText, actualText);
        } catch (IOException e) {
            throw new RuntimeException("Files required for the test can not be found or read properly.");
        }
    }

    @Test
    public void huffman_Should_CorrectlyCompressAndDecompress_When_TestNiemanie() {
        try {
            //given
            String tempPath = path + "testNiemanie/";
            String exptectedText = Files.readString(Path.of(tempPath + "text.txt"));

            //when
            int result1 = huffman.huffman(tempPath, true);
            int result2 = huffman.huffman(tempPath, false);
            String actualText = Files.readString(Path.of(tempPath + "result.txt"));
            File resultFileCode = new File(tempPath + "text_code.txt");
            File resultFileTree = new File(tempPath + "text_tree.txt");
            File resultFileResult = new File(tempPath + "result.txt");

            //then
            Assert.assertEquals((resultFileCode.length() + resultFileTree.length()) * 8, result1);
            Assert.assertEquals(resultFileResult.length(), result2);
            Assert.assertEquals(exptectedText, actualText);
        } catch (IOException e) {
            throw new RuntimeException("Files required for the test can not be found or read properly.");
        }
    }

    @Test
    public void huffman_Should_CorrectlyCompressAndDecompress_When_Test100K() {
        try {
            //given
            String tempPath = path + "test100K/";
            String exptectedText = Files.readString(Path.of(tempPath + "/text.txt"));

            //when
            int result1 = huffman.huffman(tempPath, true);
            int result2 = huffman.huffman(tempPath, false);
            String actualText = Files.readString(Path.of(tempPath + "result.txt"));
            File resultFileCode = new File(tempPath + "text_code.txt");
            File resultFileTree = new File(tempPath + "text_tree.txt");
            File resultFileResult = new File(tempPath + "result.txt");

            //then
            Assert.assertEquals((resultFileCode.length() + resultFileTree.length()) * 8, result1);
            Assert.assertEquals(resultFileResult.length(), result2);
            Assert.assertEquals(exptectedText, actualText);
        } catch (IOException e) {
            throw new RuntimeException("Files required for the test can not be found or read properly.");
        }
    }

    @Test
    public void huffman_Should_CorrectlyDecompress_When_OnlyCodeAndTreeGiven() {
        try {
            //given
            String tempPath = path + "testOnlyCodeAndTree/";
            String exptectedText = Files.readString(Path.of(path + "testNiemanie/text.txt"));

            //when
            int result2 = huffman.huffman(tempPath, false);
            String actualText = Files.readString(Path.of(tempPath + "result.txt"));
            File resultFileResult = new File(tempPath + "result.txt");

            //then
            Assert.assertEquals(resultFileResult.length(), result2);
            Assert.assertEquals(exptectedText, actualText);
        } catch (IOException e) {
            throw new RuntimeException("Files required for the test can not be found or read properly.");
        }
    }

    @Test
    public void huffman_Should_ReturnZero_When_EmptyCode() {
        //given
        String tempPath = path + "testEmptyCode/";

        //when
        int result = huffman.huffman(tempPath, false);

        Assert.assertEquals(0, result);
    }

    @Test
    public void huffman_Should_ReturnZero_When_EmptyTree() {
        //given
        String tempPath = path + "testEmptyTree/";

        //when
        int result = huffman.huffman(tempPath, false);

        //then
        Assert.assertEquals(0, result);
    }

    @Test
    public void huffman_Should_CreateFileUsingOnlyOneLetter() {
        //given
        String tempPath = path + "testNotMatchingTreeAndCode/";

        //when
        huffman.huffman(tempPath, false);
        try {
            String exptectedText = Files.readString(Path.of(tempPath + "/result.txt"));

            //then
            if (!exptectedText.matches("^h+$")) {
                assert false;
            } else {
                assert true;
            }
        } catch (IOException e) {
            throw new RuntimeException("Files required for the test can not be found or read properly.");
        }
    }

    @Test
    public void huffman_Should_CorrectlyCompress_FewTimeInARow() {
        //when
        int result1 = huffman.huffman(path + "testNiemanie/", true);
        int result2 = huffman.huffman(path + "testNiemanie/", true);
        int result3 = huffman.huffman(path + "testNiemanie/", true);

        //then
        Assert.assertEquals(11240, result1);
        Assert.assertEquals(11240, result2);
        Assert.assertEquals(11240, result3);
    }

    @Test
    public void huffman_Should_CorrectlyDecompress_FewTimeInARow() {
        //when
        int result1 = huffman.huffman(path + "testNiemanie/", false);
        int result2 = huffman.huffman(path + "testNiemanie/", false);
        int result3 = huffman.huffman(path + "testNiemanie/", false);

        //then
        Assert.assertEquals(1912, result1);
        Assert.assertEquals(1912, result2);
        Assert.assertEquals(1912, result3);
    }
}
