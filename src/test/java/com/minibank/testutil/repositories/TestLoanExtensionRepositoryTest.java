package com.minibank.testutil.repositories;

import com.minibank.SpringContextTest;
import com.minibank.core.fixtures.LoanExtensionFixture;
import com.minibank.core.fixtures.LoanFixture;
import com.minibank.core.model.Loan;
import com.minibank.core.model.LoanExtension;
import com.minibank.core.repositories.LoanRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestLoanExtensionRepositoryTest extends SpringContextTest {

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private TestLoanExtensionRepository testLoanExtensionRepository;

    @Autowired
    private LoanRepository loanRepository;


    @Before
    public void setUp() {
        databaseCleaner.clear();
    }

    @Test
    public void testGetLastIfLoanExtensionDoesNotExist() {
        LoanExtension retrievedLoanExtension = testLoanExtensionRepository.getLast();
        assertNull(retrievedLoanExtension);
    }

    @Test
    public void testGetLastIfLoanExtensionExists() {
        LoanExtension loanExtension = createLoanExtension();
        LoanExtension retrievedLoanExtension = testLoanExtensionRepository.getLast();
        assertEquals(loanExtension, retrievedLoanExtension);
    }

    private Loan createLoan() {
        Loan loan = LoanFixture.standardLoan();
        loanRepository.create(loan);
        return loan;
    }

    private LoanExtension createLoanExtension() {
        Loan loan = createLoan();
        LoanExtension loanExtension = LoanExtensionFixture.standardLoanExtension();
        loan.addLoanExtension(loanExtension);
        return loanExtension;
    }

}