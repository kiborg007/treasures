package com.project.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.project.entities.Book;
import com.project.utils.HibernateUtil;

public class BookDao {

	SessionFactory sessionFactory = HibernateUtil.getSessionFactory() ;
	
	public Book getBookByName(String name){
        Book book = null;
        Session session = null;
        
        try{
            session = sessionFactory.openSession() ;
            session.beginTransaction() ;
            book = (Book)session.createQuery("from Book p where p.name = :ID").setParameter("ID", name).uniqueResult();
            session.getTransaction().commit();
        }catch(Exception ex){
            if(session != null){
                session.getTransaction().rollback();
            }
        }finally{
            if(session != null){
                session.close();
            }
        }
        
        return book ;
    }
	
	public List<Book> getAllBooks(){
        List<Book> books = null;
        Session session = null;
        
        try{
            session = sessionFactory.openSession() ;
            session.beginTransaction() ;
            books = session.createQuery("from Book p").list();
            session.getTransaction().commit();
        }catch(Exception ex){
            if(session != null){
                session.getTransaction().rollback();
            }
        }finally{
            if(session != null){
                session.close();
            }
        }
        
        return books ;
    }
}
