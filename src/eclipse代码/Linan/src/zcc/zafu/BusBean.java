package zcc.zafu;

import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 为线路直达，换乘，站点搜索的具体访问数据库查询方法
 * @author 黄睿
 *
 */
public class BusBean {
	/**
	 * 换乘方法，传入起点与终点，访问数据库对应存储过程，返回对应haungcehng1封装类
	 * @param startstop 起点站
	 * @param endstop 终点站
	 * @return
	 */
	public List <huancheng1> huancheng1(String startstop,String endstop){
		List<huancheng1> data = new ArrayList<huancheng1>();
		java.sql.Connection conn = DBConn.getConnection();
		try {
			String sql = "call InquiryT1(?,?)";
			java.sql.PreparedStatement st = conn.prepareStatement(sql);
			int index = 1;
			st.setString(index, startstop);
			++index;
			st.setString(index, endstop);
			++index;
			ResultSet rs = st.executeQuery();
			System.out.println("查询结果为：");
			while(rs.next()) {
				String sstop = rs.getString("启始站点");
				String mstop = rs.getString("中转站点");
				String estop = rs.getString("目的站点");
				String routefir = rs.getString("乘坐路线1");
				String routesec = rs.getString("乘坐路线2");
				int stopcount = rs.getInt("总站点数");
				huancheng1 info = new huancheng1(sstop, mstop, estop, routefir, routesec, stopcount);
				System.out.println(info.toString());
				data.add(info);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.printf( "数据库查询失败\n" + e.getMessage());
		}finally {

			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
					System.out.printf("关闭连接失败\n",e.getMessage());
				}
			}
			
		
		}
		
		
		return data;
	}
	/**
	 * 直达方法，传入起点与终点，访问数据库对应存储过程，返回对应haungcehng封装类
	 * @param startstop 起点站
	 * @param endstop 终点站
	 * @return
	 */
	public List <huancheng> huancheng(String startstop,String endstop){
		List<huancheng> data = new ArrayList<huancheng>();
		java.sql.Connection conn = DBConn.getConnection();
		try {
			String sql = "call InquiryT0(?,?)";
			java.sql.PreparedStatement st = conn.prepareStatement(sql);
			int index = 1;
			st.setString(index, startstop);
			++index;
			st.setString(index, endstop);
			++index;
			ResultSet rs = st.executeQuery();
			System.out.println("查询结果为：");
			while(rs.next()) {
				String sstop = rs.getString("启始站点");
				String estop = rs.getString("目的站点");
				String route = rs.getString("乘坐线路");
				int stopcount = rs.getInt("经过的站点数");
				huancheng info = new huancheng(sstop, estop, route, stopcount);
				data.add(info);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.printf( "数据库查询失败\n" + e.getMessage());
		}finally {

			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
					System.out.printf("关闭连接失败\n",e.getMessage());
				}
			}
			
		
		}
		
		return data;
	}
	/**
	 * 站点查找方法，传入字符，访问数据库对应存储过程，返回对应列表
	 * @param stop 终点站
	 * @return
	 */
	public List<String> stopsearch(String stop){
		List<String> data = new ArrayList<String>();
		java.sql.Connection conn = DBConn.getConnection();
		try {
			String sql = "call InquiryT2(?)";
			java.sql.PreparedStatement st = conn.prepareStatement(sql);
			int index = 1;
			String str = "%"+stop+"%";
			st.setString(index, str);
			++index;
			ResultSet rs = st.executeQuery();
			System.out.println("查询结果为：");
			while(rs.next()) {
				String stop2 = rs.getString("站点");
				System.out.println(stop2);
				data.add(stop2);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.printf( "数据库查询失败\n" + e.getMessage());
		}finally {

			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
					System.out.printf("关闭连接失败\n",e.getMessage());
				}
			}
			
		
		}
			
		return data;
		
	}
	public static void main(String[] args) {
		new BusBean().huancheng("农大西门", "东站");
		new BusBean().huancheng1("农大西门", "东站");
		new BusBean().stopsearch("东站");
	}
}
