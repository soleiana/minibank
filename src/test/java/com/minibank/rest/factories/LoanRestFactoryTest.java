package com.minibank.rest.factories;

import com.minibank.InjectMocksTest;
import com.minibank.communications.fixtures.LoanFixture;
import com.minibank.communications.model.Loan;
import com.minibank.rest.fixtures.LoanExtensionFixture;
import com.minibank.rest.model.LoanExtension;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.when;

public class LoanRestFactoryTest extends InjectMocksTest {

    @InjectMocks
    private LoanRestFactory loanRestFactory;

    @Mock
    private LoanExtensionRestFactory loanExtensionRestFactory;

    @Test
    public void testGetLoansIfLoanExtensionsExist() {
        final int NUMBER_OF_LOANS = 2;
        final int NUMBER_OF_EXTENSIONS = 3;
        List<Loan> loans = createLoans(NUMBER_OF_LOANS);
        Loan loan = loans.get(0);
        List<LoanExtension> loanExtensions = createLoanExtensions(NUMBER_OF_EXTENSIONS);
        when(loanExtensionRestFactory.getLoanExtensions(anyList())).thenReturn(loanExtensions);

        List<com.minibank.rest.model.Loan> resultLoans = loanRestFactory.getLoans(loans);
        assertEquals(NUMBER_OF_LOANS, resultLoans.size());
        assertNumberOfLoanExtensions(resultLoans, NUMBER_OF_EXTENSIONS);
        com.minibank.rest.model.Loan resultLoan = resultLoans.get(0);
        assertLoan(loan, resultLoan);
    }

    @Test
    public void testGetLoansIfNoLoanExtensionsExist() {
        final int NUMBER_OF_LOANS = 2;
        final int NUMBER_OF_EXTENSIONS = 0;
        List<Loan> loans = createLoans(NUMBER_OF_LOANS);
        Loan loan = loans.get(0);
        List<LoanExtension> loanExtensions = new ArrayList<>();
        when(loanExtensionRestFactory.getLoanExtensions(anyList())).thenReturn(loanExtensions);

        List<com.minibank.rest.model.Loan> resultLoans = loanRestFactory.getLoans(loans);
        assertEquals(NUMBER_OF_LOANS, resultLoans.size());
        assertNumberOfLoanExtensions(resultLoans, NUMBER_OF_EXTENSIONS);
        com.minibank.rest.model.Loan resultLoan = resultLoans.get(0);
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

    private void assertNumberOfLoanExtensions(List<com.minibank.rest.model.Loan> loans, int numberOfExtensions) {
        for (com.minibank.rest.model.Loan loan: loans) {
            assertEquals(numberOfExtensions, loan.getLoanExtensions().size());
        }
    }

    private void assertLoan(Loan communicationLoan, com.minibank.rest.model.Loan restLoan) {
        assertEquals(communicationLoan.getId(), restLoan.getId());
        assertEquals(communicationLoan.getAmount(), restLoan.getAmount());
        assertEquals(communicationLoan.getStartDate(), restLoan.getStartDate());
        assertEquals(communicationLoan.getEndDate(), restLoan.getEndDate());
        assertEquals(communicationLoan.getCurrInterest(), restLoan.getCurrInterest());
        assertEquals(communicationLoan.getCurrInterestRate(), restLoan.getCurrInterestRate());
    }
}