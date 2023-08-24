package service.impl;

import entity.Ticket;

import service.CrudService;

import util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class TicketCrudServiceImpl implements CrudService<Ticket, Long> {

    private final SessionFactory sessionFactory;

    public TicketCrudServiceImpl() {
        this.sessionFactory = HibernateUtil.getInstance().getSessionFactory();
    }

    @Override
    public Ticket create(Ticket entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            if (entity.getClient() == null || entity.getFromPlanet() == null || entity.getToPlanet() == null) {
                throw new IllegalArgumentException("The ticket must contain the client and the planet");
            }

            session.persist(entity);
            session.getTransaction().commit();
            return entity;

        }
    }

    @Override
    public Ticket update(Ticket entity) {
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
            Ticket entity = session.get(Ticket.class, entityId);
            if (entity != null) {
                session.remove(entity);
            }
            session.getTransaction().commit();
        }
    }

    @Override
    public Ticket getById(Long entityId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Ticket.class, entityId);
        }
    }

    @Override
    public List<Ticket> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Ticket> query = builder.createQuery(Ticket.class);
            Root<Ticket> root = query.from(Ticket.class);
            query.select(root);
            return session.createQuery(query).getResultList();
        }
    }

}
