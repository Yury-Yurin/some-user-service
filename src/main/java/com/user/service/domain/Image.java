package com.user.service.domain;

/**
 * Created by yury on 8/4/16.
 */
public class Image {

    private Integer imgId;
    private Integer userId;
    private String url;

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Image() {

    }

    public Image(Image image) {
        this.imgId = image.getImgId();
        this.userId = image.getUserId();
        this.url = image.getUrl();
    }
}
