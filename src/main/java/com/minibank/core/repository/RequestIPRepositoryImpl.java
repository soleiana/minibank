package com.minibank.core.repository;

import com.minibank.core.domain.LoanRequest;
import com.minibank.core.domain.RequestIP;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Ann on 10/09/14.
 */
@Component
public class RequestIPRepositoryImpl extends SessionProvider
    implements RequestIPRepository
{
    @Override
    public void create(RequestIP requestIP)  throws DBException
    {
          getCurrentSession().saveOrUpdate(requestIP);
    }

    @Override
    public RequestIP getByIP(String ip) throws DBException
    {
        Session session = getCurrentSession();
        Query query = session.createQuery("from RequestIP where ip = :ip ");
        query.setParameter("ip", ip);
        return (RequestIP) query.uniqueResult();
    }
}
