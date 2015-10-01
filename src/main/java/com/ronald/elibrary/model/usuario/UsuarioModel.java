package com.ronald.elibrary.model.usuario;

import com.ronald.elibrary.entity.Usuario;
import com.ronald.elibrary.entity.NewHibernateUtil;
import com.ronald.elibrary.helper.HQL;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

public abstract class UsuarioModel
{
    public static void save(Usuario obj) { HQL.save(obj); }

    public static void delete(Integer id) { Usuario obj = getObjById(id); HQL.delete(obj); }

    public static Usuario getObjById(int id)
    {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Usuario result = (Usuario) session.get(Usuario.class, id);
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
                + "     FROM    Usuario AS c";

        return HQL.queryList(hql);
    }

    public static Object get(Integer id)
    {
        String hql = "  SELECT  new map"
                + "             ( "
                + "             c.id AS id, "
                + "             c.nombre AS nombre "
                + "             ) "
                + "     FROM    Usuario AS c "
                + "     WHERE   c.id = :id";

        HQL q = new HQL();
        q.setHql(hql);
        q.addParameter("id", id);
        return q.getObject();
    }

//    public static Usuario exist(Usuario obj)
//    {
//        Example e = Example.create(obj);
//
//        Session session = NewHibernateUtil.getSessionFactory().openSession();
//        Transaction tx = session.beginTransaction();
//        Usuario result = (Usuario) session.createCriteria(Usuario.class).add(e);
//        tx.commit();
//        session.close();
//        return result;
//    }
    
    public static Usuario exist(Usuario obj)
    {
        Example e = Example.create(obj);
        return (Usuario) NewHibernateUtil.getSessionFactory().openSession().createCriteria(Usuario.class).add(e).uniqueResult();
    }
}
