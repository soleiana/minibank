package com.minibank.core.services;


import com.minibank.core.communications.loans.GetAllLoansQuery;
import com.minibank.core.communications.loans.GetAllLoansResponse;
import com.minibank.core.communications.loans.domain.AllLoansDetails;
import com.minibank.core.communications.loans.factories.AllLoansDetailsFactory;
import com.minibank.core.domain.AllLoans;
import com.minibank.core.services.factories.AllLoansCoreFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Ann on 02/10/14.
 */
@Component
public class GetAllLoansQueryHandler
        implements QueryHandler<GetAllLoansQuery, GetAllLoansResponse>
{

    @Autowired
    private AllLoansCoreFactory allLoansCoreFactory;

        @Autowired
        private AllLoansDetailsFactory allLoansDetailsFactory;

    @Override
    public GetAllLoansResponse execute(GetAllLoansQuery query)
    {
        AllLoans allLoans;
        AllLoansDetails allLoansDetails = null;

        Integer customerId = query.getCustomerId();
        try
        {
            allLoans = allLoansCoreFactory.getNewAllLoans(customerId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new GetAllLoansResponse(allLoansDetails,false);
        }

        allLoansDetails = allLoansDetailsFactory.getNewAllLoansDetails(allLoans);

        if (allLoansDetails.getLoans().size() == 0)
            return new GetAllLoansResponse(allLoansDetails,false);
        else
            return new GetAllLoansResponse(allLoansDetails,true);

    }

    @Override
    public Class getQueryType()
    {
        return GetAllLoansQuery.class;
    }
}
