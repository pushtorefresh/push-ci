## Push CI
*Extra simple CI system. From developers to developers.*

Two components:

1. Push CI BOSS — Web UI + backend and workers controll.
2. Push CI WORKER — stupid worker that runs your build code and sends state to the BOSS.

Shipped via Docker. No need in external DB, we will use SQLite. No complicated UI (just build log), no plugins.

We think that CI should not care about your build system, your project structure, your programming language and so on. It should just run build script placed under source control.
