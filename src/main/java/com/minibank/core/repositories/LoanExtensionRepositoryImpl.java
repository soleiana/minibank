package com.minibank.core.repositories;

import com.minibank.core.domain.Loan;
import com.minibank.core.domain.LoanExtension;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Ann on 10/09/14.
 */
@Component
public class LoanExtensionRepositoryImpl extends  SessionProvider
        implements LoanExtensionRepository
{
    @Override
    public void create(LoanExtension loanExtension)
    {
        getCurrentSession().saveOrUpdate(loanExtension);
    }

    @Override
    public List<LoanExtension> getByLoan(Loan loan)
    {
        Session session = getCurrentSession();
        Query query = session.createQuery("from LoanExtension where loan = :loan");
        query.setParameter("loan", loan);
        return query.list();
    }

    @Override
    public LoanExtension getLast()
    {
        Session session = getCurrentSession();
        Query query = session.createQuery("from LoanExtension order by id DESC");
        query.setMaxResults(1);
        return  (LoanExtension) query.uniqueResult();
    }
}
