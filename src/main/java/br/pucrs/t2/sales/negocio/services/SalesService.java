package br.pucrs.t2.sales.negocio.services;

import br.pucrs.t2.sales.adaptadores.repositories.SalesRepository;
import br.pucrs.t2.sales.negocio.entities.ItemEstoqueDTO;
import br.pucrs.t2.sales.negocio.entities.Venda;
import org.springframework.stereotype.Service;

@Service
public class SalesService {

    private SalesRepository salesRepository;

    public SalesService(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    public Iterable<Venda> vendasEfetuadas() {
        return salesRepository.findAll();
    }

    public void salvarVenda(Venda venda) {
        salesRepository.save(venda);
    }

    public boolean podeVender(long codProd, int qtdade){
        if(qtdade <= 0)
            return false;
        //ver como faz a chamada para o microserviço de estoque
        ItemEstoqueDTO item = estoqueService.getItemEstoque(codProd);
        if(item == null || qtdade > item.getQuantidade() || item.getQuantidade() - qtdade < 0)
            return false;

        item.setQuantidade(item.getQuantidade()-qtdade);
        return true;
    }
}
