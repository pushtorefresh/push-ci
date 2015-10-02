package com.pushtorefresh.push_ci.boss.request_handlers

import io.netty.buffer.ByteBuf
import io.reactivex.netty.protocol.http.server.HttpServerRequest
import io.reactivex.netty.protocol.http.server.HttpServerResponse
import io.reactivex.netty.protocol.http.server.RequestHandler
import rx.Observable

public interface PushCiRequestHandler : RequestHandler<ByteBuf, ByteBuf> {
  public fun willHandleRequest(request: HttpServerRequest<ByteBuf>): Boolean;
}