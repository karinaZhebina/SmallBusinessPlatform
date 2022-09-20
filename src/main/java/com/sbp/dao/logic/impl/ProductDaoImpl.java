package com.sbp.dao.logic.impl;

import com.sbp.dao.connection.ConnectionPool;
import com.sbp.dao.entity.Pageable;
import com.sbp.dao.entity.ProductRow;
import com.sbp.dao.exception.DaoException;
import com.sbp.dao.logic.ProductDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public class ProductDaoImpl extends AbstractDao implements ProductDao {
  private static final String SAVE_ONE = "INSERT INTO products VALUES (?, ?, ?, ?, ?);";

  private static final String GET_BY_ID = "SELECT * FROM products WHERE product_id = ?;";

  private static final String DELETE_ONE = "DELETE FROM products WHERE product_id = ?;";

  private static final String UPDATE = "UPDATE products SET product_id = ?, name = ?, description = ?, business_owner_id = ?, price = ? WHERE product_id = ?;";

  private static final String COUNT_ALL_SORTED = "SELECT count(p.product_id) FROM products p";

  private static final String FIND_PAGE_SORTED = "SELECT * FROM products p ORDER BY p.%s %s LIMIT ? OFFSET ?";

  public ProductDaoImpl(final ConnectionPool connectionPool) {
    super(connectionPool);
  }

  @Override
  public ProductRow getById(String id) throws DaoException {
    Connection connection = getConnection();
    ProductRow productRow = null;
    try (PreparedStatement ps = connection.prepareStatement(GET_BY_ID)) {
      ps.setString(1, id);
      ResultSet resultSet = ps.executeQuery();
      while (resultSet.next()) {
        productRow = createNewProduct(resultSet);
      }
      resultSet.close();
      return productRow;
    } catch (SQLException e) {
      throw new DaoException(e);
    } finally {
      retrieve(connection);
    }
  }

  @Override
  public boolean save(ProductRow productRow) throws DaoException {
    boolean result = false;
    Connection connection = getConnection();
    try (PreparedStatement ps = connection.prepareStatement(SAVE_ONE)) {
      ps.setString(1, productRow.getProduct_id());
      ps.setString(2, productRow.getName());
      ps.setString(3, productRow.getDescription());
      ps.setString(4, productRow.getBusiness_owner_id());
      ps.setBigDecimal(5, productRow.getPrice());
      return ps.executeUpdate() != 0;
    } catch (SQLException e) {
      throw new DaoException(e);
    } finally {
      retrieve(connection);
    }
  }

  @Override
  public boolean delete(ProductRow productRow) throws DaoException {
    Connection connection = getConnection();
    try (PreparedStatement ps = connection.prepareStatement(DELETE_ONE)) {
      ps.setString(1, productRow.getProduct_id());
      ps.execute();
      return ps.executeUpdate() == 0;
    } catch (SQLException e) {
      throw new DaoException(e);
    } finally {
      retrieve(connection);
    }
  }

  @Override
  public boolean update(ProductRow productRow) throws DaoException {
    Connection connection = getConnection();
    boolean result = false;
    try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {
      ProductRow existingProduct = getById(productRow.getProduct_id());
      if (nonNull(existingProduct)) {
        ps.setString(1, productRow.getProduct_id());
        ps.setString(2, productRow.getName());
        ps.setString(3, productRow.getDescription());
        ps.setString(4, productRow.getBusiness_owner_id());
        ps.setBigDecimal(5, productRow.getPrice());
        ps.setString(6, productRow.getProduct_id());
        ps.execute();
        result = ps.executeUpdate() != 0;
      }
      return result;
    } catch (SQLException e) {
      throw new DaoException(e);
    } finally {
      retrieve(connection);
    }
  }

  private ProductRow createNewProduct(ResultSet resultSet) throws SQLException {
    return new ProductRow(resultSet.getString("product_id"),
        resultSet.getString("name"),
        resultSet.getString("description"),
        resultSet.getString("business_owner_id"),
        resultSet.getBigDecimal("price"));
  }

  @Override
  public Pageable<ProductRow> getAll(Pageable<ProductRow> productRowPageable) throws DaoException, SQLException {
    Connection connection = getConnection();

    final int offset = (productRowPageable.getPageNumber() - 1) * productRowPageable.getLimit();

    final String findPageOrderedQuery = String.format(FIND_PAGE_SORTED, productRowPageable.getSortBy(), productRowPageable.getDirection());

    try (PreparedStatement ps1 = connection.prepareStatement(COUNT_ALL_SORTED);
         PreparedStatement ps2 = connection.prepareStatement(findPageOrderedQuery)) {
      ps2.setInt(1, productRowPageable.getLimit());
      ps2.setInt(2, offset);

      ResultSet resultSet1 = ps1.executeQuery();
      ResultSet resultSet2 = ps2.executeQuery();

      Pageable<ProductRow> pageable = getProductRowPageable(productRowPageable, resultSet1, resultSet2);

      resultSet1.close();
      resultSet2.close();

      return pageable;
    } catch (SQLException e) {
      throw new DaoException(e);
    } finally {
      retrieve(connection);
    }
  }

  private Pageable<ProductRow> getProductRowPageable(Pageable<ProductRow> productRowPageable,
                                                     ResultSet totalProducts,
                                                     ResultSet productsPageList) throws SQLException {
    final Pageable<ProductRow> pageable = new Pageable<>();
    long totalElements = 0L;
    while (totalProducts.next()) {
      totalElements = totalProducts.getLong(1);
    }
    final List<ProductRow> productRows = new ArrayList<>();
    while (productsPageList.next()) {
      productRows.add(new ProductRow(productsPageList.getString(1),
          productsPageList.getString(2),
          productsPageList.getString(3),
          productsPageList.getString(4),
          productsPageList.getBigDecimal(5)));
    }
    pageable.setPageNumber(productRowPageable.getPageNumber());
    pageable.setLimit(productRowPageable.getLimit());
    pageable.setTotalElements(totalElements);
    pageable.setElements(productRows);
    pageable.setSortBy(productRowPageable.getSortBy());
    pageable.setDirection(productRowPageable.getDirection());
    return pageable;
  }

}
