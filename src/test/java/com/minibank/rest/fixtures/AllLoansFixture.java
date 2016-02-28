package com.minibank.rest.fixtures;

import com.minibank.rest.model.AllLoans;
import com.minibank.rest.model.Customer;
import com.minibank.rest.model.Loan;
import com.minibank.rest.model.LoanExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class AllLoansFixture {

    private static final String NAME = "James";
    private static final String SURNAME = "Bond";
    private static final Integer CUSTOMER_ID = 2;
    private static final Integer LOAN_ID = 5;
    private static final BigDecimal CURR_INTEREST_RATE = new BigDecimal("150");
    private static final BigDecimal CURR_INTEREST = new BigDecimal("600");
    private static final BigDecimal INTEREST_RATE = CURR_INTEREST_RATE;
    private static final BigDecimal INTEREST = CURR_INTEREST;
    private static final BigDecimal AMOUNT = new BigDecimal("500");
    private static final LocalDate LOAN_START_DATE = LocalDate.of(2014, 9, 1);
    private static final LocalDate LOAN_END_DATE = LocalDate.of(2014, 9, 20);
    private static final LocalDate LOAN_EXTENSION_START_DATE = LocalDate.of(2014, 9, 10);
    private static final LocalDate LOAN_EXTENSION_END_DATE = LOAN_END_DATE;
    private static final LocalDate LOAN_EXTENSION_SUBMISSION_DATE = LOAN_EXTENSION_START_DATE;


    public static AllLoans standardAllLoans() {
        AllLoans allLoans = new AllLoans();
        Customer customer = standardCustomer();
        allLoans.setCustomer(customer);

        Loan loan = standardLoan();
        List<LoanExtension> loanExtensions = new ArrayList<>();
        loanExtensions.add(standardLoanExtension());
        loan.setLoanExtensions(loanExtensions);

        List<Loan> loans = new ArrayList<>();
        loans.add(loan);
        allLoans.setLoans(loans);
        return allLoans;
    }

    private static Customer standardCustomer() {
        Customer customer = new Customer();
        customer.setId(CUSTOMER_ID);
        customer.setName(NAME);
        customer.setSurname(SURNAME);
        return customer;
    }

    private static  Loan standardLoan() {
        Loan loan = new Loan();
        loan.setId(LOAN_ID);
        loan.setCurrInterestRate(CURR_INTEREST_RATE);
        loan.setCurrInterest(CURR_INTEREST);
        loan.setAmount(AMOUNT);
        loan.setStartDate(LOAN_START_DATE);
        loan.setEndDate(LOAN_END_DATE);
        return  loan;
    }

    private static LoanExtension standardLoanExtension() {
        LoanExtension loanExtension = new LoanExtension();
        loanExtension.setInterestRate(INTEREST_RATE);
        loanExtension.setInterest(INTEREST);
        loanExtension.setStartDate(LOAN_EXTENSION_START_DATE);
        loanExtension.setEndDate(LOAN_EXTENSION_END_DATE);
        loanExtension.setSubmissionDate(LOAN_EXTENSION_SUBMISSION_DATE);
        return loanExtension;
    }
}
