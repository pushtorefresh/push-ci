package com.pushtorefresh.push_ci.boss.util

import java.io.File

/**
 * Useful for testing.
 */
public open class FileAccessor {

  public open fun file(path: String): File {
    return File(path)
  }

  public open fun file(parent: File, child: String): File {
    return File(parent, child)
  }
}