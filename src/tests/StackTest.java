/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import dataStructures.Stack;
import java.util.Random;

/**
 *
 * @author Piotrek
 */
public class StackTest {
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        int stackSize;
        Random r = new Random();
        stackSize = r.nextInt(20);
        for (int i = 0; i < stackSize; i++) {
            s.push(r.nextInt(100));
        }
        System.out.println(s);
        System.out.println("Element zdjęty to " + s.pop());
    }
}
