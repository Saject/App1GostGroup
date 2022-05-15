package ru.gostgroup.contollers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gostgroup.dao.DepartDAO;
import ru.gostgroup.dao.EmployeesDAO;
import ru.gostgroup.models.Departs;
import ru.gostgroup.models.Employees;

@Controller
@RequestMapping("/employees")
public class EmployeesController {


    private final EmployeesDAO employeesDAO;


    @Autowired
    public EmployeesController(EmployeesDAO employeesDAO) {
        this.employeesDAO = employeesDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("employees", employeesDAO.index());
        return "employees/index";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("employee", employeesDAO.show(id));
        return "employees/show";
    }

    @GetMapping("/new")
    public String newEmployee(Model model) {
        model.addAttribute("departs", employeesDAO.departForEmployees());
        model.addAttribute("employee", new Employees());
        return "employees/new";
    }


    @PostMapping()
    public String create(@ModelAttribute("employee")
                         @Valid Employees employee,
                          BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("departs", employeesDAO.departForEmployees());
            return "employees/new"; }
        employeesDAO.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("employee", employeesDAO.show(id));
        model.addAttribute("departs", employeesDAO.departForEmployees());
        return "employees/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("employee") @Valid Employees employee, BindingResult br, @PathVariable("id") int id,
                         Model model) {

        if (br.hasErrors()) {
            model.addAttribute("departs", employeesDAO.departForEmployees());
            return "employees/edit"; }

        employeesDAO.update(id, employee);
        return "redirect:/employees";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        employeesDAO.delete(id);
        return "redirect:/employees";
    }
}
