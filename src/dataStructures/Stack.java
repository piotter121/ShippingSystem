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

    public Stack() {
        stack = new ArrayList<>();
    }

    public void push(T value) {
        stack.add(value);
    }

    public T pop() {
        return stack.remove(stack.size() - 1);
    }
    
    @Override
    public String toString() {
        String result = new String();
        for (T i : stack) {
            result += i.toString() + '\n';
        }
        return result;
    }
}
