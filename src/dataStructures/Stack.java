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
public class Stack<T> {
    ArrayList<T> stack;
    int n;
    
    public Stack() {
        n = 0;
        stack = new ArrayList<>();
    }    
    
    public void push(T value) {
        stack.add(value);
        n++;
    }
    
    public T pop() {
        return stack.remove(stack.size());
    }
}
