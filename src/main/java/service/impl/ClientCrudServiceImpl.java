package service.impl;

import entity.Client;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import service.CrudService;

import util.HibernateUtil;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
public class ClientCrudServiceImpl implements CrudService<Client, Long> {

    private final SessionFactory sessionFactory;

    public ClientCrudServiceImpl() {
        this.sessionFactory = HibernateUtil.getInstance().getSessionFactory();
    }

    @Override
    public Client create(Client entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
            return entity;
        }
    }

    @Override
    public Client update(Client entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(entity);
            session.getTransaction().commit();
            return entity;
        }
    }

    @Override
    public void delete(Long entityId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Client entity = session.get(Client.class, entityId);
            if (entity != null) {
                session.remove(entity);

            }
            session.getTransaction().commit();
        }
    }

    @Override
    public Client getById(Long entityId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Client.class, entityId);
        }
    }

    @Override
    public List<Client> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Client> query = builder.createQuery(Client.class);
            Root<Client> root = query.from(Client.class);
            query.select(root);
            return session.createQuery(query).getResultList();
        }
    }
}


