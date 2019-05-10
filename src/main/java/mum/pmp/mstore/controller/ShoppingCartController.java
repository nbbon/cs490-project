/*
 * Author: Yee Mon Zaw
 * Date: 01-May-2019
 * Class Name: ShoppingCartController
 * Package: mum.pmp.mstore.controller
 * Description: ShoppingCartController - responsible to get the products from catalogs and add to shopping cart. 
 * User can add and remove products from the Shopping Cart.
 * 
 */

package mum.pmp.mstore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mum.pmp.mstore.domain.OrderLine;
import mum.pmp.mstore.domain.Product;
import mum.pmp.mstore.domain.ShoppingCart;
import mum.pmp.mstore.domain.ShoppingCartLine;
import mum.pmp.mstore.exception.NotEnoughProductsInStockException;
import mum.pmp.mstore.service.ProductService;
import mum.pmp.mstore.service.ShoppingCartService;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    private final ProductService productService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, ProductService productService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }

    // Display shopping cart page.
    @GetMapping
    public ModelAndView shoppingCart() {
        ModelAndView modelAndView = new ModelAndView("shoppingcart/shoppingCart");
        modelAndView.addObject("products", shoppingCartService.getProductsInCart());
        modelAndView.addObject("total", shoppingCartService.getTotal().toString());
        return modelAndView;
    }

    // Add product to the shopping cart
    @PostMapping("/addProduct")
    public ModelAndView addProductToCart(@RequestBody int productId) {
    	System.out.println("Here in add to cart product controller");
        productService.getProduct(productId).ifPresent(shoppingCartService::addProduct);
        return shoppingCart();
    }

    // Remove product from the shopping cart.
    @GetMapping("/removeProduct/{productId}")
    public ModelAndView removeProductFromCart(@PathVariable("productId") int productId) {
        productService.getProduct(productId).ifPresent(shoppingCartService::removeProduct);
        return shoppingCart();
    }

    // once user click the place order button, will keep shopping cart in session and call to order controller for further processing.
    @PostMapping("/placeOrder")
    public String checkout(HttpServletRequest request,
			HttpServletResponse response, Model model) {
        try {
        	System.out.println("In placeorder shopping cart");
        	ShoppingCart cart = (ShoppingCart) request.getAttribute("Shopping_Cart");
        	if(cart == null)
        	{
        		
        		cart = shoppingCartService.checkout();
        		
//        		for (ShoppingCartLine cline : cart.getCartlineList()) {
//        			System.out.println("cartline quantity " + cline.getQuantity());
//        		}
        		System.out.println("In place order" + cart);
        		request.setAttribute("Shopping_Cart", cart);
        		return "forward:order/create";
        	}
        	
         } catch (NotEnoughProductsInStockException e) {
            //
        	 System.out.println(e.getMessage());
        	 model.addAttribute("outOfStockMessage", e.getMessage());
        	 model.addAttribute("products", shoppingCartService.getProductsInCart());
        	 model.addAttribute("total", shoppingCartService.getTotal().toString());
        	 return "shoppingcart/shoppingCart";
        	 
        }
        return "forward:order/create";
    }
    
}
