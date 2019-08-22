package com.finansys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finansys.domain.Categoria;
import com.finansys.domain.ItemPedido;

@Repository
public interface ItemPedidoRepository  extends JpaRepository<ItemPedido, Integer>{

}
