package com.minibank.core.services;

import com.minibank.core.communications.DomainQuery;
import com.minibank.core.communications.DomainResponse;

/**
 * Created by Ann on 01/10/14.
 */
public interface QueryExecutor
{
    <T extends DomainResponse> T execute(DomainQuery<T> query);
}
