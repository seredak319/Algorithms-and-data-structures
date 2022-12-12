package pl.edu.pw.ee;

import pl.edu.pw.ee.pq.SNode;
import pl.edu.pw.ee.pq.SPQ;

import java.util.HashMap;

public class HuffmanTree {

    private SNode head;
    private String code;
    private HashMap<Character, String> hashMapTree;

    public HashMap<Character, String> buildTree(HashMap<Character, Integer> frequencies) {
        validHashMap(frequencies);
        if (frequencies.isEmpty()) {
            return null;
        }

        hashMapTree = new HashMap<>();
        SPQ spq = new SPQ();

        for (HashMap.Entry<Character, Integer> iter : frequencies.entrySet()) {
            validFrequenciesValues(iter.getKey(), iter.getValue());
            try {
                SNode sNode = new SNode(iter.getKey(), iter.getValue());
                spq.put(sNode);
            } catch (Error error) {
                throw new OutOfMemoryError("Lack of space occurred. Too big file!");
            }
        }

        if (spq.getSize() > 1) {
            while (spq.getSize() > 1) {
                SNode leftChild = spq.pop();
                SNode rightChild = spq.pop();
                int sumOfFrequencies = leftChild.getFreq() + rightChild.getFreq();
                try {
                    SNode headChild = new SNode('\0', sumOfFrequencies, leftChild, rightChild);
                    spq.put(headChild);
                } catch (Error error) {
                    throw new OutOfMemoryError("Lack of space occurred. Too big file!");
                }
            }

            head = spq.pop();
            hashMapTree = buildCode(head, "");
        } else if (spq.getSize() == 1) {
            head = spq.pop();
            hashMapTree.put(head.getKey(), "0");
        }

        return hashMapTree;
    }

    private HashMap<Character, String> buildCode(SNode sNode, String code) {
        if (sNode.getLeft() == null && sNode.getRight() == null) {
            try {
                hashMapTree.put(sNode.getKey(), code);
            } catch (Error error) {
                throw new OutOfMemoryError("Lack of space occurred. Too big file!");
            }
        }
        if (sNode.getRight() != null) {
            buildCode(sNode.getRight(), code.concat("1"));
        }
        if (sNode.getLeft() != null) {
            buildCode(sNode.getLeft(), code.concat("0"));
        }

        return hashMapTree;
    }

    public String encodeHuffmanTree() {
        if (head == null) {
            throw new NullPointerException("Huffman tree should have been built before encoding. "
                    + "\nUse:     huffmanTree.buildTree(HashMap<Character,Integer>)"
                    + "\nsuch HashMap should contain frequencies of appearance of each symbol");
        }

        StringBuilder stringBuilder = new StringBuilder();
        try {
            encodeTree(head, stringBuilder);
        } catch (Error error) {
            throw new StackOverflowError("Lack of space occurred. Too big file!");
        }
        try {
            return stringBuilder.toString();
        } catch (Error error) {
            throw new OutOfMemoryError("Lack of space occurred.");
        }
    }

    private void encodeTree(SNode sNode, StringBuilder stringBuilder) {
        if (sNode.getLeft() == null && sNode.getRight() == null) {
            stringBuilder.append('1');
            String charVal = Integer.toBinaryString(sNode.getKey());
            charVal = String.format("%8s", charVal).replace(' ', '0');
            stringBuilder.append(charVal);
        } else {
            stringBuilder.append('0');
            encodeTree(sNode.getLeft(), stringBuilder);
            encodeTree(sNode.getRight(), stringBuilder);
        }
    }

    public SNode decodeHuffmanTree(String code) {
        validCode(code);
        if (code.length() == 0) {
            return null;
        }
        this.code = code;
        try {
            return decodeTree();
        } catch (Error error) {
            throw new OutOfMemoryError("Lack of space occurred. Too big file!");
        }
    }

    private SNode decodeTree() {
        validBitCode(1);
        if (code.charAt(0) == '1') {
            validBitCode(8);
            String charVal = code.substring(1, 9);
            int val = Integer.parseInt(charVal, 2);
            code = code.substring(9);
            return new SNode((char) val, 0);
        } else {
            validBitCode(1);
            code = code.substring(1);
            SNode leftChild = decodeTree();
            SNode rightChild = decodeTree();
            return new SNode('\0', 0, leftChild, rightChild);
        }
    }

    private void validBitCode(int i) {
        if ((i == 8 & code.length() < 9) || (i == 1 & code.length() < 1)) {
            throw new IllegalArgumentException("Given code is not proper."
                    + "\nIt should have a path to node and binary character value in next 8 characters."
                    + "\nFor instance: 101100001, provides one node with value 'a' "
                    + "\n 0 - recursive step to next node, 1 - leaf occurred, then next 8 characters is a value.");
        }
    }

    private void validCode(String code) {
        if (code == null) {
            throw new NullPointerException("Huffman tree can not be decoded with empty code");
        }

        if (code.length() > 0 && !code.matches("^[01]+$")) {
            throw new IllegalArgumentException("Given code is not proper."
                    + "\nCode should have only contain '0' and '1' characters");
        }
    }

    private void validHashMap(HashMap<?, ?> hashMap) {
        if (hashMap == null) {
            throw new NullPointerException("HashMap can not be null.");
        }
    }

    private void validFrequenciesValues(Character c, Integer i) {
        if (c == null || i == null) {
            throw new NullPointerException("Given HashMap<Character,Integer> can not contain null values.");
        }

        if (c < 0 || c > 127) {
            throw new IllegalArgumentException("Characters in given HashMap<Character,Integer> should only contain ASCII symbols."
                    + "\n<Character,Integer>  --  <" + c + "," + i + ">");
        }

        if (i < 1) {
            throw new IllegalArgumentException("Integers in given HashMap<Character,Integer> should be positive ones."
                    + "\n<Character,Integer>  --  <" + c + "," + i + ">");
        }
    }
}
