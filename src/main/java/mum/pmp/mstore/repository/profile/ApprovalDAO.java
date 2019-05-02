package mum.pmp.mstore.repository.profile;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import mum.pmp.mstore.model.Admin;
import mum.pmp.mstore.model.Vendor;

@Repository
@Transactional
public class ApprovalDAO {

	@PersistenceContext
	private EntityManager em;

	public List<Admin> findNewAdmins(){
		
		String sql = "select p.first_name,p.last_name,p.email,p.phone,p.enable from profile p where p.email in(select usr.username from user usr where usr.enabled = false and usr.id in (select u.user_id from user_roles  u inner join role r where r.role=\'ADMIN\' and r.id=u.role_id))";
		Query query =  em.createNativeQuery (sql);
		
		@SuppressWarnings("unchecked")
		List<Object[]> admins = query.getResultList();
		List<Admin> adminList = new ArrayList<Admin>();
		
		for (Object[] a : admins) {
			System.out.println(">>" + a[0] + ", " + a[1]);
		    Admin admin = new Admin();
		    admin.setFirstName(a[0].toString());
		    admin.setLastName(a[1].toString());
		    admin.setEmail(a[2].toString());
		    admin.setPhone(a[3].toString());
		    if(a[4].equals(0))
		    	admin.setEnable(false);
		    else 
		    	admin.setEnable(true);
		    adminList.add(admin);
		}

		
		return adminList;
	}
	
	public List<Vendor> findNewVendors(){
		
		String sql = "select p.vendor_name,p.vendor_number,p.email,p.phone,p.enable,p.reg_id,p.contact_person from profile p "
				+ "where p.email in(select usr.username from user usr where usr.enabled = false and usr.id in (select u.user_id from user_roles  u inner join role r where r.role=\'VENDOR\' and r.id=u.role_id))";
		Query query =  em.createNativeQuery (sql);
		
		@SuppressWarnings("unchecked")
		List<Object[]> vendors = query.getResultList();
		List<Vendor> vendorList = new ArrayList<Vendor>();
		
		for (Object[] a : vendors) {
			System.out.println(">>" + a[0] + ", " + a[1]);
			Vendor vendor = new Vendor();
			vendor.setVendorName(a[0].toString());
			vendor.setVendorNumber(a[1].toString());
			vendor.setEmail(a[2].toString());
			vendor.setPhone(a[3].toString());
			vendor.setEnable((boolean) a[4]);
			vendor.setRegId(a[5].toString());
			vendor.setContactPerson(a[6].toString());
			
		    vendorList.add(vendor);
		}

		
		return vendorList;
	}
}
