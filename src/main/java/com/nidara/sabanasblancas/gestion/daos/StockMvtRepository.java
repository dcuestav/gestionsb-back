package com.nidara.sabanasblancas.gestion.daos;

import com.nidara.sabanasblancas.gestion.model.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface StockMvtRepository extends JpaRepository<StockMovement, Long> {

    public List<StockMovement> getByDateBetween(Date from, Date until);
}
