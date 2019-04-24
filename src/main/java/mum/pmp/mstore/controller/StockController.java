package mum.pmp.mstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import mum.pmp.mstore.domain.Stock;
import mum.pmp.mstore.service.StockService;

@Controller
public class StockController {
	
	@Autowired 
	StockService stockService;
	
	
	
	
	  @GetMapping(value="/stock")
	    public String stocksList(Model model) {
	        model.addAttribute("stocksList", stockService.getStocks());
	        return "stock/stocksList";
	    }
	
	
	  @GetMapping(value={"/stocksEdit","/stocksEdit/{id}"})
	    public String editStock(Model model, @PathVariable(required = false, name = "id") Integer id) {
	        if (null != id) {
	            model.addAttribute("stocks", stockService.getStock(id));
	        } else {
	            model.addAttribute("stocks", new Stock());
	        }
	        return "stock/stocksEdit";
	    }
	
	
	  @PostMapping(value="/stocksEdit") 
	  public String addStock(Model model, Stock stock) { 
		  stockService.addStock(stock);
	  model.addAttribute("stocksList", stockService.getStocks()); 
	  return "stock/stocksList"; }
	 
	  
	  @DeleteMapping(value="/stocksDelete/{id}")
	    public String deleteStock(Model model, @PathVariable(required = true, name = "id") Integer id) {
		  	stockService.deleteStock(id);        
			model.addAttribute("stocksList", stockService.getStock(id));
	        return "stock/stocksList";
	    }
	
	
	
	

}
