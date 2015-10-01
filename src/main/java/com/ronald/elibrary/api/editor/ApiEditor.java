package com.ronald.elibrary.api.editor;

import com.ronald.elibrary.entity.Editor;
import com.ronald.elibrary.helper.Success;
import com.ronald.elibrary.model.editor.EditorModel;
import java.util.List;
import javax.servlet.http.HttpServlet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("editor")
public class ApiEditor extends HttpServlet
{
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public @ResponseBody Object get(@PathVariable Integer id) { return EditorModel.get(id); }
    
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List getAll() { return EditorModel.getAll(); }
    
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Object save(@RequestBody Editor obj) { EditorModel.save(obj); return Success.Redirect("editor/login"); }
    
    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody Object update(@RequestBody Editor obj) { EditorModel.save(obj); return Success.Status(); }
    
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public @ResponseBody Object delete(@PathVariable Integer id) { EditorModel.delete(id); return Success.Status(); }
}