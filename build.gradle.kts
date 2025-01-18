import com.github.imflog.schema.registry.Subject

plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.7"
    id("com.github.imflog.kafka-schema-registry-gradle-plugin") version "2.1.1"
}


group = "org.example"
version = "0.0.1-SNAPSHOT"

buildscript {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        maven("https://packages.confluent.io/maven/")
        maven("https://jitpack.io")
    }
    dependencies {
        classpath("com.github.imflog:kafka-schema-registry-gradle-plugin:2.1.1-SNAPSHOT")
    }
}
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

schemaRegistry {
    url = "http://localhost:8982/"
    quiet = true
    outputDirectory = "schemas/json/results/"
    pretty = true
    register{
        subject("message","src/main/json/message.json","JSON")
        subject("company", "src/main/json/company_v2.json", "JSON")
    }

    download{
        subject("company", "schemas/json/downloaded")
    }
    compatibility {
        subject("company","src/main/json/company_v2.json","JSON")
    }
}

repositories {
    mavenCentral()
    maven("https://packages.confluent.io/maven/")
}

dependencies {
    implementation("io.confluent:kafka-json-schema-provider:7.8.0")
    implementation("io.confluent:kafka-json-schema-serializer:7.8.0")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.kafka:spring-kafka")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.springframework.kafka:spring-kafka-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("com.kjetland:mbknor-jackson-jsonschema_2.12:1.0.39")

}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
