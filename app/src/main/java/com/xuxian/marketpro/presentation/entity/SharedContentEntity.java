package com.xuxian.marketpro.presentation.entity;

/**
 * Created by youarenotin on 16/8/22.
 */
public class SharedContentEntity extends BaseEntity{
    private DataEntity data;
    private StatusEntity status;

    public static class DataEntity {
        private String back_img;
        private String endtime;
        private String get_img;
        private String have_img;
        private String id;
        private String intro;
        private String is_callback;
        private String push_img;
        private String pushlink;
        private String seccess_img;
        private String share_txt;
        private String share_type;
        private String starttime;
        private String title;

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public void setBack_img(String back_img) {
            this.back_img = back_img;
        }

        public void setShare_type(String share_type) {
            this.share_type = share_type;
        }

        public void setHave_img(String have_img) {
            this.have_img = have_img;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setPushlink(String pushlink) {
            this.pushlink = pushlink;
        }

        public void setGet_img(String get_img) {
            this.get_img = get_img;
        }

        public void setShare_txt(String share_txt) {
            this.share_txt = share_txt;
        }

        public void setSeccess_img(String seccess_img) {
            this.seccess_img = seccess_img;
        }

        public void setPush_img(String push_img) {
            this.push_img = push_img;
        }

        public void setIs_callback(String is_callback) {
            this.is_callback = is_callback;
        }

        public String getEndtime() {
            return this.endtime;
        }

        public String getStarttime() {
            return this.starttime;
        }

        public String getBack_img() {
            return this.back_img;
        }

        public String getShare_type() {
            return this.share_type;
        }

        public String getHave_img() {
            return this.have_img;
        }

        public String getIntro() {
            return this.intro;
        }

        public String getId() {
            return this.id;
        }

        public String getTitle() {
            return this.title;
        }

        public String getPushlink() {
            return this.pushlink;
        }

        public String getGet_img() {
            return this.get_img;
        }

        public String getShare_txt() {
            return this.share_txt;
        }

        public String getSeccess_img() {
            return this.seccess_img;
        }

        public String getPush_img() {
            return this.push_img;
        }

        public String getIs_callback() {
            return this.is_callback;
        }
    }

    public static class StatusEntity {
        private int code;
        private String message;

        public void setMessage(String message) {
            this.message = message;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return this.message;
        }

        public int getCode() {
            return this.code;
        }
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public StatusEntity getStatus() {
        return this.status;
    }

    public DataEntity getData() {
        return this.data;
    }
}
