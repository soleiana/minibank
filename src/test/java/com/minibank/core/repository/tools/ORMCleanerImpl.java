package com.minibank.core.repository.tools;

import com.minibank.core.repository.DBException;
import com.minibank.core.repository.SessionProvider;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

/**
 * Created by Ann on 12/09/14.
 */
@Component(value = "ORM")
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
        hqlTruncate("BankParams");

    }

    public int hqlTruncate(String myTable){
        String hql = String.format("delete from %s",myTable);
        Query query = getCurrentSession().createQuery(hql);
        return query.executeUpdate();
    }
}
