package com.minibank.core.repository;

import com.minibank.core.domain.LoanRequest;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

/**
 * Created by Ann on 10/09/14.
 */
@Component
public class LoanRequestRepositoryImpl extends SessionProvider
    implements LoanRequestRepository
{
    public void create(LoanRequest loanRequest)  throws DBException
    {
        getCurrentSession().saveOrUpdate(loanRequest);
    }

    public void update(LoanRequest loanRequest) throws DBException
    {
        getCurrentSession().saveOrUpdate(loanRequest);
    }

    public LoanRequest getById(Integer id) throws  DBException
    {
        Session session = getCurrentSession();
        return (LoanRequest) session.get(LoanRequest.class, id);
    }
}
