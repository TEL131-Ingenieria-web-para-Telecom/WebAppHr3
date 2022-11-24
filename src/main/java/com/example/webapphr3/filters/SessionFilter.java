package com.example.webapphr3.filters;

import com.example.webapphr3.Beans.Employee;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "SessionFilter",
        servletNames = {"CountryServlet",
                "DepartmentServlet",
                "EmployeeServlet",
                "JobServlet",
                "LocationServlet"})
public class SessionFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        System.out.println("filtro SessionFilter");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        Employee em = (Employee) session.getAttribute("employee");

        if (em != null && em.getEmployeeId() > 0) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(req.getContextPath());
        }
    }

    public void destroy() {
    }

}
