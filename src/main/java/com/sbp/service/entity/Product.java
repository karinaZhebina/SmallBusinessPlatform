package com.sbp.service.entity;

import com.sbp.dao.entity.ProductRow;

import java.math.BigDecimal;

public class Product {
  private String product_id;
  private String name;
  private String description;
  private String business_owner_id;
  private BigDecimal price;

  public Product() {
  }

  public Product(String product_id, String name, String description, String business_owner_id, BigDecimal price) {
    this.product_id = product_id;
    this.name = name;
    this.description = description;
    this.business_owner_id = business_owner_id;
    this.price = price;
  }

  public Product(ProductRow productRow) {
    this.product_id = productRow.getProduct_id();
    this.name = productRow.getName();
    this.description = productRow.getDescription();
    this.business_owner_id = productRow.getBusiness_owner_id();
    this.price = productRow.getPrice();
  }

  public Product(String product_id) {
    this.product_id = product_id;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Product product = (Product) o;

    if (!product_id.equals(product.product_id)) return false;
    if (!name.equals(product.name)) return false;
    if (description != null ? !description.equals(product.description) : product.description != null) return false;
    if (!business_owner_id.equals(product.business_owner_id)) return false;
    return price != null ? price.equals(product.price) : product.price == null;
  }

  @Override
  public int hashCode() {
    int result = product_id.hashCode();
    result = 31 * result + name.hashCode();
    result = 31 * result + (description != null ? description.hashCode() : 0);
    result = 31 * result + business_owner_id.hashCode();
    result = 31 * result + (price != null ? price.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Product{" +
        "product_id='" + product_id + '\'' +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", business_owner_id='" + business_owner_id + '\'' +
        ", price=" + price +
        '}';
  }
}
