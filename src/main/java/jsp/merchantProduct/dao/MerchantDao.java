package jsp.merchantProduct.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import jsp.merchantProduct.dto.Merchant;

public class MerchantDao {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
	EntityManager em = emf.createEntityManager();
	
	
	
	public Merchant saveMerchant(Merchant m) {
		
		EntityTransaction etran = em.getTransaction();
		etran.begin();
		em.persist(m);
		etran.commit();
		return m;
	}
	
	
	public Merchant updateMerchant(Merchant m) {
		
		EntityTransaction etran = em.getTransaction();
		etran.begin();
		Merchant mdb = em.find(Merchant.class, m.getId());
		if(mdb != null) {
			mdb.setName(m.getName());
			mdb.setGst_num(m.getGst_num());
			mdb.setEmail(m.getEmail());
			mdb.setPhone(m.getPhone());
			mdb.setPassword(m.getPassword());
			etran.commit();
			return m;
		}
		else {
			return null;
		}	
	}

	
	public Merchant findMerchantById(int mid) {
		
		Merchant m = em.find(Merchant.class, mid);
		return m;
	}


	public Merchant findMerchantByPhonePassword(long mphone, String mpass) {
		
		Query q = em.createQuery("select m from Merchant m where m.phone = :phone and m.password = :pass");
		q.setParameter("phone", mphone);
		q.setParameter("pass", mpass);
		try {
			Merchant m = (Merchant) q.getSingleResult();
			return m;
		}
		catch(NoResultException e){
			System.err.println("No result found in the database!!");
			return null;
		}
	}


	public Merchant findMerchantByEmailPassword(String memail, String mpass) {
		
		Query q = em.createQuery("select m from Merchant m where m.email = :email and m.password = :pass");
		q.setParameter("email", memail);
		q.setParameter("pass", mpass);
		try {
			Merchant m = (Merchant) q.getSingleResult();
			return m;
		}
		catch(NoResultException e){
			System.err.println("No result found in the database!!");
			return null;
		}
	}


	public Merchant findMerchantByProductId(int pid) {

		Query q = em.createQuery(
				"select p.m from Product p where p.id=?1");

		q.setParameter(1, pid);

		try {
			Merchant m = (Merchant) q.getSingleResult();
			return m;
		}
		catch(Exception e) {
			return null;
		}
	}
	

}
