package com.ronald.elibrary.model.comentario;

import com.ronald.elibrary.entity.Comentario;
import com.ronald.elibrary.entity.NewHibernateUtil;
import com.ronald.elibrary.helper.HQL;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

public abstract class ComentarioModel
{
    public static void save(Comentario obj) { HQL.save(obj); }

    public static void delete(Integer id) { Comentario obj = getObjById(id); HQL.delete(obj); }

    public static Comentario getObjById(int id)
    {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Comentario result = (Comentario) session.get(Comentario.class, id);
        session.close();
        return result;
    }

    public static List getAll()
    {
        String hql = "  SELECT  new map"
                + "             ( "
                + "             c.id AS id, "
                + "             c.texto AS texto "
                + "             ) "
                + "     FROM    Comentario AS c";

        return HQL.queryList(hql);
    }

    public static Object get(Integer id)
    {
        String hql = "  SELECT  new map"
                + "             ( "
                + "             c.id AS id, "
                + "             c.texto AS texto "
                + "             ) "
                + "     FROM    Comentario AS c "
                + "     WHERE   c.id = :id";

        HQL q = new HQL();
        q.setHql(hql);
        q.addParameter("id", id);
        return q.getObject();
    }

    public static Comentario exist(Comentario obj)
    {
        Example e = Example.create(obj);

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Comentario result = (Comentario) session.createCriteria(Comentario.class).add(e);
        tx.commit();
        session.close();
        return result;
    }
    
    public static List getByLibro(Integer libro_id)
    {
        String hql = "  SELECT  new map"
                + "             ( "
                + "             c.id AS id, "
                + "             c.texto AS texto, "
                + "             u.nombres AS usuario "
                + "             ) "
                + "     FROM    Comentario AS c "
                + "     JOIN    c.usuario AS u "
                + "     WHERE   c.libro.id = :libro_id "
                + "     ORDER BY c.id DESC";

        HQL q = new HQL();
        q.setHql(hql);
        q.addParameter("libro_id", libro_id);
        return q.getList();
    }
}
