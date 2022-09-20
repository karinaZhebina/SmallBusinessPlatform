package com.sbp.dao.entity;

import com.sbp.service.entity.Product;

import java.math.BigDecimal;

public class ProductRow {

  private String product_id;
  private String name;
  private String description;
  private String business_owner_id;
  private BigDecimal price;

  public ProductRow() {
  }

  public ProductRow(String product_id, String name, String description, String business_owner_id, BigDecimal price) {
    this.product_id = product_id;
    this.name = name;
    this.description = description;
    this.business_owner_id = business_owner_id;
    this.price = price;
  }

  public ProductRow(Product product) {
    this.product_id = product.getProduct_id();
    this.name = product.getName();
    this.description = product.getDescription();
    this.business_owner_id = product.getBusiness_owner_id();
    this.price = product.getPrice();
  }

  public String getProduct_id() {
    return product_id;
  }

  public void setProduct_id(String product_id) {
    this.product_id = product_id;
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

  public String getBusiness_owner_id() {
    return business_owner_id;
  }

  public void setBusiness_owner_id(String business_owner_id) {
    this.business_owner_id = business_owner_id;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }
}
