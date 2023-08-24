package service.impl;

import entity.Planet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import service.CrudService;

import util.HibernateUtil;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class PlanetCrudServiceImpl implements CrudService<Planet, String> {

    private final SessionFactory sessionFactory;

    public PlanetCrudServiceImpl() {
        this.sessionFactory = HibernateUtil.getInstance().getSessionFactory();
    }

    @Override
    public Planet create(Planet entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
            return entity;
        }
    }

    @Override
    public Planet update(Planet entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(entity);
            session.getTransaction().commit();
            return entity;
        }
    }

    @Override
    public void delete(String entityId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Planet entity = session.get(Planet.class, entityId);
            if (entity != null) {
                session.remove(entity);
            }
            session.getTransaction().commit();
        }
    }

    @Override
    public Planet getById(String entityId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Planet.class, entityId);
        }
    }

    @Override
    public List<Planet> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Planet> query = builder.createQuery(Planet.class);
            Root<Planet> root = query.from(Planet.class);
            query.select(root);
            return session.createQuery(query).getResultList();
        }
    }
}
