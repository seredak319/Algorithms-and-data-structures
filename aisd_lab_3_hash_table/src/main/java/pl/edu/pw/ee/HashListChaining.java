package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

import java.util.NoSuchElementException;

public class HashListChaining<T extends Comparable<T>> implements HashTable<T> {

    private final Elem<T> nil = null;
    private final Elem<T>[] hashElems;
    private int nElem;

    private static class Elem<T> {

        private T value;
        private Elem<T> next;

        Elem(T value, Elem<T> nextElem) {
            this.value = value;
            this.next = nextElem;
        }
    }

    public HashListChaining(int size) {
        if (size <= -1) {
            throw new NegativeArraySizeException("Wartość inicjalizacji powinna być nieujemna");
        }
        if (size == 0) {
            throw new ArithmeticException("Wielkość HashList powinna być dodatnia");
        }

        hashElems = new Elem[size];
        initializeHash();
    }

    @Override
    public void add(T value) {
        if (value == null) {
            throw new NullPointerException("Dodawana wartość nie może być pusta");
        }

        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem<T> oldElem = hashElems[hashId];
        while (oldElem != nil && !oldElem.value.equals(value)) {
            oldElem = oldElem.next;
        }
        if (oldElem != nil) {
            oldElem.value = value;
        } else {
            hashElems[hashId] = new Elem<>(value, hashElems[hashId]);
            nElem++;
        }
    }

    @Override
    public T get(T value) {
        if (value == null) {
            throw new NullPointerException("Pobierana wartość nie może być pusta");
        }

        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem<T> elem = hashElems[hashId];

        while (elem != nil && !elem.value.equals(value)) {
            elem = elem.next;
        }

        return elem != nil ? elem.value : (T) nil;
    }

    @Override
    public void delete(T value) {
        if (value == null) {
            throw new NullPointerException("Usuwana wartość nie może być pusta");
        }

        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem<T> elem = hashElems[hashId];
        Elem<T> elemPrev = elem;

        if (elem == null) {
            throw new NoSuchElementException("Nie ma takiego elementu do usuniecia");
        }

        while (elem != nil) {
            if (elem.value.equals(value) && elem.equals(elemPrev)) {
                hashElems[hashId] = elem.next;
                nElem--;
                return;
            } else if (elem.value.equals(value)) {
                elemPrev.next = elem.next;
                nElem--;
                return;
            }

            elemPrev = elem;
            elem = elem.next;
        }

        if (elem == null) {
            throw new NoSuchElementException("Nie ma takiego elementu do usuniecia");
        }

        elem = nil;
    }

    public double countLoadFactor() {
        double size = hashElems.length;
        return nElem / size;
    }

    private void initializeHash() {
        int n = hashElems.length;

        for (int i = 0; i < n; i++) {
            hashElems[i] = nil;
        }
    }

    private int countHashId(int hashCode) {
        int n = hashElems.length;
        return Math.abs(hashCode) % n;
    }

}
