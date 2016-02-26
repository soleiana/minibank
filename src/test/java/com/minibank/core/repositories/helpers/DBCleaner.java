package com.minibank.core.repositories.helpers;

import com.minibank.core.repositories.SessionProvider;
import org.hibernate.Query;
import org.springframework.stereotype.Component;


@Component
public class DBCleaner extends SessionProvider {

    public void clear() {
        hqlTruncate("LoanExtension");
        hqlTruncate("Loan");
        hqlTruncate("LoanRequest");
        hqlTruncate("Customer");
    }

    private void hqlTruncate(String myTable){
        String hql = String.format("delete from %s",myTable);
        Query query = getCurrentSession().createQuery(hql);
        query.executeUpdate();
    }
}
