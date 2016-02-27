package com.minibank.core.repositories.helpers;


import com.minibank.core.repositories.SessionProvider;
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
        if(!criteria.list().isEmpty()) {
            return (T)criteria.list().get(0);
        } else {
            return null;
        }
    }
}

