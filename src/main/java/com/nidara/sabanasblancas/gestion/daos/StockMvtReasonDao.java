package com.nidara.sabanasblancas.gestion.daos;

import com.nidara.sabanasblancas.gestion.daos.querybuilders.InsertQueryBuilder;
import com.nidara.sabanasblancas.gestion.model.StockMovement;
import com.nidara.sabanasblancas.gestion.model.enums.StockMvtReasonEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public class StockMvtReasonDao {

    static final int SPANISH_LANG = 1;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public Integer createReason(String reason) {
        int reasonId = createNewReason();
        createReasonLang(reasonId, reason);
        return reasonId;
    }

    private int createNewReason() {
        InsertQueryBuilder builder = new InsertQueryBuilder("ps_stock_mvt_reason");
        Date now = new Date();
        builder.setField("sign", StockMovement.SIGN_POSITIVE);
        builder.setField("date_add", now);
        builder.setField("date_upd", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(builder.build(), keyHolder);
        return keyHolder.getKey().intValue();
    }

    private void createReasonLang(int reasonId, String reason) {
        InsertQueryBuilder builder = new InsertQueryBuilder("ps_stock_mvt_reason_lang");
        builder.setField("id_stock_mvt_reason", reasonId);
        builder.setField("id_lang", SPANISH_LANG);
        builder.setField("name", reason);
        jdbcTemplate.update(builder.build());
    }

    public StockMvtReasonEnum getDefaultReasonForSign(int sign) {
        if (sign == StockMovement.SIGN_POSITIVE) {
            return StockMvtReasonEnum.INCREASE_DUE_TO_EMPLOYEE_EDITION;
        }
        return StockMvtReasonEnum.DECREASE_DUE_TO_EMPLOYEE_EDITION;
    }

}
