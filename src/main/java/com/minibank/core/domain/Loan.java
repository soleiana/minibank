package com.minibank.core.domain;

/**
 * Created by Ann on 06/09/14.
 */
import org.hibernate.annotations.Cascade;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.AbstractList;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name="LOAN")
public class Loan
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID", nullable = false)
    private Integer id;

    @Transient
    private Integer loanRequestId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="CUSTOMER_ID", nullable = false)
    private Customer customer;

    @Column(name="CURRENT_INTEREST_RATE", precision = 10, scale = 2, nullable = false)
    private BigDecimal currInterestRate;

    @Column(name="CURRENT_INTEREST", precision = 10, scale = 2, nullable = false)
    private BigDecimal currInterest;

    @Column(name="START_DATE", nullable = false)
    private Date startDate;

    @Column(name="END_DATE", nullable = false)
    private Date endDate;

    public Loan()
    {}

    public Loan(Integer loanRequestId, Customer customer,
                BigDecimal currInterestRate, BigDecimal currInterest,
                Date startDate, Date endDate)
    {
        this.loanRequestId = loanRequestId;
        this.customer = customer;
        this.currInterestRate = currInterestRate;
        this.currInterest = currInterest;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getLoanRequestId()
    {
        return loanRequestId;
    }

    public void setLoanRequestId(Integer loanRequestId)
    {
        this.loanRequestId = loanRequestId;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public BigDecimal getCurrInterestRate()
    {
        return currInterestRate;
    }

    public void setCurrInterestRate(BigDecimal currInterestRate)
    {
        this.currInterestRate = currInterestRate;
    }

    public BigDecimal getCurrInterest()
    {
        return currInterest;
    }

    public void setCurrInterest(BigDecimal currInterest)
    {
        this.currInterest = currInterest;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }
}
