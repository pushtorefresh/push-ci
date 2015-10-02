package com.pushtorefresh.push_ci.boss

import com.pushtorefresh.push_ci.boss.request_handlers.PushCiRequestsHandlers
import io.netty.buffer.ByteBuf
import io.reactivex.netty.protocol.http.server.HttpServerRequest
import io.reactivex.netty.protocol.http.server.HttpServerResponse
import io.reactivex.netty.protocol.http.server.RequestHandler
import rx.Observable
import rx.schedulers.Schedulers

class BossRequestHandler(private val pushCiRequestsHandlers: PushCiRequestsHandlers) : RequestHandler<ByteBuf, ByteBuf> {
  override fun handle(request: HttpServerRequest<ByteBuf>, response: HttpServerResponse<ByteBuf>): Observable<Void> {
    System.out.println(">> request.uri = ${request.uri}")

    for (requestHandler in pushCiRequestsHandlers.requestsHandlers) {
      try {
        if (requestHandler.willHandleRequest(request)) {
          return requestHandler.handle(request, response)
        }
      } catch (e : Exception) {
        // TODO return error page
        throw e
      }
    }

    // TODO return error page
    throw IllegalArgumentException("unknown request.uri = ${request.uri}")
  }
}