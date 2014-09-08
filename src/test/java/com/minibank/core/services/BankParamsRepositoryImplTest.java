package com.minibank.core.services;

import com.minibank.SpringContextTest;
import com.minibank.core.repository.BankParamsRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by Ann on 07/09/14.
 */

public class BankParamsRepositoryImplTest extends SpringContextTest
{
    @Autowired
    private BankParamsRepository repository;

    @Test
    @Transactional
    public void testSomeMethod() throws Exception
    {
        assertNotNull(repository);

    }
}
