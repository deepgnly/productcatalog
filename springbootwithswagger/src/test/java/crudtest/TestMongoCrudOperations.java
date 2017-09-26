package crudtest;

import com.dboperations.ProductCrudOperations;

public class TestMongoCrudOperations {

	public static void main(String args[]) {

		boolean runOnCf = false;
		String COLLECTION_NAME = "ProductCollection";
		String MONGO_LOCAL_HOST_URL = "mongodb://localhost:27017";

		new ProductCrudOperations(COLLECTION_NAME, runOnCf, MONGO_LOCAL_HOST_URL).findAll();
	}
}
