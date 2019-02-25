package com.example.linan;

/**
 * @brief 记录车号，起始站、终点站等信息类
 * Created by 11357 on 2018/12/15.
 */

public class RouteInfo {
    private int num; ///< 车编号
    private String name;///< 车名
    private String start;///< 起点站
    private String end;///< 终点站

    /**
     * 构造函数
     * @param num 车编号
     * @param name 车名
     * @param start 起点站
     * @param end 终点站
     */
    public RouteInfo(int num, String name, String start,String end) {
        this.num = num;
        this.name = name;
        this.start = start;
        this.end = end;
    }
    public int getNum() {
        return num;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setNum(int num) {
        this.num = num;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
