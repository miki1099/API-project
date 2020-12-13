package dao;

import model.Criminal;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import java.util.List;

public class CriminalDao {

    public void insert(Criminal criminal){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(criminal);
        session.getTransaction().commit();
        session.close();
    }

    public List<Criminal> getAll(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List resultList = session.createQuery("select c from Criminal c").getResultList();
        session.close();
        return resultList;
    }

    public boolean isCriminalInDataBase(Criminal criminal){
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.createQuery("select c from Criminal c where " +
                    "c.uid =: uid and c.title =:title")
                    .setParameter("uid", criminal.getUid())
                    .setParameter("title", criminal.getTitle())
                    .getSingleResult();
            session.close();
            return true;
        }catch (NoResultException e){
            return false;
        } catch (NonUniqueResultException e){
            return true;
        }
    }

    public Criminal getRandomCriminal(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criminal criminal = (Criminal) session.createQuery("from Criminal order by rand()")
                .setMaxResults(1).getSingleResult();
        session.close();
        return criminal;
    }

    public Criminal getYoungestCriminal(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criminal criminal = (Criminal) session.createQuery("select c from Criminal c " +
                "where c.age_min > 0" +
                "order by c.age_min")
                .setMaxResults(1).getSingleResult();
        session.close();
        return criminal;
    }

    public Criminal getOldestCriminal(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criminal criminal = (Criminal) session.createQuery("select c from Criminal c " +
                "where c.age_min > 0" +
                "order by c.age_min desc")
                .setMaxResults(1).getSingleResult();
        session.close();
        return criminal;
    }

}
