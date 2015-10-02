package com.pushtorefresh.push_ci.boss;

import com.pushtorefresh.push_ci.boss.configs.BossConfig;

import javax.inject.Inject;

import io.netty.buffer.ByteBuf;
import io.reactivex.netty.RxNetty;
import io.reactivex.netty.protocol.http.server.HttpServer;

public class BossApp {

  private static BossApp bossApp;

  public static void main(String[] args) {
    bossApp = new BossApp();
    bossApp.start();
  }

  public static BossApp getInstance() {
    return bossApp;
  }

  private final BossAppComponent appComponent;

  @Inject BossConfig config;

  @Inject BossRequestHandler requestHandler;

  private final HttpServer<ByteBuf, ByteBuf> httpServer;

  public BossApp() {
    appComponent = createAppComponent();
    appComponent.inject(this);

    httpServer = RxNetty.createHttpServer(
      config.getPort(),
      requestHandler
    );
  }

  public BossAppComponent getAppComponent() {
    return appComponent;
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
