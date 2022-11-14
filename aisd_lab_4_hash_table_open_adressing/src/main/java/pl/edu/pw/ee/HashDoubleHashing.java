package pl.edu.pw.ee;

public class HashDoubleHashing<T extends Comparable<T>> extends HashOpenAdressing<T> {

    public HashDoubleHashing() {
        super();
    }

    public HashDoubleHashing(int size) {
        super(size);
        if(size == 3){
            throw new IllegalArgumentException("Double hashing hash does not allow number three as a size.");
        }
    }

    @Override
    int hashFunc(int key, int i) {
        int m = getSize();

        int hash = (f(key,m) + i*g(key,m)) % m;

        hash = hash < 0 ? -hash : hash;
        return hash;
    }

    private int f(int k, int m){
        return k % m;
    }

    private int g(int k, int m){
        return 1 + (k % (m-3));
    }
}
