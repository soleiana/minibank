package com.minibank;

import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by Ann on 08/09/14.
 */
public class MavenTest
{
    @Test
    public void someMethod()
    {
        int a = 5;
        int b = 5;
        int c = 6;
        assertEquals(a,b);
        assert a!=c;
    }
}
