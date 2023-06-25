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
public class ComparatorProdutoPorPrecoDecrescente implements Comparator<Produto> {

    public int compare(Produto o1, Produto o2) {
        if (o2.getPreco() < o1.getPreco()) {
            return -1;
        } else if (o2.getPreco() > o1.getPreco()) {
            return 1;
        } else {
            return 0;
        }
    }
}
