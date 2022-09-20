package com.sbp.dao.logic;

import com.sbp.dao.entity.Pageable;
import com.sbp.dao.entity.ProductRow;
import com.sbp.dao.exception.DaoException;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
  Pageable<ProductRow> getAll(Pageable<ProductRow> productRowPageable) throws DaoException, SQLException;

  ProductRow getById(String id) throws DaoException;

  boolean save(ProductRow productRow) throws DaoException;

  boolean update(ProductRow productRow) throws DaoException;

  boolean delete(ProductRow productRow) throws DaoException;
}
