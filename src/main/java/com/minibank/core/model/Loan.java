package com.minibank.core.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "LOAN")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOAN_ID")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<LoanExtension> loanExtensions = new ArrayList<>();

    @Column(name = "AMOUNT", precision = 10, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(name = "TERM", nullable = false)
    private Integer term;

    @Column(name = "CURRENT_INTEREST_RATE", precision = 10, scale = 2, nullable = false)
    private BigDecimal currInterestRate;

    @Column(name = "CURRENT_INTEREST", precision = 10, scale = 2, nullable = false)
    private BigDecimal currInterest;

    @Column(name = "START_DATE", nullable = false)
    private LocalDate startDate;

    @Column(name = "END_DATE", nullable = false)
    private LocalDate endDate;

    public Loan() {}

    public Integer getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<LoanExtension> getLoanExtensions() {
        return loanExtensions;
    }

    public void addLoanExtension(LoanExtension loanExtension) {
        getLoanExtensions().add(loanExtension);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Loan loan = (Loan) o;

        if (!amount.equals(loan.amount)) return false;
        if (!term.equals(loan.term)) return false;
        if (!currInterestRate.equals(loan.currInterestRate)) return false;
        if (!currInterest.equals(loan.currInterest)) return false;
        if (!startDate.equals(loan.startDate)) return false;
        return endDate.equals(loan.endDate);
    }

    @Override
    public int hashCode() {
        int result = amount.hashCode();
        result = 31 * result + term.hashCode();
        result = 31 * result + currInterestRate.hashCode();
        result = 31 * result + currInterest.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        return result;
    }
}
