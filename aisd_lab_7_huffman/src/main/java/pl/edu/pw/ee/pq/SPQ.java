package pl.edu.pw.ee.pq;

import pl.edu.pw.ee.services.PriorityQueue;

import java.util.Arrays;

public class SPQ implements PriorityQueue<SNode> {

    private final int INIT_SIZE = 512;
    private int n;
    private int size;
    private SNode[] pq;

    public SPQ(int size) {
        validSize(size);
        pq = new SNode[size];
        this.size = size;
    }

    public SPQ() {
        pq = new SNode[INIT_SIZE];
        this.size = INIT_SIZE;
    }

    @Override
    public void put(SNode node) {
        validPut(node);
        if (n == size) {
            resizeArray();
        }
        pq[n++] = node;
        heapUp();
    }

    @Override
    public SNode pop() {
        validPop();
        SNode result = pq[0];
        pq[0] = pq[--n];
        heapDown();
        return result;
    }

    @Override
    public int getSize() {
        return n;
    }

    private void heapUp() {
        int i = n - 1;
        while (i > 0) {
            int p = (i - 1) / 2;
            if (pq[i].compareTo(pq[p]) >= 0) {
                return;
            }
            swap(i, p);
            i = p;
        }
    }

    private void heapDown() {
        int i = 0;
        int c = 0;

        while (c < n) {
            if (c + 1 < n && (pq[c].compareTo(pq[c + 1]) > 0)) {
                c++;
            }
            if (pq[i].compareTo(pq[c]) <= 0) {
                return;
            }
            swap(i, c);
            i = c;
            c = 2 * i + 1;
        }
    }

    private void swap(int i, int j) {
        SNode temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private void resizeArray() {
        this.size = size * 2;
        pq = Arrays.copyOf(pq, size);
    }

    private void validSize(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Set size must be greater than one.");
        }
    }

    private void validPop() {
        if (n < 1) {
            throw new NullPointerException("There is nothing to pop from priority queue.");
        }
    }

    private void validPut(SNode node) {
        if (node == null) {
            throw new NullPointerException("Put value must not be null.");
        }
    }
}
