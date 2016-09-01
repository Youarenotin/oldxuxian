package com.xuxian.marketpro.presentation.entity;

import java.util.List;

/**
 * Created by youarenotin on 16/9/1.
 */
public class GetHistoryInfoEntity extends BaseEntity{
    private List<OrderEntity> data;
    private StatusEntity status;

    public void setStatus(StatusEntity status) {
        this.status = status;
    }

    public void setData(List<OrderEntity> data) {
        this.data = data;
    }

    public StatusEntity getStatus() {
        return this.status;
    }

    public List<OrderEntity> getData() {
        return this.data;
    }
}
