package com.minibank.communications.factories;

import com.minibank.communications.model.LoanExtension;
import com.minibank.core.fixtures.LoanExtensionFixture;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LoanExtensionFactoryTest {

    private LoanExtensionFactory loanExtensionFactory = new LoanExtensionFactory();


    @Test
    public void testGetLoanExtensions()  {
        final int NUMBER_OF_EXTENSIONS = 2;
        List<com.minibank.core.model.LoanExtension> loanExtensions = createLoanExtensions(NUMBER_OF_EXTENSIONS);
        com.minibank.core.model.LoanExtension loanExtension = loanExtensions.get(0);

        List<LoanExtension> resultLoanExtensions = loanExtensionFactory.getLoanExtensions(loanExtensions);

        assertEquals(NUMBER_OF_EXTENSIONS, resultLoanExtensions.size());
        LoanExtension resultLoanExtension = resultLoanExtensions.get(0);
        assertLoanExtension(loanExtension, resultLoanExtension);
    }

    private List<com.minibank.core.model.LoanExtension> createLoanExtensions(int numberOfExtensions) {
        List<com.minibank.core.model.LoanExtension> loanExtensions = new ArrayList<>();
        for (int i = 1; i <= numberOfExtensions; i++) {
            com.minibank.core.model.LoanExtension loanExtension = LoanExtensionFixture.standardLoanExtension();
            loanExtensions.add(loanExtension);
        }
        return loanExtensions;
    }

    private void assertLoanExtension(com.minibank.core.model.LoanExtension coreLoanExtension, LoanExtension communicationLoanExtension) {
        assertEquals(coreLoanExtension.getInterest(), communicationLoanExtension.getInterest());
        assertEquals(coreLoanExtension.getInterestRate(), communicationLoanExtension.getInterestRate());
        assertEquals(coreLoanExtension.getStartDate(), communicationLoanExtension.getStartDate());
        assertEquals(coreLoanExtension.getEndDate(), communicationLoanExtension.getEndDate());
        assertEquals(coreLoanExtension.getSubmissionDate(), communicationLoanExtension.getSubmissionDate());

    }
}