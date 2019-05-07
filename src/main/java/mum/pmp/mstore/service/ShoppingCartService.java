package mum.pmp.mstore.service;

import java.util.Map;

import mum.pmp.mstore.domain.Product;
import mum.pmp.mstore.domain.ShoppingCart;
import mum.pmp.mstore.exception.NotEnoughProductsInStockException;

public interface ShoppingCartService {

    void addProduct(Product product);

    void removeProduct(Product product);

    Map<Product, Integer> getProductsInCart();

    ShoppingCart checkout() throws NotEnoughProductsInStockException;

    Double getTotal();
}
