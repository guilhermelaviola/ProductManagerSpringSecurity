package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

@Controller
public class ApplicationController {

	@Autowired
	private ProductService productService;

	@RequestMapping("/")
	public String DisplayHomePage(org.springframework.ui.Model model) {
		List<Product> listProducts = productService.listAllProducts();
		model.addAttribute("listProduct", listProducts);
		return "index";
	}

	@RequestMapping("/newProduct")
	public String addProduct(org.springframework.ui.Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		return "new_product";
	}

	@RequestMapping(value="/saveProduct")
	public String saveProduct(@ModelAttribute("product") Product product) {
		productService.saveProduct(product);
		return "redirect:/";
	}

	@RequestMapping(value="/editProduct/{id}")
	public ModelAndView editProduct(@PathVariable(name="id") Long id) {
		ModelAndView modelAndView = new ModelAndView("edit_product");
		Product product = productService.getProduct(id);
		modelAndView.addObject("product", product);
		return modelAndView;
	}

	@RequestMapping(value="/delete/{id}")
	public String deleteProduct(@PathVariable(name="id") Long id) {
		productService.deleteProduct(id);
		return "redirect:/";
	}
}
