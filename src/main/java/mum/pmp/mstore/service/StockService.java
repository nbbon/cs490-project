/*
 * Author: Jean Chrisner Jean Charles
 * Date: 24-Apr-2019
 * Class Name: StockService
 * Package: service
 * Description: contains the business logic of stock module to operate on the data sent to and from the DAO and the client.
 * 
 */

package mum.pmp.mstore.service;

import java.util.List;

import mum.pmp.mstore.domain.Stock;

public interface StockService {
	
	void addStock(Stock stock);
	Stock getStock(int id);
	List<Stock> getStocks();
	void deleteStock(int id);
	

}
