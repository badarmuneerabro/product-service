package com.shop.productservice.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.shop.productservice.dto.ProductDTO;
import com.shop.productservice.model.Product;
import com.shop.productservice.services.ProductService;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductRestController 
{
	@Autowired
	private ProductService productService;
	
	@GetMapping("/")
	@CrossOrigin
	public ResponseEntity<List<ProductDTO>> getAllProducts()
	{
		List<ProductDTO> list = productService.getAllProducts();
		
		return new ResponseEntity<List<ProductDTO>>(list, HttpStatus.OK);
	}
	
	@PostMapping("/")
	@CrossOrigin
	public ResponseEntity<?> saveProduct(@RequestBody ProductDTO productDTO) 
	{
		Product product = productService.saveProduct(productDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(product.getId()).toUri();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);
		
		return new ResponseEntity<>(null, headers, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{productId}")
	@CrossOrigin
	public ResponseEntity<ProductDTO> getProduct(@PathVariable("productId") String productId)
	{
		ProductDTO productDTO = productService.getProductById(productId);
		
		return new ResponseEntity<>(productDTO, HttpStatus.OK);
	}
	
	
	@PutMapping("/{productId}")
	@CrossOrigin
	public ResponseEntity<ProductDTO> updateProduct(@PathVariable("productId") String productId, @RequestBody ProductDTO productDTO)
	{
		ProductDTO product = productService.getProductById(productId);
		
		product.setId(productId);
		product.setCategory(productDTO.getCategory());
		product.setDescription(productDTO.getDescription());
		product.setIsAvailable(productDTO.getIsAvailable());
		product.setName(productDTO.getName());
		product.setStockQuantity(productDTO.getStockQuantity());
		product.setUnitPrice(productDTO.getUnitPrice());
		
		productService.saveProduct(product);
		
		return new ResponseEntity<ProductDTO>(product, HttpStatus.OK);
	}
	
	@DeleteMapping("/{productId}")
	@CrossOrigin
	public ResponseEntity<?> deleteProduct(@PathVariable("productId") String productId)
	{
		productService.deleteProduct(productId);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

}
