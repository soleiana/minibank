package com.minibank.core.communications;


public abstract class GetEntityResponse {

    protected boolean entityFound = true;

    public boolean isEntityFound() {
        return entityFound;
    }
}
