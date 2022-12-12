package pl.edu.pw.ee.services;

import pl.edu.pw.ee.pq.SNode;

public interface Node {

    void setFreq(int value);

    int getFreq();

    char getKey();

    void setRight(SNode right);

    void setLeft(SNode left);

    SNode getRight();

    SNode getLeft();
}
