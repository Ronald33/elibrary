package com.ronald.elibrary.api.usuario;

import com.ronald.elibrary.entity.Usuario;
import com.ronald.elibrary.model.usuario.UsuarioModel;
import com.ronald.elibrary.helper.JSON;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ApiUsuarioLogin", urlPatterns =
    {
        "/api/usuario/login"
})
public class ApiUsuarioLogin extends HttpServlet
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
        
        Usuario obj = new Usuario();
        obj.setUsuario(usuario);
        obj.setContrasenha(contrasena);
        
        Usuario result = UsuarioModel.exist(obj);
        
        if(result == null) { JSON.errorMessage(response, "Usuario desconocido"); }
        else
        {
            HttpSession session = request.getSession(false);
            session.setAttribute("id", result.getId());
            session.setAttribute("nombres", result.getNombres());
            JSON.successRedirect(response, "index.jsp");
        }
    }
}
