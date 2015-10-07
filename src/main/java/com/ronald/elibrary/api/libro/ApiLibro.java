package com.ronald.elibrary.api.libro;

import com.google.common.base.Joiner;
import com.ronald.elibrary.entity.Autor;
import com.ronald.elibrary.entity.Categoria;
import com.ronald.elibrary.entity.Editor;
import com.ronald.elibrary.entity.Editorial;
import com.ronald.elibrary.entity.Libro;
import com.ronald.elibrary.helper.Helper;
import com.ronald.elibrary.model.categoria.CategoriaModel;
import com.ronald.elibrary.model.editor.EditorModel;
import com.ronald.elibrary.model.editorial.EditorialModel;
import com.ronald.elibrary.model.libro.LibroModel;
import com.ronald.elibrary.helper.JSON;
import com.ronald.elibrary.model.autor.AutorModel;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(name = "ApiLibro", urlPatterns =
    {
        "/api/libro"
})
@MultipartConfig
public class ApiLibro extends HttpServlet
{
    private final String folder = "uploads";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            Integer editor_id = 0;
            
            if(request.getSession(false).getAttribute("id") != null)
            {
                editor_id = (Integer) request.getSession(false).getAttribute("id");
            }
                
//            editor_id = (Integer) request.getSession(false).getAttribute("id");
            if(editor_id == 0)
            {
                List reg = LibroModel.getAll();
                JSON.showObject(response, reg);
            }
            else
            {
                if(request.getParameter("id") == null)
                {
                    List reg = LibroModel.getByEditor(editor_id);
                    JSON.showObject(response, reg);
                }
                else
                {
                    Integer libro_id = Integer.parseInt(request.getParameter("id"));
                    Object reg = LibroModel.get(libro_id);
                    JSON.showObject(response, reg);
                }
            }
        }
        catch(IOException e) { JSON.errorMessage(response, Helper.getMessageGlobal(e)); }
        catch(NumberFormatException e) { JSON.errorMessage(response, Helper.getMessageGlobal(e)); }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {   
        try
        {
            String nombre = "", descripcion = "", key = "", portada = "", pdf = "", root = getServletContext().getRealPath("/"), s_autores = "", s_tags = "";
            List<String> autores = new ArrayList<String>();
            List<String> tags = new ArrayList<String>();
            int count_libros = (int) (LibroModel.count() + 1), editor_id = 0, categoria_id = 0, editorial_id = 0;

            File path = new File(root + this.folder);
            if(!path.exists())
            {
                path.mkdirs();
            }

            // Guardamos todos los inputs
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            for(FileItem item : items) // Los recorremos
            {
                if(item.isFormField())
                {
                    if(item.getFieldName().equals("nombre"))
                    {
                        nombre = item.getString();
                    }
                    else if(item.getFieldName().equals("descripcion"))
                    {
                        descripcion = item.getString();
                    }
                    else if(item.getFieldName().equals("editor"))
                    {
                        key = item.getString() + Integer.toString(count_libros);
                        editor_id = Integer.parseInt(item.getString());
                    }
                    else if(item.getFieldName().equals("categoria"))
                    {
                        categoria_id = Integer.parseInt(item.getString());
                    }
                    else if(item.getFieldName().equals("editorial"))
                    {
                        editorial_id = Integer.parseInt(item.getString());
                    }
                    else if(item.getFieldName().equals("tags[]"))
                    {
                        tags.add(item.getString());
                    }
                    else if(item.getFieldName().equals("autores[]"))
                    {
                        autores.add(item.getString());
                    }
                }
                else
                {
                    String file = "";
                    if(item.getFieldName().equals("portada"))
                    {
                        portada = this.folder + "/portada_" + key + ".jpg";
                        file = root + portada;
                    }
                    else if(item.getFieldName().equals("pdf"))
                    {
                        pdf = this.folder + "/pdf_" + key + ".pdf";
                        file = root + pdf;
                    }
                    if(!"".equals(file))
                    {
                        File storeFile = new File(file);
                        item.write(storeFile);
                    }
                }
            }
            
            s_autores = Joiner.on(",").join(autores);
            s_tags = Joiner.on(",").join(tags);

            if(!"".equals(portada) && !"".equals(pdf))
            {
                Editor editor = EditorModel.getObjById(editor_id);
                Categoria categoria = CategoriaModel.getObjById(categoria_id);
                Editorial editorial = EditorialModel.getObjById(editorial_id);

                Libro libro = new Libro();
                libro.setTitulo(nombre);
                libro.setDescripcion(descripcion);
                libro.setPortada(portada);
                libro.setLink(pdf);
                libro.setTags(s_tags);
                libro.setCategoria(categoria);
                libro.setEditor(editor);
                libro.setEditorial(editorial);
                
                // Seteando los autores
                Set<Autor> mis_autores = new HashSet<Autor>();
                for (String s_autor_id : autores)
                {
                    int autor_id = Integer.parseInt(s_autor_id);
                    Autor autor = AutorModel.getObjById(autor_id);
                    mis_autores.add(autor);
		}
                libro.setAutors(mis_autores);
                // Fin del seteo de autores
                
//                LibroModel.save(libro);
                response.sendRedirect("/elibrary/editor/dashboard");
            }
            else
            {
                response.sendRedirect("editor/dashboard#/nuevoLibro");
            }
        }
        catch(FileUploadException e)
        {
            throw new ServletException("Cannot parse multipart request.", e);
        }
        catch(Exception ex)
        {
            Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
