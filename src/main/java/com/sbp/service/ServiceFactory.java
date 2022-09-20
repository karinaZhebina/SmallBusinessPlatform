package com.sbp.service;

import com.sbp.service.logic.ProductService;
import com.sbp.service.logic.impl.ProductServiceImpl;

public class ServiceFactory {

  private static final ServiceFactory INSTANCE = new ServiceFactory();

  private final ProductService productService = new ProductServiceImpl();

  private ServiceFactory() {
  }

  public static ServiceFactory getInstance() {
    return INSTANCE;
  }

  public ProductService getProductService() {
    return productService;
  }

}
