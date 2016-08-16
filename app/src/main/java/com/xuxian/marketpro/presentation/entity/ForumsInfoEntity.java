package com.xuxian.marketpro.presentation.entity;

import java.util.List;

/**
 * Created by youarenotin on 16/8/16.
 */
public class ForumsInfoEntity {

    /**
     * sub : 0
     * msg : success
     * data : {"forums":[{"article":"1065","created_userid":"0","vieworder":"1","lastpost_username":"头号小许鲜","subposts":"0","allow_reply":"","fid":"9","lastpost_info":"Re:说好的会员专属产品呢？","style":"","hassub":"0","allow_read":"","threads":"101","todayposts":"0","lastpost_time":"1460947206","created_time":"0","isshow":"1","fup":"1","lastpost_tid":"2410","todaythreads":"0","across":"0","created_ip":"0","fupname":"许鲜新闻 News","type":"forum","app_logo":"forum/app_logo/9.jpg","password":"","icon":"forum/icon/9.png","created_username":"","manager":",xuxian,","allow_download":"","subthreads":"0","newtime":"0","isshowsub":"0","issub":"0","allow_post":"","parentid":"1","allow_upload":"","name":"许鲜新闻 News ","posts":"964","uppermanager":",xuxian,","descrip":"许鲜官方 公告信息","allow_visit":"","logo":""},{"article":"2971","created_userid":"0","vieworder":"1","lastpost_username":"头号小许鲜","subposts":"0","allow_reply":"","fid":"41","lastpost_info":"Re:武汉大学信息学部水果团！","style":"","hassub":"1","allow_read":"","threads":"367","todayposts":"0","lastpost_time":"1460947926","created_time":"0","isshow":"1","fup":"8","lastpost_tid":"2395","todaythreads":"0","across":"0","created_ip":"0","fupname":"相遇Meet","type":"forum","app_logo":"forum/app_logo/41.jpg","password":"","icon":"forum/icon/41.jpg","created_username":"","manager":",xuxian,","allow_download":"","subthreads":"255","newtime":"0","isshowsub":"0","issub":"0","allow_post":"","parentid":"8","allow_upload":"","name":"相遇Meet","posts":"562","uppermanager":",xuxian,","descrip":"鹊桥相会 男神女神","allow_visit":"","logo":""},{"article":"1189","created_userid":"0","vieworder":"1","lastpost_username":"xu_725250","subposts":"0","allow_reply":"","fid":"33","lastpost_info":"Re:活动 | 分享 邀请送壕礼！受邀用户首单立减5...","style":"","hassub":"1","allow_read":"","threads":"479","todayposts":"0","lastpost_time":"1460431744","created_time":"0","isshow":"1","fup":"6","lastpost_tid":"752","todaythreads":"0","across":"0","created_ip":"0","fupname":"八卦江湖 Life","type":"forum","app_logo":"forum/app_logo/33.jpg","password":"","icon":"","created_username":"","manager":",xuxian,","allow_download":"","subthreads":"32","newtime":"0","isshowsub":"0","issub":"0","allow_post":"","parentid":"6","allow_upload":"","name":"八卦江湖Life","posts":"568","uppermanager":",xuxian,","descrip":"八卦江湖 娱乐生活","allow_visit":"","logo":""},{"article":"928","created_userid":"0","vieworder":"1","lastpost_username":"头号小许鲜","subposts":"0","allow_reply":"9,10,11,12,13,14,15,3,4,5,17,16,1,2,6,7","fid":"57","lastpost_info":"Re:坐标上海求进鲜拼群！！！","style":"","hassub":"0","allow_read":"9,10,11,12,13,14,15,3,4,5,17,16,1,2,6,7","threads":"332","todayposts":"0","lastpost_time":"1460630847","created_time":"0","isshow":"1","fup":"51","lastpost_tid":"2376","todaythreads":"0","across":"0","created_ip":"0","fupname":"许鲜皇家学院","type":"forum","app_logo":"forum/app_logo/57.jpg","password":"","icon":"","created_username":"","manager":",xuxian,","allow_download":"","subthreads":"0","newtime":"0","isshowsub":"0","issub":"0","allow_post":"9,10,11,12,13,14,15,3,4,5,17,16,1,2,6,7","parentid":"51","allow_upload":"9,10,11,12,13,14,15,3,4,5,17,16,1,2,6,7","name":"皇家学院Fans","posts":"596","uppermanager":",xuxian,","descrip":"粉丝专享 鲜果乐园","allow_visit":"9,10,11,12,13,14,15,3,4,5,17,16,1,2,6,7","logo":""}]}
     * ret : 0
     */

