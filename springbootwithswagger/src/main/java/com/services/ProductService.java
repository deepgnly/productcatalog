package com.services;

import com.model.Product;

public interface ProductService {
	Iterable<Product> findAllProducts();

	Product getProductById(String id);

	Product createProduct(Product product);
	Product updateProduct(Product product);


	void deleteProduct(String id);

}
