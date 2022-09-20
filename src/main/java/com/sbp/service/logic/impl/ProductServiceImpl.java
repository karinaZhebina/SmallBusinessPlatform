package com.sbp.service.logic.impl;

import com.sbp.dao.DaoFactory;
import com.sbp.dao.entity.Pageable;
import com.sbp.dao.entity.ProductRow;
import com.sbp.dao.exception.DaoException;
import com.sbp.dao.logic.ProductDao;
import com.sbp.service.entity.Page;
import com.sbp.service.entity.Product;
import com.sbp.service.exception.ServiceException;
import com.sbp.service.logic.ProductService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public class ProductServiceImpl implements ProductService {

  private final ProductDao productDao = DaoFactory.getInstance().getProductDao();

  @Override
  public Product getById(String id) throws ServiceException {
    try {
      ProductRow productRow = productDao.getById(id);
      Product product = new Product();
      if (nonNull(productRow)) {
        product.setProduct_id(productRow.getProduct_id());
        product.setName(productRow.getName());
        product.setDescription(productRow.getDescription());
        product.setBusiness_owner_id(productRow.getBusiness_owner_id());
        product.setPrice(productRow.getPrice());
      }
      return product;
    } catch (DaoException e) {
      throw new ServiceException(e);
    }
  }

  @Override
  public Page<Product> getAllProducts(Page<Product> productPage) throws ServiceException {
    try {
      // prepare data
      final Pageable<ProductRow> daoProductPageable = convertToPageableProduct(productPage);
      // process data
      final Pageable<ProductRow> productRows = productDao.getAll(daoProductPageable);
      // return
      return convertToServicePage(productRows);
    } catch (final DaoException | SQLException e) {
      throw new ServiceException(e);
    }
  }

  @Override
  public boolean delete(Product product) throws ServiceException {
    try {
      return productDao.delete(new ProductRow(product));
    } catch (DaoException e) {
      throw new ServiceException(e);
    }
  }

  @Override
  public boolean create(Product product) throws ServiceException {
    try {
      ProductRow productRow = new ProductRow(product);
      return productDao.save(productRow);
    } catch (DaoException e) {
      throw new ServiceException("Duplicate with this title already exists", e);
    }
  }

  @Override
  public boolean update(Product product) throws ServiceException {
    try {
      ProductRow productRow = new ProductRow(product);
      return productDao.update(productRow);
    } catch (DaoException e) {
      throw new ServiceException(e);
    }
  }

  private Pageable<ProductRow> convertToPageableProduct(Page<Product> pageableRequest) {
    final Pageable<ProductRow> pageable = new Pageable<>();
    pageable.setPageNumber(pageableRequest.getPageNumber());
    pageable.setLimit(pageableRequest.getLimit());
    pageable.setTotalElements(pageableRequest.getTotalElements());
    pageable.setSortBy(pageableRequest.getSortBy());
    pageable.setDirection(pageableRequest.getDirection());
    return pageable;
  }

  private Page<Product> convertToServicePage(Pageable<ProductRow> productRowPageable) {
    Page<Product> page = new Page<>();
    page.setPageNumber(productRowPageable.getPageNumber());
    page.setLimit(productRowPageable.getLimit());
    page.setTotalElements(productRowPageable.getTotalElements());
    page.setElements(convertToProducts(productRowPageable.getElements()));
    page.setSortBy(productRowPageable.getSortBy());
    page.setDirection(productRowPageable.getDirection());
    return page;
  }

  private List<Product> convertToProducts(final List<ProductRow> productRows) {
    List<Product> list = new ArrayList<>();
    for (ProductRow productRow : productRows) {
      list.add(new Product(productRow));
    }
    return list;
  }

}
