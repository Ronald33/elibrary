package com.ronald.elibrary.api.editor;

import com.ronald.elibrary.entity.Editor;
import com.ronald.elibrary.model.editor.EditorModel;
import com.ronald.elibrary.helper.JSON;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ApiEditorLogin", urlPatterns =
    {
        "/api/editor/login"
})
public class ApiEditorLogin extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
//        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");
        
        Editor obj = new Editor();
        obj.setUsuario(usuario);
        obj.setContrasenha(contrasena);
        
        Editor result = EditorModel.exist(obj);
        
        if(result == null) { JSON.errorMessage(response, "Usuario desconocido"); }
        else
        {
            HttpSession session = request.getSession(false);
            session.setAttribute("id", result.getId());
            session.setAttribute("nombres", result.getNombres());
            JSON.successRedirect(response, "editor/dashboard");
        }
    }
}
