package pl.edu.pw.ee.pq;

import pl.edu.pw.ee.services.Node;

import static java.lang.Integer.compare;

public class SNode implements Node, Comparable<SNode> {

    private int freq;
    private final char key;
    private SNode left;
    private SNode right;

    public SNode(char c, int val) {
        this.freq = val;
        this.key = c;
    }

    public SNode(char c, int val, SNode left, SNode right) {
        validSonOfTheNode(right);
        validSonOfTheNode(left);
        this.key = c;
        this.freq = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public void setFreq(int val) {
        validValue(val);
        this.freq = val;
    }

    @Override
    public char getKey() {
        return key;
    }

    @Override
    public int getFreq() {
        return freq;
    }

    @Override
    public void setRight(SNode right) {
        validSonOfTheNode(right);
        this.right = right;
    }

    @Override
    public void setLeft(SNode left) {
        validSonOfTheNode(left);
        this.left = left;
    }

    @Override
    public SNode getRight() {
        return this.right;
    }

    @Override
    public SNode getLeft() {
        return this.left;
    }

    private void validSonOfTheNode(SNode son) {
        if (son == null) {
            throw new NullPointerException("Set child of the node must not be null.");
        }
    }

    private void validValue(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Set frequency should be greater or equal to zero.");
        }
    }

    @Override
    public int compareTo(SNode o) {
        return compare(this.freq, o.freq);
    }
}
