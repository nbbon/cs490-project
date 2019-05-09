/*
 * Author: Niveen Abdelaatty
 * Date: 1-May-2019
 * Class Name: SearchController
 * Package: controller
 * Description: contains the handler methods that are used to handles GET and POST requests for searching
 */
package mum.pmp.mstore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mum.pmp.mstore.domain.Product;
import mum.pmp.mstore.repository.SearchRepository;

@Controller
public class SearchController {

	@Autowired
	private SearchRepository searchRepository;

	@GetMapping(value = "/search")
	public String showSearchForm(ModelMap modelMap) {
		modelMap.addAttribute("product", new Product());
		return "search/findProducts";
	}

	@GetMapping(value = "/adsearch")
	public String showAdSearchForm(ModelMap modelMap) {
		modelMap.addAttribute("product", new Product());
		return "/search/findProducts";
	}

	@PostMapping(value = "/adsearch")
	public String findProducts(
			@RequestParam(name = "productNumber", defaultValue = "", required = false) String productNumber,
			@RequestParam(name = "productName", defaultValue = "", required = false) String productName,
			@RequestParam(name = "price", defaultValue = "0.0", required = false) Double price, ModelMap modelMap) {

		List<Product> productList = new ArrayList<>();

		if (!productNumber.isEmpty() && productName.isEmpty() && price == 0.0) {
			productList = searchRepository.findProductByNum(productNumber);
			modelMap.addAttribute("productList", productList);
		}
		if (productNumber.isEmpty() && !productName.isEmpty() && price == 0.0) {
			productList = searchRepository.findProductByName(productName);
			modelMap.addAttribute("productList", productList);
		}
		if (productNumber.isEmpty() && productName.isEmpty() && price != 0.0) {
			productList = searchRepository.findProductByPrice(price);
			modelMap.addAttribute("productList", productList);
		}
		if (!productNumber.isEmpty() && !productName.isEmpty() && price == 0.0) {
			productList = searchRepository.findProductByNaNu(productNumber, productName);
			modelMap.addAttribute("productList", productList);
		}
		if (productNumber.isEmpty() && !productName.isEmpty() && price != 0.0) {
			productList = searchRepository.findProductByNaP(productName, price);
			modelMap.addAttribute("productList", productList);
		}
		if (!productNumber.isEmpty() && productName.isEmpty() && price != 0.0) {
			productList = searchRepository.findProductByNuP(productNumber, price);
			modelMap.addAttribute("productList", productList);
		}
		if (!productNumber.isEmpty() && !productName.isEmpty() && price != 0.0) {
			productList = searchRepository.findProductBy(productNumber, productName, price);
			modelMap.addAttribute("productList", productList);
		}
		return "search/displayProducts";
	}
}
