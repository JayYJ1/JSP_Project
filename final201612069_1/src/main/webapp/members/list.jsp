<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!--jstl 라이브러리 선언-->
<%-- JSTL core 라이브러리 사용을 위한 선언 --%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Clean Blog - Start Bootstrap Theme</title>
    <jsp:include page="../main/header.jsp" /> <!-- page 인클루드 --></head>
<body>
<!-- Navigation-->
<jsp:include page="../main/nav.jsp" /> <!-- page 인클루드 -->
<!-- Page Header-->
<header class="masthead" style="background-image: url('../assets/img/sky.jpg')">
    <div class="container position-relative px-4 px-lg-5">
        <div class="row gx-4 gx-lg-5 justify-content-center">
            <div class="col-md-10 col-lg-8 col-xl-7">
                <div class="site-heading">
                    <h1>member list</h1>
                    <span class="subheading">A Blog Theme by Start Bootstrap</span>
                </div>
            </div>
        </div>
    </div>
</header>
<!-- Main Content-->
<div class="container px-4 px-lg-5">
    <div class="row gx-4 gx-lg-5 justify-content-center">
        <div class="col-md-10 col-lg-8 col-xl-7">
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable">
                        <thead>
                        <tr>
                            <th>Id</th>
                            <th>Email</th>
                            <th>Name</th>
                            <th>Phone</th>
                            <th>Address</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.memberList}" var="member">
                            <c:if test="${member.email ne 'admin'}">
                            <tr>
                                <td><a href="../members/detail.do?id=${member.id}">${member.id}</a></td>
                                <td>${member.email}</td>
                                <td>${member.name}</td>
                                <td>${member.phone}</td>
                                <td>${member.address}</td>
                            </tr>
                            </c:if>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <!-- Pager-->
            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">

                    <c:if test="${pagination.beginPageNo > pagination.perPagination }" >
                        <li class="page-item">
                            <a class="page-link" href="../members/list.do?pn=${pagination.beginPageNo - 1}" tabindex="-1" aria-disabled="true">Prev</a>
                        </li>
                    </c:if>

                    <c:forEach var="pageNo" begin="${pagination.beginPageNo}" end="${pagination.endPageNo}">
                        <c:choose>
                            <c:when test="${pageNo == pagination.curPageNo }">
                                <li class="page-item active"><a class="page-link" href="../members/list.do?pn=${pageNo}">${pageNo}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link" href="../members/list.do?pn=${pageNo}">${pageNo}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:if test="${pagination.endPageNo < pagination.totalPages }" >
                        <li class="page-item">
                            <a class="page-link" href="../members/list.do?pn=${pagination.endPageNo + 1}" tabindex="-1" aria-disabled="true">Next</a>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </div>
    </div>
</div>
<!-- Footer-->
<jsp:include page="../main/footer.jsp" /> <!-- page 인클루드 -->
</body>
</html>
