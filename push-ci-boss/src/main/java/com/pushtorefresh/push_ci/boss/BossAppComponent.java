package com.pushtorefresh.push_ci.boss;

import com.pushtorefresh.push_ci.boss.requests_handlers.RequestsHandlersModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
  modules = {
    BossAppModule.class,
    RequestsHandlersModule.class
  }
)
public interface BossAppComponent {
  void inject(BossApp bossApp);
}
