package ru.gostgroup.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gostgroup.dao.DepartDAO;
import ru.gostgroup.dao.OrdersDAO;
import ru.gostgroup.models.Employees;
import ru.gostgroup.models.Orders;

import javax.validation.Valid;
import java.time.Duration;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersDAO ordersDAO;

    @Autowired
    public OrdersController(OrdersDAO ordersDAO) {
        this.ordersDAO = ordersDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("orders", ordersDAO.index());
        return "orders/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Duration dur = Duration.between(LocalDateTime.now(),ordersDAO.show(id).getDeadLineDate());
        model.addAttribute("localDateTime", LocalDateTime.parse(LocalDateTime.now().format(ordersDAO.FORMATTER),ordersDAO.FORMATTER));
        model.addAttribute("remainDay", dur.toDaysPart());
        model.addAttribute("remainHours", dur.toHoursPart());
        model.addAttribute("order", ordersDAO.show(id));
        return "orders/show";
    }

    @GetMapping("/otdels")
    public String ordersByOtdels(@RequestParam(name = "dep", required = false) Integer depId, Model model) {
        System.out.println(depId);
        model.addAttribute("departs", ordersDAO.departForOrder());
        return depId==null?"orders/otdels":"redirect:/orders/otdels/" + depId;
    }


    @GetMapping("/otdels/{depId}")
    public String showByDep(@PathVariable("depId") int depId, Model model) {
        System.out.println(depId + " департа");
        model.addAttribute("orders", ordersDAO.showByDep(depId));
        return "orders/index";
    }

    @GetMapping("/employees")
    public String ordersByEmployee(@RequestParam(name = "emp", required = false) Integer empId, Model model) {
        System.out.println(empId);
        model.addAttribute("employees", ordersDAO.employeesForOrder());
        return empId==null?"orders/employees":"redirect:/orders/employees/" + empId;
    }

    @GetMapping("/employees/{empId}")
    public String showByEmp(@PathVariable("empId") int empId, Model model) {
        model.addAttribute("orders", ordersDAO.showByEmp(empId));
        return "orders/index";
    }

    @GetMapping("/new")
    public String newOrderAuto(Model model) {
        model.addAttribute("products", ordersDAO.productForOrder());
        model.addAttribute("order", new Orders());
        return "orders/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("order")
                         @Valid Orders order,
                         BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("products", ordersDAO.productForOrder());
            return "orders/new";
        }
        System.out.println(order.toString());
        System.out.println("перед методом");
        ordersDAO.save(order);
        //ordersDAO.show(order.getOrderId());
        return "redirect:/orders";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        ordersDAO.delete(id);
        return "redirect:/orders";
    }

    @GetMapping("/unfinishedOrders")
    public String unfinishedOrders(Model model) {
        System.out.println(ordersDAO.unfinisheedOrders());
        model.addAttribute("orders", ordersDAO.unfinisheedOrders());
        return "orders/index";
    }



}
