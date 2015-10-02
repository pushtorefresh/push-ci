package com.pushtorefresh.push_ci.boss.db.tables

class UsersTable {

  companion object {
    public const val TABLE_NAME = "users"

    public const val COLUMN_ID = "_id"
    public const val COLUMN_NAME = "name"

    public const val CREATE_QUERY = "create table $TABLE_NAME ($COLUMN_ID integer not null primary key, $COLUMN_NAME text not null);"
  }
}