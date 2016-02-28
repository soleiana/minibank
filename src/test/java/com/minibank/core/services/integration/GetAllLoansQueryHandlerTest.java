package com.minibank.core.services.integration;

import com.minibank.SpringContextTest;
import com.minibank.communications.GetAllLoansQuery;
import com.minibank.communications.GetAllLoansResponse;
import com.minibank.communications.model.AllLoansDetails;
import com.minibank.core.fixtures.*;
import com.minibank.core.model.*;
import com.minibank.core.repositories.BankParametersRepository;
import com.minibank.core.repositories.LoanRepository;
import com.minibank.core.repositories.LoanRequestRepository;
import com.minibank.core.repositories.TestCustomerRepository;
import com.minibank.core.repositories.helpers.DBCleaner;
import com.minibank.core.services.GetAllLoansQueryHandler;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class GetAllLoansQueryHandlerTest extends SpringContextTest {

    @Autowired
    private DBCleaner dbCleaner;

    @Autowired
    private TestCustomerRepository testCustomerRepository;

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BankParametersRepository bankParametersRepository;

    @Autowired
    private GetAllLoansQueryHandler getAllLoansQueryHandler;

    private BankParameters bankParameters;
    private Customer customer;

    @Before
    public void setUp() {
        dbCleaner.clear();
        bankParameters = BankParametersFixture.standardBankParameters();
        customer = CustomerFixture.standardCustomer();
        bankParametersRepository.create(bankParameters);
        testCustomerRepository.create(customer);
    }

    @Test
    public void testExecuteCustomerHasNoLoans() {
        GetAllLoansQuery getAllLoansQuery = createGetAllLoansQuery();

        GetAllLoansResponse getAllLoansResponse = getAllLoansQueryHandler.execute(getAllLoansQuery);

        AllLoansDetails allLoansDetails = getAllLoansResponse.getAllLoansDetails();

        assertEquals(customer.getId(), allLoansDetails.getCustomerId());
        assertEquals(customer.getName(), allLoansDetails.getName());
        assertEquals(customer.getSurname(), allLoansDetails.getSurname());
        assertEquals(0, allLoansDetails.getLoans().size());
        assertEquals(false, getAllLoansResponse.isEntityFound());
    }

    @Test
    public void testExecuteCustomerHasLoansWithoutExtensions() {
        final int NUMBER_OF_LOANS = 2;

        createLoans(NUMBER_OF_LOANS);
        GetAllLoansQuery getAllLoansQuery = createGetAllLoansQuery();

        GetAllLoansResponse getAllLoansResponse = getAllLoansQueryHandler.execute(getAllLoansQuery);

        AllLoansDetails allLoansDetails = getAllLoansResponse.getAllLoansDetails();
        assertEquals(NUMBER_OF_LOANS, allLoansDetails.getLoans().size());
        assertEquals(true, getAllLoansResponse.isEntityFound());
    }

    @Test
    public void testExecuteCusterHasLoansAndOneLoanHasExtensions() {
        final int NUMBER_OF_LOANS = 2;
        final int NUMBER_OF_EXTENSIONS = 2;

        List<Loan> loans = createLoans(NUMBER_OF_LOANS);
        createLoanExtensions(loans.get(0), NUMBER_OF_EXTENSIONS);
        GetAllLoansQuery getAllLoansQuery = createGetAllLoansQuery();

        GetAllLoansResponse getAllLoansResponse = getAllLoansQueryHandler.execute(getAllLoansQuery);

        AllLoansDetails allLoansDetails = getAllLoansResponse.getAllLoansDetails();

        assertEquals(NUMBER_OF_LOANS, allLoansDetails.getLoans().size());
        assertEquals(NUMBER_OF_EXTENSIONS, allLoansDetails.getLoans().get(0).getLoanExtensions().size());
        assertEquals(0, allLoansDetails.getLoans().get(1).getLoanExtensions().size());
        assertEquals(true, getAllLoansResponse.isEntityFound());
    }

    private Loan createLoan() {
        LoanRequest loanRequest = LoanRequestFixture.standardLoanRequest();
        loanRequest.setCustomer(customer);
        loanRequestRepository.create(loanRequest);
        Loan loan = LoanFixture.standardLoan();
        customer.addLoan(loan);
        loanRepository.create(loan);
        return loan;
    }

    private GetAllLoansQuery createGetAllLoansQuery() {
        return new GetAllLoansQuery(customer.getId());
    }

    private List<Loan> createLoans(int numberOfLoans) {
       List<Loan> loans = new ArrayList<>();
       for (int i = 1; i <= numberOfLoans; i++) {
           loans.add(createLoan());
       }
        return loans;
    }

    private void createLoanExtensions(Loan loan, int numberOfExtensions) {
        for (int i = 1; i <= numberOfExtensions; i++) {
            LoanExtension loanExtension = LoanExtensionFixture.standardLoanExtension();
            loan.addLoanExtension(loanExtension);
        }
        loanRepository.update(loan);
    }

}
