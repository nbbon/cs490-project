package mum.pmp.mstore.integration.mockpayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mum.pmp.mstore.integration.mockpayment.model.MasterCardBalance;

public interface MasterCardBalanceRepository extends JpaRepository<MasterCardBalance, String> {

}
