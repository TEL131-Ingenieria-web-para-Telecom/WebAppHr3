<%@ page import="com.example.webapphr3.Beans.Employee" %>
<%
    Employee emp = (Employee) session.getAttribute("employee");
    if (emp != null && emp.getEmployeeId() != 0) {
        response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Gestión HR</title>
        <jsp:include page="includes/headCss.jsp"></jsp:include>
    </head>
    <body>
        <div class='container'>
            <jsp:include page="includes/navbar.jsp">
                <jsp:param name="currentPage" value="emp"/>
            </jsp:include>
            <div class="row mt-4">
                <div class="col"><img width="100%" src="<%=request.getContextPath()%>/resources/images/hr.jpg"></div>
            </div>
            <jsp:include page="includes/footer.jsp"/>
        </div>
    </body>
</html>
