<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
      <style>
              .text-error {
                  color: red;
                  font-size: 12px;
              }
      </style>
<jsp:include page="header.jsp" />
<body>
    <div class="container" style="margin-top: 60px;">
        <div class="col-md-8">
            <form:form action="${action}" method="post" modelAttribute="book">
                <fieldset class="scheduler-border">
                    <legend class="schedule-border"><c:out value="${msg}"/></legend>
                    <c:if test="${type.equals('update')}">
                        <div class="form-group">
                            <label class="control-label">ID</label>
                            <form:input path="id" type="text" class="form-control" id="id" placeholder="ID" disabled="true"/>
                            <form:hidden path="id"/>
                            <form:hidden path="bookDetails.id"/>
                        </div>
                    </c:if>
                    <div class="form-group">
                        <label class="control-label">Name (*)</label><br>
                        <form:input path="name" type="text" id="name" class="form-control" placeholder="Name" />
                        <form:errors path="name" class="text-error" />
                    </div>
                    <div class="form-group">
                        <label class="control-label">Author (*)</label><br>
                        <form:input path="author" type="text" id="author" class="form-control" placeholder="Author"/>
                        <form:errors path="author" class="text-error" />
                    </div>
                    <div class="form-group">
                        <label class="control-label">ISBN (*)</label><br>
                        <form:input path="bookDetails.isbn" type="text" class="form-control" placeholder="ISBN" required="true"/>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Price (*)</label><br>
                        <form:input path="bookDetails.price" type="number" step="any" class="form-control" placeholder="Price" required="true" min="1"/>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Number of pages (*)</label><br>
                        <form:input path="bookDetails.numberOfPage" type="number" class="form-control" placeholder="Number of pages" required="true" min="1"/>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="publishDate">Publish Date (*)</label><br>
                        <form:input path="bookDetails.publishDate" type="date" class="form-control" placeholder="Publish Date" required="true"/>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Category (*)</label><br>
                        <form:select path="category.id" class="form-control">
                            <form:option value="0" label="---Select---"/>
                            <form:options items="${categoryList}"/>
                        </form:select>
                    </div>
                    <br>
                    <button type="submit" class="btn btn-primary">Save</button>
                </fieldset>
            </form:form>
        </div>
    </div>

</body>
</html>
