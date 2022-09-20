package com.sbp.dao.logic.impl;

import com.sbp.dao.connection.ConnectionPool;
import com.sbp.dao.exception.DaoException;

import java.sql.Connection;

public abstract class AbstractDao {
  protected final ConnectionPool connectionPool;

  public AbstractDao(final ConnectionPool connectionPool) {
    this.connectionPool = connectionPool;
  }

  protected Connection getConnection() throws DaoException {
    return connectionPool.take();
  }

  protected void retrieve(final Connection connection) {
    connectionPool.retrieve(connection);
  }
}
