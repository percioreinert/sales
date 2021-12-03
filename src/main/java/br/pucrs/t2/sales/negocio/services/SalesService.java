package br.pucrs.t2.sales.negocio.services;

import br.pucrs.t2.sales.adaptadores.repositories.SalesRepository;
import br.pucrs.t2.sales.negocio.entities.ItemEstoqueDTO;
import br.pucrs.t2.sales.negocio.entities.ItemVenda;
import br.pucrs.t2.sales.negocio.entities.Venda;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class SalesService {

    private SalesRepository salesRepository;
    private static long IMPOSTO = 10L;

    public SalesService(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    public Integer[] calculaSubtotal(ItemEstoqueDTO[] itens) {
        int subtotal = 0;
        int imposto = 0;
        for (ItemEstoqueDTO it : itens) {
            validaItemEstoque(it, 0);
            int quantidade = it.getQuantidade();
            // TODO: fazer a chamada para o serviço de estoque
            it = estoqueService.getItemEstoque(it.getCodigo());
            validaItemEstoque(it, quantidade);
            subtotal += (int) (it.getProduto().getPreco() * quantidade);

        }
        imposto = (int)(subtotal * (IMPOSTO / 100.0));
        final Integer[] resp = new Integer[3];
        resp[0] = subtotal;
        resp[1] = imposto;
        resp[2] = subtotal + imposto;
        return resp;
    }

    public boolean confirmaVenda(ItemEstoqueDTO[] itens) {
        ArrayList<ItemVenda> listaItemVenda = new ArrayList<>();
        ArrayList<ItemEstoqueDTO> listaItemEstoque = new ArrayList<>();
        Venda venda = new Venda(LocalDateTime.now(), listaItemVenda);
        for (ItemEstoqueDTO item : itens) {

            if(item.getProduto() == null)
                return false;

            long codigoProduto = item.getCodigo();
            int quantidade = item.getQuantidade();

            if(quantidade <= 0)
                return false;

            ItemEstoqueDTO itemEstoque = listaItemEstoque.stream().filter(y -> y.getCodigo() == codigoProduto).findFirst().get().orElseThrow(null);

            if(itemEstoque == null || itemEstoque.getProduto() == null){
                // TODO: fazer a chamada para o serviço de estoque
                itemEstoque = estoqueService.getItemEstoque(codigoProduto);

                if(itemEstoque == null || itemEstoque.getProduto() == null)
                    return false;

                listaItemEstoque.add(itemEstoque);
            }

            quantidade = itemEstoque.getQuantidade() - quantidade;

            if(quantidade >= 0)
                itemEstoque.setQuantidade(quantidade);
            else
                return false;

            ItemVenda itemLista = listaItemVenda.stream().filter(x -> x.getCodigo() == codigoProduto).findFirst().get().orElseThrow(null);

            if(itemLista != null)
                itemLista.setQtdProduto(quantidade);
            else{
                itemLista = new ItemVenda(quantidade, itemEstoque.getProduto().getPreco(), IMPOSTO, itemEstoque.getProduto(), venda);
                listaItemVenda.add(itemLista);
            }
        }
        salesRepository.save(venda);
        // TODO: fazer a chamada para o serviço de estoque
        estoqueService.save(listaItemEstoque);
        return true;
    }

    public Iterable<Venda> vendasEfetuadas() {
        return salesRepository.findAll();
    }

    public boolean podeVender(long codProd, int qtdade){
        if(qtdade <= 0)
            return false;
        // TODO: fazer a chamada para o serviço de estoque
        ItemEstoqueDTO item = estoqueService.getItemEstoque(codProd);
        if(item == null || qtdade > item.getQuantidade() || item.getQuantidade() - qtdade < 0)
            return false;

        item.setQuantidade(item.getQuantidade()-qtdade);
        return true;
    }

    private void validaItemEstoque(ItemEstoqueDTO itemEstoque, int quantidade){
        String errorMessage = "";

        if (itemEstoque == null)
            errorMessage = "item inválido";
        else if(itemEstoque.getQuantidade() - quantidade < 0)
            errorMessage = "quantidade inválida";

        if(!errorMessage.isEmpty())
            throw new IllegalArgumentException(errorMessage);
    }
}
