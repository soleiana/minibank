package com.minibank.core.repositories.tools;

import com.minibank.core.repositories.DBException;
import com.minibank.core.repositories.SessionProvider;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

/**
 * Created by Ann on 12/09/14.
 */
@Component
public class ORMCleanerImpl extends SessionProvider
        implements  DBCleaner
{
    public void clear() throws DBException
    {
        hqlTruncate("LoanExtension");
        hqlTruncate("Loan");
        hqlTruncate("LoanRequest");
        hqlTruncate("Customer");
        hqlTruncate("RequestIP");
    }

    public int hqlTruncate(String myTable){
        String hql = String.format("delete from %s",myTable);
        Query query = getCurrentSession().createQuery(hql);
        return query.executeUpdate();
    }
}
