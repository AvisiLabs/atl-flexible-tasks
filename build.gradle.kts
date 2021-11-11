plugins {
    application
    kotlin("jvm") version "1.5.31"
    kotlin("kapt") version "1.5.31"
    kotlin("plugin.spring") version "1.5.31"
    id("org.springframework.boot") version "2.5.6"
}

application {
    mainClass.set("nl.avisi.atl.flexible.tasks.orchestrator.Orchestrator")
}

repositories {
    mavenCentral()
}

val kotlinVersion by extra { KotlinVersion(1, 5, 31) }
val springBootVersion = "2.5.6"

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:$kotlinVersion"))
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")

    implementation("org.springframework.boot:spring-boot-starter-actuator:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-jetty:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion") {
        exclude("org.springframework.boot", "spring-boot-starter-tomcat")
    }

    implementation("io.github.microutils:kotlin-logging-jvm:2.0.11")
}

tasks.compileKotlin {
    kotlinOptions {
        jvmTarget = "16"
        javaParameters = true
    }
}

plugins.withId("org.springframework.boot") {
    dependencies {
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:$springBootVersion")
    }

    springBoot {
        mainClass.set("nl.avisi.atl.flexible.tasks.orchestrator.Orchestrator")
    }
}
