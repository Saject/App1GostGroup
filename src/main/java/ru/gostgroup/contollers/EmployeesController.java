package ru.gostgroup.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gostgroup.dao.IDepartsDAO;
import ru.gostgroup.dao.IEmployeesDAO;
import ru.gostgroup.models.EmployeesModel;

import javax.validation.Valid;

@Controller
@RequestMapping("/employees")
public class EmployeesController {


    private final IEmployeesDAO employeesDAO;
    private final IDepartsDAO departsDAO;


    @Autowired
    public EmployeesController(IEmployeesDAO employeesDAO, IDepartsDAO departsDAO) {
        this.employeesDAO = employeesDAO;
        this.departsDAO = departsDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("employees", employeesDAO.index());
        //System.out.println(model.getAttribute("employees").toString());
        return "employees/index";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("employee", employeesDAO.show(id));
        return "employees/show";
    }

    @GetMapping("/new")
    public String newEmployee(Model model) {
        model.addAttribute("departs", departsDAO.index());
        model.addAttribute("employee", new EmployeesModel());
        return "employees/new";
    }


    @PostMapping()
    public String create(@ModelAttribute("employee")
                         @Valid EmployeesModel employee,
                          BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("departs", departsDAO.index());
            return "employees/new"; }
        employeesDAO.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("employee", employeesDAO.show(id));
        model.addAttribute("departs", departsDAO.index());
        return "employees/edit";
    }
//
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("employee") @Valid EmployeesModel employee, BindingResult br, @PathVariable("id") long id,
                         Model model) {

        if (br.hasErrors()) {
            model.addAttribute("departs", departsDAO.index());
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
