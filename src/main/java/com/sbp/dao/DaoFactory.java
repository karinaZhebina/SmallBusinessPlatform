package com.sbp.dao;

import com.sbp.dao.config.DataBaseConfig;
import com.sbp.dao.connection.ConnectionPool;
import com.sbp.dao.connection.impl.ConnectionPoolImpl;
import com.sbp.dao.logic.ProductDao;
import com.sbp.dao.logic.impl.ProductDaoImpl;

public class DaoFactory {

  private static final DaoFactory INSTANCE = new DaoFactory();

  private final ConnectionPool connectionPool = new ConnectionPoolImpl(new DataBaseConfig());

  private final ProductDao productDao = new ProductDaoImpl(connectionPool);

  private DaoFactory() {
  }

  public static DaoFactory getInstance() {
    return INSTANCE;
  }

  public ProductDao getProductDao() {
    return productDao;
  }

}
