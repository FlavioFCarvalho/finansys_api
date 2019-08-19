package com.finansys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finansys.domain.Estado;

@Repository
public interface EstadoRepository  extends JpaRepository<Estado, Integer>{

}
