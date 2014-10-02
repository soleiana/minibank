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
        AllLoans allLoans = null;

        Integer customerId = query.getCustomerId();
        try
        {
            allLoans = allLoansCoreFactory.getNewAllLoans(customerId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        AllLoansDetails allLoansDetails = allLoansDetailsFactory.getNewAllLoansDetails(allLoans);

        GetAllLoansResponse getAllLoansResponse;

        if (allLoansDetails.getLoans().size() == 0)
            getAllLoansResponse = new GetAllLoansResponse(allLoansDetails,false);
        else getAllLoansResponse = new GetAllLoansResponse(allLoansDetails,true);

        return getAllLoansResponse;
    }

    @Override
    public Class getQueryType()
    {
        return GetAllLoansQuery.class;
    }
}
