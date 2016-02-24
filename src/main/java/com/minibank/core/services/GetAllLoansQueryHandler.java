package com.minibank.core.services;


import com.minibank.core.communications.GetAllLoansQuery;
import com.minibank.core.communications.GetAllLoansResponse;
import com.minibank.core.communications.domain.AllLoansDetails;
import com.minibank.core.communications.factories.AllLoansDetailsFactory;
import com.minibank.core.domain.AllLoans;
import com.minibank.core.services.factories.AllLoansCoreFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class GetAllLoansQueryHandler {

    @Autowired
    private AllLoansCoreFactory allLoansCoreFactory;

    @Autowired
    private AllLoansDetailsFactory allLoansDetailsFactory;

    public GetAllLoansResponse execute(GetAllLoansQuery query) {
        AllLoans allLoans;
        Integer customerId = query.getCustomerId();

        allLoans = allLoansCoreFactory.getNewAllLoans(customerId);
        AllLoansDetails allLoansDetails = allLoansDetailsFactory.getNewAllLoansDetails(allLoans);

        if ((allLoansDetails != null)&&(allLoansDetails.getLoans().size() == 0)) {
            return new GetAllLoansResponse(allLoansDetails, false);
        }
        else {
            return new GetAllLoansResponse(allLoansDetails, true);
        }
    }

}
