package com.minibank.core.events;

/**
 * Created by Ann on 06/09/14.
 */
public class ReadEvent
{
    protected boolean entityFound = true;

    public boolean isEntityFound()
    {
        return entityFound;
    }

    public void setEntityFound(boolean entityFound)
    {
        this.entityFound = entityFound;
    }
}
