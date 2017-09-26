package com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model.Product;
import com.services.ProductService;
import com.services.ProductServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class ProductController {

	private ProductService productService;
	public static final String PLAIN_RESPONSE_TYPE = "text/plain";
	public static final String JSON_RESPONSE_TYPE = "application/json";
	public static final String COLLECTION_NAME = "productCollection";
	boolean USE_CF = true;
	String MONGO_LOCAL_HOST_URL = "mongodb://localhost:27017";

	@Autowired
	public void setProductService() {
		this.productService = new ProductServiceImpl(COLLECTION_NAME, USE_CF, MONGO_LOCAL_HOST_URL);
	}

	@ApiOperation(value = "Creates a product")
	@RequestMapping(value = "/product/create", method = RequestMethod.POST, produces = PLAIN_RESPONSE_TYPE)
	public ResponseEntity<?> addProduct(@RequestBody Product product) {
		Product createdProduct = productService.createProduct(product);
		return new ResponseEntity<>("New Product added successfully", HttpStatus.OK);
	}

	@ApiOperation(value = "Updates a product")
	@RequestMapping(value = "/product/update/{productId}", method = RequestMethod.PUT, produces = PLAIN_RESPONSE_TYPE)
	public ResponseEntity<?> updateProduct(@PathVariable String productId, @RequestBody Product updatedProduct) {
		productService.updateProduct(updatedProduct);
		return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
	}

	@ApiOperation(value = "List all available products", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfull in finding the list"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(value = "/product/list", method = RequestMethod.GET, produces = "application/json")
	public Iterable<Product> list() {
		Iterable<Product> productList = productService.findAllProducts();
		return productList;
	}

	@ApiOperation(value = "Find product with an Id", response = Product.class)
	@RequestMapping(value = "/product/find/{productId}", method = RequestMethod.GET, produces = JSON_RESPONSE_TYPE)
	public Product findProduct(@PathVariable String productId) {
		Product product = productService.getProductById(productId);
		return product;
	}

	@ApiOperation(value = "Delete a product")
	@RequestMapping(value = "/product/delete/{productId}", method = RequestMethod.DELETE, produces = PLAIN_RESPONSE_TYPE)
	public ResponseEntity<?> delete(@PathVariable String productId) {
		productService.deleteProduct(productId);
		return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);

	}

}
