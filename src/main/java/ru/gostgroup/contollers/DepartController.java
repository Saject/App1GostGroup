package ru.gostgroup.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gostgroup.dao.DepartDAO;
import ru.gostgroup.models.Departs;

import javax.validation.Valid;

@Controller
@RequestMapping("/departs")
public class DepartController {

    private final DepartDAO departDAO;

    @Autowired
    public DepartController(DepartDAO departDAO) {
        this.departDAO = departDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("departs", departDAO.index());
        return "departs/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("depart", departDAO.show(id));
        return "departs/show";
    }

    @GetMapping("/new")
    public String newDepart(Model model) {
        model.addAttribute("depart", new Departs());
        return "departs/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("depart") @Valid Departs depart, BindingResult br) {
        if (br.hasErrors()) {
            return "departs/new"; }

        departDAO.save(depart);
        return "redirect:/departs";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("depart", departDAO.show(id));
        return "departs/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("depart") @Valid Departs depart, BindingResult br, @PathVariable("id") int id) {

        if (br.hasErrors()) {
            return "departs/edit"; }

        departDAO.update(id, depart);
        return "redirect:/departs";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        departDAO.delete(id);
        return "redirect:/departs";
    }

}
