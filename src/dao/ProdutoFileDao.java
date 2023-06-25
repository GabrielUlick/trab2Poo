/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.ComparatorProdutoPorDataCrescente;
import domain.ComparatorProdutoPorDataDecrescente;
import domain.ComparatorProdutoPorCodigoCrescente;
import domain.ComparatorProdutoPorCodigoDecrescente;
import domain.ComparatorProdutoPorPrecoCrescente;
import domain.ComparatorProdutoPorPrecoDecrescente;
import domain.ComparatorProdutoPorNomeCrescente;
import domain.ComparatorProdutoPorNomeDecrescente;
import domain.Produto;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Vector;

/**
 *
 * @author Giovany
 */
public class ProdutoFileDao {

    public void salvarProdutos(Vector<Produto> Produtos) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileOutputStream arquivo = new FileOutputStream(ProdutoFileInformation.getCaminhoArquivo() + ProdutoFileInformation.getNomeArquivo());

        //Classe responsavel por inserir os objetos
        ObjectOutputStream objGravar = new ObjectOutputStream(arquivo);

        //Grava o objeto de vetor de Produtos no arquivo
        objGravar.writeObject(Produtos);
        objGravar.flush();
        objGravar.close();
        arquivo.flush();
        arquivo.close();
    }

    private boolean estaArquivoVazio() throws FileNotFoundException, IOException {
        //Carrega o arquivo
        String local_arquivo = ProdutoFileInformation.getCaminhoArquivo() + ProdutoFileInformation.getNomeArquivo();
        FileInputStream arquivoLeitura = new FileInputStream(local_arquivo);
        boolean estaVazio = (arquivoLeitura.read() == -1);
        arquivoLeitura.close();
        return estaVazio;
    }

    public Vector<Produto> obterProdutos() throws FileNotFoundException, IOException, ClassNotFoundException {
        //Carrega o arquivo
        if (estaArquivoVazio()) {
            return new Vector();
        } else {
            //Classe responsavel por recuperar os objetos do arquivo
            String local_arquivo = ProdutoFileInformation.getCaminhoArquivo() + ProdutoFileInformation.getNomeArquivo();
            FileInputStream arquivoLeitura = new FileInputStream(local_arquivo);
            ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);
            Vector<Produto> vetor = (Vector<Produto>) objLeitura.readObject();
            objLeitura.close();
            arquivoLeitura.close();
            return vetor;
        }
    }

    public Vector<Produto> obterProdutos(String coluna, boolean crescente) throws FileNotFoundException, IOException, ClassNotFoundException {
        Vector<Produto> Produtos = this.obterProdutos();
        if (coluna.equals("codigo")) {
            if (crescente) {
                Collections.sort(Produtos, new ComparatorProdutoPorCodigoCrescente());
            } else {
                Collections.sort(Produtos, new ComparatorProdutoPorCodigoDecrescente());
            }
        } else if (coluna.equals("nome")) {
            if (crescente) {
                Collections.sort(Produtos, new ComparatorProdutoPorNomeCrescente());
            } else {
                Collections.sort(Produtos, new ComparatorProdutoPorNomeDecrescente());
            }
        } else if (coluna.equals("Preco")) {
            if (crescente) {
                Collections.sort(Produtos, new ComparatorProdutoPorPrecoCrescente());
            } else {
                Collections.sort(Produtos, new ComparatorProdutoPorPrecoDecrescente());
            }
        } else if (coluna.equals("datacompra")) {
            if (crescente) {
                Collections.sort(Produtos, new ComparatorProdutoPorDataCrescente());
            } else {
                Collections.sort(Produtos, new ComparatorProdutoPorDataDecrescente());
            }
        }
        return Produtos;
    }
}
