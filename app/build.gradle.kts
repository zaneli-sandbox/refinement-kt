plugins {
    kotlin("jvm") version "2.0.0"
    id("com.diffplug.spotless") version "6.0.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.michael-bull.kotlin-result:kotlin-result:2.0.1")
    implementation("io.arrow-kt:arrow-core:2.0.1")
    implementation("dev.ustits.krefty:krefty-core:0.5.0")
    implementation("dev.ustits.krefty:krefty-arrow:0.5.0")
    testImplementation("io.kotest:kotest-runner-junit5:5.9.1")
    testImplementation("io.kotest:kotest-assertions-core:5.9.1")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

spotless {
    kotlin {
        target("**/*.kt")
        ktfmt()
    }
}
