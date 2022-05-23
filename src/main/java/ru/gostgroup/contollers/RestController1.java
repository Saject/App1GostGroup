package ru.gostgroup.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gostgroup.dao.OrdersDAO;
import ru.gostgroup.dao.ProductDAO;
import ru.gostgroup.models.Orders;
import ru.gostgroup.myutils.DBOrdersJSON;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/test")
public class RestController1 {

    private final OrdersDAO ordersDAO;

    @Autowired
    public RestController1(OrdersDAO ordersDAO) {
        this.ordersDAO = ordersDAO;
    }

    @GetMapping(produces = "application/json", headers="Accept=application/json")
    public DBOrdersJSON index() {
        List<Orders> jsonOrders = null;
        jsonOrders = ordersDAO.index();  //JPA (Hibernate)
        System.out.println(jsonOrders);
        DBOrdersJSON dbOrdersJSON = new DBOrdersJSON();
        dbOrdersJSON.setLogList(jsonOrders);
        return dbOrdersJSON;
    }

}
