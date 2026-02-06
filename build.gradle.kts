plugins {
    kotlin("jvm") version "2.3.0"
}

group = "br.com.lbenaducci"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}