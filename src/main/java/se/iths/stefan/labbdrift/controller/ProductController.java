package se.iths.stefan.labbdrift.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.iths.stefan.labbdrift.model.Product;
import se.iths.stefan.labbdrift.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", service.getAll());
        return "products";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("product", new Product());
        return "create-product";
    }

    @PostMapping
    public String create(@ModelAttribute Product product) {
        service.create(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", service.getOne(id));
        return "edit-product";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Product product) {
        service.update(id, product);
        return "redirect:/products";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/products";
    }
}
