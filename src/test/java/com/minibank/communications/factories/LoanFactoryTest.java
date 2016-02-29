package com.minibank.communications.factories;

import com.minibank.InjectMocksTest;
import com.minibank.communications.fixtures.LoanExtensionFixture;
import com.minibank.communications.model.LoanExtension;
import com.minibank.core.fixtures.LoanFixture;
import com.minibank.core.model.Loan;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.when;

public class LoanFactoryTest extends InjectMocksTest {

    @InjectMocks
    private LoanFactory loanFactory;

    @Mock
    private LoanExtensionFactory loanExtensionFactory;


    @Test
    public void testGetLoansIfLoanExtensionsExist() {
        final int NUMBER_OF_LOANS = 2;
        final int NUMBER_OF_EXTENSIONS = 3;
        List<Loan> loans = createLoans(NUMBER_OF_LOANS);
        Loan loan = loans.get(0);
        List<LoanExtension> loanExtensions = createLoanExtensions(NUMBER_OF_EXTENSIONS);
        when(loanExtensionFactory.getLoanExtensions(anyList())).thenReturn(loanExtensions);

        List<com.minibank.communications.model.Loan> resultLoans = loanFactory.getLoans(loans);
        assertEquals(NUMBER_OF_LOANS, resultLoans.size());
        assertNumberOfLoanExtensions(resultLoans, NUMBER_OF_EXTENSIONS);
        com.minibank.communications.model.Loan resultLoan = resultLoans.get(0);
        assertLoan(loan, resultLoan);
    }

    @Test
    public void testGetLoansIfNoLoanExtensionsExist() {
        final int NUMBER_OF_LOANS = 2;
        final int NUMBER_OF_EXTENSIONS = 0;
        List<Loan> loans = createLoans(NUMBER_OF_LOANS);
        Loan loan = loans.get(0);
        List<LoanExtension> loanExtensions = new ArrayList<>();
        when(loanExtensionFactory.getLoanExtensions(anyList())).thenReturn(loanExtensions);

        List<com.minibank.communications.model.Loan> resultLoans = loanFactory.getLoans(loans);
        assertEquals(NUMBER_OF_LOANS, resultLoans.size());
        assertNumberOfLoanExtensions(resultLoans, NUMBER_OF_EXTENSIONS);
        com.minibank.communications.model.Loan resultLoan = resultLoans.get(0);
        assertLoan(loan, resultLoan);
    }

    private List<Loan> createLoans(int numberOfLoans) {
        List<Loan> loans = new ArrayList<>();
        for (int i = 1; i <= numberOfLoans; i++) {
            Loan loan = LoanFixture.standardLoan();
            loans.add(loan);
        }
        return loans;
    }

    private List<LoanExtension> createLoanExtensions(int numberOfExtensions) {
        List<LoanExtension> loanExtensions = new ArrayList<>();
        for (int i = 1; i <= numberOfExtensions; i++) {
            LoanExtension loanExtension = LoanExtensionFixture.standardLoanExtension();
            loanExtensions.add(loanExtension);
        }
        return loanExtensions;
    }

    private void assertNumberOfLoanExtensions(List<com.minibank.communications.model.Loan> loans, int numberOfExtensions) {
        for (com.minibank.communications.model.Loan loan: loans) {
            assertEquals(numberOfExtensions, loan.getLoanExtensions().size());
        }
    }

    private void assertLoan(Loan coreLoan, com.minibank.communications.model.Loan communicationLoan) {
        assertEquals(coreLoan.getId(), communicationLoan.getId());
        assertEquals(coreLoan.getAmount(), communicationLoan.getAmount());
        assertEquals(coreLoan.getStartDate(), communicationLoan.getStartDate());
        assertEquals(coreLoan.getEndDate(), communicationLoan.getEndDate());
        assertEquals(coreLoan.getCurrentInterest(), communicationLoan.getCurrInterest());
        assertEquals(coreLoan.getCurrentInterestRate(), communicationLoan.getCurrInterestRate());
    }
}