package com.minibank.core.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;


@Entity
@Table(name = "LOAN_EXTENSION")
public class LoanExtension {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "START_DATE", nullable = false)
    private Date startDate;

    @Column(name = "END_DATE", nullable = false)
    private Date endDate;

    @Column(name = "INTEREST_RATE", precision = 10, scale = 2, nullable = false)
    private BigDecimal interestRate;

    @Column(name = "INTEREST", precision = 10, scale = 2, nullable = false)
    private BigDecimal interest;

    @Column(name = "SUBMISSION_DATE", nullable = false)
    private LocalDate submissionDate;

    public LoanExtension() {}

    public Integer getId() {
        return id;
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

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoanExtension that = (LoanExtension) o;

        if (!startDate.equals(that.startDate)) return false;
        if (!endDate.equals(that.endDate)) return false;
        if (!interestRate.equals(that.interestRate)) return false;
        if (!interest.equals(that.interest)) return false;
        return submissionDate.equals(that.submissionDate);

    }

    @Override
    public int hashCode() {
        int result = startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + interestRate.hashCode();
        result = 31 * result + interest.hashCode();
        result = 31 * result + submissionDate.hashCode();
        return result;
    }
}
