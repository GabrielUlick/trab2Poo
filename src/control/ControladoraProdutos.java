/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import dao.*;
import domain.Produto;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;


public class ControladoraProdutos {
    private Vector<Produto> Produtos;
    private int marcador;
    ProdutoFileDao ProdutoDao;

    private String obterNomeColunaBanco(String coluna) {
        if (coluna.equals("Código"))
            return "codigo";
        if (coluna.equals("Nome"))
            return "nome";
        if (coluna.equals("Preco"))
            return "Preco";
        if (coluna.equals("Data Compra"))
            return "datacompra";
        return "id";
    }

    public ControladoraProdutos() {
        this.ProdutoDao = new ProdutoFileDao();
    }

    private void atualizarProduto(Produto Produto, Vector linha){
        Produto.setCodigo(linha.get(0).toString());
        Produto.setNome(linha.get(1).toString());
        Produto.setPreco(Double.parseDouble(linha.get(2).toString()));
        Produto.setDatacompra(linha.get(3).toString());
    }

    private Vector criarLinhaProduto(Produto Produto) {
        Vector linha = new Vector();
        linha.addElement(Produto.getCodigo());
        linha.addElement(Produto.getNome());
        linha.addElement(Produto.getPreco());
        linha.addElement(Produto.getDatacompra());
        return linha;
    }


    public void inserirNovoProduto(Vector linha) throws FileNotFoundException, IOException, ClassNotFoundException{
        Produto Produto = new Produto();
        this.atualizarProduto(Produto, linha);
        this.Produtos.add(Produto);
        ProdutoDao.salvarProdutos(this.Produtos);
    }

    public void setMarcador(int marcador){
        this.marcador = marcador;
    }

    public void alterarProduto(Vector linha) throws FileNotFoundException, IOException, ClassNotFoundException {
        Produto Produto = Produtos.get(marcador);
        this.atualizarProduto(Produto, linha);
        ProdutoDao.salvarProdutos(this.Produtos);
    }

    public void  excluirProduto() throws FileNotFoundException, IOException, ClassNotFoundException{
        Produtos.remove(marcador);
        ProdutoDao.salvarProdutos(this.Produtos);
    }

    private Vector<Produto> obterProdutos(String coluna, boolean crescente) throws FileNotFoundException, IOException, ClassNotFoundException{
        String nomeColunaBanco = this.obterNomeColunaBanco(coluna);
        Produtos = ProdutoDao.obterProdutos(nomeColunaBanco, crescente);
        return Produtos;
    }

    public Vector obterLinhasProdutos(String coluna, boolean crescente) throws FileNotFoundException, IOException, ClassNotFoundException {
        Vector<Produto> Produtos = obterProdutos(coluna, crescente);
        Vector linhas = new Vector();

        // Montando as linhas
        for(int i = 0; i < Produtos.size(); i++){
            Produto Produto = Produtos.get(i);
            linhas.addElement(this.criarLinhaProduto(Produto));
        }
        return linhas;
    }
}
