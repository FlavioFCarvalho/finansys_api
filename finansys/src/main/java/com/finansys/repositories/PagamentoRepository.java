package com.finansys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finansys.domain.Pagamento;

@Repository
public interface PagamentoRepository  extends JpaRepository<Pagamento, Integer>{

}
