package PR15.Application.service;

import PR15.Application.model.Manufacture;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.hibernate.query.Query;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.UUID;

@Service
public class ManufactureService {
    @Autowired
    private final SessionFactory sessionFactory;

    private Session session;
    private CriteriaBuilder builder;
    private CriteriaQuery<Manufacture> manufactureCriteriaQuery;
    private Root<Manufacture> root;

    public ManufactureService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @PostConstruct
    public void init() {
        session = sessionFactory.openSession();
        builder = session.getCriteriaBuilder();
        manufactureCriteriaQuery = builder.createQuery(Manufacture.class);
        root = manufactureCriteriaQuery.from(Manufacture.class);
    }

    @PreDestroy
    public void unSession() {
        session.close();
    }

    public void addManufacture(Manufacture manufacture) {
        session.beginTransaction();
        session.saveOrUpdate(manufacture);
        session.getTransaction().commit();
    }

    public List<Manufacture> getManufacture() {
        return session.createQuery("select u from Manufacture u", Manufacture.class).list();
    }

    public Manufacture getManufacture(UUID id) {
        return session.createQuery("select p from Manufacture u where u.id = p.id = '" + id + "'", Manufacture.class).getSingleResult();
    }
    public List<Manufacture> getByName() {
        manufactureCriteriaQuery.select(root).orderBy(builder.asc(root.get("name")));
        Query<Manufacture> query = session.createQuery(manufactureCriteriaQuery);
        return query.getResultList();
    }

    public List<Manufacture> getByAddress() {
        manufactureCriteriaQuery.select(root).orderBy(builder.asc(root.get("address")));
        Query<Manufacture> query = session.createQuery(manufactureCriteriaQuery);
        return query.getResultList();
    }
    public void deleteManufacture(UUID id) {
        session.beginTransaction();

        Manufacture t = session.load(Manufacture.class, id);
        session.delete(t);

        session.getTransaction().commit();
    }
}
