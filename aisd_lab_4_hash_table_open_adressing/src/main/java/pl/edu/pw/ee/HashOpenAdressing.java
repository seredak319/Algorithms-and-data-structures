package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

import java.util.NoSuchElementException;

public abstract class HashOpenAdressing<T extends Comparable<T>> implements HashTable<T> {

    private final T nil = null;
    private int size;
    private int nElems;
    private T[] hashElems;
    private final double correctLoadFactor;
    private final Comparable DEL_MARK = o -> 0;

    HashOpenAdressing() {
        this(2039); // initial size as random prime number
    }

    HashOpenAdressing(int size) {
        validateHashInitSize(size);
        this.size = size;
        this.hashElems = (T[]) new Comparable[this.size];
        this.correctLoadFactor = 0.75;
    }

    @Override
    public void put(T newElem) {
        validateInputElem(newElem);
        resizeIfNeeded();
        boolean alreadyAdded = false;
        nElems++;

        int key = newElem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);
        int tempHashId = hashId;

        while (hashElems[hashId] != nil) {
            if(hashElems[hashId] == DEL_MARK && !alreadyAdded){
                tempHashId = hashId;
                alreadyAdded = true;
            }
            if(newElem.equals(hashElems[hashId])){
                tempHashId = hashId;
                alreadyAdded = true;
                nElems--;
                break;
            }
            if (i + 1 == size) {
                doubleResize();
                i = -1;
                nElems++;
            }
            i = (i + 1) % size;
            hashId = hashFunc(key, i);
        }
        if(!alreadyAdded){
            tempHashId = hashId;
        }
        hashElems[tempHashId] = newElem;
    }

    @Override
    public T get(T elem) {
        if(nElems < 1){
            throw new NoSuchElementException();
        }
        int counter = 0;
        validateInputElem(elem);

        int key = elem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        while (hashElems[hashId] != nil) {
            counter++;
            if(elem.equals(hashElems[hashId])){
                return hashElems[hashId];
            }
            i = (i + 1) % size;
            hashId = hashFunc(key, i);
            if(counter > hashElems.length){
                break;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public void delete(T elem) {
        validateInputElem(elem);
        if(nElems < 1){
            throw new NoSuchElementException();
        }
        int counter = 0;
        int key = elem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);
        while (hashElems[hashId] != nil) {
            counter++;
            if(elem.equals(hashElems[hashId])){
                hashElems[hashId] = (T) DEL_MARK;
                nElems--;
                return;
            }
            i = (i + 1) % size;
            hashId = hashFunc(key, i);
            if(counter > hashElems.length){
                break;
            }
        }
        throw new NoSuchElementException();
    }

    private void validateHashInitSize(int initialSize) {
        if (initialSize < 1) {
            throw new IllegalArgumentException("Initial size of hash table cannot be lower than 1!");
        }
    }

    private void validateInputElem(T newElem) {
        if (newElem == null) {
            throw new IllegalArgumentException("Input elem cannot be null!");
        }
    }

    abstract int hashFunc(int key, int i);

    int getSize() {
        return size;
    }

    private void resizeIfNeeded() {
        double loadFactor = countLoadFactor();
        if (loadFactor >= correctLoadFactor) {
            doubleResize();
        }
    }

    public double countLoadFactor() {
        return (double) nElems / size;
    }

    private void doubleResize() {
        this.size *= 2;

        T[] oldElems = hashElems;
        hashElems = (T[]) new Comparable[this.size];
        nElems = 0;

        for (T elem : oldElems) {
            if (elem != nil && elem != DEL_MARK) {
                put(elem);
            }
        }
    }
}
