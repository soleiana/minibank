package com.minibank.core.repositories;

import com.minibank.core.model.LoanExtension;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Component;


@Component
public class TestLoanExtensionRepository extends SessionProvider {

    public LoanExtension getLast() {
        Session session = getCurrentSession();
        Criteria criteria = session.createCriteria(LoanExtension.class);
        criteria.addOrder(Order.desc("id"));
        if(!criteria.list().isEmpty()) {
            return (LoanExtension) criteria.list().get(0);
        } else {
            return null;
        }
    }
}
