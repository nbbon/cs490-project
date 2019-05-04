package mum.pmp.mstore.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mum.pmp.mstore.domain.Category;
import mum.pmp.mstore.domain.Product;
import mum.pmp.mstore.repository.SearchRepository;
import mum.pmp.mstore.service.SearchService;

@Controller
public class SearchController {

	@Resource
	private SearchService searchService;

	/*
	 * @GetMapping(value = "/search") public String showSearchForm(ModelMap
	 * modelMap) {
	 * 
	 * Map<Integer, String> searchMenu = new HashMap<>(); searchMenu.put(1,
	 * "categories"); searchMenu.put(2, "products"); searchMenu.put(3, "price");
	 * modelMap.addAttribute("searchMenu", searchMenu);
	 * 
	 * modelMap.addAttribute("product", new Product()); return
	 * "/search/findProducts"; }
	 * 
	 * @PostMapping(value = "/adsearch") public String findProducts(
	 * 
	 * @RequestParam(name = "productNumber", defaultValue = "", required = false)
	 * String productNumber,
	 * 
	 * @RequestParam(name = "productName", defaultValue = "", required = false)
	 * String productName,
	 * 
	 * @RequestParam(name = "price", defaultValue = "0", required = false) int
	 * price,
	 * 
	 * @RequestParam(name = "category", defaultValue = "", required = false)
	 * Category category, ModelMap modelMap) {
	 * 
	 * List<Product> productList = searchRepository.findProductBy(productNumber,
	 * productName, price, category); modelMap.addAttribute("productList",
	 * productList);
	 * 
	 * return "/search/displayProducts"; }
	 */

	@GetMapping(value = "/search")
	public String showSearchForm(ModelMap modelMap) {
		Map<String, String> searchMenu = new HashMap<>();
		searchMenu.put("categories", "category");
		searchMenu.put("products", "product");
		modelMap.addAttribute("searchMenu", searchMenu);
		return "/search/findProducts";
	}

	@PostMapping(value = "/adsearch")
	public String findProducts(@RequestParam("searchmenu") String searchTerm, ModelMap modelMap) {
		System.out.println(searchTerm);
		if (searchTerm == "category") {
			List<Category> catList = searchService.getAllCategories();
			for (Category category : catList) {
				System.out.println(category);
			}
			System.out.println(searchTerm);
			modelMap.addAttribute("categoryDiv", true);
			modelMap.addAttribute("categories", catList);
		} else if (searchTerm == "product") {
			List<Product> proList = searchService.getAllProducts();
			for(Product product: proList) {
				System.out.println(product);
			}
			System.out.println(searchTerm);
			modelMap.addAttribute("productDiv", true);
			modelMap.addAttribute("products", proList);
		} else {
			modelMap.addAttribute("emptyDiv", true);
			System.out.println(searchTerm);

		}
		return "/search/displayProducts";
	}

}
