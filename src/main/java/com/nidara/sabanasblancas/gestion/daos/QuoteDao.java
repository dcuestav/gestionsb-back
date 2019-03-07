package com.nidara.sabanasblancas.gestion.daos;

import com.nidara.sabanasblancas.gestion.daos.querybuilders.QuoteQueryBuilder;
import com.nidara.sabanasblancas.gestion.daos.rowmappers.QuoteRowMapper;
import com.nidara.sabanasblancas.gestion.model.Quote;
import com.nidara.sabanasblancas.gestion.model.dtos.PagedResult;
import com.nidara.sabanasblancas.gestion.model.enums.QuoteState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class QuoteDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

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

        Query query = entityManager.createQuery("SELECT q FROM Quote q WHERE q.id = :id");
        query.setParameter("id", quoteId);
        return (Quote) query.getSingleResult();
    }

    @Transactional
    public void updateQuoteState(int quoteId, QuoteState state) {
        String query = "UPDATE gsb_quote SET state=? WHERE id_quote=?";
        jdbcTemplate.update(query, state.toString(), quoteId);
    }

    @Transactional
    public Integer create(Quote quote) {
        entityManager.persist(quote);
        return quote.getId();
    }

    @Transactional
    public void update(Quote quote) {
        quote.recalculateTotal();
        entityManager.merge(quote);
    }

}
