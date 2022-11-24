package com.example.webapphr3.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebFilter(filterName = "RoleFilter", servletNames = {"EmployeeServlet","JobServlet"})
public class SessionRoleFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("filtro RoleFilter");
        //validación del rol
        chain.doFilter(request, response);
    }
}
