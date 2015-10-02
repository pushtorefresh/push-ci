package com.pushtorefresh.push_ci.boss;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pushtorefresh.push_ci.boss.configs.BossConfig;
import com.pushtorefresh.push_ci.boss.request_handlers.PushCiRequestsHandlers;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * It's a module in terms of Dagger DI.
 */
@Module
public class BossAppModule {

  @Provides @Singleton
  public ObjectMapper provideObjectMapper() {
    return new ObjectMapper();
  }

  @Provides @Singleton
  public BossConfig provideConfig(ObjectMapper objectMapper) {
    try {
      return objectMapper.readValue(new File("boss_config.json"), BossConfig.class);
    } catch (Exception e) {
      throw new IllegalStateException("Looks like your boss_config.json is incorrect, see stacktrace", e);
    }
  }

  @Provides @Singleton
  public BossRequestHandler provideBossRequestHandler(PushCiRequestsHandlers pushCiRequestsHandlers) {
    return new BossRequestHandler(pushCiRequestsHandlers);
  }
}
