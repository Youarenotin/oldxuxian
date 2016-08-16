package com.xuxian.marketpro.presentation.entity;

import java.util.List;

/**
 * Created by youarenotin on 16/8/15.
 */
public class ClassifyListEntity {
    private List<ClassifyEntity> data;
    private StatusEntity status;

    public List<ClassifyEntity> getData() {
        return data;
    }

    public void setData(List<ClassifyEntity> data) {
        this.data = data;
    }

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }


    public    class StatusEntity {
        private int  code;
        private String message;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
