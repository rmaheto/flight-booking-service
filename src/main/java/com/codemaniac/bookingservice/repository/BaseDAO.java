package com.codemaniac.bookingservice.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BaseDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public BaseDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    protected SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
