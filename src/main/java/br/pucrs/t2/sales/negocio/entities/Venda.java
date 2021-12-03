package br.pucrs.t2.sales.negocio.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Venda")
@Table(name = "vendas")
public class Venda {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long numero;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dateTime;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, fetch = FetchType.EAGER,targetEntity = ItemVenda.class)
    private List<ItemVenda> itemVendas;

    public Venda(LocalDateTime dateTime, List<ItemVenda> itemVendas){
        this.dateTime = dateTime;
        this.itemVendas = itemVendas;
    }

    public Venda(){}
    public long getNumero(){return numero;}
    public LocalDateTime getDateTime(){return dateTime;}
    public List<ItemVenda> getItemVendas(){return itemVendas;}
    public void setItemVendas(List<ItemVenda> itemVendas){ this.itemVendas = itemVendas;}

}

