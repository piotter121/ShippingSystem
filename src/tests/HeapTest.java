/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import dataStructures.Heap;
import java.util.Random;

/**
 *
 * @author Piotrek
 */
public class HeapTest {

    public static void main(String[] args) {
        Heap<Integer> h = new Heap<>();
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            h.push(r.nextInt());
        }
        for (int i = 0; i < 10; i++) 
            System.out.println(h.pop());
    }
}
