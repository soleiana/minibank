package com.minibank.rest.factories;

import com.minibank.core.communications.loans.domain.AllLoansDetails;
import com.minibank.rest.domain.AllLoans;
import com.minibank.rest.domain.Loan;
import com.minibank.rest.domain.LoanExtension;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ann on 16/09/14.
 */
@Component
public class AllLoansRestFactoryImpl implements AllLoansRestFactory
{
    private Loan convert(com.minibank.core.communications.loans.domain.Loan fromLoan)
    {
        Loan toLoan = new Loan();
        toLoan.setId(fromLoan.getId());
        toLoan.setCurrInterestRate(fromLoan.getCurrInterestRate());
        toLoan.setCurrInterest(fromLoan.getCurrInterest());
        toLoan.setAmount(fromLoan.getAmount());
        toLoan.setStartDate(fromLoan.getStartDate());
        toLoan.setEndDate(fromLoan.getEndDate());
        return toLoan;
    }

    private LoanExtension convert(com.minibank.core.communications.loans.domain.LoanExtension
                                          fromLoanExtension)
    {
        LoanExtension toLoanExtension = new LoanExtension();
        toLoanExtension.setLoanId(fromLoanExtension.getLoanId());
        toLoanExtension.setInterestRate(fromLoanExtension.getInterestRate());
        toLoanExtension.setInterest(fromLoanExtension.getInterest());
        toLoanExtension.setStartDate(fromLoanExtension.getStartDate());
        toLoanExtension.setEndDate(fromLoanExtension.getEndDate());
        toLoanExtension.setSubmissionDate(fromLoanExtension.getSubmissionDate());
        return  toLoanExtension;
    }

    @Override
    public  AllLoans getNewAllLoans(AllLoansDetails allLoansDetails)
    {
        AllLoans allLoans = new AllLoans();
        allLoans.setCustomerId(allLoansDetails.getCustomerId());
        allLoans.setName(allLoansDetails.getName());
        allLoans.setSurname(allLoansDetails.getSurname());

        List<Loan> toLoans = new ArrayList<>();
        for(com.minibank.core.communications.loans.domain.Loan fromLoan: allLoansDetails.getLoans())
        {
            Loan toLoan = convert(fromLoan);
            List<LoanExtension> toLoanExtensions = new ArrayList<>();

            for(com.minibank.core.communications.loans.domain.LoanExtension fromLoanExtension:
                    fromLoan.getLoanExtensions())
            {

                LoanExtension toLoanExtension = convert(fromLoanExtension);
                toLoanExtensions.add(toLoanExtension);
            }
            toLoan.setLoanExtensions(toLoanExtensions);

            toLoans.add(toLoan);
        }
        allLoans.setLoans(toLoans);

        return  allLoans;
    }
}
