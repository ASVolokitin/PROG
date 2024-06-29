val MAIN_CLASS = "org.example.Main"
plugins {
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
    java
}
repositories {
    gradlePluginPortal()
}
dependencies {
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.16.1")
    implementation("org.apache.commons:commons-lang3:3.1")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.12.3")
    implementation("javax.xml.stream:stax-api:1.0-2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.2")
    implementation("com.google.guava:guava:32.1.1-jre")
    implementation("com.fasterxml.woodstox:woodstox-core:6.6.0")
    implementation(project(mapOf("path" to ":apps:util")))
    implementation(project(mapOf("path" to ":apps:util")))
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}
application {
    mainClass.set(MAIN_CLASS)
}
tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}
tasks.shadowJar {
    archiveBaseName.set("lab6Client")
    archiveClassifier.set("")
    minimize()
}
tasks.jar {
    enabled = false
    manifest.attributes["Main-Class"] = MAIN_CLASS
    dependsOn("shadowJar")
}