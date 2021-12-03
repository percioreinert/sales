package br.pucrs.t2.sales.adaptadores.controllers;

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

    @GetMapping("/historico")
    @CrossOrigin(origins = "*")
    public Iterable<Venda> vendasEfetuadas() {
        return salesService.vendasEfetuadas();
    }

    @PutMapping("/salvar")
    @CrossOrigin(origins = "*")
    public void salvarVenda(@RequestBody Venda venda) {
        salesService.salvarVenda(venda);
    }
}
