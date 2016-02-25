package com.minibank.core.repositories;


import com.minibank.core.domain.Loan;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Component;


@Component
public class LoanRepository extends SessionProvider {

    public void create(Loan loan) {
        getCurrentSession().saveOrUpdate(loan);
    }

    public void update(Loan loan) {
        getCurrentSession().saveOrUpdate(loan);
    }

    public Loan getById(Integer id) {
        return (Loan) getCurrentSession().get(Loan.class, id);
    }

    public Loan getLast() {
        Session session = getCurrentSession();
        Criteria criteria = session.createCriteria(Loan.class);
        criteria.addOrder(Order.desc("id"));
        if(criteria.list().size() != 0) {
            return (Loan) criteria.list().get(0);
        } else {
            return null;
        }
    }
}
