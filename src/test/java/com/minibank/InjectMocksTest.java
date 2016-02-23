package com.minibank;

import org.junit.Before;
import org.mockito.MockitoAnnotations;


public class InjectMocksTest
{
    @Before
    public void initMocks()
    {
         MockitoAnnotations.initMocks(this);
    }
}

