<%--
  Created by IntelliJ IDEA.
  User: tntnqk12
  Date: 2021-11-18
  Time: 오후 3:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="iducs.jsp.b.csjyjbblog.model.Blog" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <title>blogs/test.jsp</title>
</head>
<body>
<%-- JSP주석 : out은 jsp의 기본 객체 중 하나, System.out과는 다른 객체이다 --%>
<%
    out.println("<h1>스크립팅 요소 중 스크립틀릿(Scriptlet)</h1>");
    for(int i=0; i < 10; i++){
        out.println("<h3>"+ i +"</h3>"); // 웹브라우저에 출력
    }
    ArrayList<Blog> blogList = (ArrayList<Blog>) request.getAttribute("blogList");
    System.out.println(blogList.size()); //콘솔창에 출력
    for(Blog blog : blogList){
%>
    <div class="post-preview">
        <a href="detail.do?id=<%= blog.getId() %>"> <!-- <%-- "<% %>" 표현식 --%>-->
            <h2 class="post-title"><%= blog.getName() %></h2>
            <h3 class="post-subtitle"></h3>
        </a>
        <p class="post-meta">
            Posted by <%=blog.getEmail() %>
        </p>
    </div>
    <!-- Divider-->
    <hr class="my-4" />
<%
    }
%>
</body>
</html>
