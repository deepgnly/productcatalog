package com.services;

import org.springframework.stereotype.Service;

import com.dboperations.ProductCrudOperations;
import com.model.Product;

@Service
public class ProductServiceImpl implements ProductService {
	private ProductCrudOperations productCrudObj;

	public ProductServiceImpl(String collectionName,boolean useCF,String mongoUrl) {
		productCrudObj = new ProductCrudOperations(collectionName,useCF,mongoUrl);
	}

	public Iterable<Product> findAllProducts() {
		return this.productCrudObj.findAll();
	}

	public Product getProductById(String id) {
		return this.productCrudObj.findOne(id);
	}

	public Product createProduct(Product productObj) {
		return this.productCrudObj.save(productObj);
	}

	public void deleteProduct(String id) {
		this.productCrudObj.delete(id);
	}

	@Override
	public Product updateProduct(Product productObj) {
		// TODO Auto-generated method stub
		return productCrudObj.updateProduct(productObj);
	}


}