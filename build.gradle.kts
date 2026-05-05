plugins {
    kotlin("jvm") version "1.9.20"
    id("io.ktor.plugin") version "2.3.5"
}

group = "com.nouran"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:2.3.5")
    implementation("io.ktor:ktor-server-netty:2.3.5")
    implementation("io.ktor:ktor-server-content-negotiation:2.3.5")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.5")

    testImplementation("io.ktor:ktor-server-test-host:2.3.5")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.9.20")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<JavaCompile> {
    options.release.set(17)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "com.nouran.ApplicationKt"
    }

    val dependencies = configurations.runtimeClasspath.get().map {
        if (it.isDirectory) it else zipTree(it)
    }

    from(dependencies)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.test {
    useJUnit()
}