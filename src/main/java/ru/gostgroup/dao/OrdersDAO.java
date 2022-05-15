package ru.gostgroup.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.DateUtils;
import ru.gostgroup.models.Departs;
import ru.gostgroup.models.Employees;
import ru.gostgroup.models.Orders;
import ru.gostgroup.models.Products;
import ru.gostgroup.myutils.AutoPersonAndDep;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Component
public class OrdersDAO {

    private final JdbcTemplate jdbcTemplate;
    private static final String NAME_ZAKAZ = "Заказ№";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    @Autowired
    public OrdersDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Orders> index() {
        return jdbcTemplate.query("select o.orderid as orderId, \n" +
                "o.nameorder as orderName,\n" +
                "o.prod_id as prodId, \n" +
                "p.nameprod as prodName, \n" +
                "o.dep_id as depId,\n" +
                "d.name as depName,\n" +
                "o.emp_id as employeeId,\n" +
                "e.fio as employeeName,\n" +
                "o.create_date as createDate, \n" +
                "o.deadline_date as deadLineDate, \n" +
                "o.countprod as countProd,\n" +
                "now() as nowDate\n" +
                "from orders o\n" +
                "inner join production p\n" +
                "on o.prod_id = p.id\n" +
                "inner join employees e\n" +
                "on o.emp_id = e.id\n" +
                "inner join departament d\n" +
                "on o.dep_id = d.id\n" +
                "order by o.nameorder", new BeanPropertyRowMapper<>(Orders.class));
    }

    public List<Orders> showByDep(int id) {
        List<Orders> t = jdbcTemplate.query("select o.orderid as orderId, \n" +
                "o.nameorder as orderName,\n" +
                "o.prod_id as prodId, \n" +
                "p.nameprod as prodName, \n" +
                "o.dep_id as depId,\n" +
                "d.name as depName,\n" +
                "o.emp_id as employeeId,\n" +
                "e.fio as employeeName,\n" +
                "o.create_date as createDate, \n" +
                "o.deadline_date as deadLineDate, \n" +
                "o.countprod as countProd\n" +
                "from orders o\n" +
                "inner join production p\n" +
                "on o.prod_id = p.id\n" +
                "inner join employees e\n" +
                "on o.emp_id = e.id\n" +
                "inner join departament d\n" +
                "on o.dep_id = d.id\n" +
                "where o.dep_id = ?" +
                "order by o.nameorder", new BeanPropertyRowMapper<>(Orders.class), id);
        System.out.println(t);
        return t;
    }

    public List<Orders> showByEmp(int id) {
        List<Orders> t = jdbcTemplate.query("select o.orderid as orderId, \n" +
                "o.nameorder as orderName,\n" +
                "o.prod_id as prodId, \n" +
                "p.nameprod as prodName, \n" +
                "o.dep_id as depId,\n" +
                "d.name as depName,\n" +
                "o.emp_id as employeeId,\n" +
                "e.fio as employeeName,\n" +
                "o.create_date as createDate, \n" +
                "o.deadline_date as deadLineDate, \n" +
                "o.countprod as countProd\n" +
                "from orders o\n" +
                "inner join production p\n" +
                "on o.prod_id = p.id\n" +
                "inner join employees e\n" +
                "on o.emp_id = e.id\n" +
                "inner join departament d\n" +
                "on o.dep_id = d.id\n" +
                "where o.emp_id = ?" +
                "order by o.nameorder", new BeanPropertyRowMapper<>(Orders.class), id);
        System.out.println(t);
        return t;
    }

    public Orders show(int id) {
        return jdbcTemplate.query("select o.orderid as orderId, \n" +
                        "o.nameorder as orderName,\n" +
                        "o.prod_id as prodId, \n" +
                        "p.nameprod as prodName, \n" +
                        "o.dep_id as depId,\n" +
                        "d.name as depName,\n" +
                        "o.emp_id as employeeId,\n" +
                        "e.fio as employeeName,\n" +
                        "o.create_date as createDate, \n" +
                        "o.deadline_date as deadLineDate, \n" +
                        "o.countprod as countProd\n" +
                        "from orders o\n" +
                        "inner join production p\n" +
                        "on o.prod_id = p.id\n" +
                        "inner join employees e\n" +
                        "on o.emp_id = e.id\n" +
                        "inner join departament d\n" +
                        "on o.dep_id = d.id\n" +
                        "where o.orderid = ?\n" +
                        "order by o.nameorder", new BeanPropertyRowMapper<>(Orders.class), id)
                .stream().findAny().orElse(null);
    }

    public List<Products> productForOrder() {
        return jdbcTemplate.query("select * from production", new BeanPropertyRowMapper<>(Products.class));
    }

    public List<Departs> departForOrder() {
        return jdbcTemplate.query("select * from departament", new BeanPropertyRowMapper<>(Departs.class));
    }

    public List<Employees> employeesForOrder() {
        return jdbcTemplate.query("select * from employees order by fio", new BeanPropertyRowMapper<>(Employees.class));
    }

    public void save(Orders order) {
        order.setCreateDate(LocalDateTime.parse(LocalDateTime.now().format(FORMATTER),FORMATTER));
        AutoPersonAndDep apad = jdbcTemplate.query("select e.dep_id as depId, e.id as empId, e.fio from production p\n" +
                "join employees e on p.dep_id = e.dep_id\n" +
                "where p.id = ?\n" +
                "order by random()\n" +
                "limit 1", new BeanPropertyRowMapper<>(AutoPersonAndDep.class),order.getProdId()).stream().findAny().orElse(null);
        SqlRowSet srs = jdbcTemplate.queryForRowSet("SELECT NEXTVAL('ord_id_seq')");
        srs.next();
        order.setOrderId(srs.getInt(1));
        order.setDepId(apad.getDepId());
        order.setEmployeeId(apad.getEmpId());
        String zakaz = NAME_ZAKAZ + order.getOrderId();
        System.out.println(apad.getDepId() + " " + apad.getEmpId());
        //System.out.println(result);
        jdbcTemplate.update("INSERT INTO orders VALUES (?, ?, ?, ?, ?, ?, ?, ?);", order.getOrderId(), order.getProdId(), order.getDepId(), order.getCountProd(),order.getCreateDate(),order.getDeadLineDate(), zakaz, order.getEmployeeId());
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from orders where orderid = ?",id);

    }

    public List<Orders> unfinisheedOrders() {
        LocalDateTime datetime = LocalDateTime.now();
        System.out.println(datetime.toString() + "Сегодня");
        return jdbcTemplate.query("select o.orderid as orderId, \n" +
                "o.nameorder as orderName,\n" +
                "o.prod_id as prodId, \n" +
                "p.nameprod as prodName, \n" +
                "o.dep_id as depId,\n" +
                "d.name as depName,\n" +
                "o.emp_id as employeeId,\n" +
                "e.fio as employeeName,\n" +
                "o.create_date as createDate, \n" +
                "o.deadline_date as deadLineDate, \n" +
                "o.countprod as countProd\n" +
                "from orders o\n" +
                "inner join production p\n" +
                "on o.prod_id = p.id\n" +
                "inner join employees e\n" +
                "on o.emp_id = e.id\n" +
                "inner join departament d\n" +
                "on o.dep_id = d.id\n" +
                "where o.deadline_date > ?\n" +
                "order by o.nameorder",new BeanPropertyRowMapper<>(Orders.class),datetime);
    }

}
