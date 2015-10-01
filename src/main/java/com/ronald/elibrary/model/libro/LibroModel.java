package com.ronald.elibrary.model.libro;

import com.ronald.elibrary.entity.Libro;
import com.ronald.elibrary.entity.NewHibernateUtil;
import com.ronald.elibrary.helper.HQL;
import java.util.List;
import org.hibernate.Session;

public class LibroModel
{
    public static void save(Libro obj) { HQL.save(obj); }
    public static void delete(Integer id) { Libro obj = getObjById(id); HQL.delete(obj); }
    
    public static Libro getObjById(int id)
    {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Libro result = (Libro)session.get(Libro.class, id);
        session.close();
        return result;
    }
    
    public static List getAll()
    {
        String hql = "  SELECT  new map"
                + "             ( "
                + "             l.id AS id, "
                + "             l.titulo AS titulo, "
                + "             l.descripcion AS descripcion, "
                + "             l.descargas AS descargas, "
                + "             l.puntuacion AS puntuacion, "
                + "             l.portada AS portada, "
                + "             l.link AS link, "
                + "             c.id AS cate_id, "
                + "             c.nombre AS cate_nombre, "
                + "             e.id AS edit_id, "
                + "             e.nombre AS edit_nombre "
                + "             ) "
                + "     FROM    Libro AS l "
                + "     JOIN    l.categoria AS c "
                + "     JOIN    l.editorial AS e "
                + "     ORDER BY l.id DESC";

        return HQL.queryList(hql);
    }
    
    public static Object get(Integer id)
    {
        String hql = "  SELECT  new map"
                + "             ( "
                + "             l.id AS id, "
                + "             l.titulo AS titulo, "
                + "             l.descripcion AS descripcion, "
                + "             l.descargas AS descargas, "
                + "             l.puntuacion AS puntuacion, "
                + "             l.portada AS portada, "
                + "             l.link AS link, "
                + "             c.id AS cate_id, "
                + "             c.nombre AS cate_nombre, "
                + "             e.id AS edit_id, "
                + "             e.nombre AS edit_nombre "
                + "             ) "
                + "     FROM    Libro AS l "
                + "     JOIN    l.categoria AS c "
                + "     JOIN    l.editorial AS e "
                + "     WHERE   l.id = :id";

        HQL q = new HQL();
        q.setHql(hql);
        q.addParameter("id", id);
        return q.getObject();
    }
    
    public static List getByEditor(Integer editor_id)
    {
        String hql = "  SELECT  new map"
                + "             ( "
                + "             l.titulo AS titulo, "
                + "             l.descripcion AS descripcion, "
                + "             l.portada AS portada, "
                + "             l.link AS link, "
                + "             c.nombre AS categoria "
                + "             ) "
                + "     FROM    Libro AS l "
                + "     JOIN    l.categoria AS c "
                + "     WHERE   l.editor.id = :editor_id "
                + "     ORDER BY l.id DESC";
        
        HQL q = new HQL();
        q.setHql(hql);
        q.addParameter("editor_id", editor_id);
        return q.getList();
    }
    
    public static Long count()
    {
        String hql = "SELECT COUNT(*) FROM Libro";
        return (Long) HQL.queryObject(hql);
    }
}