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
    private BigDecimal currentInterestRate;

    @Column(name = "CURRENT_INTEREST", precision = 10, scale = 2, nullable = false)
    private BigDecimal currentInterest;

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

    public BigDecimal getCurrentInterestRate() {
        return currentInterestRate;
    }

    public void setCurrentInterestRate(BigDecimal currentInterestRate) {
        this.currentInterestRate = currentInterestRate;
    }

    public BigDecimal getCurrentInterest() {
        return currentInterest;
    }

    public void setCurrentInterest(BigDecimal currentInterest) {
        this.currentInterest = currentInterest;
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
        if (!currentInterestRate.equals(loan.currentInterestRate)) return false;
        if (!currentInterest.equals(loan.currentInterest)) return false;
        if (!startDate.equals(loan.startDate)) return false;
        return endDate.equals(loan.endDate);
    }

    @Override
    public int hashCode() {
        int result = amount.hashCode();
        result = 31 * result + term.hashCode();
        result = 31 * result + currentInterestRate.hashCode();
        result = 31 * result + currentInterest.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        return result;
    }
}
