plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.junit:junit-bom:5.10.0"))
    implementation("org.junit.jupiter:junit-jupiter")
    implementation ("org.seleniumhq.selenium:selenium-java:4.27.0")
    implementation ("org.junit.jupiter:junit-jupiter-engine:5.11.4")
    implementation ("org.testng:testng:7.8.0")
    implementation ("io.github.bonigarcia:webdrivermanager:5.6.2")

}

tasks.test {
    useJUnitPlatform()
}