package com.xuxian.marketpro.presentation.entity;


import java.util.List;

/**
 * Created by youarenotin on 16/8/9.
 */
public class GoodsFragmentHeaderEntity {
    private DataEntity data;
    private StatusEntiy status;

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public StatusEntiy getStatus() {
        return status;
    }

    public void setStatus(StatusEntiy status) {
        this.status = status;
    }

    public  static class DataEntity {
        private List<AppEntity> app;
        private List<BannerEntity> banner;
        private List<List<BlocksEntity>> blocks;

        public List<AppEntity> getApp() {
            return app;
        }

        public void setApp(List<AppEntity> app) {
            this.app = app;
        }

        public List<BannerEntity> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerEntity> banner) {
            this.banner = banner;
        }

        public List<List<BlocksEntity>> getBlocks() {
            return blocks;
        }

        public void setBlocks(List<List<BlocksEntity>> blocks) {
            this.blocks = blocks;
        }

        public static class AppEntity {
            private String bannertype;
            private String endtime;
            private  String img;
            private String message;
            private String starttime;
            private String title;
            private  String webid;

            public String getBannertype() {
                return bannertype;
            }

            public void setBannertype(String bannertype) {
                this.bannertype = bannertype;
            }

            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getStarttime() {
                return starttime;
            }

            public void setStarttime(String starttime) {
                this.starttime = starttime;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getWebid() {
                return webid;
            }

            public void setWebid(String webid) {
                this.webid = webid;
            }
        }

        public static  class BannerEntity {
            private String bannertype;
            private String endtime;
            private String img;
            private String message;
            private String title;
            private String starttime;
            private  String webid;

            public String getBannertype() {
                return bannertype;
            }

            public void setBannertype(String bannertype) {
                this.bannertype = bannertype;
            }

            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getStarttime() {
                return starttime;
            }

            public void setStarttime(String starttime) {
                this.starttime = starttime;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getWebid() {
                return webid;
            }

            public void setWebid(String webid) {
                this.webid = webid;
            }
        }

        public static  class BlocksEntity {
            private String a_position;
            private  String allheight;
            private String city_id;
            private  String endtime;
            private String height;
            private String id;
            private String sa_id;
            private List<SaleAsEntity> sale_as;
            private String show_type;
            private  String sort;
            private String starttime;
            private  String width;

            public String getA_position() {
                return a_position;
            }

            public void setA_position(String a_position) {
                this.a_position = a_position;
            }

            public String getAllheight() {
                return allheight;
            }

            public void setAllheight(String allheight) {
                this.allheight = allheight;
            }

            public String getCity_id() {
                return city_id;
            }

            public void setCity_id(String city_id) {
                this.city_id = city_id;
            }

            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }

            public String getHeight() {
                return height;
            }

            public void setHeight(String height) {
                this.height = height;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getSa_id() {
                return sa_id;
            }

            public void setSa_id(String sa_id) {
                this.sa_id = sa_id;
            }

            public List<SaleAsEntity> getSale_as() {
                return sale_as;
            }

            public void setSale_as(List<SaleAsEntity> sale_as) {
                this.sale_as = sale_as;
            }

            public String getShow_type() {
                return show_type;
            }

            public void setShow_type(String show_type) {
                this.show_type = show_type;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getStarttime() {
                return starttime;
            }

            public void setStarttime(String starttime) {
                this.starttime = starttime;
            }

            public String getWidth() {
                return width;
            }

            public void setWidth(String width) {
                this.width = width;
            }

            public static class SaleAsEntity {
                private String  bannerimg;
                private String bannertype;
                private String city_id;
                private String endtime;
                private String goods_ids;
                private String head_center;
                private String head_left;
                private String head_right;
                private String html_content;
                private  String id;
                private String is_time;
                private String message;
                private String mode_id;
                private String sale_name;
                private  String show_type;
                private String starttime;

                public String getBannerimg() {
                    return bannerimg;
                }

                public void setBannerimg(String bannerimg) {
                    this.bannerimg = bannerimg;
                }

                public String getBannertype() {
                    return bannertype;
                }

                public void setBannertype(String bannertype) {
                    this.bannertype = bannertype;
                }

                public String getCity_id() {
                    return city_id;
                }

                public void setCity_id(String city_id) {
                    this.city_id = city_id;
                }

                public String getEndtime() {
                    return endtime;
                }

                public void setEndtime(String endtime) {
                    this.endtime = endtime;
                }

                public String getGoods_ids() {
                    return goods_ids;
                }

                public void setGoods_ids(String goods_ids) {
                    this.goods_ids = goods_ids;
                }

                public String getHead_center() {
                    return head_center;
                }

                public void setHead_center(String head_center) {
                    this.head_center = head_center;
                }

                public String getHead_left() {
                    return head_left;
                }

                public void setHead_left(String head_left) {
                    this.head_left = head_left;
                }

                public String getHead_right() {
                    return head_right;
                }

                public void setHead_right(String head_right) {
                    this.head_right = head_right;
                }

                public String getHtml_content() {
                    return html_content;
                }

                public void setHtml_content(String html_content) {
                    this.html_content = html_content;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getIs_time() {
                    return is_time;
                }

                public void setIs_time(String is_time) {
                    this.is_time = is_time;
                }

                public String getMessage() {
                    return message;
                }

                public void setMessage(String message) {
                    this.message = message;
                }

                public String getMode_id() {
                    return mode_id;
                }

                public void setMode_id(String mode_id) {
                    this.mode_id = mode_id;
                }

                public String getSale_name() {
                    return sale_name;
                }

                public void setSale_name(String sale_name) {
                    this.sale_name = sale_name;
                }

                public String getShow_type() {
                    return show_type;
                }

                public void setShow_type(String show_type) {
                    this.show_type = show_type;
                }

                public String getStarttime() {
                    return starttime;
                }

                public void setStarttime(String starttime) {
                    this.starttime = starttime;
                }


            }
        }
    }

    public    class StatusEntiy {
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
