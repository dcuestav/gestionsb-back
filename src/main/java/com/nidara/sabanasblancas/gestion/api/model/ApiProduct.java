package com.nidara.sabanasblancas.gestion.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "product")
@Data
public class ApiProduct {
    private Integer id;
    private String reference;

    public Integer getId() {
        return id;
    }

}
