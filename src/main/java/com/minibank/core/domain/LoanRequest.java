package com.minibank.core.domain;

/**
 * Created by Ann on 06/09/14.
 */

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name="LOAN_REQUEST")
public class LoanRequest
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID", nullable = false)
    private Integer id;

    @Transient
    private Integer customerId;

    @Transient
    private Integer requestIPId;

    @Column(name="SUBMISSION_DATE", nullable = false)
    private Date submissionDate;

    @Column(name="SUBMISSION_TIME", nullable = false)
    private Time submissionTime;

    @Column(name="TERM", nullable = false)
    private Integer term;

    @Column(name="AMOUNT", precision = 10, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(name="STATUS", nullable = false)
    @Enumerated (EnumType.STRING)
    private LoanRequestStatus status;

    public LoanRequest()
    {}

    public LoanRequest(Integer customerId, Integer requestIPId,
                       Date submissionDate, Time submissionTime,
                       Integer term, BigDecimal amount, LoanRequestStatus status)
    {
        this.customerId = customerId;
        this.requestIPId = requestIPId;
        this.submissionDate = submissionDate;
        this.submissionTime = submissionTime;
        this.term = term;
        this.amount = amount;
        this.status = status;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(Integer customerId)
    {
        this.customerId = customerId;
    }

    public Integer getRequestIPId() {
        return requestIPId;
    }

    public void setRequestIPId(Integer requestIPId)
    {
        this.requestIPId = requestIPId;
    }

    public Date getSubmissionDate()
    {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate)
    {
        this.submissionDate = submissionDate;
    }

    public Time getSubmissionTime() {
        return submissionTime;
    }

    public void setSubmissionTime(Time submissionTime)
    {
        this.submissionTime = submissionTime;
    }

    public Integer getTerm()
    {
        return term;
    }

    public void setTerm(Integer term)
    {
        this.term = term;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public LoanRequestStatus getStatus()
    {
        return status;
    }

    public void setStatus(LoanRequestStatus status)
    {
        this.status = status;
    }
}
