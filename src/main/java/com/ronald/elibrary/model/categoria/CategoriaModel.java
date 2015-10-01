package com.ronald.elibrary.model.categoria;

import com.ronald.elibrary.entity.Categoria;
import com.ronald.elibrary.entity.NewHibernateUtil;
import com.ronald.elibrary.helper.HQL;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

public abstract class CategoriaModel
{
    public static void save(Categoria obj) { HQL.save(obj); }

    public static void delete(Integer id) { Categoria obj = getObjById(id); HQL.delete(obj); }

    public static Categoria getObjById(int id)
    {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Categoria result = (Categoria) session.get(Categoria.class, id);
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
                + "     FROM    Categoria AS c";

        return HQL.queryList(hql);
    }

    public static Object get(Integer id)
    {
        String hql = "  SELECT  new map"
                + "             ( "
                + "             c.id AS id, "
                + "             c.nombre AS nombre "
                + "             ) "
                + "     FROM    Categoria AS c "
                + "     WHERE   c.id = :id";

        HQL q = new HQL();
        q.setHql(hql);
        q.addParameter("id", id);
        return q.getObject();
    }

    public static Categoria exist(Categoria obj)
    {
        Example e = Example.create(obj);

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Categoria result = (Categoria) session.createCriteria(Categoria.class).add(e);
        tx.commit();
        session.close();
        return result;
    }
}
