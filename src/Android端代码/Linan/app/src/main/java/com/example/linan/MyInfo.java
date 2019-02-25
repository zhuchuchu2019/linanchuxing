package com.example.linan;

/**
 * @brief 记录图片id，内容类
 * Created by 11357 on 2018/12/21.
 */

public class MyInfo {
    private int ImageId;
    private String name;
    private int ImageIdR;

    /**
     * 构造函数
     * @param imageId
     * @param name
     * @param imageIdR
     */
    public MyInfo(int imageId, String name, int imageIdR) {
        ImageId = imageId;
        ImageIdR = imageIdR;
        this.name = name;
    }

    public int getImageIdR() {
        return ImageIdR;
    }

    public void setImageIdR(int imageIdR) {
        ImageIdR = imageIdR;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }
}
