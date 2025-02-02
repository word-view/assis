plugins {
    kotlin("jvm") version "2.1.0"
    `maven-publish`
}

group = "cc.wordview.assis"
version = "0.1.2"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jsoup:jsoup:1.18.3")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = group.toString()
            artifactId = rootProject.name
            version = version

            from(components["java"])
        }
    }
}