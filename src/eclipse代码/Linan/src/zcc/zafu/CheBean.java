package zcc.zafu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * 实现关于checi表的查询操作
 * @author 11357
 *
 */
public class CheBean {
	/**
	 * 根据车编号获取车次的具体信息
	 * @param value 车编号
	 * @return 保存CheInfo的List列表
	 */
public  List<CheInfo> getCheInfoByNum(int value){
		
		List< CheInfo > data = 
				new ArrayList< CheInfo >();
		Connection conn = DBConn.getConnection();
		try{
			
			String sql = "select * "
					+ " from  checi where num = ? ";
			java.sql.PreparedStatement st = 
					conn.prepareStatement( sql);
			int index = 1;
			st.setInt(index, value);
			ResultSet rs = st.executeQuery(  );
			while ( rs.next() ){
				int num = rs.getInt("num");
				String dir = rs.getString("dir");
				String startTime = rs.getString("startTime");
				String endTime = rs.getString("endTime");
				String price = rs.getString("price");
				CheInfo info = new CheInfo(num,dir,startTime,endTime,price);
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
}
