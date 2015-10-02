package com.pushtorefresh.push_ci.boss.request_handlers

import io.netty.buffer.ByteBuf
import io.reactivex.netty.protocol.http.server.HttpServerRequest
import io.reactivex.netty.protocol.http.server.HttpServerResponse
import io.reactivex.netty.protocol.http.server.RequestHandler
import rx.Observable

class ProjectsRequestsHandler : PushCiRequestHandler {
  override fun willHandleRequest(request: HttpServerRequest<ByteBuf>): Boolean {
    return request.uri.equals("/projects") || request.uri.startsWith("/projects/")
  }

  override fun handle(request: HttpServerRequest<ByteBuf>, response: HttpServerResponse<ByteBuf>): Observable<Void> {
    return Observable.create { subscriber ->
      response.writeStringAndFlush("yo!")
      subscriber.onCompleted()
    }
  }
}