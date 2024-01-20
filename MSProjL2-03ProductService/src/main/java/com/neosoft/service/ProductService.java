package com.neosoft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosoft.model.Product;
import com.neosoft.respository.IProductRepo;

@Service
public class ProductService implements IService{
	
	@Autowired
	private IProductRepo repo;
	
	@Override
	public String saveProduct(Product prod) {
		Product product=null;
		try {
		 product = repo.save(prod);
		 return product.getId()+" is saved successfully";
		}catch(Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}
	@Override
	public List<Product> fetchAllProduct() {
		List<Product> li=(List<Product>) repo.findAll();
		return li;
	}
	@Override
	public String fullUpdate(Product prod) {
		Optional<Product> OptProd = repo.findById(prod.getId());
		Product updatedProd =null;
		
		if(OptProd.isPresent()) {
			
			updatedProd = repo.save(prod);
			return updatedProd.getId()+" is updated successfully";
		}else {
			return updatedProd.getId()+" not found";
		}
		
	}
	@Override
	public String partialUpdate(Double price,Integer id) {
		Optional<Product> optProd=repo.findById(id);
		Product updatedProd=null;
		if(optProd.isPresent()) {
			Product prod=optProd.get();
			prod.setPrice(price);
			updatedProd=repo.save(prod);
			System.out.println("=========updatedProd "+updatedProd);
			return updatedProd.getId()+" is updated successfully";
		}else {
			return updatedProd.getId()+" is not found";
		}
		
	}
	@Override
	public String deleteProduct(Integer id) {
		Optional<Product> optProd=repo.findById(id);
		if(optProd.isPresent()) {
		repo.deleteById(id);
		return id+" successfully deleted";
		}else {
			return id+" not found";
		}
	}
}
