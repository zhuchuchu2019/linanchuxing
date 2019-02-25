<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="zcc.zafu.BusBean"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*
*获得对应字符，UTF-8解码，运用Bean方法，获得相关站点数据并使用迭代器以xml格式输出
*/
	String stop = request.getParameter("stop");
	stop = URLDecoder.decode(stop, "UTF-8");
	System.out.println(stop);
	BusBean bean = new BusBean();
	List<String> data = bean.stopsearch(stop);
	out.println("<stopsearch_data>");
	Iterator<String> iter = data.iterator();
	while (iter.hasNext()){
		String info = iter.next();
		out.println("<stopsearch>");
		out.println("<stop>"+info+"</stop>");
		out.println("</stopsearch>");
		System.out.printf("%s\n",info.toString());
	}
	out.println("</stopsearch_data>");

%>
