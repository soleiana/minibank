package com.minibank.core.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;


@Entity
@Table(name = "LOAN_REQUEST")
public class LoanRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private Customer customer;

    @Column(name = "REQUEST_IP", nullable = false)
    private String requestIp;

    @Column(name = "SUBMISSION_DATE", nullable = false)
    private Date submissionDate;

    @Column(name = "SUBMISSION_TIME", nullable = false)
    private Time submissionTime;

    @Column(name = "TERM", nullable = false)
    private Integer term;

    @Column(name = "AMOUNT", precision = 10, scale = 2, nullable = false)
    private BigDecimal amount;

    public LoanRequest() {}

    public Integer getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public Time getSubmissionTime() {
        return submissionTime;
    }

    public void setSubmissionTime(Time submissionTime) {
        this.submissionTime = submissionTime;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoanRequest that = (LoanRequest) o;

        if (!requestIp.equals(that.requestIp)) return false;
        if (!submissionDate.equals(that.submissionDate)) return false;
        if (!submissionTime.equals(that.submissionTime)) return false;
        if (!term.equals(that.term)) return false;
        return amount.equals(that.amount);

    }

    @Override
    public int hashCode() {
        int result = requestIp.hashCode();
        result = 31 * result + submissionDate.hashCode();
        result = 31 * result + submissionTime.hashCode();
        result = 31 * result + term.hashCode();
        result = 31 * result + amount.hashCode();
        return result;
    }
}
