package mum.pmp.mstore.service.settlement;

import java.util.Map;

import mum.pmp.mstore.domain.Order;

public interface SettlementService {

	Map<String, String> buildInvoice(Order order);

}
