package com.nidara.sabanasblancas.gestion.daos.querybuilders;

import java.util.Collection;

public class StockMvtReasonQueryBuilder extends AbstractQueryBuilder {

    public static final String ID_REASON = "id_reason";
    public static final String REASON_NAME = "name";

    public StockMvtReasonQueryBuilder() {
        addSelectField("r.id_stock_mvt_reason", ID_REASON);
        addSelectField("l.name", REASON_NAME);

        from("ps_stock_mvt_reason", "r");
        addJoin("JOIN ps_stock_mvt_reason_lang l ON r.id_stock_mvt_reason=l.id_stock_mvt_reason AND l.id_lang=1");
    }

    public StockMvtReasonQueryBuilder withIdIn(Collection<Integer> ids) {
        addWhere("r.id_stock_mvt_reason IN " + intArrayToInClause(ids));
        return this;
    }

    public String build() {
        return buildQuery();
    }
}
