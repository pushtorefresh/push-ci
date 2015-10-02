package com.pushtorefresh.push_ci.boss.requests_handlers;

import com.pushtorefresh.push_ci.boss.request_handlers.PushCiRequestsHandlers;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RequestsHandlersModule {

  @Provides @Singleton
  public PushCiRequestsHandlers providePushCiRequestsHandlers() {
    return new PushCiRequestsHandlers();
  }
}
