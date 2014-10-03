package com.minibank.core.domain;

import org.hibernate.annotations.Cascade;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ann on 06/09/14.
 */
@Entity
@Table(name="REQUEST_IP")
public class RequestIP
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "IP", unique = true, nullable = false)
    private String ip;

    public RequestIP()
    {}

    public RequestIP(String ip)
    {
        this.ip = ip;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getIP()
    {
        return ip;
    }

    public void setIP(String ip)
    {
        this.ip = ip;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequestIP requestIP = (RequestIP) o;

        if (id != null ? !id.equals(requestIP.id) : requestIP.id != null) return false;
        if (ip != null ? !ip.equals(requestIP.ip) : requestIP.ip != null) return false;
        return true;
    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        return result;
    }
}
