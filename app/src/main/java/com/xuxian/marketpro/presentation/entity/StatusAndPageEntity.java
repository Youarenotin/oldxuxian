package com.xuxian.marketpro.presentation.entity;

/**
 * Created by youarenotin on 16/8/23.
 */
public class StatusAndPageEntity extends BaseEntity{
    private PageEntity page;
    private StatusEntity status;

    public PageEntity getPage() {
        return page;
    }

    public void setPage(PageEntity page) {
        this.page = page;
    }

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }

    public static class StatusEntity{
        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        private int code;
        private String message;

    }
}
