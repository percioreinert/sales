package br.pucrs.t2.sales.negocio.entities;

import javax.persistence.*;

@Entity(name = "ItemVenda")
@Table(name = "item_vendas")
public class ItemVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="vendas_id", nullable=false)
    private Venda venda;
    private int qtdProduto;
    private int precoUnit;
    private long imposto;

    @OneToOne(cascade = CascadeType.ALL, optional = false,  orphanRemoval = true)
    @JoinColumn(name = "cod_produto")
    private Produto produto;

    public ItemVenda(int qtdProduto, int precoUnit, long imposto, Produto produto, Venda venda) {
        this.qtdProduto = qtdProduto;
        this.precoUnit = precoUnit;
        this.imposto = imposto;
        this.produto = produto;
        this.venda = venda;
    }

    public ItemVenda(){}
    public long getImposto() {
        return imposto;
    }
    public void setImposto(long imposto) {
        this.imposto = imposto;
    }
    public int getPrecoUnit() {
        return precoUnit;
    }
    public void setPrecoUnit(int precoUnit) {
        this.precoUnit = precoUnit;
    }
    public int getQtdProduto(){return this.qtdProduto;}

    public long getCodigo() {
        return produto.getCodigo();
    }

    public Produto getProduto(){return this.produto;}
    public void setQtdProduto(int qtdProduto){this.qtdProduto = qtdProduto;}

    public ItemVenda orElseThrow(Object object) {
        return null;
    }
}
