package zcc.zafu;
/**
 * 换乘信息的封装类，有成员：起点，中转站点、终点、线路一、二、与总站数，有配套构造、get/set、tostring等方法
 * @author 黄睿
 *
 */
public class huancheng1 {
	private String startstop;///<起始站点
	private String midstop;///<中转站点
	private String endstop;///<目的站
	private String routefir;///<线路一
	private String routersec;///<线路二
	private int stopcount;///<总站点数
	
	public huancheng1() {
		
	}
	/**
	 * 构造函数
	 * @param startstop 起始站点
	 * @param midstop 中转站点
	 * @param endstop  目的站
	 * @param routefir 线路一
	 * @param routrsec 线路二
	 * @param stopcount 总站点数
	 */
	public huancheng1(String startstop, String midstop, String endstop, String routefir, String routrsec,
			int stopcount) {
		super();
		this.startstop = startstop;
		this.midstop = midstop;
		this.endstop = endstop;
		this.routefir = routefir;
		this.routersec = routrsec;
		this.stopcount = stopcount;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endstop == null) ? 0 : endstop.hashCode());
		result = prime * result + ((midstop == null) ? 0 : midstop.hashCode());
		result = prime * result + ((routefir == null) ? 0 : routefir.hashCode());
		result = prime * result + ((routersec == null) ? 0 : routersec.hashCode());
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
		huancheng1 other = (huancheng1) obj;
		if (endstop == null) {
			if (other.endstop != null)
				return false;
		} else if (!endstop.equals(other.endstop))
			return false;
		if (midstop == null) {
			if (other.midstop != null)
				return false;
		} else if (!midstop.equals(other.midstop))
			return false;
		if (routefir == null) {
			if (other.routefir != null)
				return false;
		} else if (!routefir.equals(other.routefir))
			return false;
		if (routersec == null) {
			if (other.routersec != null)
				return false;
		} else if (!routersec.equals(other.routersec))
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


	@Override
	public String toString() {
		return "huancheng1 [startstop=" + startstop + ", midstop=" + midstop + ", endstop=" + endstop + ", routefir="
				+ routefir + ", routrsec=" + routersec + ", stopcount=" + stopcount + "]";
	}

	public String getStartstop() {
		return startstop;
	}

	public void setStartstop(String startstop) {
		this.startstop = startstop;
	}


	public String getMidstop() {
		return midstop;
	}

	public void setMidstop(String midstop) {
		this.midstop = midstop;
	}

	public String getEndstop() {
		return endstop;
	}

	public void setEndstop(String endstop) {
		this.endstop = endstop;
	}

	public String getRoutefir() {
		return routefir;
	}

	public void setRoutefir(String routefir) {
		this.routefir = routefir;
	}

	public String getRoutrsec() {
		return routersec;
	}

	public void setRoutrsec(String routrsec) {
		this.routersec = routrsec;
	}

	public int getStopcount() {
		return stopcount;
	}

	public void setStopcount(int stopcount) {
		this.stopcount = stopcount;
	}
	
	
}
