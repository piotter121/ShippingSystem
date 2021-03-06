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
public class Matrix<T> {

    private Object[] matrix;
    private int rows;
    private int cols;

    public Matrix(int m, int n) {
        if (m > 0 && n > 0) {
            rows = m;
            cols = n;
        } else {
            rows = 0;
            cols = 0;
        }
        matrix = new Object[rows * cols];
    }

    public void set(int m, int n, T value) {
        if (m >= 0 && m < rows && n >= 0 && n < cols) {
            matrix[m * cols + n] = value;
        }
    }

    public T get(int m, int n) {
        if (m >= 0 && m < rows && n >= 0 && n < cols) {
            return (T) matrix[m * cols + n];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public String toString() {
        String result = new String();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                T get = get(i, j);
                if (get != null) {
                    result += get.toString() + ' ';
                } else {
                    result += "null ";
                }
            }
            result += '\n';
        }
        return result;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int[] getCoordinates(T obj) {
        int[] coordinates = {-1, -1};
        T tmp;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; i++) {
                tmp = get(i, j);
                if (obj.equals(tmp)) {
                    coordinates[0] = i;
                    coordinates[1] = j;
                    return coordinates;
                }
            }
        }

        return coordinates;
    }
}
