package com.ronald.elibrary.api.editor;

import com.ronald.elibrary.model.libro.LibroModel;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("editorLibro")
public class ApiEditorLibro extends HttpServlet
{
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List getLibros(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session = request.getSession(true);
        Integer id = (Integer) session.getAttribute("id");
        return LibroModel.getByEditor(id);
    }
}