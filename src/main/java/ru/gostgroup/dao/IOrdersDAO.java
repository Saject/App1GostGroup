package ru.gostgroup.dao;

import ru.gostgroup.models.OrdersModel;
import ru.gostgroup.models.ProductionModel;

import java.util.List;

public interface IOrdersDAO {

    public List<OrdersModel> index();
    public OrdersModel show(long id);
    public void update(long id, OrdersModel updatedOrder);
    public void save(OrdersModel order);
    public void delete(long id);
    public List<OrdersModel> showByDep(long id);
    public List<OrdersModel> showByEmp(long id);
    public List<OrdersModel> unFinishedOrders();


}
