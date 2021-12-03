package br.pucrs.t2.sales.negocio.entities;

public class ItemEstoqueDTO {

    private Produto produto;
    private int quantidade;

    public ItemEstoqueDTO(Produto produto, int quantidade) {
        this.quantidade = quantidade;
        this.produto=produto;
    }

    public int getQuantidade(){return this.quantidade;}

    public long getCodigo() {
        return produto.getCodigo();
    }

    public void setQuantidade(int qtdProduto){this.quantidade=qtdProduto;}

    public Produto getProduto(){return this.produto;}

    public ItemEstoqueDTO orElseThrow(Object object) {
        return null;
    }
}

