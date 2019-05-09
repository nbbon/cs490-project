
package mum.pmp.mstore.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mum.pmp.mstore.service.ProductService;

@Component
public class OrderFactory {
	@Autowired
	ProductService productService;
	
	public Order createOrder(ShoppingCart cart) {
		LocalDateTime now = LocalDateTime.now();
		String orderNumber = ("ORD" + now.getYear() + now.getMonth() + now.getNano());
		Order order = new Order(orderNumber  , LocalDate.now(), "placed");
		for (ShoppingCartLine cline : cart.getCartlineList()) {
			OrderLine oline = new OrderLine();
			Product product = productService.getProductByProductNumber(cline.getProduct().getProductNumber());
			oline.setProduct(product);
			oline.setQuantity(cline.getQuantity());
			order.addOrderLine(oline);
		}
		return order;

	}

}