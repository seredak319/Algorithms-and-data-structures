package pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LongestCommonSubsequenceTest {

    @Test(expected = NullPointerException.class)
    public void should_ThrowException_WhenDisplayUsedBefore_DisplayMethod() {
        //given
        LongestCommonSubsequence lcs = new LongestCommonSubsequence("JAN", "FRANCISZKAŃSKA");

        //when
        lcs.display();

        //then
        assert false;
    }

    @Test
    public void should_ReturnEmptyString_WhenOneOfTheStringsIsNull_FindLCSMethod() {
        //given
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(null, "FRANCISZKAŃSKA");

        //when
        String substringResult = lcs.findLCS();

        //then
        Assert.assertEquals("", substringResult);
    }

    @Test
    public void should_CorrectlyFind_WhenAverageStrings_FindLCSMethod() {
        //given
        LongestCommonSubsequence lcs = new LongestCommonSubsequence("WATYKAŃSKI", "FRANCISZKAŃSKA");

        //when
        String substringResult = lcs.findLCS();

        //then
        Assert.assertEquals("AKAŃSK", substringResult);
    }

    @Test
    public void should_DoNothing_WhenStringIsNull_DisplayMethod() {
        //given
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(null, "FRANCISZKAŃSKA");

        //when
        String substringResult = lcs.findLCS();

        //then
        lcs.display();
    }

    @Test
    public void should_DoNotThrowErrorOrException_WhenStringIsEmpty_DisplayMethod() {
        //given
        LongestCommonSubsequence lcs = new LongestCommonSubsequence("", "FRANCISZKAŃSKA");

        //when
        String substringResult = lcs.findLCS();

        //then
        Assert.assertEquals("", substringResult);
        lcs.display();
    }

    @Test
    public void should_DoNotThrowErrorOrException_WhenBothStringsAreEmpty_DisplayMethod() {
        //given
        LongestCommonSubsequence lcs = new LongestCommonSubsequence("", "");

        //when
        String substringResult = lcs.findLCS();

        //then
        lcs.display();
    }

    @Test
    public void should_CorrectlyFindLCS_WhenOneLetterString_TheSame_FindLCSMethod() {
        //given
        LongestCommonSubsequence lcs = new LongestCommonSubsequence("A", "A");

        //when
        String substringResult = lcs.findLCS();

        //then
        Assert.assertEquals("A", substringResult);
        lcs.display();
    }

    @Test
    public void should_CorrectlyFindLCS_WhenOneLetterString_Differ_FindLCSMethod() {
        //given
        LongestCommonSubsequence lcs = new LongestCommonSubsequence("A", "a");

        //when
        String substringResult = lcs.findLCS();

        //then
        Assert.assertEquals("", substringResult);
        lcs.display();
    }

    @Test
    public void should_CorrectlyFindLCS_WhenSpecialSignsInRow_FindLCSMethod() {
        //given
        LongestCommonSubsequence lcs = new LongestCommonSubsequence("często_z_odkrywaniem", "rzeczy_nie_trzeba\n_się_spieszyć");

        //when
        String substringResult = lcs.findLCS();
        System.out.println(substringResult);

        //then
        Assert.assertEquals("cz__raie", substringResult);
        lcs.display();
    }

    @Test
    public void should_CorrectlyFindLCS_WhenSpecialSignsInColumn_FindLCSMethod() {
        //given
        LongestCommonSubsequence lcs = new LongestCommonSubsequence("rzeczy_nie_trzeba\n_się_spieszyć", "często_z_odkrywaniem");

        //when
        String substringResult = lcs.findLCS();

        //then
        Assert.assertEquals("cz__raie", substringResult);
        lcs.display();
    }

    @Test
    public void should_CorrectlyFindLCS_WhenOnlySpecialSigns_FindLCSMethod() {
        //given
        LongestCommonSubsequence lcs = new LongestCommonSubsequence("\n\t\b\b\b\b\t\n'\"\"\r\r\\\b\n\n\t\f", "\n\t\b\b\b\b\t\n'\"\"\r\r\\\b\n\n\t\f");

        //when
        String substringResult = lcs.findLCS();

        //then
        Assert.assertEquals("\n\t\b\b\b\b\t\n'\"\"\r\r\\\b\n\n\t\f", substringResult);
        lcs.display();
    }

    @Test
    public void should_CorrectlyFindLCS_WhenTheSameStrings_FindLCSMethod() {
        //given
        LongestCommonSubsequence lcs = new LongestCommonSubsequence("FRANCISZKAŃSKA", "FRANCISZKAŃSKA");

        //when
        String substringResult = lcs.findLCS();

        //then
        Assert.assertEquals("FRANCISZKAŃSKA", substringResult);
        lcs.display();
    }

    @Test
    public void should_CorrectlyFindLCS_WhenStringsAreLong_FindLCSMethod() {
        //given
        LongestCommonSubsequence lcs = new LongestCommonSubsequence("FRANCISZKAŃSKAFRANCISZKAŃSKAFRANCISZKAŃSKAFRANCISZKAŃSKAFRANCISZKAŃSKA"
                + "FRANCISZKAŃSKAFRANCISZKAŃSKAFRANCISZKAŃSKAFRANCISZKAŃSKAFRFRANCISZKAŃSKA\n", "FRANCISZKAŃSKAFRANCISZKAŃSKAFRANCISZKAŃSKAFRANCISZKAŃSKAZUPAZUPA"
                + "FRANCISZKAŃSKAFRANCISZKAŃSKFRANCISZKAŃSKA\n");

        //when
        String substringResult = lcs.findLCS();

        //then
        Assert.assertEquals("FRANCISZKAŃSKAFRANCISZKAŃSKAFRANCISZKAŃSKAFRANCISZKAŃSKAZAZAFRANCISZKAŃSKAFRANCISZKAŃSKFRANCISZKAŃSKA\n", substringResult);
        lcs.display();
    }

    @Test
    public void should_CorrectlyFindLCS_WhenSpaceIncluded_FindLCSMethod() {
        //given
        LongestCommonSubsequence lcs = new LongestCommonSubsequence("POLITECHNIKA drzewo", "TOALETA drewno");

        //when
        String substringResult = lcs.findLCS();

        //then
        Assert.assertEquals("OLTA drewo", substringResult);
        lcs.display();
    }

    @Test
    public void should_CorrectlyFindLCS_WhenStringsDontHaveCommon_FindLCSMethod() {
        //given
        LongestCommonSubsequence lcs = new LongestCommonSubsequence("jadwiga", "przemek");

        //when
        String substringResult = lcs.findLCS();

        //then
        System.out.println(substringResult);
        lcs.display();
    }

    @Test
    public void should_CorrectlyPrintDisplay_WhenAverageStrings_DisplayMethod() {
        ByteArrayOutputStream outPut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPut));

        //given
        LongestCommonSubsequence lcs = new LongestCommonSubsequence("zupa", "lufa");

        //when
        String substringResult = lcs.findLCS();
        lcs.display();

        //then
        Assert.assertEquals("+-------+-----+-----+-----+-----+-----+\n"
                + "|       |     |     |     |     |     |\n"
                + "|       |     |  l  |  u  |  f  |  a  |\n"
                + "|       |     |     |     |     |     |\n"
                + "+-------+-----+-----+-----+-----+-----+\n"
                + "|       |     |     |     |     |     |\n"
                + "|       |  0  |  0  |  0  |  0  |  0  |\n"
                + "|       |     |     |     |     |     |\n"
                + "+-------+-----+-----+-----+-----+-----+\n"
                + "|       |     |  ^  |     |     |     |\n"
                + "|   z   |  0  |  0  |  0  |  0  |  0  |\n"
                + "|       |     |     |     |     |     |\n"
                + "+-------+-----+-----+-----+-----+-----+\n"
                + "|       |     |     |\\    |     |     |\n"
                + "|   u   |  0  |  0  |  1  |< 1  |  1  |\n"
                + "|       |     |     |     |     |     |\n"
                + "+-------+-----+-----+-----+-----+-----+\n"
                + "|       |     |     |     |  ^  |     |\n"
                + "|   p   |  0  |  0  |  1  |  1  |  1  |\n"
                + "|       |     |     |     |     |     |\n"
                + "+-------+-----+-----+-----+-----+-----+\n"
                + "|       |     |     |     |     |\\    |\n"
                + "|   a   |  0  |  0  |  1  |  1  |  2  |\n"
                + "|       |     |     |     |     |     |\n"
                + "+-------+-----+-----+-----+-----+-----+", outPut.toString().trim());

        System.setOut(System.out);
    }

    @Test
    public void should_CorrectlyPrintDisplay_WhenEmptyString_DisplayMethod() {
        ByteArrayOutputStream outPut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPut));

        //given
        LongestCommonSubsequence lcs = new LongestCommonSubsequence("", "lufa");

        //when
        String substringResult = lcs.findLCS();
        lcs.display();

        //then
        Assert.assertEquals("+-------+-----+-----+-----+-----+-----+\n"
                + "|       |     |     |     |     |     |\n"
                + "|       |     |  l  |  u  |  f  |  a  |\n"
                + "|       |     |     |     |     |     |\n"
                + "+-------+-----+-----+-----+-----+-----+\n"
                + "|       |     |     |     |     |     |\n"
                + "|       |  0  |  0  |  0  |  0  |  0  |\n"
                + "|       |     |     |     |     |     |\n"
                + "+-------+-----+-----+-----+-----+-----+", outPut.toString().trim());

        System.setOut(System.out);
    }
}
