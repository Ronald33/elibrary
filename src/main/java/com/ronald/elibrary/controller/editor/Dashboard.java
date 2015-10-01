package com.ronald.elibrary.controller.editor;

import com.ronald.elibrary.helper.Helper;
import com.ronald.elibrary.helper.JSON;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EditorDashboard", urlPatterns =
    {
        "/editor/dashboard"
})
public class Dashboard extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            if(Helper.isAuth(request))
            {
                request.getRequestDispatcher("/jsp/editor/dashboard.jsp").forward(request, response);
            }
            else { response.sendRedirect("index.jsp"); }
        }
        catch(IOException e) { JSON.errorMessage(response, Helper.getMessageGlobal(e)); }
        catch(ServletException e) { JSON.errorMessage(response, Helper.getMessageGlobal(e)); }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
//        processRequest(request, response);
    }
}