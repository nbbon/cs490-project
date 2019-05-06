package mum.pmp.mstore.integration.mockpayment.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mum.pmp.mstore.integration.mockpayment.model.VisaCardTransaction;

@Repository
@Transactional
public interface VisaCardTransactionRepository extends JpaRepository<VisaCardTransaction, Long> {

}
