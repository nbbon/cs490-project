/*
 * Author: Bon Nguyen
 * Date: 25-Apr-2019
 * Class Name: MasterCardBalanceRepository
 * Module: Mock Payment
 * Description: The repository interface MasterCardBalance entity. Used as interface from ER to DB.
 * Used by MockPaymentService
 * 
 */

package mum.pmp.mstore.integration.mockpayment.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mum.pmp.mstore.integration.mockpayment.model.MasterCardBalance;

@Repository
@Transactional
public interface MasterCardBalanceRepository extends JpaRepository<MasterCardBalance, String> {

}
