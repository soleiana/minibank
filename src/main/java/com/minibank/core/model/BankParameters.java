package com.minibank.core.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;


@Entity
@Table(name = "BANK_PARAMETERS")
public class BankParameters {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "MAX_LOAN_AMOUNT", precision = 10, scale = 2, nullable = false)
    private BigDecimal maxLoanAmount;

    @Column(name = "BASE_INTEREST_RATE", precision = 10, scale = 2, nullable = false)
    private BigDecimal baseInterestRate;

    @Column(name = "INTEREST_RATE_FACTOR", precision = 10, scale = 2, nullable = false)
    private BigDecimal interestRateFactor;

    @Column(name = "MAX_LOAN_ATTEMPTS", nullable = false)
    private Byte maxLoanAttempts;

    @Column(name = "RISK_TIME_START", nullable = false)
    private Time riskTimeStart;

    @Column(name = "RISK_TIME_END", nullable = false)
    private Time riskTimeEnd;

    @Column(name = "LOAN_EXTENSION_TERM", nullable = false)
    private Short loanExtensionTerm;

    public BankParameters() {}

    public Integer getId() {
        return id;
    }

    public BigDecimal getMaxLoanAmount() {
        return maxLoanAmount;
    }

    public void setMaxLoanAmount(BigDecimal maxLoanAmount) {
        this.maxLoanAmount = maxLoanAmount;
    }

    public BigDecimal getBaseInterestRate() {
        return baseInterestRate;
    }

    public void setBaseInterestRate(BigDecimal baseInterestRate) {
        this.baseInterestRate = baseInterestRate;
    }

    public BigDecimal getInterestRateFactor() {
        return interestRateFactor;
    }

    public void setInterestRateFactor(BigDecimal interestRateFactor) {
        this.interestRateFactor = interestRateFactor;
    }

    public void setMaxLoanAttempts(Byte maxLoanAttempts) {
        this.maxLoanAttempts = maxLoanAttempts;
    }

    public Byte getMaxLoanAttempts() {
        return maxLoanAttempts;
    }

    public Time getRiskTimeStart() {
        return riskTimeStart;
    }

    public void setRiskTimeStart(Time riskTimeStart) {
        this.riskTimeStart = riskTimeStart;
    }

    public Time getRiskTimeEnd() {
        return riskTimeEnd;
    }

    public void setRiskTimeEnd(Time riskTimeEnd) {
        this.riskTimeEnd = riskTimeEnd;
    }

    public Short getLoanExtensionTerm() {
        return loanExtensionTerm;
    }

    public void setLoanExtensionTerm(Short loanExtensionTerm) {
        this.loanExtensionTerm = loanExtensionTerm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankParameters that = (BankParameters) o;

        if (!maxLoanAmount.equals(that.maxLoanAmount)) return false;
        if (!baseInterestRate.equals(that.baseInterestRate)) return false;
        if (!interestRateFactor.equals(that.interestRateFactor)) return false;
        if (!maxLoanAttempts.equals(that.maxLoanAttempts)) return false;
        if (!riskTimeStart.equals(that.riskTimeStart)) return false;
        if (!riskTimeEnd.equals(that.riskTimeEnd)) return false;
        return loanExtensionTerm.equals(that.loanExtensionTerm);

    }

    @Override
    public int hashCode() {
        int result = maxLoanAmount.hashCode();
        result = 31 * result + baseInterestRate.hashCode();
        result = 31 * result + interestRateFactor.hashCode();
        result = 31 * result + maxLoanAttempts.hashCode();
        result = 31 * result + riskTimeStart.hashCode();
        result = 31 * result + riskTimeEnd.hashCode();
        result = 31 * result + loanExtensionTerm.hashCode();
        return result;
    }
}
