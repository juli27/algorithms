plugins {
  `java-library`
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

repositories {
  mavenCentral()
}

dependencies {
  testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
  testImplementation("com.google.truth:truth:1.0.1")
  testImplementation("com.google.truth.extensions:truth-java8-extension:1.0.1")
}

tasks.named<Test>("test") {
  useJUnitPlatform()
}
