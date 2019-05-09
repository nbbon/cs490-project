/*
 * Author: Bon Nguyen
 * Date: 25-Apr-2019
 * Class Name: VisaCardBalanceRepository
 * Module: Mock Payment
 * Description: The repository interface VisaCardBalance entity. Used as interface from ER to DB.
 * Used by MockPaymentService
 * 
 */

package mum.pmp.mstore.integration.mockpayment.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mum.pmp.mstore.integration.mockpayment.model.VisaCardBalance;

@Repository
@Transactional
public interface VisaCardBalanceRepository extends JpaRepository<VisaCardBalance, String> {

}
