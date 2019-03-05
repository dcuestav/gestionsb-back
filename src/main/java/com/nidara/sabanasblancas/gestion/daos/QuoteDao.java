package com.nidara.sabanasblancas.gestion.daos;

import com.nidara.sabanasblancas.gestion.daos.querybuilders.QuoteLineQueryBuilder;
import com.nidara.sabanasblancas.gestion.daos.querybuilders.QuoteQueryBuilder;
import com.nidara.sabanasblancas.gestion.daos.rowmappers.QuoteLineRowMapper;
import com.nidara.sabanasblancas.gestion.daos.rowmappers.QuoteRowMapper;
import com.nidara.sabanasblancas.gestion.model.Quote;
import com.nidara.sabanasblancas.gestion.model.dtos.PagedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuoteDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public PagedResult<Quote> getQuotes(int page, int size) {

        String sql = new QuoteQueryBuilder()
                .page(page, size)
                .build();

        List<Quote> quotes = jdbcTemplate.query(sql, new QuoteRowMapper());

        String countSql = new QuoteQueryBuilder().buildCount();

        Long totalElements = jdbcTemplate.queryForObject(countSql, Long.class);

        return new PagedResult<Quote>(quotes, totalElements, page, size);
    }

    public Quote getById(int quoteId) {

        String sql = new QuoteQueryBuilder()
                .withClientDetails()
                .withId(quoteId)
                .build();

        Quote quote = jdbcTemplate.queryForObject(sql, new QuoteRowMapper());

        if (quote!=null) {
            String linesSql = new QuoteLineQueryBuilder(quoteId).build();
            quote.setLines(jdbcTemplate.query(linesSql, new QuoteLineRowMapper()));
        }

        return quote;
    }
}
