package ru.gostgroup.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.gostgroup.models.Departs;
import ru.gostgroup.models.Employees;
import ru.gostgroup.models.Products;

import java.util.List;

@Component
public class ProductDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Products> index() {
        return jdbcTemplate.query("select * from production", new BeanPropertyRowMapper<>(Products.class));
    }

    public Products show(int id) {
        return jdbcTemplate.query("select p.id, p.nameProd, p.dep_id, d.name as NameDep from production p\n" +
                        "    inner join departament d\n" +
                        "on p.dep_id = d.id where p.id=?", new BeanPropertyRowMapper<>(Products.class), id)
                .stream().findAny().orElse(null);
    }

    public List<Departs> departForProducts() {
        return jdbcTemplate.query("select * from departament", new BeanPropertyRowMapper<>(Departs.class));
    }

    public void save(Products prod) {
        SqlRowSet srs = jdbcTemplate.queryForRowSet("select max(id) from production");
        srs.next();
        int index = srs.getInt(1) + 1;
        jdbcTemplate.update("INSERT INTO production values(?,?,?)", index, prod.getNameProd(), prod.getDepId());
    }

    public void update(int id, Products updatedProd) {
        jdbcTemplate.update("UPDATE production SET nameprod=?, dep_id=? where id=?", updatedProd.getNameProd(), updatedProd.getDepId(), id);
        //jdbcTemplate.update(INSERT INTO employees VALUES())
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM production where id=?", id);
    }


}


