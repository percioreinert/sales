package br.pucrs.t2.sales.adaptadores.repositories;

import br.pucrs.t2.sales.negocio.entities.Venda;
import br.pucrs.t2.sales.negocio.repositories.ISalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class SalesRepository implements ISalesRepository {

    private final ISalesRepositoryJPA repository;

    @Autowired
    public SalesRepository(ISalesRepositoryJPA repository) {
        this.repository = repository;
    }

    @Override
    public Venda findById(long id) {
        return repository.findById(id).get();
    }

    @Override
    public Iterable<Venda> findAll() {
        return repository.findAll();
    }

    @Override
    public Venda save(Venda Venda) {
        return repository.save(Venda);
    }

    @Override
    public boolean saveAll(List<Venda> Vendas) {
        return repository.saveAll(Vendas) != null;
    }

    @Override
    public void delete(Venda Venda) {
        repository.delete(Venda);
    }

    @Override
    public boolean update(Venda Venda) {
        return repository.save(Venda) != null;
    }

    @Override
    public List<Venda> findByDateTimeBetween(LocalDateTime dateStart, LocalDateTime dateEnd) {
        return repository.findByDateTimeBetween(dateStart, dateEnd);
    }
}

