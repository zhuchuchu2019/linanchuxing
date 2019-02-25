<%@page import="java.util.List"%>
<%@page import="zcc.zafu.RouteInfo"%>
<%@page import="java.util.Iterator"%>
<%@page import="zcc.zafu.RouteBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String numStr = request.getParameter("num");
RouteBean bean = new RouteBean();
int num = Integer.parseInt(numStr);
List<RouteInfo> data =  bean.getStationByNum(num);
System.out.printf("共有%d条记录\n", data.size());
Iterator<RouteInfo> iter = data.iterator();
String trans = "\"";
//int j;
//封装成JSON
out.println("[");
while( iter.hasNext() ){
	RouteInfo info = iter.next();
 int size = info.getPlace().size();
 for(int j =0;j<size;++j){
	out.println("{");
 	out.println(trans+"station"+trans +":"+ trans+ info.getPlace().get(j)+ trans);
 	out.println("}");
 	if(j < size-1){
 		 out.println(",");
 	 }
 }
 out.println("]");
}
%>
