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
public interface Queue<V extends Comparable<V>> {

    void push(V value);

    V pop();
}
