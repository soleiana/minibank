package com.minibank.core.communications;


public abstract class GetEntityResponse {

    protected boolean entityFound = true;

    protected GetEntityResponse(final boolean entityFound) {
        this.entityFound = entityFound;
    }

    public boolean isEntityFound() {
        return entityFound;
    }
}
