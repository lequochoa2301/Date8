<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book List</title>
    <link rel="stylesheet" href="/webjars/bootstrap/5.1.3/css/bootstrap.min.css">
</head>
    <jsp:include page="header.jsp" />
<body>
    <div class="container" style="margin-top: 60px;">

        <div class="row">
            <div class="col-xs-12 col-sm-8 col-md-6">
                <form:form action="search" method="get">
                    <div class="input-group">
                        <input type="text" name="searchInput" class="form-control"
                               placeholder="Search by name or author...." />
                        <span class="input-group-btn">
                            <button class="btn btn-primary" type="submit">Search</button>
                        </span>
                    </div>
                </form:form>
            </div>

            <div class="col-xs-12 col-sm-12 col-md-10">
                <c:if test="${not empty bookList}">
                    <table class="table table-condensed table-hover">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Name</th>
                                <th>Author</th>
                                <th>ISBN</th>
                                <th>Price</th>
                                <th>Publish Date</th>
                                <th>Category</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="book" items="${bookList}" varStatus="index">
                                <tr>
                                    <td>${book.id}</td>
                                    <td>${book.name}</td>
                                    <td>${book.author}</td>
                                    <td>${book.bookDetails.isbn}</td>
                                    <td>${book.bookDetails.price}</td>
                                    <td>${book.bookDetails.publishDate}</td>
                                    <td>${book.category.name}</td>
                                    <td>
                                        <button class="btn btn-sm btn-primary"
                                                onclick="location.href='edit/${book.id}'">Edit</button>
                                        <button class="btn btn-sm btn-danger"
                                                onclick="location.href='delete/${book.id}'">Delete</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                <c:if test="${bookList.size() == 0}">
                    <br>
                    <div class="alert alert-warning">There is no data</div>
                </c:if>
            </div>
        </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.mim.js"></script>
    <script src="/webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
</body>
</html>
