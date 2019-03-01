package com.nidara.sabanasblancas.gestion.daos.rowmappers;

import com.mysql.cj.util.StringUtils;
import com.nidara.sabanasblancas.gestion.model.Quote;
import com.nidara.sabanasblancas.gestion.model.QuoteClient;
import com.nidara.sabanasblancas.gestion.model.QuoteState;
import com.nidara.sabanasblancas.gestion.model.Taxes;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.nidara.sabanasblancas.gestion.daos.querybuilders.QuoteQueryBuilder.*;

public class QuoteRowMapper extends AbstractRowMapper<Quote> {

    @Override
    public Quote mapRow(ResultSet resultSet, int i) throws SQLException {
        Quote quote = new Quote();
        quote.setId(getInteger(resultSet, ID_QUOTE, null));
        quote.setDate(getDate(resultSet, QUOTE_DATE, null));
        quote.setTotal(getBigDecimal(resultSet, TOTAL, null));
        quote.setTaxes(getTaxes(resultSet, TAXES, null));
        quote.setState(getQuoteState(resultSet, STATE, null));
        quote.setComments(getString(resultSet, COMMENTS, ""));
        quote.setDeliveryTime(getString(resultSet, DELIVERY_TIME, ""));

        QuoteClient client = new QuoteClient();
        client.setId(getInteger(resultSet, CLIENT_ID, null));
        client.setPrestaId(getInteger(resultSet, CLIENT_PRESTA_ID, null));
        client.setName(getString(resultSet, CLIENT_NAME, ""));
        client.setAddress(getString(resultSet, CLIENT_ADDRESS, ""));
        client.setCompany(getString(resultSet, CLIENT_COMPANY, ""));
        client.setEmail(getString(resultSet, CLIENT_EMAIL, ""));
        client.setTelephone(getString(resultSet, CLIENT_PHONE, ""));

        quote.setClient(client);

        return quote;
    }

    private Taxes getTaxes(ResultSet resultSet, String field, Taxes defaultValue) {
        String taxesString = getString(resultSet, field, null);
        if (!StringUtils.isNullOrEmpty(taxesString)) {
            return Taxes.valueOf(taxesString);
        }
        return defaultValue;
    }

    private QuoteState getQuoteState(ResultSet resultSet, String field, QuoteState defaultValue) {
        String quoteStateString = getString(resultSet, field, null);
        if (!StringUtils.isNullOrEmpty(quoteStateString)) {
            return QuoteState.valueOf(quoteStateString);
        }
        return defaultValue;
    }
}
