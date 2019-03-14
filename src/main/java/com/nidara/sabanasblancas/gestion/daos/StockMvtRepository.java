package com.nidara.sabanasblancas.gestion.daos;

import com.nidara.sabanasblancas.gestion.model.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockMvtRepository extends JpaRepository<StockMovement, Long> {
}
