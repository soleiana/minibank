package com.minibank.core.services;


import com.minibank.communications.GetAllLoansQuery;
import com.minibank.communications.GetAllLoansResponse;
import com.minibank.communications.factories.AllLoansDetailsFactory;
import com.minibank.communications.model.AllLoansDetails;
import com.minibank.core.factories.AllLoansCoreFactory;
import com.minibank.core.model.AllLoans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class GetAllLoansQueryHandler {

    @Autowired
    private AllLoansCoreFactory allLoansCoreFactory;

    @Autowired
    private AllLoansDetailsFactory allLoansDetailsFactory;


    @Transactional
    public GetAllLoansResponse execute(GetAllLoansQuery query) {
        try {
            AllLoans allLoans;
            int customerId = query.getCustomerId();
            allLoans = allLoansCoreFactory.getAllLoans(customerId);
            AllLoansDetails allLoansDetails = allLoansDetailsFactory.getAllLoansDetails(allLoans);
            if (allLoansDetails.isEmpty()) {
                return new GetAllLoansResponse(allLoansDetails, false);
            } else {
                return new GetAllLoansResponse(allLoansDetails, true);
            }
        } catch (Exception exception) {
            return new GetAllLoansResponse(true);
        }
    }

}
