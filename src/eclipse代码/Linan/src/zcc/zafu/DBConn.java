package zcc.zafu;

import java.sql.DriverManager;
import com.mysql.jdbc.Connection;
/**
 * 通过jdbc连接MySQL数据库
 * @author 11357
 *
 */
public class DBConn {
	
    public static Connection getConnection(){

    	Connection con = null;
    	try{

    		Class.forName( "com.mysql.jdbc.Driver" );

    	    con = (Connection)DriverManager.getConnection( 
    	    	"jdbc:mysql://localhost:3306/linan"
    	    	+ "?useUnicode=true&characterEncoding=UTF-8",
				"root", 
				"123456" ); 
    	    System.out.printf( "数据库连接成功\n" );
    	}
    	catch( Exception e ){
    		System.out.printf( "数据库连接失败\n" );
    	}	
    	return con;
    }
}
