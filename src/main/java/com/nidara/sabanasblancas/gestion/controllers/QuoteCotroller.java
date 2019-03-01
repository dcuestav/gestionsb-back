package com.nidara.sabanasblancas.gestion.controllers;

import com.nidara.sabanasblancas.gestion.daos.QuoteDao;
import com.nidara.sabanasblancas.gestion.model.Quote;
import com.nidara.sabanasblancas.gestion.model.dtos.PagedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:4200")
public class QuoteCotroller {

    @Autowired
    QuoteDao quoteDao;

    @RequestMapping("/quote")
    public PagedResult<Quote> getCategoryProducts(@RequestParam int page, @RequestParam int size) {

        return quoteDao.getQuotes(page, size);
    }
}
