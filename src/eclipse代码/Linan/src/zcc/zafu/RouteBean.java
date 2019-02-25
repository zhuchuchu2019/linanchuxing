package zcc.zafu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * 实现关于route表的查询操作
 * @author 11357
 *
 */
public class RouteBean {
	/**
	 * 获取全部路线
	 * @return 保存RouteInfo的List列表
	 */
	public  List<RouteInfo> getAll(){
		
		List< RouteInfo > data = 
				new ArrayList< RouteInfo >();
		Connection conn = DBConn.getConnection();
		try{
			
			String sql = "select * "
					+ " from  route ";
			java.sql.PreparedStatement st = 
					conn.prepareStatement( sql);
			ResultSet rs = st.executeQuery(  );
			while ( rs.next() ){
				int num = rs.getInt("num");
				String name = rs.getString( "name" );	
				List<String> place = new ArrayList<String>();
				for(int i =3;i<=23;i++) {
					if(rs.getString(i)!=null)
					place.add(rs.getString(i));
				}
				int n =0;
				Iterator<String> iter = place.iterator();
				StringBuilder builder = new StringBuilder();
				while(iter.hasNext()) {
					String info = iter.next();
					builder.append(info).append("\n");
					n++;						
				}
				System.out.println(builder);
				RouteInfo info = new RouteInfo(num,name,place);
				System.out.println(info.toString());
				data.add(info);
			}
		}
		catch( SQLException e ){
			e.printStackTrace();
			System.out.printf( "数据库查询失败\n" + e.getMessage()  );
		}
		finally{
			if( conn != null ){
				try{
					conn.close();
				}
				catch( SQLException e ){
					System.out.printf( "关闭数据库失败\n" + e.getMessage()  );
				}
			}
			
		}

		return data;
	}
	/**
	 * 根据车编号查找线路
	 * @param value 车编号
	 * @return 保存RouteInfo的List列表
	 */
public  List<RouteInfo> getStationByNum(int value){
		
		List< RouteInfo > data = 
				new ArrayList< RouteInfo >();
		Connection conn = DBConn.getConnection();
		try{
			
			String sql = "select * "
					+ " from  route where num = ? ";
			java.sql.PreparedStatement st = 
					conn.prepareStatement( sql);
			int index = 1;
			st.setInt(index, value);
			ResultSet rs = st.executeQuery(  );
			StringBuilder builder = new StringBuilder();
			while ( rs.next() ){
				int num = rs.getInt("num");
				String name = rs.getString("name");
				List<String> place = new ArrayList<String>();
				for(int i =3;i<=23;i++) {
					if(rs.getString(i)!=null)
					place.add(rs.getString(i));
				}
				Iterator<String> iter = place.iterator();
				int n =1;
				while(iter.hasNext()) {
					String info = iter.next();
					builder.append(info).append("\n");
					n++;						
				}
				System.out.println(builder);
				RouteInfo info = new RouteInfo(num,name,place);
				System.out.println(info.toString());
				data.add(info);
			}
		}
		catch( SQLException e ){
			e.printStackTrace();
			System.out.printf( "数据库查询失败\n" + e.getMessage()  );
		}
		finally{
			if( conn != null ){
				try{
					conn.close();
				}
				catch( SQLException e ){
					System.out.printf( "关闭数据库失败\n" + e.getMessage()  );
				}
			}
			
		}

		return data;
	}
	public static void main(String[] args) {
		new RouteBean().getAll();
		//new RouteBean().getStationByNum(1);
	}
}
