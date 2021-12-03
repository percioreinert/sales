package br.pucrs.t2.sales.adaptadores.controllers;

import br.pucrs.t2.sales.negocio.entities.ItemEstoqueDTO;
import br.pucrs.t2.sales.negocio.entities.Venda;
import br.pucrs.t2.sales.negocio.services.SalesService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vendas")
public class SalesController {

    private SalesService salesService;

    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @GetMapping("/autorizacao")
    @CrossOrigin(origins = "*")
    public boolean podeVender(@RequestParam final long codProd,
                              @RequestParam final int qtdade) {
        return salesService.podeVender(codProd, qtdade);
    }

    @PostMapping("/confirmacao")
    @CrossOrigin(origins = "*")
    public boolean confirmaVenda(@RequestBody final ItemEstoqueDTO[] itens) {
        // for(ItemEstoque item : itens){
        //   sEstoque.save(item);
        // }
        salesService.confirmaVenda(itens);
        return true;
    }

    @GetMapping("/historico")
    @CrossOrigin(origins = "*")
    public Iterable<Venda> vendasEfetuadas() {
        return salesService.vendasEfetuadas();
    }

    @PostMapping("/subtotal")
    @CrossOrigin(origins = "*")
    public Integer[] calculaSubtotal(@RequestBody final ItemEstoqueDTO[] itens) {
        return salesService.calculaSubtotal(itens);
    }
}
