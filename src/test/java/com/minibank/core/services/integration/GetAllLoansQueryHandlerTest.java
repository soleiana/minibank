package com.minibank.core.services.integration;

import com.minibank.SpringContextTest;
import com.minibank.core.communications.loans.GetAllLoansQuery;
import com.minibank.core.communications.loans.GetAllLoansResponse;
import com.minibank.core.communications.loans.domain.AllLoansDetails;
import com.minibank.core.domain.*;
import com.minibank.core.repositories.*;
import com.minibank.core.repositories.tools.DBCleaner;
import com.minibank.core.services.QueryExecutor;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Ann on 02/10/14.
 */
public class GetAllLoansQueryHandlerTest extends SpringContextTest
{
    @Autowired
    private DBCleaner dbCleaner;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RequestIPRepository requestIPRepository;
    @Autowired
    private LoanRequestRepository loanRequestRepository;
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private BankParamsRepository bankParamsRepository;
    @Autowired
    private LoanExtensionRepository loanExtensionRepository;
    @Autowired
    private QueryExecutor queryExecutor;

    private BankParams bankParams;
    private Customer customer;
    private RequestIP requestIP;
    private LoanRequest loanRequest;
    private Loan loan;
    private LoanExtension loanExtension;

    private void createLoan()
    {
        loanRequest = LoanRequestFixture.standardLoanRequest();
        loanRequest.setCustomer(customer);
        loanRequest.setRequestIP(requestIP);
        loanRequestRepository.create(loanRequest);
        loan = LoanFixture.standardLoan();
        loan.setCustomer(customer);
        loan.setLoanRequest(loanRequest);
        loanRepository.create(loan);
    }

    private void createLoanExtension()
    {
        loanExtension = LoanExtensionFixture.standardLoanExtension();
        loanExtension.setLoan(loan);
        loanExtensionRepository.create(loanExtension);
    }

    private GetAllLoansQuery createGetAllLoansQuery()
    {
        return new GetAllLoansQuery(customer.getId());
    }

    private void prepareTestData_1()
    {
        createLoan();
        createLoan();
    }

    private void prepareTestData_2()
    {
        createLoan();
        createLoanExtension();
        createLoanExtension();
        createLoan();
    }
    @Before
    public void setUp()
    {
        dbCleaner.clear();

        bankParams = BankParamsFixture.standardBankParams();
        customer = CustomerFixture.standardCustomer();
        requestIP = RequestIPFixture.standardRequestIP();

        bankParamsRepository.create(bankParams);
        customerRepository.create(customer);
        requestIPRepository.create(requestIP);
    }

    @Test
    @Transactional
    public void testExecute_1()
    {
        //customer has no loans
        GetAllLoansQuery getAllLoansQuery = createGetAllLoansQuery();

        GetAllLoansResponse getAllLoansResponse = queryExecutor.execute(getAllLoansQuery);

        AllLoansDetails allLoansDetails = getAllLoansResponse.getAllLoansDetails();

        assertEquals(customer.getId(), allLoansDetails.getCustomerId());
        assertEquals(customer.getName(), allLoansDetails.getName());
        assertEquals(customer.getSurname(), allLoansDetails.getSurname());
        assertEquals(0, allLoansDetails.getLoans().size());
        assertEquals(false, getAllLoansResponse.isEntityFound());

    }

    @Test
    @Transactional
    public void testExecute_2()
    {
        //customer has 2 loans
        //customer has no loan extensions
        prepareTestData_1();
        GetAllLoansQuery getAllLoansQuery = createGetAllLoansQuery();

        GetAllLoansResponse getAllLoansResponse = queryExecutor.execute(getAllLoansQuery);

        AllLoansDetails allLoansDetails = getAllLoansResponse.getAllLoansDetails();

        assertEquals(2, allLoansDetails.getLoans().size());
        assertEquals(true, getAllLoansResponse.isEntityFound());
    }

    @Test
    @Transactional
    public void testExecute_3()
    {
        //customer has 2 loans
        //customer has 2 extensions of a loan
        prepareTestData_2();
        GetAllLoansQuery getAllLoansQuery = createGetAllLoansQuery();

        GetAllLoansResponse getAllLoansResponse = queryExecutor.execute(getAllLoansQuery);

        AllLoansDetails allLoansDetails = getAllLoansResponse.getAllLoansDetails();

        assertEquals(2, allLoansDetails.getLoans().size());
        assertEquals(2, allLoansDetails.getLoans().get(0).getLoanExtensions().size());
        assertEquals(0, allLoansDetails.getLoans().get(1).getLoanExtensions().size());
        assertEquals(true, getAllLoansResponse.isEntityFound());
    }
}
