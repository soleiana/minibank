package com.minibank.core.services.unit;

import com.minibank.communications.CreateEntityResponse;
import com.minibank.communications.GetAllLoansResponse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class TestUtility {

    static void assertResponse(CreateEntityResponse expectedResponse, CreateEntityResponse actualResponse) {
        assertNotNull(actualResponse);
        assertEquals(expectedResponse.getMessage(), actualResponse.getMessage());
        assertEquals(expectedResponse.isCreated(), actualResponse.isCreated());
    }

    static void assertResponse(GetAllLoansResponse expectedResponse, GetAllLoansResponse actualResponse) {
        assertNotNull(actualResponse);
        assertEquals(expectedResponse.isEntityFound(), actualResponse.isEntityFound());
        assertEquals(expectedResponse.getAllLoansDetails(), actualResponse.getAllLoansDetails());
    }
}
