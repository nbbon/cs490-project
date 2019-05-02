package mum.pmp.mstore.repository.profile;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import mum.pmp.mstore.model.Admin;

@Repository
@Transactional
public class AdminDAO {

	@PersistenceContext
	private EntityManager em;

	public List<Admin> findAllAdmins(){
		
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
}
