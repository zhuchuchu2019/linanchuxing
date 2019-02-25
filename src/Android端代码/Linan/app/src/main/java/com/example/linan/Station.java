package com.example.linan;

/**
 * @brief 显示站点信息类
 * Created by 11357 on 2018/12/15.
 */

public class Station {
    private String Name;///<车名
    private int ImageId;///<上图片id
    private int ImageIdL;///<左图片id

    /**
     * 构造函数
     * @param name 车名
     * @param imageId 上图片id
     */
    public Station(String name, int imageId) {
        Name = name;
        ImageId = imageId;
    }

    /**
     * 构造函数
     * @param name 车名
     * @param imageId 上图片id
     * @param imageIdL 左图片id
     */
    public Station(String name, int imageId, int imageIdL) {
        Name = name;
        ImageIdL = imageIdL;
        ImageId = imageId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }

    public int getImageIdL() {
        return ImageIdL;
    }

    public void setImageIdL(int imageIdL) {
        ImageIdL = imageIdL;
    }
}
