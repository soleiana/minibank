package com.minibank.core.services;

import com.minibank.core.communications.DomainQuery;
import com.minibank.core.communications.DomainResponse;


public interface QueryExecutor
{
    <T extends DomainResponse> T execute(DomainQuery<T> query);
}
