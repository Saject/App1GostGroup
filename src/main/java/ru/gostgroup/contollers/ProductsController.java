package ru.gostgroup.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gostgroup.dao.DepartDAO;
import ru.gostgroup.dao.ProductDAO;
import ru.gostgroup.models.Employees;
import ru.gostgroup.models.Products;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductsController {

    private final ProductDAO productDAO;

    @Autowired
    public ProductsController(ProductDAO productDAO) {
        this.productDAO = productDAO;
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
        model.addAttribute("departs", productDAO.departForProducts());
        model.addAttribute("product", new Products());
        return "products/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("product") @Valid Products prod,
                         BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("departs", productDAO.departForProducts());
            return "products/new"; }

        productDAO.save(prod);
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("departs", productDAO.departForProducts());
        model.addAttribute("product", productDAO.show(id));
        return "products/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("product") @Valid Products products, BindingResult br, @PathVariable("id") int id,
                         Model model) {
        if (br.hasErrors()) {
            model.addAttribute("departs", productDAO.departForProducts());
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
