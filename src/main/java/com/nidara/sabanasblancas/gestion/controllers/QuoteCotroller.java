package com.nidara.sabanasblancas.gestion.controllers;

import com.nidara.sabanasblancas.gestion.daos.QuoteDao;
import com.nidara.sabanasblancas.gestion.model.Quote;
import com.nidara.sabanasblancas.gestion.model.dtos.PagedResult;
import com.nidara.sabanasblancas.gestion.model.enums.QuoteState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuoteCotroller {

    @Autowired
    QuoteDao quoteDao;

    @RequestMapping("/quote")
    public PagedResult<Quote> getQuotes(@RequestParam int page, @RequestParam int size) {

        return quoteDao.getQuotes(page, size);
    }

    @PostMapping("/quote")
    public Integer createQuote(@RequestBody Quote quote) {
        return quoteDao.create(quote);
    }

    @PutMapping("/quote")
    @ResponseStatus( HttpStatus.OK )
    public void updateQuote(@RequestBody Quote quote) {
        quoteDao.update(quote);
    }

    @RequestMapping("/quote/{quoteId}")
    public Quote getQuoteById(@PathVariable int quoteId) {

        return quoteDao.getById(quoteId);
    }

    @PutMapping("/quote/{quoteId}/state")
    @ResponseStatus( HttpStatus.OK )
    public void updateQuoteState(@PathVariable int quoteId,
                                 @RequestBody String state) {

        quoteDao.updateQuoteState(quoteId, QuoteState.valueOf(state));
    }
}
