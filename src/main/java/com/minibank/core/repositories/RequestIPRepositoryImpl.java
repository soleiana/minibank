package com.minibank.core.repositories;

import com.minibank.core.domain.RequestIP;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

/**
 * Created by Ann on 10/09/14.
 */
@Component
public class RequestIPRepositoryImpl extends SessionProvider
    implements RequestIPRepository
{
    @Override
    public void create(RequestIP requestIP)
    {
          getCurrentSession().saveOrUpdate(requestIP);
    }

    @Override
    public RequestIP getByIP(String ip)
    {
        Session session = getCurrentSession();
        Criteria criteria = session.createCriteria(RequestIP.class);
        criteria.add(Restrictions.eq("ip", ip));
        return  (RequestIP)criteria.uniqueResult();
    }
}
