package com.ronald.elibrary.helper;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class Helper
{
    private static final boolean isProduction = false;
    
    public static String getBaseURL(HttpServletRequest request)
    {
        String url = request.getRequestURL().toString();
        return url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
    }
    
    public static String getMessageGlobal(Exception e)
    {
        if(Helper.isProduction) { return "Error Desconocido"; }
        else { return e.getMessage(); }
    }
    
    public static boolean isAuth(HttpServletRequest request) throws IOException
    {
        HttpSession session = request.getSession(false);
        return session.getAttribute("id") != null;
    }
}