package com.minibank.core.services.helpers;

import com.minibank.SpringContextTest;
import com.minibank.core.fixtures.BankParametersFixture;
import com.minibank.core.fixtures.CustomerFixture;
import com.minibank.core.fixtures.LoanRequestFixture;
import com.minibank.core.model.BankParameters;
import com.minibank.core.model.Customer;
import com.minibank.core.model.LoanRequest;
import com.minibank.core.repositories.BankParametersRepository;
import com.minibank.core.repositories.LoanRequestRepository;
import com.minibank.core.repositories.TestCustomerRepository;
import com.minibank.core.repositories.helpers.DBCleaner;
import com.minibank.core.services.InputConstraintChecker;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertTrue;


public class InputConstraintCheckerTest extends SpringContextTest {

    @Autowired
    private DBCleaner dbCleaner;

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    @Autowired
    private TestCustomerRepository testCustomerRepository;

    @Autowired
    private BankParametersRepository bankParametersRepository;

    @Autowired
    private InputConstraintChecker checker;

    private BankParameters bankParameters;
    private LoanRequest loanRequest;

    @Before
    public void setUp() {
        dbCleaner.clear();
        bankParameters = BankParametersFixture.standardBankParameters();
        bankParametersRepository.create(bankParameters);

        createLoanRequest(LoanRequestFixture.SUBMISSION_DATE);
    }

    @Test
    public void testIsEqualOrLessThanMaxLoanAmount() {
        BigDecimal maxLoanAmount = bankParameters.getMaxLoanAmount();
        assertTrue(checker.isEqualOrLessThanMaxLoanAmount(maxLoanAmount));

        BigDecimal loanAmount = maxLoanAmount.add(new BigDecimal(1.00));
        assertTrue(!checker.isEqualOrLessThanMaxLoanAmount(loanAmount));

        loanAmount = maxLoanAmount.subtract(new BigDecimal(1.00));
        assertTrue(checker.isEqualOrLessThanMaxLoanAmount(loanAmount));
    }

    private void createLoanRequest(LocalDate submissionDate) {
        Customer customer = CustomerFixture.standardCustomer();
        loanRequest = LoanRequestFixture.standardLoanRequest();
        loanRequest.setSubmissionDate(submissionDate);
        testCustomerRepository.create(customer);
        loanRequest.setCustomer(customer);
        loanRequestRepository.create(loanRequest);
    }
}
