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
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import static junit.framework.TestCase.assertTrue;


public class CreditExpertTest extends SpringContextTest {

    @Autowired
    private DBCleaner dbCleaner;

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    @Autowired
    private TestCustomerRepository testCustomerRepository;

    @Autowired
    private BankParametersRepository bankParametersRepository;

    @Autowired
    private LoanExpert expert;

    private BankParameters bankParameters;
    private LoanRequest loanRequest;


    @Before
    public void setUp() {
        dbCleaner.clear();
        bankParameters = BankParametersFixture.standardBankParameters();
        bankParametersRepository.create(bankParameters);
    }

    @Test
    public void testHasRisks_1() {
        //negative loan request scenario with max loan amount in risk time
        //loan request gets rejected

        BigDecimal maxLoanAmount = bankParameters.getMaxLoanAmount();
        LocalTime riskTimeStart = bankParameters.getRiskTimeStart();
        LocalTime submissionTime = riskTimeStart.plusHours(1);

        createLoanRequest();
        loanRequest.setAmount(maxLoanAmount);
        loanRequest.setSubmissionTime(submissionTime);
        loanRequestRepository.create(loanRequest);

        assertTrue(expert.hasRisks(loanRequest));
    }

    @Test
    public void testHasRisks_2() {
        //negative loan request scenario with max number of loan requests (attempts)
        //during one day

        LocalDate now = LocalDate.now();
        for(int i = 0; i <= bankParameters.getMaxLoanAttempts(); i++) {
            createLoanRequest();
            loanRequest.setSubmissionDate(now);
            loanRequestRepository.create(loanRequest);
        }
        assertTrue(expert.hasRisks(loanRequest));
    }

    @Test
    public void testHasRisks_3() {
        //positive loan request scenario with loan amount below maximum in no risk time
        //loan request gets accepted

        createLoanRequest();
        loanRequestRepository.create(loanRequest);
        assertTrue(!expert.hasRisks(loanRequest));
    }

    @Test
    public void testHasRisks_4() {
        //positive loan request scenario with loan amount below maximum in no risk time
        //loan requests get accepted (maxLoanAttempts - 1) times

        LocalDate now = LocalDate.now();

        for(int i = 0; i < bankParameters.getMaxLoanAttempts()-1; i++) {
            createLoanRequest();
            loanRequest.setSubmissionDate(now);
            loanRequestRepository.create(loanRequest);
        }
        assertTrue(!expert.hasRisks(loanRequest));
    }

    private void createLoanRequest() {
        Customer customer = CustomerFixture.standardCustomer();
        loanRequest = LoanRequestFixture.standardLoanRequest();
        testCustomerRepository.create(customer);
        loanRequest.setCustomer(customer);
    }

}
