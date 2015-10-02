package com.pushtorefresh.push_ci.boss.configs

import com.fasterxml.jackson.annotation.JsonProperty

data class BossConfig(@JsonProperty("port") val port: Int) {
}