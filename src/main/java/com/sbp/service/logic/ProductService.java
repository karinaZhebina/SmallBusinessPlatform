package com.sbp.service.logic;

import com.sbp.service.entity.Page;
import com.sbp.service.entity.Product;
import com.sbp.service.exception.ServiceException;

public interface ProductService {

  Product getById(String id) throws ServiceException;

  Page<Product> getAllProducts(Page<Product> productPageRequest) throws ServiceException;

  boolean delete(Product product) throws ServiceException;

  boolean create(Product product) throws ServiceException;

  boolean update(Product product) throws ServiceException;

}
