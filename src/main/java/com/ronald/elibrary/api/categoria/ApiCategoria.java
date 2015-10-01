package com.ronald.elibrary.api.categoria;

import com.ronald.elibrary.entity.Categoria;
import com.ronald.elibrary.helper.Success;
import com.ronald.elibrary.model.categoria.CategoriaModel;
import java.util.List;
import javax.servlet.http.HttpServlet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("categoria")
public class ApiCategoria extends HttpServlet
{
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public @ResponseBody Object get(@PathVariable Integer id) { return CategoriaModel.get(id); }
    
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List getAll() { return CategoriaModel.getAll(); }
    
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Object save(@RequestBody Categoria obj) { CategoriaModel.save(obj); return Success.Status(); }
    
    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody Object update(@RequestBody Categoria obj) { CategoriaModel.save(obj); return Success.Status(); }
    
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public @ResponseBody Object delete(@PathVariable Integer id) { CategoriaModel.delete(id); return Success.Status(); }
}