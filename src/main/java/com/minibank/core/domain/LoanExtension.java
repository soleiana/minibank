package com.minibank.core.domain;

/**
 * Created by Ann on 06/09/14.
 */
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name="LOAN_EXTENSION")
public class LoanExtension
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID", nullable = false)
    private Integer id;

    @Transient
    private Integer loanId;

    @Column(name="START_DATE", nullable = false)
    private Date startDate;

    @Column(name="END_DATE", nullable = false)
    private Date endDate;

    @Column(name="INTEREST_RATE", precision = 10, scale = 2, nullable = false)
    private BigDecimal interestRate;

    @Column(name="INTEREST", precision = 10, scale = 2, nullable = false)
    private BigDecimal interest;

    @Column(name="SUBMISSION_DATE", nullable = false)
    private Date submissionDate;

    public LoanExtension()
    {}

    public LoanExtension(Integer loanId, Date startDate, Date endDate,
                         BigDecimal interestRate, BigDecimal interest,
                         Date submissionDate)
    {
        this.loanId = loanId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.interestRate = interestRate;
        this.interest = interest;
        this.submissionDate = submissionDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }
}
