package com.example.webapphr3.Controllers;

import com.example.webapphr3.Beans.Country;
import com.example.webapphr3.Beans.Employee;
import com.example.webapphr3.Daos.CountryDao;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

@WebServlet(name = "CountryServlet", urlPatterns = {"/CountryServlet"})
public class CountryServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        Employee em = (Employee) session.getAttribute("employee");
        if (em == null) {
            response.sendRedirect(request.getContextPath());
        } else {
            String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

            CountryDao countryDao = new CountryDao();
            RequestDispatcher view;
            Country country;
            String countryId;

            switch (action) {
                case "formCrear":
                    view = request.getRequestDispatcher("country/newCountry.jsp");
                    view.forward(request, response);
                    break;
                case "crear":
                    countryId = request.getParameter("id");
                    String countryName = request.getParameter("countryName");
                    System.out.println(countryName);
                    BigDecimal regionId = new BigDecimal(request.getParameter("regionId"));

                    country = countryDao.obtener(countryId);

                    if (country == null) {
                        countryDao.crear(countryId, countryName, regionId);
                    } else {
                        countryDao.actualizar(countryId, countryName, regionId);
                    }
                    response.sendRedirect(request.getContextPath() + "/CountryServlet");
                    break;
                case "lista":
                    ArrayList<Country> lista = countryDao.listar();

                    request.setAttribute("lista", lista);

                    view = request.getRequestDispatcher("country/listaCountry.jsp");
                    view.forward(request, response);
                    break;

                case "editar":
                    countryId = request.getParameter("id");
                    country = countryDao.obtener(countryId);
                    if (country == null) {
                        response.sendRedirect(request.getContextPath() + "/CountryServlet");
                    } else {
                        request.setAttribute("country", country);
                        view = request.getRequestDispatcher("country/updateCountry.jsp");
                        view.forward(request, response);
                    }
                    break;
                case "borrar":
                    countryId = request.getParameter("id");
                    if (countryDao.obtener(countryId) != null) {
                        countryDao.borrar(countryId);
                    }
                    response.sendRedirect(request.getContextPath() + "/CountryServlet");
                    break;
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
