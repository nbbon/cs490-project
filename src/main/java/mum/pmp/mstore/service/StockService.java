package mum.pmp.mstore.service;

import java.util.List;

import mum.pmp.mstore.domain.Stock;

public interface StockService {
	
	void addStock(Stock stock);
	Stock getStock(int id);
	List<Stock> getStocks();
	void deleteStock(int id);
	

}
