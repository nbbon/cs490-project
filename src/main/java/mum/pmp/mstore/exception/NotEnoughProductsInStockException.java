/*
 * Author: Yee Mon Zaw
 * Date: 02-May-2019
 * Class Name: NotEnoughProductsInStockException
 * Package: mum.pmp.mstore.exception
 * Description: NotEnoughProductsInStockException - Invalid no. of stocks
 */

package mum.pmp.mstore.exception;

import mum.pmp.mstore.domain.Product;

public class NotEnoughProductsInStockException extends Exception {

    private static final String DEFAULT_MESSAGE = "Not enough products in stock";

    public NotEnoughProductsInStockException() {
        super(DEFAULT_MESSAGE);
    }

    public NotEnoughProductsInStockException(Product product) {
        super(String.format("Not enough %s products in stock. Only %d left", 
        		product.getProductName(), product.getStock().getQuantity()));
    }

}
