package pl.edu.pw.ee;

public class HashQuadraticProbing<T extends Comparable<T>> extends HashOpenAdressing<T> {

    private final double a;
    private final double b;

    public HashQuadraticProbing() {
        super();
        a = 5;
        b = 2;
    }

    public HashQuadraticProbing(int size, double a, double b) {
        super(size);
        if(a==0 & b==0){
            throw new IllegalArgumentException("Constants a and b should not be zero.");
        }
        this.a = a;
        this.b = b;
    }

    @Override
    int hashFunc(int key, int i) {
        int m = getSize();

        int hash = (int) ((key + a*i + b*i*i) % m);

        hash = hash < 0 ? -hash : hash;
        return hash;
    }

}
