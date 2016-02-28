package com.minibank.rest.factories;

import com.minibank.communications.fixtures.LoanExtensionFixture;
import com.minibank.communications.model.LoanExtension;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LoanExtensionRestFactoryTest {

    private LoanExtensionRestFactory loanExtensionRestFactory = new LoanExtensionRestFactory();

    @Test
    public void testGetLoanExtensions()  {
        final int NUMBER_OF_EXTENSIONS = 2;
        List<LoanExtension> loanExtensions = createLoanExtensions(NUMBER_OF_EXTENSIONS);
        LoanExtension loanExtension = loanExtensions.get(0);

        List<com.minibank.rest.model.LoanExtension> resultLoanExtensions = loanExtensionRestFactory.getLoanExtensions(loanExtensions);

        assertEquals(NUMBER_OF_EXTENSIONS, resultLoanExtensions.size());
        com.minibank.rest.model.LoanExtension resultLoanExtension = resultLoanExtensions.get(0);
        assertLoanExtension(loanExtension, resultLoanExtension);
    }

    private List<LoanExtension> createLoanExtensions(int numberOfExtensions) {
        List<LoanExtension> loanExtensions = new ArrayList<>();
        for (int i = 1; i <= numberOfExtensions; i++) {
            LoanExtension loanExtension = LoanExtensionFixture.standardLoanExtension();
            loanExtensions.add(loanExtension);
        }
        return loanExtensions;
    }

    private void assertLoanExtension(LoanExtension communicationLoanExtension, com.minibank.rest.model.LoanExtension restLoanExtension) {
        assertEquals(communicationLoanExtension.getInterest(), restLoanExtension.getInterest());
        assertEquals(communicationLoanExtension.getInterestRate(), restLoanExtension.getInterestRate());
        assertEquals(communicationLoanExtension.getStartDate(), restLoanExtension.getStartDate());
        assertEquals(communicationLoanExtension.getEndDate(), restLoanExtension.getEndDate());
        assertEquals(communicationLoanExtension.getSubmissionDate(), restLoanExtension.getSubmissionDate());
    }
}