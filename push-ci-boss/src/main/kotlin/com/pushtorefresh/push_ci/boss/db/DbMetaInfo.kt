package com.pushtorefresh.push_ci.boss.db

import com.fasterxml.jackson.annotation.JsonProperty

data class DbMetaInfo(@JsonProperty("dbVersion") val dbVersion: Int)