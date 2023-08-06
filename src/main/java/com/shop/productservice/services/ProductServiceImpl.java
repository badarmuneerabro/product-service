package com.shop.productservice.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.productservice.dto.ProductDTO;
import com.shop.productservice.model.Product;
import com.shop.productservice.repositories.ProductRepository;

@Service 
public class ProductServiceImpl implements ProductService 
{
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public Product saveProduct(ProductDTO productDTO) 
	{
		Product product = modelMapper.map(productDTO, Product.class);
		
		return productRepository.save(product);
		
		
	}
	
	@Override
	public List<ProductDTO> getAllProducts() 
	{
		List<Product> list = productRepository.findAll();
		
		List<ProductDTO> allProducts = new ArrayList<>();
		
		for(Product p : list)
		{
			ProductDTO productDTO = modelMapper.map(p, ProductDTO.class);
			
			allProducts.add(productDTO);
		}
		
		return allProducts;
	}
	
	@Override
	public ProductDTO getProductById(String id) 
	{
		Product product = productRepository.findById(id).orElse(null);
		if(product == null)
			return null;
		ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
		
		return productDTO;
	}
	
	@Override
	public void deleteProduct(String id) 
	{
		
		productRepository.deleteById(id);
		
	}

}
