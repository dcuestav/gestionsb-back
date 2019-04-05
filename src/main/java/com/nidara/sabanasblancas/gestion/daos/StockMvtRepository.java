package com.nidara.sabanasblancas.gestion.daos;

import com.nidara.sabanasblancas.gestion.model.StockMovement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface StockMvtRepository extends JpaRepository<StockMovement, Long> {

    List<StockMovement> getByDateBetween(Date from, Date until);

    Page<StockMovement> getByIdStockOrderByIdDesc(Integer idStock, Pageable pageable);

}
