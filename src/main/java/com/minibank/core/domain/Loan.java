package com.minibank.core.domain;

import org.hibernate.annotations.Cascade;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "LOAN")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LOAN_REQUEST_ID", unique = true, nullable = false)
    private LoanRequest loanRequest;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOAN_ID")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<LoanExtension> loanExtensions = new ArrayList<>();

    @Column(name = "CURRENT_INTEREST_RATE", precision = 10, scale = 2, nullable = false)
    private BigDecimal currInterestRate;

    @Column(name = "CURRENT_INTEREST", precision = 10, scale = 2, nullable = false)
    private BigDecimal currInterest;

    @Column(name = "START_DATE", nullable = false)
    private Date startDate;

    @Column(name = "END_DATE", nullable = false)
    private Date endDate;

    public Loan() {}

    public Integer getId() {
        return id;
    }

    public LoanRequest getLoanRequest() {
        return loanRequest;
    }

    public void setLoanRequest(LoanRequest loanRequest) {
        this.loanRequest = loanRequest;
    }

    public BigDecimal getCurrInterestRate() {
        return currInterestRate;
    }

    public void setCurrInterestRate(BigDecimal currInterestRate) {
        this.currInterestRate = currInterestRate;
    }

    public BigDecimal getCurrInterest() {
        return currInterest;
    }

    public void setCurrInterest(BigDecimal currInterest) {
        this.currInterest = currInterest;
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

    public List<LoanExtension> getLoanExtensions() {
        return loanExtensions;
    }

    public void setLoanExtensions(List<LoanExtension> loanExtensions) {
        this.loanExtensions = loanExtensions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Loan loan = (Loan) o;

        if (!currInterestRate.equals(loan.currInterestRate)) return false;
        if (!currInterest.equals(loan.currInterest)) return false;
        if (!startDate.equals(loan.startDate)) return false;
        return endDate.equals(loan.endDate);
    }

    @Override
    public int hashCode() {
        int result = currInterestRate.hashCode();
        result = 31 * result + currInterest.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        return result;
    }
}
