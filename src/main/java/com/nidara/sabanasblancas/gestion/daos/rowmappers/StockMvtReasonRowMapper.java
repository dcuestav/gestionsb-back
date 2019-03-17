package com.nidara.sabanasblancas.gestion.daos.rowmappers;

import com.nidara.sabanasblancas.gestion.model.StockMvtReason;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.nidara.sabanasblancas.gestion.daos.querybuilders.StockMvtReasonQueryBuilder.ID_REASON;
import static com.nidara.sabanasblancas.gestion.daos.querybuilders.StockMvtReasonQueryBuilder.REASON_NAME;

public class StockMvtReasonRowMapper extends AbstractRowMapper<StockMvtReason> {

    @Override
    public StockMvtReason mapRow(ResultSet resultSet, int i) throws SQLException {
        StockMvtReason stockMvtReason = new StockMvtReason();
        stockMvtReason.setId(getInteger(resultSet, ID_REASON, null));
        stockMvtReason.setName(getString(resultSet, REASON_NAME, "(vacío)"));
        return stockMvtReason;
    }
}
