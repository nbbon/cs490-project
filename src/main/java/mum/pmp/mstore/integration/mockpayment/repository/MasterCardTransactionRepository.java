package mum.pmp.mstore.integration.mockpayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mum.pmp.mstore.integration.mockpayment.model.MasterCardTransaction;

public interface MasterCardTransactionRepository extends JpaRepository<MasterCardTransaction, Long> {

}
