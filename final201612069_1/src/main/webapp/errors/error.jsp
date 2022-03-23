<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="../main/header.jsp"%>

</head>
<body>
<!-- Navigation-->

<jsp:include page="../main/nav.jsp" />

<!-- Page Header-->
<header class="masthead" style="background-image: url('../assets/img/sky.jpg')">
    <div class="container position-relative px-4 px-lg-5">
        <div class="row gx-4 gx-lg-5 justify-content-center">
            <div class="col-md-10 col-lg-8 col-xl-7">
                <div class="page-heading">
                    <h1>에러</h1>
                    <!-- HTML 주석
                    EL : $ {} 형식의 JSP 프로그래밍 요소
                    EL은 속성값 접근을 손쉽게 할 목적으로 정의
                    속성값은 JSP은 4개의 유효범위(scope)를 가짐 :
                        pageScpoe, requestScope, sessionScope, applicationScope
                    -->
                    <%-- JSP 주석
                    ${requestScope.name} : ${email} :
                    <%
                        String email = (String) request.getAttribute("email");
                        out.println(email);
                    %>
                    --%>
                    <span class="subheading">
                        ${work} 가 비정상처리 되었습니다.
                    </span>
                </div>
            </div>
        </div>
    </div>
</header>
<!-- Main Content-->
<main class="mb-4">
    <div class="container px-4 px-lg-5">
        <div class="row gx-4 gx-lg-5 justify-content-center">
            <div class="col-md-10 col-lg-8 col-xl-7">
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Saepe nostrum ullam eveniet pariatur voluptates odit, fuga atque ea nobis sit soluta odio, adipisci quas excepturi maxime quae totam ducimus consectetur?</p>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eius praesentium recusandae illo eaque architecto error, repellendus iusto reprehenderit, doloribus, minus sunt. Numquam at quae voluptatum in officia voluptas voluptatibus, minus!</p>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aut consequuntur magnam, excepturi aliquid ex itaque esse est vero natus quae optio aperiam soluta voluptatibus corporis atque iste neque sit tempora!</p>
            </div>
        </div>
    </div>
</main>
<!-- Footer-->

<%@include file="../main/footer.jsp" %>
</body>
</html>
