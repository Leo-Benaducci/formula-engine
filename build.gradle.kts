plugins {
    kotlin("jvm") version "2.3.0"
}

group = "br.com.lbenaducci"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    val mockitoVersion: String by project

    testImplementation(kotlin("test"))
    testImplementation("org.mockito.kotlin:mockito-kotlin:$mockitoVersion")
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}