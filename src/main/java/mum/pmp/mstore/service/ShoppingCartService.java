package mum.pmp.mstore.service;

import java.math.BigDecimal;
import java.util.Map;

import mum.pmp.mstore.domain.Product;
import mum.pmp.mstore.exception.NotEnoughProductsInStockException;

public interface ShoppingCartService {

    void addProduct(Product product);

    void removeProduct(Product product);

    Map<Product, Integer> getProductsInCart();

    void checkout() throws NotEnoughProductsInStockException;

    Double getTotal();
}
