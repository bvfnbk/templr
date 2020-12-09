plugins {
    kotlin("jvm") version "1.4.20"
}

group = "com.github.bvfnbk"
version = "1.0-SNAPSHOT"



repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    // Test
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:2.0.9")
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.23")
    testImplementation("io.mockk:mockk:1.10.3-jdk8")
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:2.0.9")
    testRuntimeOnly("org.jetbrains.kotlin:kotlin-reflect:1.4.20")

    implementation("com.github.ajalt.clikt:clikt:3.0.1")
}

tasks {
    test {
        useJUnitPlatform()
    }
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
