package com.ronald.elibrary.api.libro;

import com.ronald.elibrary.entity.Libro;
import com.ronald.elibrary.helper.Success;
import com.ronald.elibrary.model.comentario.ComentarioModel;
import com.ronald.elibrary.model.libro.LibroModel;
import java.util.List;
import javax.servlet.http.HttpServlet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("alibro")
public class ApiALibro extends HttpServlet
{
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public @ResponseBody Object get(@PathVariable Integer id) { return LibroModel.get(id); }
    
    @RequestMapping(value="/{id}/comentario", method = RequestMethod.GET)
    public @ResponseBody Object getComentarios(@PathVariable Integer id) { return ComentarioModel.getByLibro(id); }
    
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List getAll() { return LibroModel.getAll(); }
    
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Object save(@RequestBody Libro obj) { LibroModel.save(obj); return Success.Status(); }
    
    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody Object update(@RequestBody Libro obj) { LibroModel.save(obj); return Success.Status(); }
    
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public @ResponseBody Object delete(@PathVariable Integer id) { LibroModel.delete(id); return Success.Status(); }
}