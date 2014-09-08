package com.minibank.core.domain.feedback;

/**
 * Created by Ann on 08/09/14.
 */
public interface Reply
{
    public Integer getCustomerId();
    public String getMessage();
    public void setCustomerId(Integer customerId);
    public void setMessage(String message);

}
