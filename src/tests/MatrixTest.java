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
        int rows = 0;
        int cols = 0;
        Matrix<Integer> m = new Matrix<>(rows, cols);
        Random r = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                m.set(i, j, r.nextInt(20));
            }
        }
        System.out.println(m);
    }
}
