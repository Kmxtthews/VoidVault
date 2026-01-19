plugins {
    java
    id("com.gradleup.shadow") version "9.3.1"  // Your version is fine (latest-ish in 2026)
}

group = "com.saladass"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(files("libs/HytaleServer.jar"))
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

// ← This is the correct block – replace your broken "shadw" one
tasks.shadowJar {
    archiveClassifier.set("")  // Removes extra suffix from JAR name (e.g. no "-all")

    // Optional: Set Main-Class in the JAR manifest (Hytale ignores it, but harmless)
    manifest {
        attributes["Main-Class"] = "com.saladass.plugin.HelloPlugin"  // Matches your real class path
    }

    // Optional common helpers from Shadow
    mergeServiceFiles()
    // exclude("META-INF/*.SF")  // Uncomment only if you get signing/verification errors later
}