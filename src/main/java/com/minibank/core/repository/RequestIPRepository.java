package com.minibank.core.repository;

import com.minibank.core.domain.RequestIP;

/**
 * Created by Ann on 06/09/14.
 */
public interface RequestIPRepository
{
    void create(RequestIP requestIP)  throws DBException;;

    RequestIP getByIP(String ip)  throws DBException;
}
