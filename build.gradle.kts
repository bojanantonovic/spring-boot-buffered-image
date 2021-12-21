plugins {
    java
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.spring") version "1.6.0"
}

group = "ch.antonovic"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:2.6.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
