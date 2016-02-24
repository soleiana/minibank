package com.minibank.core.repositories;

import com.minibank.core.domain.BankParams;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Component;



@Component
public class BankParamsRepository extends SessionProvider {


    public void create(BankParams bankParams) {
        getCurrentSession().saveOrUpdate(bankParams);
    }

    public BankParams getById(Integer id) {
        Session session = getCurrentSession();
        return (BankParams) session.get(BankParams.class, id);
    }

    public void update(BankParams bankParams) {
        Session session = getCurrentSession();
        session.saveOrUpdate(bankParams);
    }

    public BankParams getLast() {
        Session session = getCurrentSession();
        Criteria criteria = session.createCriteria(BankParams.class);
        criteria.addOrder(Order.desc("id"));
        if(criteria.list().size() != 0) {
            return (BankParams) criteria.list().get(0);
        } else {
            return null;
        }
    }
}
