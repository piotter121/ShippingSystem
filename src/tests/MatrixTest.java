/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import dataStructures.Matrix;
import java.util.Random;

/**
 *
 * @author Piotrek
 */
public class MatrixTest {
    public static void main(String[] args) {
        Matrix<Integer> m = new Matrix<>(10, 11);
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 11; j++) {
                m.set(i, j, r.nextInt(20));
            }
        }
        System.out.println(m);
    }
}
