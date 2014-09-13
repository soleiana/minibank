package com.minibank.core.repository;

import com.minibank.core.domain.LoanRequest;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Ann on 10/09/14.
 */
@Component
public class LoanRequestRepositoryImpl extends SessionProvider
    implements LoanRequestRepository
{
    @Override
    public void create(LoanRequest loanRequest)  throws DBException
    {
        getCurrentSession().saveOrUpdate(loanRequest);
    }

    @Override
    public void update(LoanRequest loanRequest) throws DBException
    {
        getCurrentSession().saveOrUpdate(loanRequest);
    }

    @Override
    public LoanRequest getById(Integer id) throws  DBException
    {
        Session session = getCurrentSession();
        return (LoanRequest) session.get(LoanRequest.class, id);
    }

}
