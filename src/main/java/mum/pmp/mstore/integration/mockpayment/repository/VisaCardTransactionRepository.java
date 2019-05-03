package mum.pmp.mstore.integration.mockpayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mum.pmp.mstore.integration.mockpayment.model.VisaCardTransaction;

public interface VisaCardTransactionRepository extends JpaRepository<VisaCardTransaction, Long> {

}
