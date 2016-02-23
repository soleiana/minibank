package com.minibank.core.repositories;

import com.minibank.core.domain.Loan;
import com.minibank.core.domain.LoanExtension;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;


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
        Criteria criteria = session.createCriteria(LoanExtension.class);
        criteria.add(Restrictions.eq("loan",loan));
        return  (List<LoanExtension>)criteria.list();
    }

    @Override
    public LoanExtension getLast()
    {
        Session session = getCurrentSession();
        Criteria criteria = session.createCriteria(LoanExtension.class);
        criteria.addOrder(Order.desc("id"));
        if(criteria.list().size() != 0)
             return  (LoanExtension)criteria.list().get(0);
        else
            return null;
    }
}
