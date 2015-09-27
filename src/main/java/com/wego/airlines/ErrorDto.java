package com.wego.airlines;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "error")
public class ErrorDto {

    private String error;

    @XmlValue
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
