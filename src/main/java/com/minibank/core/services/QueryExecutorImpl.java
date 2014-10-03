package com.minibank.core.services;

import com.minibank.core.communications.DomainQuery;
import com.minibank.core.communications.DomainResponse;
import com.minibank.core.services.common.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ann on 01/10/14.
 */
@Component
public class QueryExecutorImpl implements QueryExecutor
{
    @Autowired
    private List<QueryHandler> services;

    private Map<Class, QueryHandler> serviceMap;

    @PostConstruct
    public void init()
    {
        serviceMap = new HashMap<>();
        if(services != null && !services.isEmpty())
            for(QueryHandler service: services)
            {
                Class queryClass = service.getQueryType();
                serviceMap.put(queryClass, service);
            }
    }

    @Transactional
    public <T extends DomainResponse> T execute(DomainQuery<T> query)
    {
        QueryHandler service = serviceMap.get(query.getClass());
        if (service != null)
        {
            return (T)service.execute(query);
        }
        else
        {
            throw new IllegalArgumentException(Message.UNKNOWN_DOMAIN_QUERY + query.toString());
        }
    }
}
