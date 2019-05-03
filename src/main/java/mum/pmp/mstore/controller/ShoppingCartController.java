package mum.pmp.mstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import mum.pmp.mstore.domain.Order;
import mum.pmp.mstore.exception.NotEnoughProductsInStockException;
import mum.pmp.mstore.service.ProductService;
import mum.pmp.mstore.service.ShoppingCartService;

@Controller
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    private final ProductService productService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, ProductService productService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }

    //Get : Show cart
    @GetMapping("/shoppingCart")
    public ModelAndView shoppingCart() {
        ModelAndView modelAndView = new ModelAndView("/shoppingcart/shoppingCart");
        modelAndView.addObject("products", shoppingCartService.getProductsInCart());
        modelAndView.addObject("total", shoppingCartService.getTotal().toString());
        return modelAndView;
    }

    @PostMapping("/shoppingCart/addProduct")
    public ModelAndView addProductToCart(@RequestBody int productId) {
    	System.out.println("Here in add to cart product controller");
        productService.getProduct(productId).ifPresent(shoppingCartService::addProduct);
        return shoppingCart();
    }

    @GetMapping("/shoppingCart/removeProduct/{productId}")
    public ModelAndView removeProductFromCart(@PathVariable("productId") int productId) {
        productService.getProduct(productId).ifPresent(shoppingCartService::removeProduct);
        return shoppingCart();
    }

    @PostMapping("/shoppingCart/placeOrder")
    public ModelAndView checkout() {
        try {
           shoppingCartService.checkout();
        } catch (NotEnoughProductsInStockException e) {
            return shoppingCart().addObject("outOfStockMessage", e.getMessage());
        }
        return shoppingCart();
    }
    
    
//    @PostMapping("/shoppingCart/placeOrder")
//	public String createOrder(ShoppingCart shoppingCart) {
//		System.out.println("Shopping Cart :");
//    	return "Place an order.";
//	}
}
