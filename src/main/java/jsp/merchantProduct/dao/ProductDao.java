package jsp.merchantProduct.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import jsp.merchantProduct.dto.Merchant;
import jsp.merchantProduct.dto.Product;

public class ProductDao {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
	EntityManager em = emf.createEntityManager();
	
	
	
	public Product addProduct(Product p, int mid) {
		
		Merchant m = em.find(Merchant.class, mid);
		
		if(m != null) {
			
			p.setM(m);	
			EntityTransaction etran = em.getTransaction();
			etran.begin();
			em.persist(p);
			etran.commit();
			return p;
			}
			else return null;
	}



	public Product updateProduct(Product p, int mid) {
		
		Merchant m = em.find(Merchant.class, mid);
		EntityTransaction etran = em.getTransaction();
		etran.begin();
		Product pdb = em.find(Product.class, p.getId());
		if(pdb != null) {
			pdb.setName(p.getName());
			pdb.setBrand(p.getBrand());
			pdb.setCategory(p.getCategory());
			pdb.setCost(p.getCost());
			pdb.setM(m);
			etran.commit();
			return p;
		}
		else
		return null;
	}



	@SuppressWarnings("unchecked")
	public List<Product> findProductByMerchantName(String mname) {
		
		Query q = em.createQuery("select p from Product p where p.m.name=?1");
		q.setParameter(1, mname);
		return q.getResultList();
	}



	public List<Product> findProductByMerchantId(int mid) {
		
		Query q = em.createQuery("select p from Product p where p.m.id=?1");
		q.setParameter(1, mid);
		@SuppressWarnings("unchecked")
		List<Product> p = q.getResultList();
		return p;
	}
}
