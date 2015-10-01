package com.ronald.elibrary.model.autor;

import com.ronald.elibrary.entity.Autor;
import com.ronald.elibrary.entity.NewHibernateUtil;
import com.ronald.elibrary.helper.HQL;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

public abstract class AutorModel
{
    public static void save(Autor obj) { HQL.save(obj); }
    public static void delete(Integer id) { Autor obj = getObjById(id); HQL.delete(obj); }
    
    public static Autor exist(Autor obj)
    {
        Example e = Example.create(obj);
        return (Autor) NewHibernateUtil.getSessionFactory().openSession().createCriteria(Autor.class).add(e).uniqueResult();
    }
    
    public static Autor getObjById(int id)
    {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Autor result = (Autor)session.get(Autor.class, id);
        session.close();
        return result;
    }
    
    public static List getAll()
    {
        String hql = "  SELECT  new map"
                + "             ( "
                + "             a.id AS id, "
                + "             a.nombres AS nombres, "
                + "             a.apellidos AS apellidos "
                + "             ) "
                + "     FROM    Autor AS a";

        return HQL.queryList(hql);
    }
    
    public static Object get(Integer id)
    {
        String hql = "  SELECT  new map"
                + "             ( "
                + "             a.id AS id, "
                + "             a.nombres AS nombres, "
                + "             a.apellidos AS apellidos "
                + "             ) "
                + "     FROM    Autor AS a"
                + "     WHERE   c.id = :id";

        HQL q = new HQL();
        q.setHql(hql);
        q.addParameter("id", id);
        return q.getObject();
    }
}