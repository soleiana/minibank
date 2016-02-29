package com.minibank.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.*;
import java.math.BigDecimal;


public class LoanRequest {

    @NotNull
    @Min(1)
    @Max(Integer.MAX_VALUE)
    private Integer customerId;

    @JsonIgnore
    private String requestIp;

    @NotNull
    @Min(7)
    @Max(360)
    private Integer term;

    @NotNull
    @DecimalMin("10.00")
    @DecimalMax("10000.00")
    @Digits(integer = 7, fraction = 2)
    private BigDecimal amount;

    public LoanRequest() {}

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
