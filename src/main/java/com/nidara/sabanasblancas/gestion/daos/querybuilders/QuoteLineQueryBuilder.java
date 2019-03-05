package com.nidara.sabanasblancas.gestion.daos.querybuilders;

public class QuoteLineQueryBuilder extends AbstractQueryBuilder {

    public static final String ID_QUOTE_LINE = "id_quote_line";
    public static final String LINE_NUMBER = "line_number";
    public static final String REFERENCE = "reference";
    public static final String NAME = "product_name";
    public static final String COLOR = "product_color";
    public static final String SIZE = "product_size";
    public static final String COMMENTS = "comments";
    public static final String PRICE = "price";
    public static final String QUANTITY = "quantity";

    public QuoteLineQueryBuilder(int quoteId) {

        addSelectField("ql.id_quote_line", ID_QUOTE_LINE);
        addSelectField("ql.line_number", LINE_NUMBER);
        addSelectField("ql.product_reference", REFERENCE);
        addSelectField("ql.product_name", NAME);
        addSelectField("ql.product_color", COLOR);
        addSelectField("ql.product_size", SIZE);
        addSelectField("ql.product_comments", COMMENTS);
        addSelectField("ql.product_price", PRICE);
        addSelectField("ql.quantity", QUANTITY);

        from("gsb_quote_line", "ql");

        addWhere("ql.id_quote=" + quoteId);
    }

    public String build() {
        return buildQuery();
    }

}
