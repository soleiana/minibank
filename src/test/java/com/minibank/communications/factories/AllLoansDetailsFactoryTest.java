package com.minibank.communications.factories;

import com.minibank.InjectMocksTest;
import com.minibank.communications.fixtures.CustomerFixture;
import com.minibank.communications.fixtures.LoanExtensionFixture;
import com.minibank.communications.fixtures.LoanFixture;
import com.minibank.communications.model.AllLoansDetails;
import com.minibank.communications.model.Loan;
import com.minibank.communications.model.LoanExtension;
import com.minibank.core.model.AllLoans;
import com.minibank.core.model.Customer;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class AllLoansDetailsFactoryTest extends InjectMocksTest {

    @InjectMocks
    private AllLoansDetailsFactory allLoansDetailsFactory;

    @Mock
    private CustomerFactory customerFactory;

    @Mock
    private LoanFactory loanFactory;

    @Mock
    private AllLoans allLoans;


    @Test
    public void testGetAllLoansDetails() {
        com.minibank.communications.model.Customer customer = CustomerFixture.standardCustomer();
        List<Loan> loans = createLoans();
        when(customerFactory.getCustomer(any(Customer.class))).thenReturn(customer);
        when(loanFactory.getLoans(anyList())).thenReturn(loans);

        AllLoansDetails allLoansDetails = allLoansDetailsFactory.getAllLoansDetails(allLoans);

        assertNotNull(allLoansDetails.getCustomer());
        assertEquals(1, allLoansDetails.getLoans().size());
        assertEquals(1, allLoansDetails.getLoans().get(0).getLoanExtensions().size());
    }

    private List<Loan> createLoans() {
        List<Loan> loans = new ArrayList<>();
        Loan loan = LoanFixture.standardLoan();
        LoanExtension loanExtension = LoanExtensionFixture.standardLoanExtension();
        loan.getLoanExtensions().add(loanExtension);
        loans.add(loan);
        return loans;
    }

}