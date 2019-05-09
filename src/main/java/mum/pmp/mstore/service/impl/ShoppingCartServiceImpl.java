/*
 * Author: Yee Mon Zaw
 * Date: 26-May-2019
 * Class Name: ShoppingCartServiceImpl
 * Package: mum.pmp.mstore.service
 * Description:  Service class for Shopping Cart add/remove Products, and checkout processes
 * 
 */

package mum.pmp.mstore.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import mum.pmp.mstore.domain.Order;
import mum.pmp.mstore.domain.Product;
import mum.pmp.mstore.domain.ShoppingCart;
import mum.pmp.mstore.domain.ShoppingCartLine;
import mum.pmp.mstore.exception.NotEnoughProductsInStockException;
import mum.pmp.mstore.repository.ProductRepository;
import mum.pmp.mstore.service.OrderService;
import mum.pmp.mstore.service.ShoppingCartService;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ProductRepository productRepository;
    
    @Autowired
    private OrderService orderService;

    private Map<Product, Integer> products = new HashMap<>();

    @Autowired
    public ShoppingCartServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public synchronized void addProduct(Product product) {
        if (products.containsKey(product)) {
            products.replace(product, products.get(product) + 1);
        } else {
            products.put(product, 1);
        }
    }

    @Override
    public synchronized void removeProduct(Product product) {
        if (products.containsKey(product)) {
            if (products.get(product) > 1)
                products.replace(product, products.get(product) - 1);
            else if (products.get(product) == 1) {
                products.remove(product);
            }
        }
    }

    @Override
    public Map<Product, Integer> getProductsInCart() {
    	System.out.println("products >> " + products);
        return Collections.unmodifiableMap(products);
    }

    /**
     * Checkout will rollback if there is not enough of some product in stock
     *
     * @throws NotEnoughProductsInStockException
     */
    @Override
    public ShoppingCart checkout() throws NotEnoughProductsInStockException {
        Product product;
        
        ArrayList<ShoppingCartLine> cartLineList = new ArrayList<ShoppingCartLine>();
        
        System.out.println("In shopping Cart checkout.");
        
        //Update stock value
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            // Refresh quantity for every product before checking
            product = productRepository.findById(entry.getKey().getId()).get();  
            if (product.getStock().getQuantity() < entry.getValue())
                throw new NotEnoughProductsInStockException(product);
            int quantity = product.getStock().getQuantity() - entry.getValue();
            
            entry.getKey().getStock().setQuantity(quantity);
            
            productRepository.save(product);
            
            //Create cart line
            ShoppingCartLine cartLine = new ShoppingCartLine();
            cartLine.setProduct(product);
            cartLine.setQuantity(entry.getValue());
            cartLineList.add(cartLine);
        }
        
        // Add to shopping Cart
        ShoppingCart cart = new ShoppingCart();
        cart.setCartid("Cart1");
        cart.setCartlineList(cartLineList);
        products.clear();
        return cart;
    }

    @Override
    public Double getTotal() {
    	return products.entrySet().stream()
    			.map(entry -> (entry.getKey().getPrice() * entry.getValue().doubleValue()))
    			.reduce(Double::sum)
    			.orElse(Double.MIN_VALUE);
    }
}
