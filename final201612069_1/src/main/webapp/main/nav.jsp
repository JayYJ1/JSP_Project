<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--jstl 라이브러리 선언-->
<nav class="navbar navbar-expand-lg navbar-light" id="mainNav">
    <div class="container px-4 px-lg-5">
        <a class="navbar-brand" href="../main/index.jsp">YJ's Blog</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            Menu
            <i class="fas fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ms-auto py-4 py-lg-0">
                <!--<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="blogs/register.jsp">Home</a></li>-->
                <!--<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="post.jsp">Sample Post</a></li>-->
<%--                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../blogs/postForm.jsp">Post</a></li>--%>
                <c:choose >
                    <c:when test="${sessionScope.logined == null}">
                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/login-form.do">Login</a></li>
                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/register-form.do">Register</a></li>
                    </c:when>
                    <c:when test="${sessionScope.logined.email == 'admin'}">
                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../blogs/list.do?pn=1">List</a></li>
                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../blogs/sort.do?by=desc,title">DescTitle</a></li>
                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/list.do?pn=1">MemberList</a></li>
                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/sort.do?by=desc,title">DescMember</a></li>
                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../blogs/post-form.do?email=${sessionScope.member}">Post</a></li>
                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/detail.do?id=${logined.id}">detail</a></li>
                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/logout.do">Logout</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../blogs/list.do">List</a></li>
                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../blogs/sort.do?by=desc,title">DescTitle</a></li>
                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../blogs/post-form.do?email=${sessionScope.member}">Post</a></li>
                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/detail.do?id=${logined.id}">detail</a></li>
                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../members/logout.do">Logout</a></li>
                    </c:otherwise>
                </c:choose><!-- if else 구문의 jstl-->
            </ul>
        </div>
    </div>
</nav>