plugins {
    kotlin("jvm") version "1.9.20"
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
    testImplementation("io.kotest:kotest-assertions-core:5.8.0")
    implementation(platform("dev.forkhandles:forkhandles-bom:2.3.0.0"))
    implementation(group = "dev.forkhandles", name = "result4k")
    implementation(platform("org.http4k:http4k-bom:5.10.2.0"))
    implementation(group = "org.http4k", name = "http4k-core")
    implementation(group = "org.http4k", name = "http4k-server-jetty")
    implementation(group = "org.http4k", name = "http4k-client-okhttp")
    implementation(group = "org.http4k", name = "http4k-format-jackson")
    testImplementation(group = "org.http4k", name = "http4k-testing-kotest")
    testImplementation(group = "dev.mrbergin", name = "result4k-kotest-matchers", version = "2022.10.2")
}

tasks.test {
    useJUnitPlatform()
}


tasks.wrapper {
    gradleVersion = "8.4"
    distributionSha256Sum = "f2b9ed0faf8472cbe469255ae6c86eddb77076c75191741b4a462f33128dd419"
    distributionType = Wrapper.DistributionType.ALL
}

application {
    mainClass.set("MainKt")
}
