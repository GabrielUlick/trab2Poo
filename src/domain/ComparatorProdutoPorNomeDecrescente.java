/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Comparator;

/**
 *
 * @author Giovany
 */
public class ComparatorProdutoPorNomeDecrescente implements Comparator<Produto> {

    public int compare(Produto o1, Produto o2) {
        return ((-1) * o1.getNome().compareTo(o2.getNome()));
    }

}
