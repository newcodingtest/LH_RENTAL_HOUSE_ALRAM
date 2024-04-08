import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.1"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.7.0"
    kotlin("plugin.spring") version "1.7.0"
    kotlin("plugin.jpa") version "1.8.22"
    id ("org.jetbrains.kotlin.plugin.allopen") version "1.8.22"
    id ("org.jetbrains.kotlin.plugin.noarg") version "1.8.22"

    kotlin("kapt") version "1.9.21"
}

group = "ord.alram.lh"
version = "0.0.1"

val jar: Jar by tasks
val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks

bootJar.enabled = true
jar.enabled = false
java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

// 추가
val queryDslVersion: String by extra


repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // 코틀린 log
    implementation ("io.github.microutils:kotlin-logging:3.0.5")

    //레디스 사용
    implementation("org.springframework.boot:spring-boot-starter-data-redis")

    //Spring Jdbc batch
    implementation("org.springframework:spring-jdbc:6.1.3")

    //Spring Data JPA
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.2.2")

    runtimeOnly("org.jetbrains.kotlin:kotlin-reflect:1.9.22")

    //mysql, mariadb
    implementation("com.mysql:mysql-connector-j:8.3.0")

    //kotlin+jpa
    implementation("org.jetbrains.kotlin.plugin.jpa:org.jetbrains.kotlin.plugin.jpa.gradle.plugin:1.9.22")
    implementation("org.jetbrains.kotlin:kotlin-allopen:1.9.22")
    implementation("com.github.consoleau:kassava:2.1.0")

    //validation
    implementation("javax.validation:validation-api:2.0.1.Final")

    //gson
    implementation ("com.google.code.gson:gson:2.10.1")

    //객체간 상호 변환을 위해 mapper 추가
    implementation("org.modelmapper:modelmapper:2.4.4")

    // QueryDSL 의존성 추가
    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
    kapt("jakarta.annotation:jakarta.annotation-api")
    kapt("jakarta.persistence:jakarta.persistence-api")
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.Embeddable")
    annotation("jakarta.persistence.MappedSuperclass")
}

noArg {
    annotation("javax.persistence.Entity")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// Querydsl 설정부 추가 - start
val generated = file("src/main/generated")

// querydsl QClass 파일 생성 위치를 지정
tasks.withType<JavaCompile> {
    options.generatedSourceOutputDirectory.set(generated)
}

// kotlin source set 에 querydsl QClass 위치 추가
sourceSets {
    main {
        kotlin.srcDirs += generated
    }
}

// gradle clean 시에 QClass 디렉토리 삭제
tasks.named("clean") {
    doLast {
        generated.deleteRecursively()
    }
}


kapt {
    generateStubs = true
}