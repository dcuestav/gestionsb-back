package com.nidara.sabanasblancas.gestion.daos;

import com.nidara.sabanasblancas.gestion.daos.querybuilders.QuoteQueryBuilder;
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
}
