package com.minibank.communications;


public abstract class GetEntityResponse {

    protected boolean entityFound = true;

    protected boolean isErrorResponse = false;

    protected GetEntityResponse(final boolean entityFound) {
        this.entityFound = entityFound;
    }

    protected GetEntityResponse(final boolean entityFound, final boolean isErrorResponse) {
        this.entityFound = entityFound;
        this.isErrorResponse = isErrorResponse;
    }
    public boolean isEntityFound() {
        return entityFound;
    }

    public boolean isErrorResponse() {
        return isErrorResponse;
    }
}
