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
public class ComparatorProdutoPorCodigoCrescente implements Comparator<Produto> {

    public int compare(Produto o1, Produto o2) {
        return o1.getCodigo().compareTo(o2.getCodigo());
    }
}
