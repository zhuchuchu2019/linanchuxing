<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.Iterator"%>
<%@page import="zcc.zafu.huancheng1"%>
<%@page import="java.util.List"%>
<%@page import="zcc.zafu.BusBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    /*
     *获得对应起点与终点，UTF-8解码，运用Bean方法，获得换乘线路数据并使用迭代器以xml格式输出
    */
  	String startstop = request.getParameter("startstop");
    String endstop = request.getParameter("endstop");
    
    startstop = URLDecoder.decode(startstop, "UTF-8");
    endstop = URLDecoder.decode(endstop,"UTF-8");
   
    System.out.printf("%s,%s",startstop,endstop);
    BusBean bean = new BusBean();
    
    List<huancheng1>data = bean.huancheng1(startstop, endstop);
    out.println("<huancheng1_data>");
    Iterator<huancheng1> iter = data.iterator();
    while(iter.hasNext()){
    	huancheng1 info = iter.next();
    	out.println("<huancheng1>");
    	out.println("<startstop>"+info.getStartstop()+"</startstop>");
    	out.println("<midstop>"+info.getMidstop()+"</midstop>");
    	out.println("<endstop>"+info.getEndstop()+"</endstop>");
    	out.println("<routefir>"+info.getRoutefir()+"</routefir>");
    	out.println("<routesec>"+info.getRoutrsec()+"</routesec>");
    	out.println("<stopcount>"+info.getStopcount()+"</stopcount>");
    	out.println("</huancheng1>");
    	System.out.printf( "%s\n", info.toString());
    }
    out.println("</huancheng1_data>");

    %>
