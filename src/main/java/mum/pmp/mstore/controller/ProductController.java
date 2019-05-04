package mum.pmp.mstore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mum.pmp.mstore.domain.Category;
import mum.pmp.mstore.domain.Product;
import mum.pmp.mstore.service.CategoryService;
import mum.pmp.mstore.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@GetMapping("/")
	public String listProducts(Model model) {

		model.addAttribute("productsList", productService.getAllProducts());
		return "product/productsList";
	}

	@GetMapping(value = { "/productsEdit", "/productsEdit/{id}" })
	public String productsEdit(Model model, @PathVariable(required = false, name = "id") Integer id) {
		if (null != id) {
			List<Category> categories = categoryService.getCategories();
			model.addAttribute("categories", categories);
			model.addAttribute("products", productService.getProduct(id));
		} else {

			List<Category> categories = categoryService.getCategories();
			model.addAttribute("categories", categories);

			model.addAttribute("products", new Product());
		}
		return "product/productsEdit";
	}

	@PostMapping(value = "/productsEdit")
	public String addproducts(@Valid @ModelAttribute("products") Product product, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "product/productsEdit";
		}

		List<Category> categories = categoryService.getCategories();
		model.addAttribute("categories", categories);
		productService.addProduct(product);
		return "product/productsList";
	}

	@RequestMapping(value = "/productsDelete/{id}", method = RequestMethod.GET)
	public String productsDelete(Model model, @PathVariable(required = true, name = "id") Integer id) {
		productService.deleteProduct(id);
		model.addAttribute("productsList", productService.getAllProducts());
		return "product/productsList";

	}

	@GetMapping(value = "/allproducts")
	public String getAllProducts(Model model) {

		model.addAttribute("productsList", productService.getAllProducts());
		return "product/allProducts";
	}

}
