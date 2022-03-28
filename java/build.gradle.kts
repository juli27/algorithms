import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
  `java-library`
}

version = "0.1.0"

repositories {
  mavenCentral()
}

dependencies {
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
  testImplementation("org.junit.jupiter:junit-jupiter-params:5.8.2")
  testImplementation("com.google.truth:truth:1.1.3")
  testImplementation("com.google.truth.extensions:truth-java8-extension:1.1.3")
  testImplementation("net.jqwik:jqwik-api:1.6.5")

  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
  testRuntimeOnly("net.jqwik:jqwik-engine:1.6.5")
}

tasks.withType<JavaCompile> {
  options.isDeprecation = true
  options.encoding = "UTF-8"
  options.release.set(15)
}

tasks.withType<Test> {
  useJUnitPlatform()

  testLogging {
    events = setOf(FAILED, SKIPPED)
  }
}
