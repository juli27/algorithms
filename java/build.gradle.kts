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
  testImplementation("org.junit.jupiter:junit-jupiter:5.4.0")
  testImplementation("com.google.truth:truth:0.42")
}

tasks.test {
  useJUnitPlatform()
}
