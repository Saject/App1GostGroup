package ru.gostgroup.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.gostgroup.models.Departs;

import java.util.List;

@Component
public class DepartDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DepartDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Departs> index() {
        return jdbcTemplate.query("select * from departament order by name", new BeanPropertyRowMapper<>(Departs.class));
    }

    public Departs show(int id) {
        Departs d = jdbcTemplate.query("select * from departament where id=?", new BeanPropertyRowMapper<>(Departs.class), id)
                .stream().findAny().orElse(null);
        System.out.println(d.toString());
        return jdbcTemplate.query("select * from departament where id=?", new BeanPropertyRowMapper<>(Departs.class), id)
                .stream().findAny().orElse(null);

    }

    public void save(Departs dep) {
        SqlRowSet srs = jdbcTemplate.queryForRowSet("select max(id) from departament");
        srs.next();
        int index = srs.getInt(1) + 1;
        jdbcTemplate.update("INSERT INTO departament values(?,?)", index, dep.getName());
    }

    public void update(int id, Departs updatedDep) {
        jdbcTemplate.update("UPDATE departament SET name=? where id=?", updatedDep.getName(), id);
        //jdbcTemplate.update(INSERT INTO employees VALUES())
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM departament where id=?",id);
    }


}
