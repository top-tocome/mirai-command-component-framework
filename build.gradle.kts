plugins {
    val kotlinVersion = "1.4.32"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("net.mamoe.mirai-console") version "2.6.7"
}

group = "top.tocome"
version = "0.0.2"

repositories {
    maven("https://maven.aliyun.com/repository/public")
    mavenCentral()
}
