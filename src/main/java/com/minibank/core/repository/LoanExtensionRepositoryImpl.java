package com.minibank.core.repository;

import com.minibank.core.domain.LoanExtension;
import org.springframework.stereotype.Component;

/**
 * Created by Ann on 10/09/14.
 */
@Component
public class LoanExtensionRepositoryImpl extends  SessionProvider
        implements LoanExtensionRepository
{
    @Override
    public void create(LoanExtension loanExtension) throws DBException
    {
        getCurrentSession().saveOrUpdate(loanExtension);
    }
}
