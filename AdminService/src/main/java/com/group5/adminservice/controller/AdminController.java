package com.group5.adminservice.controller;

import com.group5.adminservice.dto.OrderDto;
import com.group5.adminservice.dto.ProductDto;
import com.group5.adminservice.service.AdminServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    private final AdminServiceImpl adminServiceImpl;

    public AdminController(AdminServiceImpl adminServiceImpl) {
        this.adminServiceImpl = adminServiceImpl;
    }

    // Product Endpoints
    @GetMapping("/dashboard")
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
    public String showUpdateProductForm(@PathVariable String id, Model model) {
        ProductDto product = adminServiceImpl.getAllProductsDto().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));
        model.addAttribute("product", product);
        return "update_product";
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

    // Order Endpoints
    @GetMapping("/orders")
    public String viewOrders(Model model) {
        List<OrderDto> orders = adminServiceImpl.getAllOrdersDto();
        model.addAttribute("orders", orders);
        return "orders";
    }

    @PostMapping("/orders")
    public String createOrder(@ModelAttribute OrderDto orderDto) {
        adminServiceImpl.createOrder(orderDto);
        return "redirect:/admin/orders";
    }

    @GetMapping("/orders/update/{id}")
    public String showUpdateOrderForm(@PathVariable String id, Model model) {
        OrderDto order = adminServiceImpl.getAllOrdersDto().stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Order not found"));
        model.addAttribute("order", order);
        return "update_order";
    }

    @PostMapping("/orders/update")
    public String updateOrder(@ModelAttribute OrderDto orderDto) {
        adminServiceImpl.updateOrder(orderDto.getId(), orderDto);
        return "redirect:/admin/orders";
    }

    @PostMapping("/orders/delete")
    public String deleteOrder(@RequestParam String id) {
        adminServiceImpl.deleteOrder(id);
        return "redirect:/admin/orders";
    }

//    @GetMapping("/orders/add")
//    public String showAddOrderForm(Model model) {
//        model.addAttribute("orderDto", new OrderDto()); // Optional: Add an empty OrderDto for the form
//        return "add_order";
//    }
}
