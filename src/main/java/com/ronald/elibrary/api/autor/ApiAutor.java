package com.ronald.elibrary.api.autor;

import com.ronald.elibrary.entity.Autor;
import com.ronald.elibrary.helper.Helper;
import com.ronald.elibrary.helper.JSON;
import com.ronald.elibrary.helper.Success;
import com.ronald.elibrary.model.autor.AutorModel;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//@WebServlet(name = "ApiAutor", urlPatterns =
//    {
//        "/api/autor"
//})
@Controller
@RequestMapping("autor")
public class ApiAutor extends HttpServlet
{
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public @ResponseBody Object get(@PathVariable Integer id) { return AutorModel.get(id); }
    
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List getAll() { return AutorModel.getAll(); }
    
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Object save(@RequestBody Autor obj) { AutorModel.save(obj); return Success.Status(); }
    
    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody Object update(@RequestBody Autor obj) { AutorModel.save(obj); return Success.Status(); }
    
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public @ResponseBody Object delete(@PathVariable Integer id) { AutorModel.delete(id); return Success.Status(); }
}