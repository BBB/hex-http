import org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_20
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_1_9
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.10"
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


tasks.wrapper {
  gradleVersion = "8.4"
  distributionSha256Sum = "f2b9ed0faf8472cbe469255ae6c86eddb77076c75191741b4a462f33128dd419"
  distributionType = Wrapper.DistributionType.ALL
}


tasks.withType<KotlinCompile>().configureEach {
  compilerOptions {
    jvmTarget.set(JVM_20)
    apiVersion.set(KOTLIN_1_9)
    languageVersion.set(KOTLIN_1_9)
  }
}

application {
    mainClass.set("MainKt")
}
