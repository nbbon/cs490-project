package mum.pmp.mstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javassist.expr.NewArray;
import mum.pmp.mstore.domain.Category;
import mum.pmp.mstore.domain.Product;
import mum.pmp.mstore.service.CategoryService;
import mum.pmp.mstore.service.ProductService;

@Controller
public class ProductController {
	
	/*
	 * @Autowired ProductService productService;
	 * 
	 * @Autowired CategoryService categoryService;
	 * 
	 * 
	 * @GetMapping("/product") public String registration(Model model) {
	 * 
	 * List<Category> nameCategories = categoryService.getCategories();
	 * model.addAttribute("nameCategories", nameCategories);
	 * model.addAttribute("productForm",new Product());
	 * 
	 * return "product"; }
	 * 
	 * @PostMapping("/product") public String
	 * registration(@ModelAttribute("productForm") Product productForm,
	 * BindingResult bindingResult) {
	 * 
	 * if (bindingResult.hasErrors()) { return "product"; }
	 * 
	 * System.out.println("In registration..");
	 * productService.addProduct(productForm);
	 * 
	 * 
	 * return "redirect:/welcome"; }
	 * 
	 * 
	 * 
	 * @GetMapping("/products") public String getcategories(Model model) {
	 * model.addAttribute("products", productService.getProducts());
	 * 
	 * return "productsForm"; }
	 * 
	 * 
	 * 
	 * @GetMapping(value = "/product/{productNumber}") public String
	 * getProduct(@PathVariable("productNumber") String productNumber, Model model)
	 * { // Category category= categoryService.getCategory(id); Product
	 * product=productService.getProduct(productNumber); if (product != null) {
	 * model.addAttribute("product", product); return "product"; } return
	 * "redirect:/welcome"; }
	 * 
	 */
	
}
