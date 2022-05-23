package ru.gostgroup.myutils;

import ru.gostgroup.models.Orders;

import java.util.List;

public class DBOrdersJSON {

    private List<Orders> ordersList;

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setLogList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

}
