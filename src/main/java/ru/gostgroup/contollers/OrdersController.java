package ru.gostgroup.contollers;
//
//import com.fasterxml.jackson.databind.ObjectWriter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import ru.gostgroup.dao.DepartDAO;
//import ru.gostgroup.dao.OrdersDAO;
//import ru.gostgroup.models.Employees;
//import ru.gostgroup.models.Orders;
//
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gostgroup.dao.IDepartsDAO;
import ru.gostgroup.dao.IEmployeesDAO;
import ru.gostgroup.dao.IOrdersDAO;
import ru.gostgroup.dao.IProductsDAO;
import ru.gostgroup.models.OrdersModel;

import javax.validation.Valid;
import java.time.Duration;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    private final IOrdersDAO ordersDAO;
    private final IProductsDAO prodDao;
    private final IDepartsDAO depDAO;
    private final IEmployeesDAO empDAO;


    @Autowired
    public OrdersController(IOrdersDAO ordersDAO, IProductsDAO prodDao, IDepartsDAO depDAO, IEmployeesDAO empDAO) {
        this.ordersDAO = ordersDAO;
        this.prodDao = prodDao;
        this.depDAO = depDAO;
        this.empDAO = empDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("orders", ordersDAO.index());
        return "orders/index";
    }

    //
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        OrdersModel om = ordersDAO.show(id);
        Duration dur = Duration.between(LocalDateTime.now(), om.getDeadLineDate());
        model.addAttribute("localDateTime", LocalDateTime.now());
        model.addAttribute("remainDay", dur.toDaysPart());
        model.addAttribute("remainHours", dur.toHoursPart());
        model.addAttribute("order", om);
        return "orders/show";
    }

    @GetMapping(value = "/departs")
    public String ordersByDeparts(@RequestParam(name = "dep", required = false) Integer depId, Model model) {
        model.addAttribute("departs", depDAO.index());
        return depId == null ? "orders/departs" : "redirect:/orders/departs/" + depId;
    }



    @GetMapping(value= "/departs/{depId}")
    public String showByDep(@PathVariable("depId") int depId, Model model) {
        model.addAttribute("orders", ordersDAO.showByDep(depId));
        return "orders/index";
    }
//
    @GetMapping("/employees")
    public String ordersByEmployee(@RequestParam(name = "emp", required = false) Integer empId, Model model) {
        System.out.println(empId);
        model.addAttribute("employees", empDAO.index());
        return empId==null?"orders/employees":"redirect:/orders/employees/" + empId;
    }

    @GetMapping("/employees/{empId}")
    public String showByEmp(@PathVariable("empId") int empId, Model model) {
        model.addAttribute("orders", ordersDAO.showByEmp(empId));
        return "orders/index";
    }
//
    @GetMapping("/new")
    public String newOrderAuto(Model model) {
        model.addAttribute("products", prodDao.index());
        model.addAttribute("order", new OrdersModel());
        return "orders/new";
    }


    @PostMapping()
    public String create(@ModelAttribute("order")
                         @Valid OrdersModel order,
                         BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("products", prodDao.index());
            return "orders/new";
        }
        ordersDAO.save(order);
        return "redirect:/orders";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        ordersDAO.delete(id);
        return "redirect:/orders";
    }

    @GetMapping("/unfinishedOrders")
    public String unfinishedOrders(Model model) {
        model.addAttribute("orders", ordersDAO.unFinishedOrders());
        return "orders/index";
    }

}
