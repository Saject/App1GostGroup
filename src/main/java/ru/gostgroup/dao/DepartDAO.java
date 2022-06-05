package ru.gostgroup.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gostgroup.models.DepartamentModel;
import ru.gostgroup.models.EmployeesModel;

import java.util.List;

@Repository
public class DepartDAO implements IDepartsDAO {

//    private final JdbcTemplate jdbcTemplate;

    private final SessionFactory sessionFactory;

    @Autowired
    public DepartDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<DepartamentModel> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from DepartamentModel order by name", DepartamentModel.class)
                .getResultList();
    }

    @Override
    @Transactional
    public DepartamentModel show(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(DepartamentModel.class,id);
    }

    @Override
    @Transactional
    public void update(long id, DepartamentModel updatedDepartament) {
        Session session = sessionFactory.getCurrentSession();
        DepartamentModel dep = session.get(DepartamentModel.class,id);
        dep.setName(updatedDepartament.getName());
    }

    @Override
    @Transactional
    public void save(DepartamentModel depart) {
        Session session = sessionFactory.getCurrentSession();
        session.save(depart);
    }

    @Override
    @Transactional
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(DepartamentModel.class,id));
    }

    //
//    public void save(Departs dep) {
//        SqlRowSet srs = jdbcTemplate.queryForRowSet("select max(id) from departament");
//        srs.next();
//        int index = srs.getInt(1) + 1;
//        jdbcTemplate.update("INSERT INTO departament values(?,?)", index, dep.getName());
//    }
//

//
//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM departament where id=?",id);
//    }


}
