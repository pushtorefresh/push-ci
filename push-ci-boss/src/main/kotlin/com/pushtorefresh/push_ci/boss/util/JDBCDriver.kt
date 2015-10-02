package com.pushtorefresh.push_ci.boss.util

import java.sql.Connection
import java.sql.DriverManager

/**
 * Abstraction over DriverManager for testing/
 */
public open class JDBCDriver {

  public open fun getConnection(url: String): Connection {
    return DriverManager.getConnection(url)
  }
}