package mum.pmp.mstore.integration.mockpayment.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mum.pmp.mstore.integration.mockpayment.model.MasterCardBalance;

@Repository
@Transactional
public interface MasterCardBalanceRepository extends JpaRepository<MasterCardBalance, String> {

}
