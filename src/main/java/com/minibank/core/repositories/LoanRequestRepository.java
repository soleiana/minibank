package com.minibank.core.repositories;

import com.minibank.core.model.LoanRequest;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class LoanRequestRepository extends SessionProvider {

    public void create(LoanRequest loanRequest) {
        getCurrentSession().saveOrUpdate(loanRequest);
    }

    public List<LoanRequest> getByRequestIp(String requestIp) {
        Session session = getCurrentSession();
        Criteria criteria = session.createCriteria(LoanRequest.class);
        criteria.add(Restrictions.eq("requestIp", requestIp));
        return (List<LoanRequest>)criteria.list();
    }
}
