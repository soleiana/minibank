package com.minibank.core.repository;

import com.minibank.core.domain.LoanExtension;

/**
 * Created by Ann on 10/09/14.
 */
public class LoanExtensionRepositoryImpl extends  SessionProvider
        implements LoanExtensionRepository
{
    @Override
    public void create(LoanExtension loanExtension) throws DBException
    {
        getCurrentSession().saveOrUpdate(loanExtension);
    }
}
