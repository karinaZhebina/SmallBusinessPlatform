package com.sbp.dao.connection;

import com.sbp.dao.exception.DaoException;

import java.sql.Connection;

public interface ConnectionPool {
  Connection take() throws DaoException;

  void retrieve(final Connection connection);
}
