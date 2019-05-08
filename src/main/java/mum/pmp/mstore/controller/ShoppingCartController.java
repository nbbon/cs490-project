package mum.pmp.mstore.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mum.pmp.mstore.domain.ShoppingCart;
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

    @GetMapping
    public ModelAndView shoppingCart() {
        ModelAndView modelAndView = new ModelAndView("/shoppingcart/shoppingCart");
        modelAndView.addObject("products", shoppingCartService.getProductsInCart());
        modelAndView.addObject("total", shoppingCartService.getTotal().toString());
        return modelAndView;
    }

    @PostMapping("/addProduct")
    public ModelAndView addProductToCart(@RequestBody int productId) {
    	System.out.println("Here in add to cart product controller");
        productService.getProduct(productId).ifPresent(shoppingCartService::addProduct);
        return shoppingCart();
    }

    @GetMapping("/removeProduct/{productId}")
    public ModelAndView removeProductFromCart(@PathVariable("productId") int productId) {
        productService.getProduct(productId).ifPresent(shoppingCartService::removeProduct);
        return shoppingCart();
    }

    @PostMapping("/placeOrder")
    public String checkout(HttpServletRequest request,
			HttpServletResponse response) {
        try {
        	System.out.println("In placeorder shopping cart");
        	ShoppingCart cart = (ShoppingCart) request.getAttribute("Shopping_Cart");
        	if(cart == null)
        	{
        		cart = shoppingCartService.checkout();
        		System.out.println("In place order" + cart);
        		request.setAttribute("Shopping_Cart", cart);
        		//RequestDispatcher rd = request.getRequestDispatcher("/order/create");
        		return "forward:" + "/order/create";
        	}
        	
         } catch (NotEnoughProductsInStockException e) {
            //return shoppingCart().addObject("outOfStockMessage", e.getMessage());
        	 System.out.println(e.getMessage());
        }
        return "forward:" + "/order/create";
    }
    
}