    private String sub;
    private String msg;
    private DataEntity data;
    private int ret;

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public static class DataEntity {
        /**
         * article : 1065
         * created_userid : 0
         * vieworder : 1
         * lastpost_username : 头号小许鲜
         * subposts : 0
         * allow_reply :
         * fid : 9
         * lastpost_info : Re:说好的会员专属产品呢？
         * style :
         * hassub : 0
         * allow_read :
         * threads : 101
         * todayposts : 0
         * lastpost_time : 1460947206
         * created_time : 0
         * isshow : 1
         * fup : 1
         * lastpost_tid : 2410
         * todaythreads : 0
         * across : 0
         * created_ip : 0
         * fupname : 许鲜新闻 News
         * type : forum
         * app_logo : forum/app_logo/9.jpg
         * password :
         * icon : forum/icon/9.png
         * created_username :
         * manager : ,xuxian,
         * allow_download :
         * subthreads : 0
         * newtime : 0
         * isshowsub : 0
         * issub : 0
         * allow_post :
         * parentid : 1
         * allow_upload :
         * name : 许鲜新闻 News
         * posts : 964
         * uppermanager : ,xuxian,
         * descrip : 许鲜官方 公告信息
         * allow_visit :
         * logo :
         */

        private List<ForumsEntity> forums;

        public List<ForumsEntity> getForums() {
            return forums;
        }

        public void setForums(List<ForumsEntity> forums) {
            this.forums = forums;
        }

        public static class ForumsEntity {
            private String article;
            private String created_userid;
            private String vieworder;
            private String lastpost_username;
            private String subposts;
            private String allow_reply;
            private String fid;
            private String lastpost_info;
            private String style;
            private String hassub;
            private String allow_read;
            private String threads;
            private String todayposts;
            private String lastpost_time;
            private String created_time;
            private String isshow;
            private String fup;
            private String lastpost_tid;
            private String todaythreads;
            private String across;
            private String created_ip;
            private String fupname;
            private String type;
            private String app_logo;
            private String password;
            private String icon;
            private String created_username;
            private String manager;
            private String allow_download;
            private String subthreads;
            private String newtime;
            private String isshowsub;
            private String issub;
            private String allow_post;
            private String parentid;
            private String allow_upload;
            private String name;
            private String posts;
            private String uppermanager;
            private String descrip;
            private String allow_visit;
            private String logo;

            public String getArticle() {
                return article;
            }

            public void setArticle(String article) {
                this.article = article;
            }

            public String getCreated_userid() {
                return created_userid;
            }

            public void setCreated_userid(String created_userid) {
                this.created_userid = created_userid;
            }

            public String getVieworder() {
                return vieworder;
            }

            public void setVieworder(String vieworder) {
                this.vieworder = vieworder;
            }

            public String getLastpost_username() {
                return lastpost_username;
            }

            public void setLastpost_username(String lastpost_username) {
                this.lastpost_username = lastpost_username;
            }

            public String getSubposts() {
                return subposts;
            }

            public void setSubposts(String subposts) {
                this.subposts = subposts;
            }

            public String getAllow_reply() {
                return allow_reply;
            }

            public void setAllow_reply(String allow_reply) {
                this.allow_reply = allow_reply;
            }

            public String getFid() {
                return fid;
            }

            public void setFid(String fid) {
                this.fid = fid;
            }

            public String getLastpost_info() {
                return lastpost_info;
            }

            public void setLastpost_info(String lastpost_info) {
                this.lastpost_info = lastpost_info;
            }

            public String getStyle() {
                return style;
            }

            public void setStyle(String style) {
                this.style = style;
            }

            public String getHassub() {
                return hassub;
            }

            public void setHassub(String hassub) {
                this.hassub = hassub;
            }

            public String getAllow_read() {
                return allow_read;
            }

            public void setAllow_read(String allow_read) {
                this.allow_read = allow_read;
            }

            public String getThreads() {
                return threads;
            }

            public void setThreads(String threads) {
                this.threads = threads;
            }

            public String getTodayposts() {
                return todayposts;
            }

            public void setTodayposts(String todayposts) {
                this.todayposts = todayposts;
            }

            public String getLastpost_time() {
                return lastpost_time;
            }

            public void setLastpost_time(String lastpost_time) {
                this.lastpost_time = lastpost_time;
            }

            public String getCreated_time() {
                return created_time;
            }

            public void setCreated_time(String created_time) {
                this.created_time = created_time;
            }

            public String getIsshow() {
                return isshow;
            }

            public void setIsshow(String isshow) {
                this.isshow = isshow;
            }

            public String getFup() {
                return fup;
            }

            public void setFup(String fup) {
                this.fup = fup;
            }

            public String getLastpost_tid() {
                return lastpost_tid;
            }

            public void setLastpost_tid(String lastpost_tid) {
                this.lastpost_tid = lastpost_tid;
            }

            public String getTodaythreads() {
                return todaythreads;
            }

            public void setTodaythreads(String todaythreads) {
                this.todaythreads = todaythreads;
            }

            public String getAcross() {
                return across;
            }

            public void setAcross(String across) {
                this.across = across;
            }

            public String getCreated_ip() {
                return created_ip;
            }

            public void setCreated_ip(String created_ip) {
                this.created_ip = created_ip;
            }

            public String getFupname() {
                return fupname;
            }

            public void setFupname(String fupname) {
                this.fupname = fupname;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getApp_logo() {
                return app_logo;
            }

            public void setApp_logo(String app_logo) {
                this.app_logo = app_logo;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getCreated_username() {
                return created_username;
            }

            public void setCreated_username(String created_username) {
                this.created_username = created_username;
            }

            public String getManager() {
                return manager;
            }

            public void setManager(String manager) {
                this.manager = manager;
            }

            public String getAllow_download() {
                return allow_download;
            }

            public void setAllow_download(String allow_download) {
                this.allow_download = allow_download;
            }

            public String getSubthreads() {
                return subthreads;
            }

            public void setSubthreads(String subthreads) {
                this.subthreads = subthreads;
            }

            public String getNewtime() {
                return newtime;
            }

            public void setNewtime(String newtime) {
                this.newtime = newtime;
            }

            public String getIsshowsub() {
                return isshowsub;
            }

            public void setIsshowsub(String isshowsub) {
                this.isshowsub = isshowsub;
            }

            public String getIssub() {
                return issub;
            }

            public void setIssub(String issub) {
                this.issub = issub;
            }

            public String getAllow_post() {
                return allow_post;
            }

            public void setAllow_post(String allow_post) {
                this.allow_post = allow_post;
            }

            public String getParentid() {
                return parentid;
            }

            public void setParentid(String parentid) {
                this.parentid = parentid;
            }

            public String getAllow_upload() {
                return allow_upload;
            }

            public void setAllow_upload(String allow_upload) {
                this.allow_upload = allow_upload;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPosts() {
                return posts;
            }

            public void setPosts(String posts) {
                this.posts = posts;
            }

            public String getUppermanager() {
                return uppermanager;
            }

            public void setUppermanager(String uppermanager) {
                this.uppermanager = uppermanager;
            }

            public String getDescrip() {
                return descrip;
            }

            public void setDescrip(String descrip) {
                this.descrip = descrip;
            }

            public String getAllow_visit() {
                return allow_visit;
            }

            public void setAllow_visit(String allow_visit) {
                this.allow_visit = allow_visit;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }
        }
    }
}
