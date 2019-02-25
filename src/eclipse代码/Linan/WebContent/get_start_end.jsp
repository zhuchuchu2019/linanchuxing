<%@page import="java.util.List"%>
<%@page import="zcc.zafu.RouteInfo"%>
<%@page import="java.util.Iterator"%>
<%@page import="zcc.zafu.RouteBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
RouteBean bean = new RouteBean();
List< RouteInfo> data =  bean.getAll();
String trans = "\"";
int i = 0;
//封装成JSON
out.println("[");
Iterator< RouteInfo > iter = data.iterator();
while( iter.hasNext() ){
	++i;
	RouteInfo info = iter.next();
	int size = info.getPlace().size();
	out.println("{");
	out.println(trans+"num"+trans +":"+ trans+info.getNum()+ trans +",");
	out.println(trans+"name"+trans +":"+ trans+info.getName()+ trans +",");
	out.println(trans+"start"+trans +":"+ trans+info.getPlace().get(0) + trans +",");
	out.println(trans+"end"+trans +":"+ trans+info.getPlace().get(size-1)+ trans);
 
 out.println("}");
 if(i < data.size()){
	 out.println(",");
 	}
 } 
out.println("]");
%>
