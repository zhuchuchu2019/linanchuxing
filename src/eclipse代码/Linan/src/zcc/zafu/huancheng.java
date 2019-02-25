package zcc.zafu;
/**
 * 直达信息的封装类，有成员：起点，终点线路与站数，有配套构造、get/set、tostring等方法
 * @author 黄睿
 *
 */
public class huancheng {
	private String startstop;///<起始站
	private String endstop;///<终点站
	private String route;///<线路
	private int stopcount;///<站数
	
	public huancheng() {}
	/**
	 * 构造函数
	 * @param startstop 起始站
	 * @param endstop 终点站
	 * @param route 线路
	 * @param stopcount 站数
	 */
	public huancheng(String startstop, String endstop, String route, int stopcount) {
		super();
		this.startstop = startstop;
		this.endstop = endstop;
		this.route = route;
		this.stopcount = stopcount;
	}

	@Override
	public String toString() {
		return "huancheng [startstop=" + startstop + ", endstop=" + endstop + ", route=" + route + ", stopcount="
				+ stopcount + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endstop == null) ? 0 : endstop.hashCode());
		result = prime * result + ((route == null) ? 0 : route.hashCode());
		result = prime * result + ((startstop == null) ? 0 : startstop.hashCode());
		result = prime * result + stopcount;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		huancheng other = (huancheng) obj;
		if (endstop == null) {
			if (other.endstop != null)
				return false;
		} else if (!endstop.equals(other.endstop))
			return false;
		if (route == null) {
			if (other.route != null)
				return false;
		} else if (!route.equals(other.route))
			return false;
		if (startstop == null) {
			if (other.startstop != null)
				return false;
		} else if (!startstop.equals(other.startstop))
			return false;
		if (stopcount != other.stopcount)
			return false;
		return true;
	}

	public String getStartstop() {
		return startstop;
	}

	public void setStartstop(String startstop) {
		this.startstop = startstop;
	}

	public String getEndstop() {
		return endstop;
	}

	public void setEndstop(String endstop) {
		this.endstop = endstop;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public int getStopcount() {
		return stopcount;
	}

	public void setStopcount(int stopcount) {
		this.stopcount = stopcount;
	}
	
	
}
