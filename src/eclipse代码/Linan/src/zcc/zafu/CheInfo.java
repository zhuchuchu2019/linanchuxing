package zcc.zafu;
/**
 * 对应数据库中checi表字段信息
 * @author 11357
 *
 */
public class CheInfo {
private int num;///<车编号
private String direction;///<开往方向
private String startTime;///<早班车时间
private String endTime;///<末班车时间
private String price;///<票价
/**
 * 构造函数
 * @param num 车编号
 * @param direction 开往方向
 * @param startTime 早班车时间
 * @param endTime 末班车时间
 * @param price 票价
 */
public CheInfo(int num, String direction, String startTime, String endTime, String price) {
	super();
	this.num = num;
	this.direction = direction;
	this.startTime = startTime;
	this.endTime = endTime;
	this.price = price;
}
public int getNum() {
	return num;
}
public void setNum(int num) {
	this.num = num;
}
public String getDirection() {
	return direction;
}
public void setDirection(String direction) {
	this.direction = direction;
}
public String getStartTime() {
	return startTime;
}
public void setStartTime(String startTime) {
	this.startTime = startTime;
}
public String getEndTime() {
	return endTime;
}
public void setEndTime(String endTime) {
	this.endTime = endTime;
}
public String getPrice() {
	return price;
}
public void setPrice(String price) {
	this.price = price;
}
@Override
public String toString() {
	return "CheInfo [num=" + num + ", direction=" + direction + ", startTime=" + startTime + ", endTime=" + endTime
			+ ", price=" + price + "]";
}

}
