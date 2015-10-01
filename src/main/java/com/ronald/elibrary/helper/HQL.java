package com.ronald.elibrary.helper;

import com.ronald.elibrary.entity.NewHibernateUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public class HQL
{
    private String hql;
    private Session session;
    private Transaction tx;
    private Query query;
    private Map<String, Object> parameters;

    public HQL() { this.parameters = new HashMap<String, Object>(); }
    
    public Object getObject()
    {
        this.begin();
        this.setQuery();
        Object result = this.query.uniqueResult();
        this.close();
        return result;
    }
    
    public List getList()
    {
        this.begin();
        this.setQuery();
        List result = this.query.list();
        this.close();
        return result;
    }
    
    public static List queryList(String hql)
    {
        HQL q = new HQL();
        q.setHql(hql);
        return q.getList();
    }

    public static Object queryObject(String hql)
    {
        HQL q = new HQL();
        q.setHql(hql);
        return q.getObject();
    }
    
    public static void save(Object obj)
    {
        HQL q = new HQL();
        q.begin();
        q.getSession().saveOrUpdate(obj);
        q.close();
    }
    
    public static void delete(Object obj)
    {
        HQL q = new HQL();
        q.begin();
        q.getSession().delete(obj);
        q.close();
    }

    public String getHql() { return hql; }
    public void setHql(String hql) { this.hql = hql; }
    public void addParameter(String key, Object value) { this.parameters.put(key, value); }
    private Session getSession() { return session; }    
    
    private void begin()
    {
        this.session = NewHibernateUtil.getSessionFactory().openSession();
        this.tx = this.session.beginTransaction();
    }
    
    private void close() { this.tx.commit(); this.session.close(); }
    
    private void setQuery()
    {
        this.query = this.session.createQuery(this.hql);
        
        for (Map.Entry<String, Object> entry : this.parameters.entrySet())
        {
            this.query.setParameter(entry.getKey(), entry.getValue());
        }
    }

    public Map<String, Object> getParameters() { return parameters; }
    public void setParameters(Map<String, Object> parameters) { this.parameters = parameters; }
}