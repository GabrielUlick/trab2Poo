/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.Comparator;

/**
 *
 * @author Gabriel
 */
public class ComparatorProdutoPorDataDecrescente implements Comparator<Produto> {

    public int compare(Produto o1, Produto o2) {
        return ((-1) * o1.getDatacompra().compareTo(o2.getDatacompra()));
    }

}
