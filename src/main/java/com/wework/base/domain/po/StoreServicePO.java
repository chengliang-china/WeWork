package com.wework.base.domain.po;

import java.math.BigDecimal;
import java.util.Date;

public class StoreServicePO {
    private long id;
    private long storeId;
    private String serviceName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
