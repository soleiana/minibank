package com.minibank.core.repository;

import com.minibank.SpringContextTest;
import com.minibank.core.domain.LoanExtension;
import com.minibank.core.domain.LoanExtensionFixture;
import com.minibank.core.repository.tools.DBCleaner;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by Ann on 10/09/14.
 */
public class LoanExtensionRepositoryImplTest  extends SpringContextTest
{
    @Autowired
    private DBCleaner dbCleaner;
    @Autowired
    private LoanExtensionRepository loanExtensionRepository;
    private LoanExtension loanExtension;

    @Before
    public void setUp() throws DBException
    {
        dbCleaner.clear();
        loanExtension = LoanExtensionFixture.standardLoanExtension();
    }

    @Test
    public void testCreate() throws DBException
    {
        //todo:
    }
}
