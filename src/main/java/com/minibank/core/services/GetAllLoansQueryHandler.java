package com.minibank.core.services;


import com.minibank.communications.GetAllLoansQuery;
import com.minibank.communications.GetAllLoansResponse;
import com.minibank.communications.domain.AllLoansDetails;
import com.minibank.communications.factories.AllLoansDetailsFactory;
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

        allLoans = allLoansCoreFactory.getAllLoans(customerId);
        AllLoansDetails allLoansDetails = allLoansDetailsFactory.getAllLoansDetails(allLoans);

        if (allLoansDetails.isEmpty()) {
            return new GetAllLoansResponse(allLoansDetails, false);
        }
        else {
            return new GetAllLoansResponse(allLoansDetails, true);
        }
    }

}
