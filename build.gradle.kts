plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.hibernate.orm:hibernate-platform:6.6.0.Final"))
    implementation("org.hibernate.orm:hibernate-core")
    implementation("jakarta.transaction:jakarta.transaction-api")
    implementation("com.h2database:h2:2.2.220")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "no.hvl.dat250.jpa.tutorial.basicexample.Main"
}

tasks.register<JavaExec>("runH2Console") {
    mainClass.set("org.h2.tools.Server")
    classpath = sourceSets["main"].runtimeClasspath
    args = listOf("-web", "-browser")
}




/*
tasks.named<Test>("test") {
    useJUnitPlatform()
}
*/