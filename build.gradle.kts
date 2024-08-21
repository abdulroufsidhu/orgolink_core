plugins {
	id("org.springframework.boot") version "3.3.0"
	id("io.spring.dependency-management") version "1.1.5"

	kotlin("plugin.jpa") version "2.0.10"
	kotlin("jvm") version "2.0.10"
	kotlin("plugin.spring") version "2.0.10"
}

group = "io.github.abdulroufsidhu"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	developmentOnly("org.springframework.boot:spring-boot-devtools")

	// serialization | de-serialization
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-hibernate6:2.17.2")

	// docker compose support
	developmentOnly("org.springframework.boot:spring-boot-docker-compose")

	// swagger generator
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

	// json serializer
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	// jwt
	implementation("io.jsonwebtoken:jjwt-api:0.12.3")
	implementation("io.jsonwebtoken:jjwt-impl:0.12.3")
	implementation("io.jsonwebtoken:jjwt-jackson:0.12.3")

	// redis caching
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("org.springframework.boot:spring-boot-starter-cache")

	// spring security
	implementation("org.springframework.boot:spring-boot-starter-security")
	testImplementation("org.springframework.security:spring-security-test")

	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}