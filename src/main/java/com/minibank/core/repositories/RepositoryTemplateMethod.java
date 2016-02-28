package com.minibank.core.repositories;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Component;


@Component
public class RepositoryTemplateMethod<T> extends SessionProvider {

    public T getLast(Class clazz) {
        Session session = getCurrentSession();
        Criteria criteria = session.createCriteria(clazz);
        criteria.addOrder(Order.desc("id"));
        return (T)criteria.list().stream().findFirst().orElse(null);
    }
}

