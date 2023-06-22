package com.example.openinapp.Model;

public class link {
int url_id;
String  web_link;
String smart_link;
String title;
int total_clicks;
String original_image;
String thumbnail=null;
String times_go;
String created_at;
String domain_id;
String url_prefix=null;
String url_suffix;
String app;

    public link(int url_id, String web_link, String smart_link, String title, int total_clicks, String original_image, String thumbnail, String times_go, String created_at, String domain_id, String url_prefix, String url_suffix, String app) {
        this.url_id = url_id;
        this.web_link = web_link;
        this.smart_link = smart_link;
        this.title = title;
        this.total_clicks = total_clicks;
        this.original_image = original_image;
        this.thumbnail = thumbnail;
        this.times_go = times_go;
        this.created_at = created_at;
        this.domain_id = domain_id;
        this.url_prefix = url_prefix;
        this.url_suffix = url_suffix;
        this.app = app;
    }

    public int getUrl_id() {
        return url_id;
    }

    public void setUrl_id(int url_id) {
        this.url_id = url_id;
    }

    public String getWeb_link() {
        return web_link;
    }

    public void setWeb_link(String web_link) {
        this.web_link = web_link;
    }

    public String getSmart_link() {
        return smart_link;
    }

    public void setSmart_link(String smart_link) {
        this.smart_link = smart_link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotal_clicks() {
        return total_clicks;
    }

    public void setTotal_clicks(int total_clicks) {
        this.total_clicks = total_clicks;
    }

    public String getOriginal_image() {
        return original_image;
    }

    public void setOriginal_image(String original_image) {
        this.original_image = original_image;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTimes_go() {
        return times_go;
    }

    public void setTimes_go(String times_go) {
        this.times_go = times_go;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getDomain_id() {
        return domain_id;
    }

    public void setDomain_id(String domain_id) {
        this.domain_id = domain_id;
    }

    public String getUrl_prefix() {
        return url_prefix;
    }

    public void setUrl_prefix(String url_prefix) {
        this.url_prefix = url_prefix;
    }

    public String getUrl_suffix() {
        return url_suffix;
    }

    public void setUrl_suffix(String url_suffix) {
        this.url_suffix = url_suffix;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }
}
