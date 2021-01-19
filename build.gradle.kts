plugins {
    kotlin("jvm") version "1.4.21"
    kotlin("plugin.serialization") version "1.4.21"
}

group = "com.github.bvfnbk"
version = "1.0-SNAPSHOT"



repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")

    // Test
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:2.0.9")
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.23")
    testImplementation("io.mockk:mockk:1.10.5")
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:2.0.9")
    testRuntimeOnly("org.jetbrains.kotlin:kotlin-reflect:1.4.21")

    implementation("com.github.ajalt.clikt:clikt:3.1.0")
    implementation("org.freemarker:freemarker:2.3.30")
    implementation("org.koin:koin-core:2.2.1")
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
