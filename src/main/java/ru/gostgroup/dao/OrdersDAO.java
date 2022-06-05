package ru.gostgroup.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gostgroup.models.OrdersModel;
import ru.gostgroup.pojo.AutoPersonAndDep;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Repository
public class OrdersDAO implements IOrdersDAO {

    private final SessionFactory sessionFactory;
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    @Autowired
    public OrdersDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    @Transactional
    public List<OrdersModel> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from OrdersModel order by orderId", OrdersModel.class).getResultList();
    }

    @Override
    @Transactional
    public OrdersModel show(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(OrdersModel.class,id);
    }

    @Override
    @Transactional
    public void update(long id, OrdersModel updatedOrder) {

    }


    @Override
    @Transactional
    public void save(OrdersModel order) {
        Session session = sessionFactory.getCurrentSession();

//        Query query = session.createNativeQuery("select e.dep_id as \"depId\", e.id as \"empId\" from production p\n" +
//                "join employees e on p.dep_id = e.dep_id\n" +
//                "where p.id = :paramId order by random() limit 1");
//        query.setParameter("paramId",order.getProdId());
//
//        session.createNamedQuery("MyDto", MyDto.class)
//
//        AutoPersonAndDep apad = (AutoPersonAndDep)query.unwrap(org.hibernate.query.NativeQuery.class)
//                .setResultTransformer(Transformers.aliasToBean(AutoPersonAndDep.class))
//                .getSingleResult();

        AutoPersonAndDep apad = session.createNamedQuery("AutoPersonAndDepDTO",AutoPersonAndDep.class).
                setParameter("paramId",order.getProdId()).getSingleResult();

        System.out.println(apad + "Мой класс" + order.getOrderId());
        order.setCreateDate(LocalDateTime.now());
        order.setDepId(apad.getDepId());
        order.setEmpId(apad.getEmpId());
        System.out.println(order);
        session.save(order);
    }


    @Override
    @Transactional
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(OrdersModel.class,id));
    }

    @Override
    @Transactional
    public List<OrdersModel> showByDep(long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from OrdersModel where depId = :paramDepId order by orderId",OrdersModel.class);
        query.setParameter("paramDepId", id);
        return query.getResultList();
    }
//
    @Override
    @Transactional
    public List<OrdersModel> showByEmp(long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from OrdersModel where empId = :paramEmpId order by orderId",OrdersModel.class);
        query.setParameter("paramEmpId", id);
        return query.getResultList();
    }


    @Override
    @Transactional
    public List<OrdersModel> unFinishedOrders() {
        LocalDateTime dateNow = LocalDateTime.now();
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from OrdersModel where deadLineDate > :paramEmpId order by orderId",OrdersModel.class);
        query.setParameter("paramEmpId", dateNow);
        return query.getResultList();
    }

}
