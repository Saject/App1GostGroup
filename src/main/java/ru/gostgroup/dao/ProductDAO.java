package ru.gostgroup.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gostgroup.models.ProductionModel;


import java.util.List;

@Repository
public class ProductDAO implements IProductsDAO {


    private final SessionFactory sessionFactory;

    @Autowired
    public ProductDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    @Transactional
    public List<ProductionModel> index() {
        Session session = sessionFactory.getCurrentSession();
        List<ProductionModel> prodList = session.createQuery("from ProductionModel ", ProductionModel.class).getResultList();
        return prodList;
    }

    @Override
    @Transactional
    public ProductionModel show(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(ProductionModel.class, id);
    }


    @Override
    @Transactional
    public void save(ProductionModel prod) {
        Session session = sessionFactory.getCurrentSession();
        session.save(prod);
    }

    @Override
    @Transactional
    public void update(long id, ProductionModel updatedProd) {
        Session session = sessionFactory.getCurrentSession();
        ProductionModel prod = session.get(ProductionModel.class,id);
        prod.setId(updatedProd.getId());
        prod.setNameProd(updatedProd.getNameProd());
        //prod.setDepId(updatedProd.getDepId());
        //jdbcTemplate.update(INSERT INTO employees VALUES())
    }

    @Override
    @Transactional
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(ProductionModel.class,id));
    }


}


