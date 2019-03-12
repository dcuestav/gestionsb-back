package com.nidara.sabanasblancas.gestion;

import com.nidara.sabanasblancas.gestion.api.model.ApiProduct;
import com.nidara.sabanasblancas.gestion.config.ApiConnector;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(ApiConnector.class)
@Ignore
public class ApiConnectorTests {

    @Autowired
    RestTemplate restTemplate;

    @Test
    public void testGetProduct_success() throws URISyntaxException
    {
        int productId = 31;
        URI uri = new URI("http://192.168.1.2:4567/api/products/" + productId);

        ResponseEntity<ApiProduct> result = restTemplate.getForEntity(uri, ApiProduct.class);

        Assert.assertEquals(200, result.getStatusCodeValue());
        ApiProduct apiProduct = result.getBody();
        Assert.assertEquals(true, apiProduct.getId().equals(productId));
    }

}
