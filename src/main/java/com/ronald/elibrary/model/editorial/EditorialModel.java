package com.ronald.elibrary.model.editorial;

import com.ronald.elibrary.entity.Editorial;
import com.ronald.elibrary.entity.NewHibernateUtil;
import com.ronald.elibrary.helper.HQL;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

public abstract class EditorialModel
{
    public static void save(Editorial obj) { HQL.save(obj); }

    public static void delete(Integer id) { Editorial obj = getObjById(id); HQL.delete(obj); }

    public static Editorial getObjById(int id)
    {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Editorial result = (Editorial) session.get(Editorial.class, id);
        session.close();
        return result;
    }

    public static List getAll()
    {
        String hql = "  SELECT  new map"
                + "             ( "
                + "             c.id AS id, "
                + "             c.nombre AS nombre "
                + "             ) "
                + "     FROM    Editorial AS c";

        return HQL.queryList(hql);
    }

    public static Object get(Integer id)
    {
        String hql = "  SELECT  new map"
                + "             ( "
                + "             c.id AS id, "
                + "             c.nombre AS nombre "
                + "             ) "
                + "     FROM    Editorial AS c "
                + "     WHERE   c.id = :id";

        HQL q = new HQL();
        q.setHql(hql);
        q.addParameter("id", id);
        return q.getObject();
    }

    public static Editorial exist(Editorial obj)
    {
        Example e = Example.create(obj);

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Editorial result = (Editorial) session.createCriteria(Editorial.class).add(e);
        tx.commit();
        session.close();
        return result;
    }
}
