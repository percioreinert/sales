package br.pucrs.t2.sales.negocio.repositories;

import br.pucrs.t2.sales.negocio.entities.Venda;

import java.time.LocalDateTime;
import java.util.List;

public interface ISalesRepository{

    Venda findById(long id);
    Iterable<Venda> findAll();
    Venda save(Venda item);
    boolean saveAll(List<Venda> itens);
    boolean update(Venda item);
    void delete(Venda item);
    List<Venda> findByDateTimeBetween(LocalDateTime dateStart, LocalDateTime dateEnd);
}
