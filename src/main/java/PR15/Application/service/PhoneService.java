package PR15.Application.service;

import PR15.Application.model.Manufacture;
import PR15.Application.model.Phone;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.UUID;

@Service
public class PhoneService {
    @Autowired
    private final SessionFactory sessionFactory;

    private Session session;

    private CriteriaBuilder builder;
    private CriteriaQuery<Phone> phoneCriteriaQuery;
    private Root<Phone> root;

    public PhoneService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @PostConstruct
    public void init() {
        session = sessionFactory.openSession();
        builder = session.getCriteriaBuilder();
        phoneCriteriaQuery = builder.createQuery(Phone.class);
        root = phoneCriteriaQuery.from(Phone.class);
    }

    @PreDestroy
    public void unSession() {
        session.close();
    }

    public void addPhone(Phone phone) {
        session.beginTransaction();
        session.saveOrUpdate(phone);
        session.getTransaction().commit();
    }

    public List<Phone> getPhone() {
        return session.createQuery("select p from Phone p", Phone.class).list();
    }

    public Manufacture getManufacture(UUID id) {
        return session.createQuery("from Phone where id = :id", Phone.class)
                .setParameter("id",id).getSingleResult().getManufacture();
    }

    public void deletePhones(Phone phone) {
        session.beginTransaction();

        List<Phone> query = session.createQuery("select p from Phone p where p.id = '" + phone.getId() + "'", Phone.class).list();
        for (Phone p : query) {
            session.delete(p);
        }

        session.getTransaction().commit();
    }

    public List<Phone> getByName() {
        phoneCriteriaQuery.select(root).orderBy(builder.asc(root.get("name")));
        Query<Phone> query = session.createQuery(phoneCriteriaQuery);
        return query.getResultList();
    }

    public List<Phone> getByYear() {
        phoneCriteriaQuery.select(root).orderBy(builder.asc(root.get("creationYear")));
        Query<Phone> query = session.createQuery(phoneCriteriaQuery);
        return query.getResultList();
    }

    public void deletePhone(UUID id) {
        session.beginTransaction();

        Phone t = session.load(Phone.class, id);
        session.delete(t);

        session.getTransaction().commit();
    }
//    public void delete(Phone phone) {
//        session.beginTransaction();
//
//        List<Phone> query = session.createQuery("select s from Phone where name = '" +
//                phone.getName() + "' and creationYear = '" + phone.getCreationYear() + "'", Phone.class).list();
//        for (Phone lvl: query){
//            session.delete(lvl);
//        }
//
//        session.getTransaction().commit();
//    }
}
