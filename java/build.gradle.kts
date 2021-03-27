import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
  `java-library`
}

version = "0.1.0"

repositories {
  mavenCentral()
}

dependencies {
  testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
  testImplementation("com.google.truth:truth:1.0.1")
  testImplementation("com.google.truth.extensions:truth-java8-extension:1.0.1")
}

tasks.withType<JavaCompile> {
  options.isDeprecation = true
  options.encoding = "UTF-8"
  options.release.set(14)
}

tasks.withType<Test> {
  useJUnitPlatform()

  testLogging {
    events = setOf(FAILED, SKIPPED)
  }
}
