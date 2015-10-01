package com.ronald.elibrary.model.editor;

import com.ronald.elibrary.entity.Editor;
import com.ronald.elibrary.entity.NewHibernateUtil;
import com.ronald.elibrary.helper.HQL;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

public abstract class EditorModel
{
    public static Editor exist(Editor obj)
    {
        Example e = Example.create(obj);
        return (Editor) NewHibernateUtil.getSessionFactory().openSession().createCriteria(Editor.class).add(e).uniqueResult();
    }
    
    public static void save(Editor obj) { HQL.save(obj); }
    public static void delete(Integer id) { Editor obj = getObjById(id); HQL.delete(obj); }
    
    public static Editor getObjById(int id)
    {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Editor result = (Editor)session.get(Editor.class, id);
        session.close();
        return result;
    }

    public static List getAll()
    {
        String hql = "  SELECT  new map"
                + "             ( "
                + "             e.id AS id, "
                + "             e.nombres AS nombres, "
                + "             e.apellidos AS apellidos "
                + "             ) "
                + "     FROM    Editor AS e";

        return HQL.queryList(hql);
    }
    
    public static Object get(Integer id)
    {
        String hql = "  SELECT  new map"
                + "             ( "
                + "             e.id AS id, "
                + "             e.nombres AS nombres, "
                + "             e.apellidos AS apellidos "
                + "             ) "
                + "     FROM    Editor AS e"
                + "     WHERE   e.id = :id";

        HQL q = new HQL();
        q.setHql(hql);
        q.addParameter("id", id);
        return q.getObject();
    }
    
    public static List getLibros(Integer editor_id)
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
}