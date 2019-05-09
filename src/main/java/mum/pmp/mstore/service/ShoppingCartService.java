/*
 * Author: Yee Mon Zaw
 * Date: 26-May-2019
 * Class Name: ShoppingCartService
 * Package: mum.pmp.mstore.service
 * Description:  Service class for Shopping Cart add/remove Products, and checkout processes
 * 
 */

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
