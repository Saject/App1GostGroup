package ru.gostgroup.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.gostgroup.models.Departs;
import ru.gostgroup.models.Employees;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Component
public class EmployeesDAO {

    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public EmployeesDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Departs> departForEmployees() {
        return jdbcTemplate.query("select * from departament", new BeanPropertyRowMapper<>(Departs.class));
    }

    public List<Employees> index() {
        List<Employees> t = jdbcTemplate.query("select * from employees", new BeanPropertyRowMapper<>(Employees.class));
        System.out.println(t);
        return jdbcTemplate.query("select * from employees order by fio", new BeanPropertyRowMapper<>(Employees.class));
    }

    public Employees show(int id) {
        return jdbcTemplate.query("select e.id, e.fio, e.dep_id, d.name as depName from employees e\n" +
                        "    inner join departament d\n" +
                        "on e.dep_id = d.id where e.id=?", new BeanPropertyRowMapper<>(Employees.class), id)
                .stream().findAny().orElse(null);
    }

    public void save(Employees employee) {
        SqlRowSet srs = jdbcTemplate.queryForRowSet("SELECT NEXTVAL('emp_id_seq')");
        srs.next();
        int result = srs.getInt(1);
        System.out.println(result);
        jdbcTemplate.update("INSERT INTO employees values(?,?,?)", result, employee.getFIO(), employee.getDepId());
    }

    public void update(int id, Employees updatedEmployee) {
        jdbcTemplate.update("UPDATE employees SET fio=?, dep_id=? where id=?", updatedEmployee.getFIO(), updatedEmployee.getDepId(), id);
        //jdbcTemplate.update(INSERT INTO employees VALUES())
    }

    public void delete(int id) {
        jdbcTemplate.update("UPDATE orders \n" +
                "SET emp_id=(select id from employees\n" +
                "where dep_id in (select dep_id from employees where id = ?)\n" +
                "order by random() limit 1)\n" +
                "where emp_id = ?", id, id);
        jdbcTemplate.update("delete from employees where id = ?",id);
    }
}
