import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
    application
}

group = "me.relph"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("io.strikt:strikt-core:0.34.1")
    implementation(platform("dev.forkhandles:forkhandles-bom:2.3.0.0"))
    implementation("dev.forkhandles:result4k")
    implementation(group = "org.http4k", name = "http4k-core", version = "5.8.5.0")
    implementation(group = "org.http4k", name = "http4k-server-jetty", version = "5.8.5.0")
    implementation(group = "org.http4k", name = "http4k-client-okhttp", version = "5.8.5.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}
