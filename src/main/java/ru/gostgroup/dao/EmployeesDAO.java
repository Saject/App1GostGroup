package ru.gostgroup.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.gostgroup.models.EmployeesModel;
import ru.gostgroup.models.OrdersModel;
import ru.gostgroup.pojo.AutoPersonAndDep;


import java.util.List;

@Repository
public class EmployeesDAO implements IEmployeesDAO {

    //private final JdbcTemplate jdbcTemplate;
    private final SessionFactory sessionFactory;

    @Autowired
    public EmployeesDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }


    @Override
    @Transactional(readOnly = true)
    public List<EmployeesModel> index() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from EmployeesModel order by fio", EmployeesModel.class);
        List<EmployeesModel> list = query.getResultList();
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeesModel show(long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from EmployeesModel a where a.id = :id", EmployeesModel.class);
        query.setParameter("id", id);
        EmployeesModel emp = session.get(EmployeesModel.class,id);
        System.out.println(emp.getDeparts().getName());
        return emp;
    }

    @Override
    @Transactional
    public void save(EmployeesModel employee) {
        Session session = sessionFactory.getCurrentSession();
        System.out.println(employee);
        session.save(employee);
    }

    @Override
    @Transactional
    public void update(long id, EmployeesModel updatedEmployee) {
        Session session = sessionFactory.getCurrentSession();
        EmployeesModel personToBeUpdated = session.get(EmployeesModel.class, id);
        personToBeUpdated.setFio(updatedEmployee.getFio());
        personToBeUpdated.setDepId(updatedEmployee.getDepId());
    }
//
    @Override
    @Transactional
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.createNamedQuery("updatedOrder", OrdersModel.class).
                setParameter("paramEmpId",id).executeUpdate();
        session.remove(session.get(EmployeesModel.class, id));
    }
}
