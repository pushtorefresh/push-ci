package com.pushtorefresh.push_ci.boss;

import com.pushtorefresh.push_ci.boss.configs.BossConfig;

import javax.inject.Inject;

import io.netty.buffer.ByteBuf;
import io.reactivex.netty.RxNetty;
import io.reactivex.netty.protocol.http.server.HttpServer;

public class BossApp {

  public static void main(String[] args) {
    new BossApp().start();
  }

  @Inject BossConfig config;

  @Inject BossRequestHandler requestHandler;

  private HttpServer<ByteBuf, ByteBuf> httpServer;

  public BossApp() {
    createAppComponent().inject(this);

    httpServer = RxNetty.createHttpServer(
      config.getPort(),
      requestHandler
    );
  }

  public void start() {
    System.out.println("Starting…");
    httpServer.startAndWait();
  }

  public void stop() throws InterruptedException {
    httpServer.shutdown();
  }

  private BossAppComponent createAppComponent() {
    return DaggerBossAppComponent.builder()
      .bossAppModule(new BossAppModule())
      .build();
  }
}
