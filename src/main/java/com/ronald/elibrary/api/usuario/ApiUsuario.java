package com.ronald.elibrary.api.usuario;

import com.ronald.elibrary.entity.Usuario;
import com.ronald.elibrary.helper.Success;
import com.ronald.elibrary.model.usuario.UsuarioModel;
import java.util.List;
import javax.servlet.http.HttpServlet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("usuario")
public class ApiUsuario extends HttpServlet
{
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public @ResponseBody Object get(@PathVariable Integer id) { return UsuarioModel.get(id); }
    
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List getAll() { return UsuarioModel.getAll(); }
    
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Object save(@RequestBody Usuario obj) { UsuarioModel.save(obj); return Success.Redirect("usuario/login"); }
    
    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody Object update(@RequestBody Usuario obj) { UsuarioModel.save(obj); return Success.Status(); }
    
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public @ResponseBody Object delete(@PathVariable Integer id) { UsuarioModel.delete(id); return Success.Status(); }
}