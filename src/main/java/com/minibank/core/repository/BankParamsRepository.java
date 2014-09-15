package com.minibank.core.repository;

import com.minibank.core.domain.BankParams;


/**
 * Created by Ann on 06/09/14.
 */
public interface BankParamsRepository
{
    void create(BankParams bankParams) throws DBException;

    BankParams getById(Integer id) throws DBException;

    BankParams getLast() throws  DBException;

    void update(BankParams bankParams) throws DBException;
}
