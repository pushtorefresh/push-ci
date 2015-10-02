package com.pushtorefresh.push_ci.boss.db

import com.fasterxml.jackson.databind.ObjectMapper
import com.pushtorefresh.push_ci.boss.BossApp
import com.pushtorefresh.push_ci.boss.db.tables.UsersTable
import com.pushtorefresh.push_ci.boss.util.FileAccessor
import com.pushtorefresh.push_ci.boss.util.JDBCDriver
import java.io.File
import java.io.FileOutputStream
import java.sql.Connection
import java.sql.DriverManager
import javax.inject.Inject

class SQLiteOpenHelper(val pathToTheDbFile: String, val dbVersion: Int, val fileAccessor: FileAccessor,
                       val jdbcDriver: JDBCDriver, val objectMapper: ObjectMapper) {

  fun init() {
    // load the sqlite-JDBC driver using the current class loader
    Class.forName("org.sqlite.JDBC")

    val dbMetaInfo = readDbMetaInfo()

    if (dbMetaInfo == null) {
      onCreate()
    } else {
      onUpgrade(dbMetaInfo.dbVersion, dbVersion)
    }
  }

  fun getDbMetaInfoFile(): File {
    return fileAccessor.file(fileAccessor.file(pathToTheDbFile).parentFile, "db_meta_info.json")
  }

  fun getConnection(): Connection {
    return jdbcDriver.getConnection("jdbc:sqlite:$pathToTheDbFile")
  }

  fun readDbMetaInfo(): DbMetaInfo? {
    val dbMetaInfoFile = getDbMetaInfoFile()

    if (dbMetaInfoFile.exists()) {
      return objectMapper.readValue(dbMetaInfoFile, DbMetaInfo::class.java)
    } else {
      return null
    }
  }

  fun onCreate() {
    // try to delete db file if exists (probably user decided to drop db config, so we need to drop db too)
    fileAccessor.file(pathToTheDbFile).delete()

    val connection = getConnection()

    // Open transaction
    connection.autoCommit = false

    try {
      // Write new DbMetaInfo to the file
      objectMapper.writeValue(getDbMetaInfoFile(), DbMetaInfo(dbVersion))

      val statement = connection.createStatement()
      statement.execute(UsersTable.CREATE_QUERY)
      connection.commit()
    } finally {
      connection.close()
    }
  }

  fun onUpgrade(oldVersion: Int, newVersion: Int) {
    val connection = getConnection()

    // Open transaction
    connection.autoCommit = false

    try {
      // TODO add upgrade stuff in future
      connection.commit()
    } finally {
      connection.close()
    }
  }
}