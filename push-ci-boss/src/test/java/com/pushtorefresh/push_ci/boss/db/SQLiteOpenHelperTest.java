package com.pushtorefresh.push_ci.boss.db;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pushtorefresh.push_ci.boss.db.tables.UsersTable;
import com.pushtorefresh.push_ci.boss.util.FileAccessor;
import com.pushtorefresh.push_ci.boss.util.JDBCDriver;

import org.junit.Test;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SQLiteOpenHelperTest {

  @Test
  public void shouldDeleteOldDbFileInOnCreate() throws SQLException {
    FileAccessor fileAccessor = mock(FileAccessor.class);

    File dbFile = mock(File.class);
    File dbFileParent = mock(File.class);
    File dbMetaInfoFile = mock(File.class);

    when(dbFile.getParentFile()).thenReturn(dbFileParent);

    when(fileAccessor.file("fake_path_to_db_file"))
      .thenReturn(dbFile);

    when(fileAccessor.file(dbFileParent, "db_meta_info.json"))
      .thenReturn(dbMetaInfoFile);

    when(dbMetaInfoFile.getPath()).thenReturn("fake_path_to_db_file\\db_meta_info.json");

    JDBCDriver jdbcDriver = mock(JDBCDriver.class);

    Connection connection = mock(Connection.class);

    when(jdbcDriver.getConnection("jdbc:sqlite:fake_path_to_db_file"))
      .thenReturn(connection);

    when(connection.createStatement()).thenReturn(mock(Statement.class));

    SQLiteOpenHelper sqLiteOpenHelper = new SQLiteOpenHelper("fake_path_to_db_file", 1, fileAccessor, jdbcDriver, mock(ObjectMapper.class));
    sqLiteOpenHelper.onCreate();

    //noinspection ResultOfMethodCallIgnored
    verify(dbFile).delete();
  }

  @Test
  public void shouldCreateUsersTable() throws SQLException {
    FileAccessor fileAccessor = mock(FileAccessor.class);

    File dbFile = mock(File.class);
    File dbFileParent = mock(File.class);
    File dbMetaInfoFile = mock(File.class);

    when(dbFile.getParentFile()).thenReturn(dbFileParent);

    when(fileAccessor.file("fake_path_to_db_file"))
      .thenReturn(dbFile);

    when(fileAccessor.file(dbFileParent, "db_meta_info.json"))
      .thenReturn(dbMetaInfoFile);

    when(dbMetaInfoFile.getPath()).thenReturn("fake_path_to_db_file\\db_meta_info.json");

    JDBCDriver jdbcDriver = mock(JDBCDriver.class);

    Connection connection = mock(Connection.class);

    when(jdbcDriver.getConnection("jdbc:sqlite:fake_path_to_db_file"))
      .thenReturn(connection);

    Statement statement = mock(Statement.class);

    when(connection.createStatement()).thenReturn(statement);

    SQLiteOpenHelper sqLiteOpenHelper = new SQLiteOpenHelper("fake_path_to_db_file", 1, fileAccessor, jdbcDriver, mock(ObjectMapper.class));
    sqLiteOpenHelper.onCreate();

    verify(statement).execute(UsersTable.CREATE_QUERY);
  }
}
