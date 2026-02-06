plugins {
    kotlin("jvm") version "2.3.0"
}

group = "br.com.lbenaducci"
version = "0.0.1"

repositories {
    mavenCentral()
}

val mockitoAgent = configurations.create("mockitoAgent")

dependencies {
    testImplementation(kotlin("test"))
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.mockito.core)
    mockitoAgent(libs.mockito.core) {
        isTransitive = false
    }
}


kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
    jvmArgs(
        "-javaagent:${mockitoAgent.singleFile.absolutePath}",
        "-Xshare:off"
    )
}