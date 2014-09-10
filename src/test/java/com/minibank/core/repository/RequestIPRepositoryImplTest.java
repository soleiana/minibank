package com.minibank.core.repository;

import com.minibank.SpringContextTest;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.minibank.core.domain.RequestIP;
import com.minibank.core.domain.RequestIPFixture;

/**
 * Created by Ann on 10/09/14.
 */
public class RequestIPRepositoryImplTest extends SpringContextTest
{
    @Autowired
    RequestIPRepository requestIPRepository;
    RequestIP requestIP;

    @Before
    @Transactional
    public void setUp() throws DBException
    {
        requestIP = RequestIPFixture.standardRequestIP();
    }

    @Test
    @Transactional
    public void testCreate() throws DBException
    {
        requestIPRepository.create(requestIP);
        assertNotNull(requestIP.getId());
    }

    @Test
    @Transactional
    public void testGetByIP() throws DBException
    {
        requestIPRepository.create(requestIP);
        RequestIP req = requestIPRepository.getByIP(requestIP.getIP());
        assertNotNull(req);
        assertTrue(req == requestIP);
    }
}
