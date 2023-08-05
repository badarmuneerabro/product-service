package com.shop.productservice.services;

import java.util.List;

import com.shop.productservice.dto.ProductDTO;
import com.shop.productservice.model.Product;

public interface ProductService 
{
	Product saveProduct(ProductDTO productDTO);

	List<ProductDTO> getAllProducts();
	
	ProductDTO getProductById(String id);
	
	void deleteProduct(String id);
}
