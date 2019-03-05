package com.nidara.sabanasblancas.gestion.daos.rowmappers;

import com.nidara.sabanasblancas.gestion.model.QuoteLine;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.nidara.sabanasblancas.gestion.daos.querybuilders.QuoteLineQueryBuilder.*;

public class QuoteLineRowMapper extends AbstractRowMapper<QuoteLine> {

    @Override
    public QuoteLine mapRow(ResultSet rs, int i) throws SQLException {

        QuoteLine line = new QuoteLine();

        line.setId(getInteger(rs, ID_QUOTE_LINE, null));
        line.setLineNumber(getInteger(rs, LINE_NUMBER, null));
        line.setProductReference(getString(rs, REFERENCE, ""));
        line.setProductName(getString(rs, NAME, ""));
        line.setProductColor(getString(rs, COLOR, ""));
        line.setProductSize(getString(rs, SIZE, ""));
        line.setProductComments(getString(rs, COMMENTS, ""));
        line.setProductPrice(getBigDecimal(rs, PRICE, null));
        line.setQuantity(getInteger(rs, QUANTITY, null));

        return line;
    }
}
