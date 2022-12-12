package pl.edu.pw.ee.services;

public interface PriorityQueue<SNode> {

    void put(SNode node);

    SNode pop();

    int getSize();
}
