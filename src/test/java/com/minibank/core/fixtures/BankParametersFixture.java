package com.minibank.core.fixtures;

import com.minibank.core.model.BankParameters;

import java.math.BigDecimal;
import java.time.LocalTime;


public class BankParametersFixture {

    public static final BigDecimal NEW_MAX_LOAN_AMOUNT = new BigDecimal("5000.00");
    public static final BigDecimal NEW_BASE_INTEREST_RATE = new BigDecimal("150.00");
    public static final BigDecimal NEW_INTEREST_RATE_FACTOR = new BigDecimal("2.00");
    public static final Byte NEW_MAX_LOAN_ATTEMPTS = new Byte("5");
    public static final LocalTime NEW_RISK_TIME_START = LocalTime.of(22, 0, 0);
    public static final LocalTime NEW_RISK_TIME_END = LocalTime.of(8, 0, 0);
    public static final Short NEW_LOAN_EXTENSION_TERM = new Short("14");

    private static final BigDecimal STANDARD_MAX_LOAN_AMOUNT = new BigDecimal("4000.00");
    private static final BigDecimal STANDARD_BASE_INTEREST_RATE = new BigDecimal("100.00");
    private static final BigDecimal STANDARD_INTEREST_RATE_FACTOR = new BigDecimal("1.50");
    private static final Byte STANDARD_MAX_LOAN_ATTEMPTS = new Byte("3");
    private static final LocalTime STANDARD_RISK_TIME_START = LocalTime.of(0, 0, 0);
    private static final LocalTime STANDARD_RISK_TIME_END = LocalTime.of(7, 0, 0);
    private static final Short STANDARD_lOAN_EXTENSION_TERM = new Short("7");

    public static BankParameters standardBankParameters() {
        BankParameters bankParams = new BankParameters();
        bankParams.setMaxLoanAmount(STANDARD_MAX_LOAN_AMOUNT);
        bankParams.setBaseInterestRate(STANDARD_BASE_INTEREST_RATE);
        bankParams.setInterestRateFactor(STANDARD_INTEREST_RATE_FACTOR);
        bankParams.setMaxLoanAttempts(STANDARD_MAX_LOAN_ATTEMPTS);
        bankParams.setRiskTimeStart(STANDARD_RISK_TIME_START);
        bankParams.setRiskTimeEnd(STANDARD_RISK_TIME_END);
        bankParams.setLoanExtensionTerm(STANDARD_lOAN_EXTENSION_TERM);
        return bankParams;
    }

    public static BankParameters newBankParameters() {
        BankParameters bankParams = new BankParameters();
        bankParams.setMaxLoanAmount(NEW_MAX_LOAN_AMOUNT);
        bankParams.setBaseInterestRate(NEW_BASE_INTEREST_RATE);
        bankParams.setInterestRateFactor(NEW_INTEREST_RATE_FACTOR);
        bankParams.setMaxLoanAttempts(NEW_MAX_LOAN_ATTEMPTS);
        bankParams.setRiskTimeStart(NEW_RISK_TIME_START);
        bankParams.setRiskTimeEnd(NEW_RISK_TIME_END);
        bankParams.setLoanExtensionTerm(NEW_LOAN_EXTENSION_TERM);
        return bankParams;
    }
}
