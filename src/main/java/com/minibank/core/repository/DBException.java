package com.minibank.core.repository;

/**
 * Created by Ann on 09/09/14.
 */
public class DBException extends  Exception
{
    public DBException(String message) {
        super(message);
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }

    public DBException(Throwable cause) {
        super(cause);
    }
}
