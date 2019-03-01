package com.nidara.sabanasblancas.gestion.daos.querybuilders;

import com.nidara.sabanasblancas.gestion.model.enums.QueryOrder;

public class QuoteQueryBuilder extends AbstractQueryBuilder {

    public static final String ID_QUOTE = "id_quote";
    public static final String QUOTE_DATE = "quote_date";
    public static final String TOTAL = "total";
    public static final String TAXES = "taxes";
    public static final String STATE = "state";
    public static final String COMMENTS = "comments";
    public static final String DELIVERY_TIME = "deliveryTime";

    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_PRESTA_ID = "client_ps_id";
    public static final String CLIENT_NAME = "client_name";
    public static final String CLIENT_ADDRESS = "client_address";
    public static final String CLIENT_COMPANY = "client_company";
    public static final String CLIENT_EMAIL = "client_email";
    public static final String CLIENT_PHONE = "client_phone";

    public QuoteQueryBuilder() {

        addSelectField("q.id_quote", ID_QUOTE);
        addSelectField("q.quote_date", QUOTE_DATE);
        addSelectField("q.total", TOTAL);
        addSelectField("q.taxes", TAXES);
        addSelectField("q.state", STATE);
        addSelectField("q.comments", COMMENTS);
        addSelectField("q.deliveryTime", DELIVERY_TIME);

        addSelectField("c.id_quote_customer", CLIENT_ID);
        addSelectField("c.name", CLIENT_NAME);

        from("gsb_quote", "q");
        addJoin("LEFT JOIN gsb_quote_client c USING(id_quote_customer)");

        setOrderBy("q.id_quote", QueryOrder.DESC);
    }

    public QuoteQueryBuilder withClientDetails() {
        addSelectField("c.ps_id_customer", CLIENT_PRESTA_ID);
        addSelectField("c.address", CLIENT_ADDRESS);
        addSelectField("c.companyName", CLIENT_COMPANY);
        addSelectField("c.email", CLIENT_EMAIL);
        addSelectField("c.telephone", CLIENT_PHONE);
        return this;
    }

    public QuoteQueryBuilder page(int page, int size) {
        setPage(page, size);
        return this;
    }

    public String build() {
        return buildQuery();
    }

    public String buildCount() {
        return buildCountQuery();
    }
}
