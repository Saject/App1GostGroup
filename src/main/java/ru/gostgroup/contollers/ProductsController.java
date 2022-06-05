package ru.gostgroup.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gostgroup.dao.DepartDAO;
import ru.gostgroup.dao.IDepartsDAO;
import ru.gostgroup.dao.IProductsDAO;
import ru.gostgroup.dao.ProductDAO;
import ru.gostgroup.models.ProductionModel;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductsController {

    private final IProductsDAO productDAO;
    private final IDepartsDAO departsDAO;


    public ProductsController(IProductsDAO productDAO, IDepartsDAO departsDAO) {
        this.productDAO = productDAO;
        this.departsDAO = departsDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("products", productDAO.index());
        return "products/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productDAO.show(id));
        return "products/show";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute("departs", departsDAO.index());
        model.addAttribute("product", new ProductionModel());
        return "products/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("product") @Valid ProductionModel prod,
                         BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("departs", departsDAO.index());
            return "products/new"; }

        productDAO.save(prod);
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("departs", departsDAO.index());
        model.addAttribute("product", productDAO.show(id));
        return "products/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("product") @Valid ProductionModel products, BindingResult br, @PathVariable("id") int id,
                         Model model) {
        if (br.hasErrors()) {
            model.addAttribute("departs", departsDAO.index());
            return "products/edit"; }

        productDAO.update(id, products);
        return "redirect:/products";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        productDAO.delete(id);
        return "redirect:/products";
    }

}
