package com.minibank.testutil.repositories;

import com.minibank.core.repositories.SessionProvider;
import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class DatabaseCleaner extends SessionProvider {

    @Transactional
    public void clear() {
        hqlTruncate("LoanExtension");
        hqlTruncate("Loan");
        hqlTruncate("LoanRequest");
        hqlTruncate("Customer");
        hqlTruncate("BankParameters");
    }

    private void hqlTruncate(String myTable){
        String hql = String.format("delete from %s",myTable);
        Query query = getCurrentSession().createQuery(hql);
        query.executeUpdate();
    }
}
