package com.example.linan;

/**
 * @brief 记录收藏车辆类
 * Created by 11357 on 2018/12/21.
 */

public class Collection {
    private String num;///<已收藏的车编号
    private String name;///<已收藏的车名

    /**
     * 构造函数
     * @param num 已收藏的车编号
     * @param name 已收藏的车名
     */
    public Collection(String num, String name) {
        this.num = num;
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
