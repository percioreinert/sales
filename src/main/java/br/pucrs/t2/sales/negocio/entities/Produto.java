package br.pucrs.t2.sales.negocio.entities;

public class Produto {

    private Long codigo;
    private ItemEstoqueDTO itemEstoque;
    private String descricao;
    private int preco;

    public Produto(long codigo, String descricao, int preco) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.preco = preco;
    }

    public Long getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getPreco() {
        return preco;
    }


    public void setPreco(int preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Produto [codigo=" + codigo + ", descricao=" + descricao + ", preco=" + preco
                + "]";
    }
}

