package pl.edu.pw.ee;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class HashListChainingPerformanceTest {

    private HashListChaining<String> hashListChainingStr;
    private final String filePath = "src/test/java/pl/edu/pw/ee/test";
    private final int testsAmount = 30;

    private void readAndAddFromFile(HashListChaining<String> h) {
        String currentWord;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            currentWord = reader.readLine();
            while (currentWord != null) {
                h.add(currentWord);
                currentWord = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private long searchFromFile(HashListChaining<String> h) {
        long start = 0;
        long finish = 0;
        String currentWord;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            start = System.nanoTime();
            currentWord = reader.readLine();
            while (currentWord != null) {
                h.get(currentWord);
                currentWord = reader.readLine();
            }
            finish = System.nanoTime();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return finish - start;
    }

    @Test
    public void hashSize1x4096PerformanceTime() {

        //given
        int basicSize = 4096;
        int multiply = 1;
        long time;
        long averageTime = 0;
        long[] times = new long[testsAmount];

        //when
        for (int i = 0; i < testsAmount; i++) {
            hashListChainingStr = new HashListChaining<>(basicSize * multiply);
            readAndAddFromFile(hashListChainingStr);
            time = searchFromFile(hashListChainingStr);
            times[i] = time;
        }
        Arrays.sort(times);

        for (int i = 10; i < 20; i++) {
            averageTime += times[i];
        }
        averageTime /= 10;

        //then
        System.out.println(averageTime);
    }

    @Test
    public void hashSize2x4096PerformanceTime() {

        //given
        int basicSize = 4096;
        int multiply = 2;
        long time;
        long averageTime = 0;
        long[] times = new long[testsAmount];

        //when
        for (int i = 0; i < testsAmount; i++) {
            hashListChainingStr = new HashListChaining<>(basicSize * multiply);
            readAndAddFromFile(hashListChainingStr);
            time = searchFromFile(hashListChainingStr);
            times[i] = time;
        }
        Arrays.sort(times);

        for (int i = 10; i < 20; i++) {
            averageTime += times[i];
        }
        averageTime /= 10;

        //then
        System.out.println(averageTime);
    }

    @Test
    public void hashSize3x4096PerformanceTime() {

        //given
        int basicSize = 4096;
        int multiply = 3;
        long time;
        long averageTime = 0;
        long[] times = new long[testsAmount];

        //when
        for (int i = 0; i < testsAmount; i++) {
            hashListChainingStr = new HashListChaining<>(basicSize * multiply);
            readAndAddFromFile(hashListChainingStr);
            time = searchFromFile(hashListChainingStr);
            times[i] = time;
        }
        Arrays.sort(times);

        for (int i = 10; i < 20; i++) {
            averageTime += times[i];
        }
        averageTime /= 10;

        //then
        System.out.println(averageTime);
    }

    @Test
    public void hashSize4x4096PerformanceTime() {

        //given
        int basicSize = 4096;
        int multiply = 4;
        long time;
        long averageTime = 0;
        long[] times = new long[testsAmount];

        //when
        for (int i = 0; i < testsAmount; i++) {
            hashListChainingStr = new HashListChaining<>(basicSize * multiply);
            readAndAddFromFile(hashListChainingStr);
            time = searchFromFile(hashListChainingStr);
            times[i] = time;
        }
        Arrays.sort(times);

        for (int i = 10; i < 20; i++) {
            averageTime += times[i];
        }
        averageTime /= 10;

        //then
        System.out.println(averageTime);
    }

    @Test
    public void hashSize5x4096PerformanceTime() {

        //given
        int basicSize = 4096;
        int multiply = 5;
        long time;
        long averageTime = 0;
        long[] times = new long[testsAmount];

        //when
        for (int i = 0; i < testsAmount; i++) {
            hashListChainingStr = new HashListChaining<>(basicSize * multiply);
            readAndAddFromFile(hashListChainingStr);
            time = searchFromFile(hashListChainingStr);
            times[i] = time;
        }
        Arrays.sort(times);

        for (int i = 10; i < 20; i++) {
            averageTime += times[i];
        }
        averageTime /= 10;

        //then
        System.out.println(averageTime);
    }

    @Test
    public void hashSize6x4096PerformanceTime() {

        //given
        int basicSize = 4096;
        int multiply = 6;
        long time;
        long averageTime = 0;
        long[] times = new long[testsAmount];

        //when
        for (int i = 0; i < testsAmount; i++) {
            hashListChainingStr = new HashListChaining<>(basicSize * multiply);
            readAndAddFromFile(hashListChainingStr);
            time = searchFromFile(hashListChainingStr);
            times[i] = time;
        }
        Arrays.sort(times);

        for (int i = 10; i < 20; i++) {
            averageTime += times[i];
        }
        averageTime /= 10;

        //then
        System.out.println(averageTime);
    }

    @Test
    public void hashSize7x4096PerformanceTime() {

        //given
        int basicSize = 4096;
        int multiply = 7;
        long time;
        long averageTime = 0;
        long[] times = new long[testsAmount];

        //when
        for (int i = 0; i < testsAmount; i++) {
            hashListChainingStr = new HashListChaining<>(basicSize * multiply);
            readAndAddFromFile(hashListChainingStr);
            time = searchFromFile(hashListChainingStr);
            times[i] = time;
        }
        Arrays.sort(times);

        for (int i = 10; i < 20; i++) {
            averageTime += times[i];
        }
        averageTime /= 10;

        //then
        System.out.println(averageTime);
    }

    @Test
    public void hashSizeAsA4093PrimeNumberPerformanceTime() {

        //given
        int basicSize = 4093;
        long time;
        long averageTime = 0;
        long[] times = new long[testsAmount];

        //when
        for (int i = 0; i < testsAmount; i++) {
            hashListChainingStr = new HashListChaining<>(basicSize);
            readAndAddFromFile(hashListChainingStr);
            time = searchFromFile(hashListChainingStr);
            times[i] = time;
        }
        Arrays.sort(times);

        for (int i = 10; i < 20; i++) {
            averageTime += times[i];
        }
        averageTime /= 10;

        //then
        System.out.println(averageTime);
    }

    @Test
    public void hashSizeAsA8191PrimeNumberPerformanceTime() {

        //given
        int basicSize = 8191;
        long time;
        long averageTime = 0;
        long[] times = new long[testsAmount];

        //when
        for (int i = 0; i < testsAmount; i++) {
            hashListChainingStr = new HashListChaining<>(basicSize);
            readAndAddFromFile(hashListChainingStr);
            time = searchFromFile(hashListChainingStr);
            times[i] = time;
        }
        Arrays.sort(times);

        for (int i = 10; i < 20; i++) {
            averageTime += times[i];
        }
        averageTime /= 10;

        //then
        System.out.println(averageTime);
    }

    @Test
    public void hashSizeAsA16381PrimeNumberPerformanceTime() {

        //given
        int basicSize = 16381;
        long time;
        long averageTime = 0;
        long[] times = new long[testsAmount];

        //when
        for (int i = 0; i < testsAmount; i++) {
            hashListChainingStr = new HashListChaining<>(basicSize);
            readAndAddFromFile(hashListChainingStr);
            time = searchFromFile(hashListChainingStr);
            times[i] = time;
        }
        Arrays.sort(times);

        for (int i = 10; i < 20; i++) {
            averageTime += times[i];
        }
        averageTime /= 10;

        //then
        System.out.println(averageTime);
    }

    @Test
    public void hashSizeAsA32771PrimeNumberPerformanceTime() {

        //given
        int basicSize = 32771;
        long time;
        long averageTime = 0;
        long[] times = new long[testsAmount];

        //when
        for (int i = 0; i < testsAmount; i++) {
            hashListChainingStr = new HashListChaining<>(basicSize);
            readAndAddFromFile(hashListChainingStr);
            time = searchFromFile(hashListChainingStr);
            times[i] = time;
        }
        Arrays.sort(times);

        for (int i = 10; i < 20; i++) {
            averageTime += times[i];
        }
        averageTime /= 10;

        //then
        System.out.println(averageTime);
    }

    @Test
    public void hashSizeAsA65537PrimeNumberPerformanceTime() {

        //given
         int basicSize = 65537;
        long time;
        long averageTime = 0;
        long[] times = new long[testsAmount];

        //when
        for (int i = 0; i < testsAmount; i++) {
            hashListChainingStr = new HashListChaining<>(basicSize);
            readAndAddFromFile(hashListChainingStr);
            time = searchFromFile(hashListChainingStr);
            times[i] = time;
        }
        Arrays.sort(times);

        for (int i = 10; i < 20; i++) {
            averageTime += times[i];
        }
        averageTime /= 10;

        //then
        System.out.println(averageTime);
    }

    @Test
    public void hashSizeAsA131071PrimeNumberPerformanceTime() {

        //given
        int basicSize = 131071;
        long time;
        long averageTime = 0;
        long[] times = new long[testsAmount];

        //when
        for (int i = 0; i < testsAmount; i++) {
            hashListChainingStr = new HashListChaining<>(basicSize);
            readAndAddFromFile(hashListChainingStr);
            time = searchFromFile(hashListChainingStr);
            times[i] = time;
        }
        Arrays.sort(times);

        for (int i = 10; i < 20; i++) {
            averageTime += times[i];
        }
        averageTime /= 10;

        //then
        System.out.println(averageTime);
        System.out.println(hashListChainingStr.countLoadFactor());
    }

    @Test
    public void hashSizeAsA262147PrimeNumberPerformanceTime() {

        //given
        int basicSize = 262217;
        long time;
        long averageTime = 0;
        long[] times = new long[testsAmount];

        //when
        for (int i = 0; i < testsAmount; i++) {
            hashListChainingStr = new HashListChaining<>(basicSize);
            readAndAddFromFile(hashListChainingStr);
            time = searchFromFile(hashListChainingStr);
            times[i] = time;
        }
        Arrays.sort(times);

        for (int i = 10; i < 20; i++) {
            averageTime += times[i];
        }
        averageTime /= 10;

        //then
        System.out.println(averageTime);
    }

}
