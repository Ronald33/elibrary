package com.ronald.elibrary.controller.usuario;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "UsuarioLogin", urlPatterns =
    {
        "/usuario/login"
})
public class Login extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        if(session.getAttribute("id") == null)
        {
            request.getRequestDispatcher("/jsp/usuario/login.jsp").forward(request, response);
        }
        else { response.sendRedirect("../usuario/dashboard"); }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
//        processRequest(request, response);
    }
}