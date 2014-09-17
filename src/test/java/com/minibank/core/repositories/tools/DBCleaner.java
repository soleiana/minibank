package com.minibank.core.repositories.tools;

import com.minibank.core.repositories.DBException;

/**
 * Created by Ann on 10/09/14.
 */
public interface DBCleaner
{
    void clear() throws DBException;
}
