<%@page import="zcc.zafu.CheInfo"%>
<%@page import="zcc.zafu.CheBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String numStr = request.getParameter("num");
CheBean bean = new CheBean();
int num = Integer.parseInt(numStr);
List<CheInfo> data =  bean.getCheInfoByNum(num);
System.out.printf("共有%d条记录\n", data.size());
Iterator<CheInfo> iter = data.iterator();
String trans = "\"";
//封装成JSON
out.println("[");
while( iter.hasNext() ){
	CheInfo info = iter.next();
	out.println("{");
	out.println(trans+"dir"+trans +":"+ trans+ info.getDirection()+ trans+",");
 	out.println(trans+"startTime"+trans +":"+ trans+ info.getStartTime()+ trans+",");
 	out.println(trans+"endTime"+trans +":"+ trans+ info.getEndTime()+ trans+",");
 	out.println(trans+"price"+trans +":"+ trans+ info.getPrice()+ trans);
 	out.println("}");
 }
 out.println("]");

%>
