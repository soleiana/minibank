package com.minibank.core.services.integration;

import com.minibank.SpringContextTest;
import com.minibank.core.communications.GetAllLoansQuery;
import com.minibank.core.communications.GetAllLoansResponse;
import com.minibank.core.communications.domain.AllLoansDetails;
import com.minibank.core.domain.*;
import com.minibank.core.repositories.*;
import com.minibank.core.repositories.tools.DBCleaner;
import com.minibank.core.services.GetAllLoansQueryHandler;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.assertEquals;


public class GetAllLoansQueryHandlerTest extends SpringContextTest {

    @Autowired
    private DBCleaner dbCleaner;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BankParametersRepository bankParametersRepository;

    @Autowired
    private LoanExtensionRepository loanExtensionRepository;

    @Autowired
    private GetAllLoansQueryHandler getAllLoansQueryHandler;

    private BankParameters bankParameters;
    private Customer customer;
    private LoanRequest loanRequest;
    private Loan loan;
    private LoanExtension loanExtension;

    @Before
    public void setUp() {
        dbCleaner.clear();
        bankParameters = BankParametersFixture.standardBankParameters();
        customer = CustomerFixture.standardCustomer();
        bankParametersRepository.create(bankParameters);
        customerRepository.create(customer);
    }

    @Test
    @Transactional
    public void testExecute_1() {
        //customer has no loans
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
    @Transactional
    public void testExecute_2() {
        //customer has 2 loans
        //customer has no loan extensions
        prepareTestData_1();
        GetAllLoansQuery getAllLoansQuery = createGetAllLoansQuery();

        GetAllLoansResponse getAllLoansResponse = getAllLoansQueryHandler.execute(getAllLoansQuery);

        AllLoansDetails allLoansDetails = getAllLoansResponse.getAllLoansDetails();

        assertEquals(2, allLoansDetails.getLoans().size());
        assertEquals(true, getAllLoansResponse.isEntityFound());
    }

    @Test
    @Transactional
    public void testExecute_3() {
        //customer has 2 loans
        //customer has 2 extensions of a loan
        prepareTestData_2();
        GetAllLoansQuery getAllLoansQuery = createGetAllLoansQuery();

        GetAllLoansResponse getAllLoansResponse = getAllLoansQueryHandler.execute(getAllLoansQuery);

        AllLoansDetails allLoansDetails = getAllLoansResponse.getAllLoansDetails();

        assertEquals(2, allLoansDetails.getLoans().size());
        assertEquals(2, allLoansDetails.getLoans().get(0).getLoanExtensions().size());
        assertEquals(0, allLoansDetails.getLoans().get(1).getLoanExtensions().size());
        assertEquals(true, getAllLoansResponse.isEntityFound());
    }

    private void createLoan() {
        loanRequest = LoanRequestFixture.standardLoanRequest();
        loanRequest.setCustomer(customer);
        loanRequestRepository.create(loanRequest);
        loan = LoanFixture.standardLoan();
        customer.addLoan(loan);
        loanRepository.create(loan);
    }

    private void createLoanExtension() {
        loanExtension = LoanExtensionFixture.standardLoanExtension();
        loan.addLoanExtension(loanExtension);
        loanExtensionRepository.create(loanExtension);
    }

    private GetAllLoansQuery createGetAllLoansQuery() {
        return new GetAllLoansQuery(customer.getId());
    }

    private void prepareTestData_1() {
        createLoan();
        createLoan();
    }

    private void prepareTestData_2() {
        createLoan();
        createLoanExtension();
        createLoanExtension();
        createLoan();
    }
}
