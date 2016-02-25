package com.minibank.core.services.helpers;

import com.minibank.SpringContextTest;
import com.minibank.core.model.*;
import com.minibank.core.repositories.BankParametersRepository;
import com.minibank.core.repositories.CustomerRepository;
import com.minibank.core.repositories.LoanRequestRepository;
import com.minibank.core.repositories.tools.DBCleaner;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertTrue;


public class InputConstraintCheckerTest extends SpringContextTest {

    @Autowired
    private DBCleaner dbCleaner;

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BankParametersRepository bankParametersRepository;

    @Autowired
    private InputConstraintChecker checker;

    private BankParameters bankParameters;
    private LoanRequest loanRequest;

    @Before
    @Transactional
    public void setUp() {
        dbCleaner.clear();
        bankParameters = BankParametersFixture.standardBankParameters();
        bankParametersRepository.create(bankParameters);

        createLoanRequest(LoanRequestFixture.SUBMISSION_DATE);
    }

    @Test
    @Transactional
    public void testIsEqualOrLessThanMaxLoanAmount() {
        BigDecimal maxLoanAmount = bankParameters.getMaxLoanAmount();
        assertTrue(checker.isEqualOrLessThanMaxLoanAmount(maxLoanAmount));

        BigDecimal loanAmount = maxLoanAmount.add(new BigDecimal(1.00));
        assertTrue(!checker.isEqualOrLessThanMaxLoanAmount(loanAmount));

        loanAmount = maxLoanAmount.subtract(new BigDecimal(1.00));
        assertTrue(checker.isEqualOrLessThanMaxLoanAmount(loanAmount));
    }

    private void createLoanRequest(java.sql.Date submissionDate) {
        Customer customer = CustomerFixture.standardCustomer();
        loanRequest = LoanRequestFixture.standardLoanRequest();
        loanRequest.setSubmissionDate(submissionDate);
        customerRepository.create(customer);
        loanRequest.setCustomer(customer);
        loanRequestRepository.create(loanRequest);
    }
}
