package com.group5.adminservice.controller;

import com.group5.adminservice.dto.ProductDto;
import com.group5.adminservice.entity.Product;
import com.group5.adminservice.service.AdminServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private AdminServiceImpl adminServiceImpl;

    public AdminController(AdminServiceImpl adminServiceImpl){
        this.adminServiceImpl = adminServiceImpl;
    }

    @GetMapping("/")
    public String index() {
        return adminServiceImpl.index();
    }


    @GetMapping("/products")
    public String viewProducts(Model model) {
        List<ProductDto> products = adminServiceImpl.getAllProductsDto();
        model.addAttribute("products", products);
        return "products";
    }

    @PostMapping("/products")
    public String createProduct(@ModelAttribute ProductDto productDto) {
        adminServiceImpl.createProduct(productDto);
        return "redirect:/admin/products";
    }

    @GetMapping("/products/update/{id}")
    public String showUpdateForm(@PathVariable String id, Model model) {
        ProductDto product = adminServiceImpl.getAllProductsDto().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));
        model.addAttribute("product", product);
        return "update";
    }

    @PostMapping("/products/update")
    public String updateProduct(@ModelAttribute ProductDto productDto) {
        adminServiceImpl.updateProduct(productDto.getId(), productDto);
        return "redirect:/admin/products";
    }

    @PostMapping("/products/delete")
    public String deleteProduct(@RequestParam String id) {
        adminServiceImpl.deleteProduct(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/products/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("productDto", new ProductDto()); // Optional: Add an empty ProductDto for the form
        return "add_product";
    }

}