package br.pucrs.t2.sales.adaptadores.repositories;

import br.pucrs.t2.sales.negocio.entities.Venda;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ISalesRepositoryJPA extends CrudRepository<Venda, Long> {
    List<Venda> findByDateTimeBetween(LocalDateTime dateStart, LocalDateTime dateEnd);
}
