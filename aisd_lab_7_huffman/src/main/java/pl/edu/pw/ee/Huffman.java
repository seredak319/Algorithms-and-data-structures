package pl.edu.pw.ee;

import pl.edu.pw.ee.pq.SNode;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class Huffman {

    private String readFile;

    private final String textFile = "text.txt";
    private final String codeFile = "text_code.txt";
    private final String treeFile = "text_tree.txt";
    private final String result = "result.txt";

    public int huffman(String pathToRootDir, boolean compress) {
        validInput(pathToRootDir);

        SNode tree;
        if (compress) {
            HuffmanTree huffmanTree = new HuffmanTree();

            File file = new File(pathToRootDir + textFile);
            validFile(file, textFile);

            HashMap<Character, Integer> frequencies = readAndPutCharacters(file);
            if (frequencies.isEmpty()) {
                return 0;
            }

            HashMap<Character, String> huffmanCodes = huffmanTree.buildTree(frequencies);

            String code = encodeFile(huffmanCodes);
            long codeSize = writeCodeIntoFile(code, file.getName(), pathToRootDir, "_code.txt");

            String treeCode = huffmanTree.encodeHuffmanTree();
            long treeSize = writeCodeIntoFile(treeCode, file.getName(), pathToRootDir, "_tree.txt");

            if ((codeSize + treeSize) * 8 > Integer.MAX_VALUE) {
                throw new ArithmeticException("Given file is too large, can not return information of number of bits,"
                        + "\n because bit size of encoded files is greater than number that can be hold by integer.");
            }
            return Math.toIntExact((codeSize + treeSize) * 8);
        } else {
            HuffmanTree huffmanTree1 = new HuffmanTree();

            File fileCode = new File(pathToRootDir + codeFile);
            validFile(fileCode, codeFile);

            File fileTree = new File(pathToRootDir + treeFile);
            validFile(fileTree, treeFile);

            String readCode = readEncodedFile(fileCode.getName(), pathToRootDir);
            if (readCode == null) {
                return 0;
            }
            String treeReadCode = readEncodedFile(fileTree.getName(), pathToRootDir);
            if (treeReadCode == null) {
                return 0;
            }

            tree = huffmanTree1.decodeHuffmanTree(treeReadCode);
            String decoded = decodeFile(readCode, tree);
            writeStringIntoFile(decoded, pathToRootDir);
            return decoded.length();
        }
    }

    private HashMap<Character, Integer> readAndPutCharacters(File file) {
        HashMap<Character, Integer> hashMap = new HashMap<>();

        try {
            readFile = Files.readString(Path.of(file.toURI()));
        } catch (IOException e) {
            throw new RuntimeException("File [ " + file.getName() + " ] can not be read properly.");
        }

        for (char c : readFile.toCharArray()) {
            if (c >= 128) {
                throw new IllegalArgumentException("Symbol [ " + (char) c + " ] can not be encoded using one byte.");
            }
            try {
                hashMap.put(c, hashMap.getOrDefault(c, 0) + 1);
            } catch (Error e) {
                throw new OutOfMemoryError("Given file [ " + file.getName() + " ] is too large,"
                        + "\n there is lack of space to store it.");
            }
        }

        return hashMap;
    }

    private String encodeFile(HashMap<Character, String> codes) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            for (char c : readFile.toCharArray()) {
                stringBuilder.append(codes.get(c));
            }

            return stringBuilder.toString();
        } catch (Error error) {
            throw new OutOfMemoryError("Lack of space occurred. Too big file!");
        }
    }

    private long writeCodeIntoFile(String code, String outPutName, String dirPath, String suffix) {
        outPutName = reconstructOutPutName(outPutName, suffix);
        int howManyBitesOfLastByteShouldBeTakeIntoAccount = code.length() % 8 == 0 ? 8 : code.length() % 8;
        File file = new File(dirPath + outPutName);
        file.setWritable(true);

        try {
            OutputStream os = new FileOutputStream(dirPath + outPutName);
            os.write(howManyBitesOfLastByteShouldBeTakeIntoAccount);
            for (int i = 0; i <= code.length() / 8; i++) {
                if (code.length() / 8 - i > 0) {
                    String temp = code.substring(8 * i, 8 + 8 * i);
                    byte b = (byte) Integer.parseInt(temp, 2);
                    os.write(b);
                } else {
                    if (validBitCode(code, 8 * i + howManyBitesOfLastByteShouldBeTakeIntoAccount)) {
                        String temp = code.substring(8 * i, 8 * i + howManyBitesOfLastByteShouldBeTakeIntoAccount);
                        byte b = (byte) Integer.parseInt(temp, 2);
                        os.write(b);
                    }
                }
            }
            os.close();

        } catch (IOException e) {
            throw new RuntimeException("Code can not be written to [ " + outPutName + " ].");
        }
        file.setReadOnly();
        return file.length();

    }

    private String readEncodedFile(String outPutName, String dirPath) {
        int howManyBites;
        byte[] bytes;
        try {
            bytes = Files.readAllBytes(Paths.get(dirPath + outPutName));
        } catch (IOException e) {
            throw new RuntimeException("File [ " + outPutName + " ] can not be read properly.");
        }

        if (bytes.length == 0) {
            return null;
        }

        howManyBites = bytes[0];
        StringBuilder sb = new StringBuilder();
        try {
            for (int i = 1; i < bytes.length - 1; i++) {
                String temp = Integer.toBinaryString(bytes[i] & 0xff);
                temp = String.format("%8s", temp).replace(' ', '0');
                sb.append(temp);
            }

            String lastByte = Integer.toBinaryString(bytes[bytes.length - 1] & 0xff);
            lastByte = String.format("%8s", lastByte).replace(' ', '0');
            lastByte = lastByte.substring(8 - howManyBites);
            sb.append(lastByte);
        } catch (Exception e) {
            throw new IllegalArgumentException("Incorrect code in encoded file [ " + outPutName + " ]");
        }
        try {
            return sb.toString();
        } catch (Error error) {
            throw new OutOfMemoryError("Lack of space occurred. Too big file!");
        }

    }

    private String decodeFile(String code, SNode head) {
        StringBuilder stringBuilder = new StringBuilder();
        SNode temp = head;

        for (int c : code.toCharArray()) {
            if (c == '0') {
                if (temp.getLeft() != null) {
                    temp = temp.getLeft();
                }
            } else if (c == '1') {
                if (temp.getRight() != null) {
                    temp = temp.getRight();
                }
            } else {
                throw new IllegalArgumentException("Incorrect Huffman code occurs, we have a problem.");
            }

            if (temp.getRight() == null && temp.getLeft() == null) {
                try {
                    stringBuilder.append(temp.getKey());
                } catch (Error error) {
                    throw new OutOfMemoryError("Lack of space occurred. Too big file!");
                }
                temp = head;
            }
        }

        try {
            return stringBuilder.toString();
        } catch (Error error) {
            throw new OutOfMemoryError("Lack of space occurred. Too big file!");
        }
    }

    private void writeStringIntoFile(String text, String dirPath) {
        try {
            FileWriter fileWriter = new FileWriter(dirPath + result);
            fileWriter.write(text);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("File [ " + result + " ] can not be written properly.");
        }
    }

    private void validInput(String path) {
        if (path == null) {
            throw new NullPointerException("Path to the directory has to be given.");
        }

        File file = new File(path);
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("Given path can not find a directory.");
        }
    }

    private boolean validBitCode(String code, int i) {
        return code.length() >= i;
    }

    private String reconstructOutPutName(String outPutName, String suffix) {
        String out = outPutName.substring(0, outPutName.length() - 4);
        out = out + suffix;

        return out;
    }

    private void validFile(File file, String fileName) {
        if (!file.canRead()) {
            throw new NullPointerException("File [ " + fileName + " ] can not be read.");
        }
    }
}
