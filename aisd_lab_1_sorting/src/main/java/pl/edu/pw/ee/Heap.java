package pl.edu.pw.ee;

import java.util.List;
import pl.edu.pw.ee.services.HeapExtension;
import pl.edu.pw.ee.services.HeapInterface;

public class Heap<T extends Comparable<T>> implements HeapInterface<T>, HeapExtension {

    private List<T> data;

    public Heap(List<T> data) {
        this.data = data;
    }

    @Override
    public void put(T item) {
        // TODO
        data.add(item);
        build();

    }

    @Override
    public T pop() {
        // TODO
        T t = data.remove(0);
        heapify(0, data.size());
        return t;
    }

    @Override
    public void build() {
        int n = data.size();

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(i, n);
        }
    }

    @Override
    public void heapify(int startId, int endId) {
        // TODO
        endId--;
        while (startId < endId) {
            if (data.get(startId) < data.get()) {
                swap(from, to);
            
            }
        }
    }

    private void swap(int from, int to) {

        T temp = data.get(from);
        data.remove(from);
        data.add(from, data.get(to));
        data.add(to, temp);

    }

}
