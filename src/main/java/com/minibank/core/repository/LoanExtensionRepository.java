package com.minibank.core.repository;

import com.minibank.core.domain.LoanExtension;

/**
 * Created by Ann on 06/09/14.
 */
public interface LoanExtensionRepository
{
    void create(LoanExtension loanExtension) throws DBException;
}
