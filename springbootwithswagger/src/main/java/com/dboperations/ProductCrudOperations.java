package com.dboperations;

import static com.mongodb.client.model.Filters.eq;

import org.bson.conversions.Bson;

import com.model.Product;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

public class ProductCrudOperations {
	String collectionName;
	boolean useCF;

	public ProductCrudOperations(String colName, boolean usecf, String mongoUrl) {
		collectionName = colName;
		useCF = usecf;
		if (useCF == false) {
			MongoOperations.getInstance(false).bindToDb(mongoUrl, "localDB");
		}
	}

	public Iterable<Product> findAll() {
		MongoCollection<Product> collection = MongoOperations.getInstance(useCF).getDb().getCollection(collectionName,
				Product.class);
		return collection.find();
	}

	public Product findOne(String productId) {
		MongoCollection<Product> collection = MongoOperations.getInstance(useCF).getDb().getCollection(collectionName,
				Product.class);
		Product foundProduct = collection.find(eq("productId", productId)).first();
		return foundProduct;
	}

	public Product save(Product productObj) {
		MongoOperations.getInstance(useCF).createCollection(collectionName);
		MongoCollection<Product> collection = MongoOperations.getInstance(useCF).getDb().getCollection(collectionName,
				Product.class);
		collection.insertOne(productObj);
		return productObj;

	}

	public void delete(String productId) {
		MongoCollection<Product> collection = MongoOperations.getInstance(useCF).getDb().getCollection(collectionName,
				Product.class);
		collection.deleteOne(eq("productId", productId));

	}

	public Product updateProduct(Product productObj) {
		MongoOperations.getInstance(useCF).createCollection(collectionName);
		MongoCollection<Product> collection = MongoOperations.getInstance(useCF).getDb().getCollection(collectionName,
				Product.class);
		long numberOfexistingObj = collection.count(eq("productId", productObj.getProductId()));
		if (numberOfexistingObj > 0) {
			collection.findOneAndReplace(eq("productId", productObj.getProductId()), productObj);
		} else {
			save(productObj);
		}
		return productObj;
	}

}
