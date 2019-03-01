package com.nidara.sabanasblancas.gestion.daos.querybuilders;

public class QuoteQueryBuilder extends AbastractQueryBuilder {

    public static final String ID_QUOTE = "id_quote";
    public static final String QUOTE_DATE = "quote_date";
    public static final String TOTAL = "total";
    public static final String TAXES = "taxes";
    public static final String STATE = "state";
    public static final String COMMENTS = "comments";
    public static final String DELIVERY_TIME = "deliveryTime";

    public static final String QUOTE_TABLE = "gsb_quote";

    public QuoteQueryBuilder() {

        addSelectField("q.id_quote", ID_QUOTE);
        addSelectField("q.quote_date", QUOTE_DATE);
        addSelectField("q.total", TOTAL);
        addSelectField("q.taxes", TAXES);
        addSelectField("q.state", STATE);
        addSelectField("q.comments", COMMENTS);
        addSelectField("q.deliveryTime", DELIVERY_TIME);

        from(QUOTE_TABLE, "q");

        setOrderBy("q.id_quote", QueryOrder.DESC);
    }

    public QuoteQueryBuilder page(int page, int size) {
        setPage(page, size);
        return this;
    }

    public String build() {
        return buildQuery();
    }
}
