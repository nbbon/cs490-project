package mum.pmp.mstore.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import mum.pmp.mstore.domain.Product;
import mum.pmp.mstore.exception.NotEnoughProductsInStockException;
import mum.pmp.mstore.repository.ProductRepository;
import mum.pmp.mstore.service.ShoppingCartService;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ProductRepository productRepository;

    private Map<Product, Integer> products = new HashMap<>();

    @Autowired
    public ShoppingCartServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void addProduct(Product product) {
        if (products.containsKey(product)) {
            products.replace(product, products.get(product) + 1);
        } else {
            products.put(product, 1);
        }
    }

    @Override
    public void removeProduct(Product product) {
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
        return Collections.unmodifiableMap(products);
    }

    /**
     * Checkout will rollback if there is not enough of some product in stock
     *
     * @throws NotEnoughProductsInStockException
     */
    @Override
    public void checkout() throws NotEnoughProductsInStockException {
        Product product;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            // Refresh quantity for every product before checking
            product = productRepository.findById(entry.getKey().getId()).get();  
            if (product.getStock().getQuantity() < entry.getValue())
                throw new NotEnoughProductsInStockException(product);
            entry.getKey().getStock().setQuantity(product.getStock().getQuantity() - entry.getValue());
            
            productRepository.save(product);
        }
//        productRepository.save(products.keySet());
//        productRepository.flush();
        
        
        products.clear();
    }

    @Override
    public Double getTotal() {
//        return products.entrySet().stream()
//                .map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
//                .reduce(BigDecimal::add)
//                .orElse(BigDecimal.ZERO);
        
//        return products.entrySet().stream()
//        			.map(entry -> ((entry.getKey().getPrice()) * (BigDecimal.valueOf(entry.getValue()))))
//        			 .reduce(BigDecimal::add)
//        			 .orElse(BigDecimal.ZERO);
//        			
    	
    	return products.entrySet().stream()
    			.map(entry -> (entry.getKey().getPrice() * entry.getValue().doubleValue()))
    			.reduce(Double::sum)
    			.orElse(Double.MIN_VALUE);
    }
}
