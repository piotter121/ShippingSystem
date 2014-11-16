/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataStructures;

import java.util.ArrayList;

/**
 *
 * @author Piotrek
 */
public class Heap<T> implements Queue<T> {
    private ArrayList<T> heap;
    private int height;
    
    public Heap() {
        heap = new ArrayList<>();
        height = 0;
    }

    @Override
    public boolean isEmpty() {
        return height == 0;
    }

    @Override
    public void push(T value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T pop() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void heapSort() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
