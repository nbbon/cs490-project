/*
 * Author: Yee Mon Zaw
 * Date: 01-May-2019
 * Class Name: ShoppingCartRepository
 * Package: mum.pmp.mstore.repository
 * Description:  ShoppingCart Repository for CRUD  operations ( add product,  and remove product)
 * 
 */

package mum.pmp.mstore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mum.pmp.mstore.domain.ShoppingCart;

@Repository
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, String>{

}
