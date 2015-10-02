package com.pushtorefresh.push_ci.boss.request_handlers

class PushCiRequestsHandlers {

  public val requestsHandlers: List<PushCiRequestHandler>;

  init {
    requestsHandlers = arrayListOf(
      ProjectsRequestsHandler()
    )
  }
}