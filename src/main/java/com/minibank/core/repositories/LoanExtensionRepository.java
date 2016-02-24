package com.minibank.core.repositories;

import com.minibank.core.domain.LoanExtension;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Component;


@Component
public class LoanExtensionRepository extends  SessionProvider {

    public void create(LoanExtension loanExtension) {
        getCurrentSession().saveOrUpdate(loanExtension);
    }

    public LoanExtension getLast() {
        Session session = getCurrentSession();
        Criteria criteria = session.createCriteria(LoanExtension.class);
        criteria.addOrder(Order.desc("id"));
        if(criteria.list().size() != 0) {
            return (LoanExtension) criteria.list().get(0);
        } else {
            return null;
        }
    }
}
