package com.minibank.rest.factories;

import com.minibank.InjectMocksTest;
import com.minibank.communications.model.AllLoansDetails;
import com.minibank.rest.fixtures.CustomerFixture;
import com.minibank.rest.fixtures.LoanExtensionFixture;
import com.minibank.rest.fixtures.LoanFixture;
import com.minibank.rest.model.AllLoans;
import com.minibank.rest.model.Customer;
import com.minibank.rest.model.Loan;
import com.minibank.rest.model.LoanExtension;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.when;

public class AllLoansRestFactoryTest extends InjectMocksTest {

    @InjectMocks
    private AllLoansRestFactory allLoansRestFactory;

    @Mock
    private LoanRestFactory loanRestFactory;

    @Mock
    private CustomerRestFactory customerRestFactory;

    @Mock
    private AllLoansDetails allLoansDetails;


    @Test
    public void testGetAllLoans() {
        Customer customer = CustomerFixture.standardCustomer();
        List<Loan> loans = createLoans();
        when(customerRestFactory.getCustomer(any(com.minibank.communications.model.Customer.class))).thenReturn(customer);
        when(loanRestFactory.getLoans(anyList())).thenReturn(loans);

        AllLoans allLoans = allLoansRestFactory.getAllLoans(allLoansDetails);

        assertNotNull(allLoans.getCustomer());
        assertEquals(1, allLoans.getLoans().size());
        assertEquals(1, allLoans.getLoans().get(0).getLoanExtensions().size());
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