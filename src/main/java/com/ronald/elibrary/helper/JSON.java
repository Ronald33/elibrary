package com.ronald.elibrary.helper;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class JSON
{
    public static void showJson(HttpServletResponse response, JSONObject json) throws IOException
    {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print(json.toString());
    }
    
    public static void showObject(HttpServletResponse response, Object object) throws IOException
    {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        out.print(gson.toJson(object));
    }    
    
    public static void successRedirect(HttpServletResponse response, String redirectTo)
    {
        try
        {
            JSONObject json = new JSONObject();
            json.put("success", true);
            json.put("url", redirectTo);
            JSON.showJson(response, json);
        }
        catch(JSONException ex)
        {
            Logger.getLogger(JSON.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(IOException ex)
        {
            Logger.getLogger(JSON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void errorMessage(HttpServletResponse response, String message)
    {
        try
        {
            JSONObject json = new JSONObject();
            json.put("success", false);
            json.put("message", message);
            JSON.showJson(response, json);
        }
        catch(JSONException ex)
        {
            Logger.getLogger(JSON.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(IOException ex)
        {
            Logger.getLogger(JSON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static JSONObject getByBuffer(HttpServletRequest request)
    {
        JSONObject data = null;
        
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String s_data = br.readLine();
            data = new JSONObject(s_data);
        }
        catch(IOException ex) { Logger.getLogger(JSON.class.getName()).log(Level.SEVERE, null, ex); }
        catch(JSONException ex) { Logger.getLogger(JSON.class.getName()).log(Level.SEVERE, null, ex); }
        
        return data;
    }
}