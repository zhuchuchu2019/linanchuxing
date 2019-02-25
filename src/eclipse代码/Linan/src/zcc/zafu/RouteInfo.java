package zcc.zafu;

import java.util.List;
/**
 * 对应数据库中route表字段信息
 * @author 11357
 *
 */
public class RouteInfo {
private int num;///<车编号	
private String name;///<车名
private List<String> place;///<存放站点的List
/**
 * 构造函数
 * @param num 车编号
 * @param name 车名
 * @param place 存放站点的List
 */
public RouteInfo(int num, String name, List<String> place) {
	super();
	this.num = num;
	this.name = name;
	this.place = place;
}


public RouteInfo(String name, String start, List<String> place) {
	super();
	this.name = name;
	this.place = place;
}

public int getNum() {
	return num;
}

@Override
public String toString() {
	return "RouteInfo [num=" + num + ", name=" + name + "]";
}

public void setNum(int num) {
	this.num = num;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public List<String> getPlace() {
	return place;
}

public void setPlace(List<String> place) {
	this.place = place;
}

}
