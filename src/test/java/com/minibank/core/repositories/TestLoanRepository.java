package com.minibank.core.repositories;

import com.minibank.core.model.Loan;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Component;

@Component
public class TestLoanRepository extends SessionProvider {

    public Loan getLast() {
        Session session = getCurrentSession();
        Criteria criteria = session.createCriteria(Loan.class);
        criteria.addOrder(Order.desc("id"));
        if(!criteria.list().isEmpty()) {
            return (Loan) criteria.list().get(0);
        } else {
            return null;
        }
    }
}
