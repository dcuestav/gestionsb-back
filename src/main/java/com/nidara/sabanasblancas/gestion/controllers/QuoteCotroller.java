package com.nidara.sabanasblancas.gestion.controllers;

import com.nidara.sabanasblancas.gestion.daos.QuoteDao;
import com.nidara.sabanasblancas.gestion.model.Quote;
import com.nidara.sabanasblancas.gestion.model.dtos.PagedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
public class QuoteCotroller {

    @Autowired
    QuoteDao quoteDao;

    @RequestMapping("/quote")
    public PagedResult<Quote> getQuotes(@RequestParam int page, @RequestParam int size) {

        return quoteDao.getQuotes(page, size);
    }

    @RequestMapping("/quote/{quoteId}")
    public Quote getQuoteById(@PathVariable int quoteId) {

        return quoteDao.getById(quoteId);
    }
}
