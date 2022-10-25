package pl.edu.pw.ee;

import java.util.List;
import pl.edu.pw.ee.services.HeapExtension;
import pl.edu.pw.ee.services.HeapInterface;

public class Heap<T extends Comparable<T>> implements HeapInterface<T>, HeapExtension {

    private List<T> data;

    public Heap(List<T> data) {
        this.data = data;
        if(data == null){
            throw new IllegalArgumentException("Podana lista nie istenieje");
        }
        build();
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

        if(data.isEmpty()){
            throw new ArrayIndexOutOfBoundsException("Z pustego to i Salomon nie naleje!");
        }
        swap(data,0,data.size()-1);
        T t = data.remove(data.size()-1);
        build();
        return t;
    }

    @Override
    public void build() {

        int n = data.size();

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(i, n);
        }
    }

    //int p;
    @Override
    public void heapify(int startId, int endId) {
        // TODO

        int index = 2*startId+1;
        while (index < endId) {
            if(index + 1 < endId && data.get(index+1).compareTo(data.get(index)) > 0){
                index++;
            }
            if(data.get(startId).compareTo(data.get(index)) < 0) {
                swap(data,index,startId);
            }
            startId = index;
            index = 2*startId + 1;
        }
    }

    private void swap(List<T> data, int from, int to) {
        if(from == to){
            return;
        }
        T temp = data.get(from);
        data.set(from,data.get(to));
        data.set(to, temp);
    }
}
