//package mum.pmp.mstore.domain;
//
//import java.time.LocalDate;
//
//public class OrderFactory {
//	
//	public static Order createOrder(ShoppingCart cart) {
//        Order order = new Order(cart.getCartId(), LocalDate.now(), "placed");
//        for (ShoppingCartLine cline :  cart.getShoppingCartLines()) {
//            OrderLine oline = new OrderLine();
//            Product product = new Product(cline.getProduct().getProductNumber(), cline.getProduct().getProductName(), cline.getProduct().getPrice(), cline.getProduct().getDescription());    
//            oline.setProduct(product);
//            oline.setQuantity(cline.getQuantity());
//            order.addOrderLine(oline);
//        }
//        return order;
//
//}
//}