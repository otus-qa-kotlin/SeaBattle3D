import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.0"
    application
}

group = "me.dmitrii"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val exposedVersion: String by project

dependencies {
    testImplementation(kotlin("test"))

    implementation(kotlin("stdlib"))

    // Exposed library
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion") // base module, which contains both DSL api along with mapping
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion") // DAO api
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion") // transport level implementation based on Java JDBC API

    // Datasources
    implementation("org.xerial:sqlite-jdbc:3.30.1")
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}