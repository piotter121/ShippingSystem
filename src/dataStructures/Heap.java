/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataStructures;

/**
 *
 * @author Piotrek
 */
public class Heap<T extends Comparable<T>> implements Queue<T> {

    private Comparable[] heap;
    private int n;
    private int tableSize;

    public Heap() {
        n = 0;
        tableSize = 30;
        heap = new Comparable[tableSize];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public void push(T value) {
        heap[n] = value;
        heapUp();
        n++;
        if (n == tableSize)
            resize();
    }

    @Override
    public T pop() {
        T tmp = null;
        if (!isEmpty()) {
            tmp = get(0);
            heap[0] = get(--n);
            heapDown();
        }
        return tmp;
    }

    public void heapUp() {
        int i = n;
        int p = (n - 1) / 2;
        while (i > 0 && get(p).compareTo(get(i)) == -1) {
            swap(p, i);
            i = p;
            p = (i - 1) / 2;
        }
    }

    private void swap(int p, int i) {
        T tmp;
        tmp = get(p);
        heap[p] = get(i);
        heap[i] = tmp;
    }

    private void heapDown() {
        int i = 0;
        int c = 2 * i + 1;
        while (c < n) {
            if (c + 1 < n && get(c + 1).compareTo(get(c)) == 1) {
                c++;
            }
            if (get(c).compareTo(get(i)) <= 0) {
                return;
            }
            swap(i, c);
            i = c;
            c = 2 * i + 1;
        }
    }

    private T get(int i) {
        return (T) heap[i];
    }

    @Override
    public String toString() {
        String result = new String();
        for (int i = 0; i < n; i++) {
            result += get(i).toString() + ' ';
            
        }
        return result;
    }

    private void resize() {
        tableSize *= 2;
        Comparable[] newHeap = new Comparable[tableSize];
        for (int i = 0; i < n; i++) {
            newHeap[i] = heap[i];
        }
        heap = newHeap;
    }

}
