package jsp.merchantProduct.controller;

import java.util.List;
import java.util.Scanner;

import jsp.merchantProduct.dao.MerchantDao;
import jsp.merchantProduct.dao.ProductDao;
import jsp.merchantProduct.dto.Merchant;
import jsp.merchantProduct.dto.Product;


public class MerchantProductController {
	
	static Scanner sc = new Scanner(System.in);
	static MerchantDao mdao = new MerchantDao();
	static ProductDao pdao = new ProductDao();
	
	public static void main(String[] args) {
		
		while(true) {
			System.out.println("Save Merchant –––– 1");
			System.out.println("Update Merchant –––– 2");
			System.out.println("Find Merchant by Id –––– 3");
			System.out.println("Find Merchant by Phone & Password –––– 4");
			System.out.println("Find Merchant by Email & Password –––– 5");
			
			System.out.println("Add Product –––– 6");
			System.out.println("Update Product –––– 7");
			System.out.println("Find Product by Merchant name –––– 8");
			System.out.println("Find Product by Merchant Id –––– 9");
			System.out.println("Find Merchant by Product Id –––– 10");
			
			System.out.println("Enter your Choice!. ");
			int choice = sc.nextInt();
			
			switch(choice) {
			
			case 1:saveMerchant();
			break;
			
			case 2:updateMerchant();
			break;
			
			case 3:findMerchantById();
			break;
			
			case 4:findMercahntByPhonePassword();
			break;
			
			case 5:findMerchantByEmailPassword();
			break;
			
			case 6:addProduct();
			break;
			
			case 7:updateProduct();
			break;
			
			case 8:findProductByMerchantName();
			break;
			
			case 9:findProductByMerchantId();
			break;
			
			case 10:findMerchantByProductId();
			break;
			}
		}
	}
	

	private static void findMerchantByProductId() {
		
		System.out.println("Enter the Product Id");
		int pid = sc.nextInt();
		
		Merchant mdb = mdao.findMerchantByProductId(pid);
		if(mdb != null) {
			System.out.println(mdb);
		}
		else {
			System.err.println("No Record found int the Database!!");
		}
		
	}


	private static void findProductByMerchantId() {
		
		System.out.println("Enter the Merchant Id");
		int mid = sc.nextInt();
		
		List<Product> pdb = pdao.findProductByMerchantId(mid);
		if(pdb.size()>0) {
			for(Product p : pdb) {
				System.out.println(p);
			}
		}
		else {
			System.out.println("No record found for Mercahnt name");
		}
		
	}


	private static void findProductByMerchantName() {
		
		System.out.println("Enter the Merchant Name ");
		String mname = sc.next();
		
		List<Product> pdb = pdao.findProductByMerchantName(mname);
		if(pdb.size()>0) {
			for(Product p : pdb) {
				System.out.println(p);
			}
		}
		else {
			System.out.println("No record found for Mercahnt name");
		}
	}


	private static void updateProduct() {
		
		System.out.println("Enter product info :–– Id, name, brand, category, cost, Merchant-Id");
		Product p = new Product();
		p.setId(sc.nextInt());
		p.setName(sc.next());
		p.setBrand(sc.next());
		p.setCategory(sc.next());
		p.setCost(sc.nextDouble());
		int mid = sc.nextInt();
		
		Product pdb = pdao.updateProduct(p, mid);
		if(pdb != null ) {
			System.out.println("Product is updated with Id: "+pdb.getId());
		}
		else {
			System.out.println("Record not found in the Database!!");
		}
	}


	private static void addProduct() {
		
		System.out.println("Enter product info :–– name, brand, category, cost , MerchantId");
		Product p = new Product();
		p.setName(sc.next());
		p.setBrand(sc.next());
		p.setCategory(sc.next());
		p.setCost(sc.nextDouble());
		int mid = sc.nextInt();
		
		Product pdb = pdao.addProduct(p, mid);
		System.out.println("Product Record Inerted with Id: "+pdb.getId());
	}


	private static void findMerchantByEmailPassword() {
		
		System.out.println("Enter Email of mercahnt!");
		String memail = sc.next();
		System.out.println("Enter Password of merchant");
		String mpass = sc.next();
		
		Merchant mdb = mdao.findMerchantByEmailPassword(memail, mpass);
		if(mdb != null) {
			System.out.println(mdb);
		}
		else System.out.println("No Record for the Phone and Password found!!..");	
		
	}


	private static void findMercahntByPhonePassword() {
		
		System.out.println("Enter Phone number of mercahnt!");
		long mphone = sc.nextLong();
		System.out.println("Enter Password of merchant");
		String mpass = sc.next();
		
		Merchant mdb = mdao.findMerchantByPhonePassword(mphone, mpass);
		if(mdb != null)
		System.out.println(mdb);
		else System.out.println("No Record for the Phone and Password found!!..");		
	}
	

	private static void findMerchantById() {
		
		System.out.println("Enter the Id of the Merchant!.. ");
		int mid = sc.nextInt();
		
		Merchant mdb = mdao.findMerchantById(mid);
		if(mdb != null ) {
			System.out.println(mdb);
		}
		else {
			System.out.println("No such Record exist!!..");
		}
	}

	
	private static void updateMerchant() {
		
		System.out.println("Enter merchant info :–– Id, name, gst_num, email, phone, password ");
		Merchant m = new Merchant();
		m.setId(sc.nextInt());
		m.setName(sc.next());
		m.setGst_num(sc.next());
		m.setEmail(sc.next());
		m.setPhone(sc.nextLong());
		m.setPassword(sc.next());
		
		Merchant mdb = mdao.updateMerchant(m);
		if(mdb != null) {
			System.out.println("Merchant is updated with Id: "+mdb.getId());
		}
		else {
			System.out.println("Merchant is not updates since id is invalid");
		}
	}
	

	private static void saveMerchant() {
		
		System.out.println("Enter merchant info :–– name, gst_num, email, phone, password ");
		Merchant m = new Merchant();
		m.setName(sc.next());
		m.setGst_num(sc.next());
		m.setEmail(sc.next());
		m.setPhone(sc.nextLong());
		m.setPassword(sc.next());
		
		Merchant mdb = mdao.saveMerchant(m);
		System.out.println("Merchant Record is inserted with id: "+mdb.getId());
	}
}
