package com.example.linan;

/**
 * 站点类
 * Created by 黄睿 on 2018/12/16.
 */

public class StopList {
    private String name;
    private int imageId = R.drawable.icon_hisline;

    public StopList(int imageId, String name) {
        this.imageId = imageId;
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
