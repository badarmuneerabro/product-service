package com.shop.productservice.dto;

import java.math.BigInteger;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class ProductDTO 
{
	private String id;
	
	@NotBlank(message = "Product name is required.")
	private String name;
	
	@Size(min = 20, max = 200, message = "Description must not exceed 200 characters.")
	@NotBlank(message = "Description is required.")
	private String description;
	
	private String category;
	
	@NotNull(message = "Unit price is required.")
	@Positive(message = "Unit price must be positive.")
	private Double unitPrice;
	
	@Positive(message = "Stock quantity must be positive.")
	private Long stockQuantity;
	
	private boolean isAvailable;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Long getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(Long stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	public boolean getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", name=" + name + ", description=" + description + ", category=" + category
				+ ", unitPrice=" + unitPrice + ", stockQuantity=" + stockQuantity + ", isAvailable=" + isAvailable
				+ "]";
	}
	
	

}
