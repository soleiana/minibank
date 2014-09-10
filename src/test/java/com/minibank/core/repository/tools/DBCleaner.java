package com.minibank.core.repository.tools;

import com.minibank.core.repository.DBException;

/**
 * Created by Ann on 10/09/14.
 */
public interface DBCleaner
{
    void clear() throws DBException;
}
