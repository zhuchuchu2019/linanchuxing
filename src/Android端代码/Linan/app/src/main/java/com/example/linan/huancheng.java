package com.example.linan;


/**
 * 换乘类
 */
public class huancheng implements Cloneable{
    String startstop;///<起始站点
    String midstop = "";///<中转站点
    String endstop;///<目的站点
    String routefir;///<线路一
    String routersec = "";///<线路二
    int stopcount;///<总站点计数

    public Object clone() {
        huancheng o = null;
        try {
            o = (huancheng) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }
    public huancheng() {
    }

    public huancheng(String midstop, String routefir, String endstop, String routersec, String startstop, int stopcount) {
        this.midstop = midstop;
        this.routefir = routefir;
        this.endstop = endstop;
        this.routersec = routersec;
        this.startstop = startstop;
        this.stopcount = stopcount;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        huancheng huancheng = (huancheng) o;

        if (stopcount != huancheng.stopcount) return false;
        if (!startstop.equals(huancheng.startstop)) return false;
        if (!midstop.equals(huancheng.midstop)) return false;
        if (!endstop.equals(huancheng.endstop)) return false;
        if (!routefir.equals(huancheng.routefir)) return false;
        return routersec.equals(huancheng.routersec);

    }

    @Override
    public int hashCode() {
        int result = startstop.hashCode();
        result = 31 * result + midstop.hashCode();
        result = 31 * result + endstop.hashCode();
        result = 31 * result + routefir.hashCode();
        result = 31 * result + routersec.hashCode();
        result = 31 * result + stopcount;
        return result;
    }

    public String getEndstop() {
        return endstop;
    }

    public void setEndstop() {
        this.endstop = endstop;
    }

    public String getMidstop() {
        return midstop;
    }

    public void setMidstop(String midstop) {
        this.midstop = midstop;
    }

    public String getRoutefir() {
        return routefir;
    }

    public void setRoutefir(String routefir) {
        this.routefir = routefir;
    }

    public String getRoutersec() {
        return routersec;
    }

    public void setRoutersec(String routersec) {
        this.routersec = routersec;
    }

    public String getStartstop() {
        return startstop;
    }

    public void setStartstop(String startstop) {
        this.startstop = startstop;
    }

    public int getStopcount() {
        return stopcount;
    }

    public void setStopcount(int stopcount) {
        this.stopcount = stopcount;
    }
}

