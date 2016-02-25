package com.minibank.core.repositories;

import com.minibank.core.model.BankParameters;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Component;


@Component
public class BankParametersRepository extends SessionProvider {

    public void create(BankParameters bankParameters) {
        getCurrentSession().saveOrUpdate(bankParameters);
    }

    public BankParameters getCurrentBankParameters() {
        Session session = getCurrentSession();
        Criteria criteria = session.createCriteria(BankParameters.class);
        criteria.addOrder(Order.desc("id"));
        if(!criteria.list().isEmpty()) {
            return (BankParameters) criteria.list().get(0);
        } else {
            return null;
        }
    }
}
