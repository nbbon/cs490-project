package mum.pmp.mstore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mum.pmp.mstore.domain.Stock;
import mum.pmp.mstore.repository.StockRepository;
import mum.pmp.mstore.service.StockService;

@Repository
public class StockServiceImpl implements StockService{
	@Autowired
	StockRepository stockRepository;

	@Override
	public void addStock(Stock stock) {
		stockRepository.save(stock);		
	}

	@Override
	public Stock getStock(int id) {
		Optional<Stock> optional = stockRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public List<Stock> getStocks() {
		return (List<Stock>) stockRepository.findAll();
	}

	@Override
	public void deleteStock(int id) {
		Optional<Stock> optional = stockRepository.findById(id);
		if (optional.isPresent()) {
			stockRepository.deleteById(id);
		}
		
	}

}
