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
public class Matrix<T> {

    private Object[] matrix;
    private int rows;
    private int cols;

    public Matrix(int m, int n) {
        rows = m;
        cols = n;
        matrix = new Object[rows * cols];
    }

    public void set(int m, int n, T value) {
        if (m < rows && n < cols) {
            matrix[m * cols + n] = value;
        }
    }

    public T get(int m, int n) {
        if (m < rows && n < cols) {
            return (T) matrix[m * cols + n];
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        String result = new String();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result += get(i, j).toString() + ' ';
            }
            result += '\n';
        }
        return result;
    }
}
